package com.hackathon.api;

import com.hackathon._config.EmotionSession;
import com.hackathon.common.BaseResponse;
import com.hackathon.common.Pair;
import com.hackathon.constant.IContanst;
import com.hackathon.constant.I_URI;
import com.hackathon.entity.TransactionEntity;
import com.hackathon.model.EmotionCustomerResponse;
import com.hackathon.model.TransactionModel;
import com.hackathon.service.TransactionService;
import com.hackathon.util.ValidateUtil;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@RestController("/api/emotion")
public class EmotionController {

    Logger logger = LogManager.getLogger(EmotionController.class);

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = I_URI.API_EMOTION_GET_EMOTION)
    @ResponseBody
    public BaseResponse getEmotion() {
        try {
            logger.info(IContanst.BEGIN_METHOD_CONTROLLER + Thread.currentThread().getStackTrace()[1].getMethodName());
            Pair<String, String> customerValue = EmotionSession.getValue(I_URI.SESSION_API_EMOTION_CUSTOMER_CODE);
            if (customerValue == null || ValidateUtil.isEmpty(customerValue.getKey())) {
                return new BaseResponse(false);
            }
            String customerCode = customerValue.getKey();
            EmotionCustomerResponse emotionCustomer = transactionService.getEmotionCustomer(customerCode);

            if (emotionCustomer != null) {
                return new BaseResponse(true, emotionCustomer);
            } else {
                return new BaseResponse(false);
            }
        } catch (Exception e) {
            logger.error(e);
            return new BaseResponse(false, e.getMessage());
        } finally {
            logger.info(IContanst.END_METHOD_CONTROLLER);
        }
    }


    @RequestMapping(value = I_URI.API_EMOTION_UPLOAD_IMAGE, method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse uploadImage(@RequestParam(I_URI.PARAMETER_EMOTION_ACCOUNT_ID) Long accountId,
                                    @RequestParam("image") MultipartFile imageFile) {
        try {
            logger.info(IContanst.BEGIN_METHOD_CONTROLLER + Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(String.format("accountId = '%s' ", accountId));
            Pair<String, String> customerValue = EmotionSession.getValue(I_URI.SESSION_API_EMOTION_CUSTOMER_CODE + accountId);
            if (customerValue == null || ValidateUtil.isEmpty(customerValue.getKey())) {
                return new BaseResponse(false);
            }
            String customerCode = customerValue.getKey();
            byte[] byteImage = IOUtils.toByteArray(imageFile.getInputStream());


            Boolean result = transactionService.uploadImage(new ByteArrayInputStream(byteImage), customerCode);
            if (result != null && result) {
                return new BaseResponse(true, new Pair<>("uploadSuccess", result));
            } else {
                return new BaseResponse(false);
            }

        } catch (Exception e) {
            logger.error(e);
            return new BaseResponse(false, e.getMessage());
        } finally {
            logger.info(IContanst.END_METHOD_CONTROLLER);
        }
    }

    @RequestMapping(value = I_URI.API_EMOTION_NEXT_TRANSACTION)
    @ResponseBody
    public BaseResponse nextTransaction(@RequestParam(I_URI.PARAMETER_EMOTION_ACCOUNT_ID) Long accountId,
                                        @RequestParam(value = "skip", required = false) Boolean isSkip) {
        try {
            logger.info(IContanst.BEGIN_METHOD_CONTROLLER + Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(String.format("accountId = '%s' ", accountId));

            Pair<String, String> customerValue = EmotionSession.getValue(I_URI.SESSION_API_EMOTION_CUSTOMER_CODE + accountId);
            if (customerValue == null || ValidateUtil.isEmpty(customerValue.getKey())) {
                return new BaseResponse(false);
            }
            String customerCode = customerValue.getKey();

            TransactionModel result = transactionService.nextTransaction(customerCode, isSkip);
            if (result != null && ValidateUtil.isNotEmpty(result.getCustomerCode())) {
                String newCustomer = result.getCustomerCode();

                //replace newCustomer to session
                EmotionSession.remove(I_URI.SESSION_API_EMOTION_CUSTOMER_CODE + accountId);
                EmotionSession.setValue(I_URI.SESSION_API_EMOTION_CUSTOMER_CODE + accountId, new Pair<String, String>(newCustomer));

                return new BaseResponse(true, new Pair<String, String>("customerCode", result.getCustomerCode()));
            } else {
                return new BaseResponse(false);
            }

        } catch (Exception e) {
            logger.error(e);
            return new BaseResponse(false, e.getMessage());
        } finally {
            logger.info(IContanst.END_METHOD_CONTROLLER);
        }
    }


}