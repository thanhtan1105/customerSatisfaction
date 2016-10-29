package com.hackathon.service;

import com.hackathon.accessMCS.EmotionServiceMCSImpl;
import com.hackathon.accessMCS.FaceServiceMCSImpl;
import com.hackathon.common.BaseResponse;
import com.hackathon.constant.ETransaction;
import com.hackathon.constant.IContanst;
import com.hackathon.entity.CustomerEntity;
import com.hackathon.entity.EmotionCustomerEntity;
import com.hackathon.entity.TransactionEntity;
import com.hackathon.model.*;
import com.hackathon.modelMCS.EmotionRecognizeResponse;
import com.hackathon.modelMCS.FaceDetectResponse;
import com.hackathon.repository.EmotionRepository;
import com.hackathon.repository.TransactionRepository;
import com.hackathon.util.JsonUtil;
import com.hackathon.util.UtilApps;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Service
@Component
public class TransactionService {

    private Logger logger = LogManager.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private EmotionRepository emotionRepository;

    @Autowired
    private DetectPersonService detectPersonService;

    public EmotionCustomerResponse getEmotionCustomer(String customerCode) {
        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.info("CustomerCode:" + customerCode);
            //get Customer Service with customerCode
            TransactionEntity transactionEntity = transactionRepository.findByCustomerCode(customerCode);
            if (transactionEntity != null
                    && (transactionEntity.getStatus() == ETransaction.BEGIN
                    || transactionEntity.getStatus() == ETransaction.PROCESS)) {
                Integer customerId = transactionEntity.getId();
                EmotionCustomerEntity emotionCustomerEntity = emotionRepository.findByCustomerIdLeast(customerId);
                if (emotionCustomerEntity != null) {
                    //get analysis
                    EmotionAnalysisModel analysisModel = new EmotionAnalysisModel(emotionCustomerEntity);
                    String messageEmotion = suggestionService.getEmotionMessage(analysisModel);
                    List<EmotionContentModel> suggestion = suggestionService.getSuggestion(emotionCustomerEntity.getEmotionMost(), emotionCustomerEntity.getAge(), emotionCustomerEntity.getGender());
                    //create message
                    MessageModel messageModel = new MessageModel(emotionCustomerEntity);
                    messageModel.setMessage(Collections.singletonList(UtilApps.formatSentence(messageEmotion)));
                    messageModel.setSugguest(suggestion);


                    /** History*/
                    CustomerEntity customerEntity = transactionEntity.getCustomer();
                    List<EmotionHistoryModel> historyModels = null;
                    List<TransactionEntity> transactions = customerEntity.getTransactions();
                    if (transactions.size() >0) {
                        transactions.sort((t, z) -> t.getBeginTime().compareTo(z.getBeginTime()));
                        transactions.stream().forEach(t -> t.getEmotion().sort((a, b) ->a.getCreateTime().compareTo(b.getCreateTime())));
                        historyModels = transactions.stream().map(EmotionHistoryModel::new).collect(Collectors.toList());
                    }


                    return new EmotionCustomerResponse(customerCode, analysisModel, messageModel, historyModels);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }

    public TransactionModel beginTransaction() {
        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            //create Customer service

            TransactionEntity tran = new TransactionEntity();

            //set status = BEGIN
            tran.setStatus(ETransaction.BEGIN);
            TransactionEntity entity = transactionRepository.saveAndFlush(tran);
            return new TransactionModel(entity);

        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }

    /**
     * author TrungNN
     * Web employee: end transaction
     */
    public Boolean changeStatusTransaction(String customerCode, ETransaction stauts) {
        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.info(String.format("customerCode = '%s', Status = ''", customerCode, stauts));
            //get Customer Service with customerCode
            TransactionEntity customerResultEntity = transactionRepository.findByCustomerCode(customerCode);
            if (customerResultEntity != null
                    && (customerResultEntity.getStatus() == ETransaction.BEGIN
                    || customerResultEntity.getStatus() == ETransaction.PROCESS)) {
                //change status = END
                customerResultEntity.setStatus(stauts);
                if (stauts == ETransaction.END) {
                    customerResultEntity.calculateGrade();
                }
                transactionRepository.saveAndFlush(customerResultEntity);
                return true;
            } else {
                logger.error("CustomerService has status: " + customerResultEntity.getStatus());
                return null;
            }
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }


    /**
     * upload image to transaction
     * author: hientq
     */
    public Boolean uploadImage(InputStream imageStream, String customerCode) throws IOException, URISyntaxException {
        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.info("CustomerCode: " + customerCode);

            //get Customer Service with customerCode;
            TransactionEntity customerResultEntity = transactionRepository.findByCustomerCode(customerCode);
            logger.info("customer Emotion: " + JsonUtil.toJson(new TransactionModel(customerResultEntity)));
            if (customerResultEntity != null && (customerResultEntity.getStatus() == ETransaction.BEGIN || customerResultEntity.getStatus() == ETransaction.PROCESS)) {

                //if status == BEGIN, change status = PROCESS. save db
                if (customerResultEntity.getStatus() == ETransaction.BEGIN) {
                    customerResultEntity.setStatus(ETransaction.PROCESS);
                    transactionRepository.saveAndFlush(customerResultEntity);
                }

                byte[] byteStream = IOUtils.toByteArray(imageStream);
                //analyze emotion
                EmotionAnalysisModel emotionAnalysis = getCustomerEmotion(new ByteArrayInputStream(byteStream));
                if (emotionAnalysis != null) {
                    //save mostChoose
                    EmotionCustomerEntity emotionEntity = new EmotionCustomerEntity(emotionAnalysis, customerResultEntity);
                    emotionRepository.saveAndFlush(emotionEntity);

                    CustomerModel customerModel = detectPersonService.detect(new ByteArrayInputStream(byteStream));
                    if (customerModel != null){
                        detectPersonService.addFace(imageStream, customerCode);
                    }else {
                        detectPersonService.createTraining(imageStream, customerCode);
                    }
                } else {
                    logger.error("********************** Cannot analyze customer emotion  ********************");
                    return false;
                }
            } else {
                logger.error("CustomerService has status: " + customerResultEntity.getStatus());
                return false;
            }
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
        return true;
    }


    /**
     * next transaction
     * author: hientq
     * call entransaction old, and create new transaction
     */
    public TransactionModel nextTransaction(String customerCode, Boolean isSkip) {
        ETransaction transaction = null;
        if (isSkip == null || isSkip == false) {
            transaction = ETransaction.END;
        } else {
            transaction = ETransaction.PAUSE;
        }
        Boolean accountId = changeStatusTransaction(customerCode, transaction);
        if (accountId != null) {
            return beginTransaction();
        }
        return null;
    }

    private EmotionAnalysisModel getCustomerEmotion(InputStream inputStreamImg)
            throws IOException, URISyntaxException {
        logger.info("[Get Customer Emotion] BEGIN SERVICE");

        byte[] bytes = IOUtils.toByteArray(inputStreamImg);

        Date dateFrom = new Date();
        //Call API
        // face detect
        FaceServiceMCSImpl faceServiceMCS = new FaceServiceMCSImpl();
        // emotion recognize
        EmotionServiceMCSImpl emotionServiceMCS = new EmotionServiceMCSImpl();
        BaseResponse faceResponse = faceServiceMCS.detect(new ByteArrayInputStream(bytes));
        BaseResponse emotionResponse = emotionServiceMCS.recognize(new ByteArrayInputStream(bytes));
        logger.info("[Get Customer Emotion] face response success: " + faceResponse.isSuccess());
        logger.info("EEEEEEEEEEEEE : faceResponse: " + JsonUtil.toJson(faceResponse));
        Date dateto = new Date();
        logger.info(" -- Time Call API: " + (dateto.getTime() - dateFrom.getTime()));

        if (faceResponse.isSuccess() && faceResponse.getData() != null &&
                emotionResponse.isSuccess() && emotionResponse.getData() != null) {

            // parser face response
            List<FaceDetectResponse> faceRecognizeList = (List<FaceDetectResponse>) faceResponse.getData();
            //parser emotionRecognizeList
            List<EmotionRecognizeResponse> emotionRecognizeList = (List<EmotionRecognizeResponse>) emotionResponse.getData();

            return chooseMapping(faceRecognizeList, emotionRecognizeList);

        }
        logger.info("[Get Customer Emotion] END SERVICE");
        return null;
    }

    /**
     * choose pair from list Face and list Emotion
     * author: hientq
     */

    private EmotionAnalysisModel chooseMapping(List<FaceDetectResponse> faceRecognizeList, List<EmotionRecognizeResponse> emotionRecognizeList) {
        Map<String, FaceDetectResponse> faceMap = new HashMap<>();
        for (FaceDetectResponse face : faceRecognizeList) {
            faceMap.put(face.getFaceRectangle().toFaceRectangle(), face);
        }

        long area = 0l;
        EmotionAnalysisModel analysisModel = null;
        for (EmotionRecognizeResponse emotionRecognize : emotionRecognizeList) {
            FaceDetectResponse face = faceMap.get(emotionRecognize.getFaceRectangle().toFaceRectangle());
            if (face != null) {
                long areaEmotion = emotionRecognize.getFaceRectangle().area();
                if (area < areaEmotion) {
                    area = areaEmotion;
                    analysisModel = new EmotionAnalysisModel(face, emotionRecognize);
                }

            }
        }
        return analysisModel;
    }


}
