package com.hackathon.service;

import com.hackathon.accessMCS.FaceServiceMCSImpl;
import com.hackathon.accessMCS.PersonGroupServiceMCSImpl;
import com.hackathon.accessMCS.PersonServiceMCSImpl;
import com.hackathon.common.BaseResponse;
import com.hackathon.constant.IContanst;
import com.hackathon.entity.CustomerEntity;
import com.hackathon.entity.TransactionEntity;
import com.hackathon.model.CustomerModel;
import com.hackathon.modelMCS.FaceDetectResponse;
import com.hackathon.modelMCS.FaceIdentifyConfidenceRespone;
import com.hackathon.modelMCS.FaceIdentityCandidate;
import com.hackathon.repository.CustomerRepository;
import com.hackathon.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Service
@Component
public class DetectPersonService {

    @Autowired
    private FaceServiceMCSImpl faceServiceMCS;

    @Autowired
    private PersonServiceMCSImpl personServiceMCS;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PersonGroupServiceMCSImpl personGroupServiceMCS;

    public CustomerModel detect(InputStream image) throws IOException, URISyntaxException {
        String faceImage = detectImg(image);
        if (faceImage != null) {
            String personID = checkExistFaceInDepartment(faceImage, Arrays.asList(IContanst.DEPARTMENT_NAME));
            if (personID != null) {
                CustomerEntity customerEntity = customerRepository.findByUsercode(personID);
                if (customerEntity != null) {
                    return new CustomerModel(customerEntity);
                }
            }
        }
        return null;

    }

    public void createTraining(InputStream image, String customerCode) throws IOException, URISyntaxException {

        BaseResponse response = personServiceMCS.createPerson(IContanst.DEPARTMENT_NAME, "Customer", "......");
        Map<String, String> map = (Map<String, String>) response.getData();
        if (map != null) {
            String personID = map.get("personId");

            //add new Customer
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setPersonCode(personID);
            CustomerEntity entityResult = customerRepository.saveAndFlush(customerEntity);

            TransactionEntity transactionEntity = transactionRepository.findByCustomerCode(customerCode);
            transactionEntity.setCustomer(entityResult);
            transactionRepository.saveAndFlush(transactionEntity);

            personServiceMCS.addFaceImg(IContanst.DEPARTMENT_NAME, personID, image);
            personGroupServiceMCS.trainGroup(IContanst.DEPARTMENT_NAME);
        }
    }

    public void addFace(InputStream image, String customerCode) throws IOException, URISyntaxException {


        //add new Customer
        TransactionEntity transactionEntity = transactionRepository.findByCustomerCode(customerCode);
        if (transactionEntity.getCustomer() == null || transactionEntity.getCustomer().getPersonCode() == null) {
            createTraining(image, customerCode);
        } else {
            CustomerEntity customerEntity = transactionEntity.getCustomer();

            personServiceMCS.addFaceImg(IContanst.DEPARTMENT_NAME, customerEntity.getPersonCode(), image);
            personGroupServiceMCS.trainGroup(IContanst.DEPARTMENT_NAME);
        }
    }

    /**
     * call API and detect img
     *
     * @return faceId has rectangle maximun
     */
    private String detectImg(InputStream fileInputStream) throws IOException, URISyntaxException {
//        List<String> listFace = new ArrayList<>();
        String faceId = null;

        BaseResponse responseDetect = faceServiceMCS.detect(fileInputStream);

        if (responseDetect.isSuccess()) {
            List<FaceDetectResponse> faceDetects = (List<FaceDetectResponse>) responseDetect.getData();
            if (faceDetects.size() > 0) {
                Long area = 0l;
                for (FaceDetectResponse face : faceDetects) {
                    Long areaFace = face.getFaceRectangle().area();
                    if (area < areaFace) {
                        area = areaFace;
                        faceId = face.getFaceId();
                    }
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
        return faceId;
    }

    private String checkExistFaceInDepartment(String faceID, List<String> departmentCode) throws IOException, URISyntaxException {

        java.lang.String personID = null;
        double confidence = 0d;

        for (java.lang.String departmentName : departmentCode) {
            BaseResponse response = faceServiceMCS.identify(departmentName, faceID);
            if (response.isSuccess()) {

                //get list Identifies success
                List<FaceIdentifyConfidenceRespone> faceIdentifies = (List<FaceIdentifyConfidenceRespone>) response.getData();

                //check success
                // TODO check again check in
//                if (ValidateUtil.isEmpty(faceIdentifies) && faceIdentifies.size() == 1) {
                List<FaceIdentityCandidate> candidateList = faceIdentifies.get(0).getCandidates();
                for (FaceIdentityCandidate candidate : candidateList) {
                    if (candidate.getConfidence() > confidence) {
                        confidence = candidate.getConfidence();
                        personID = candidate.getPersonId();
                    }
                }
//                } else {
//                    logger.error("When get face identify one image, has many value");
//                }

            }
        }

        //check greater then confidence
        if (confidence > IContanst.MCS_PERSON_DETECT_CONFIDINCE_CORRECT) {
            return personID;
        } else {
            return null;
        }
    }

}
