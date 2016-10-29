package com.hackathon.service;

import com.hackathon.model.EmotionReport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Service
@Component
public class EmotionService {

    public EmotionReport reportCustomerSatisfaction() {
        EmotionReport emotionReport = new EmotionReport();
        String[] employees = {"Nguyen Vu Linh", "Le Thanh Tan", "Nguyen Van Manh", "Nguyen Nang Trung","Thai Quang Hien"};
        List<String> listEmployees = new ArrayList<>();
        List<Integer> listExcellent = new ArrayList<>();
        List<Integer> listGood = new ArrayList<>();
        List<Integer> listNormal = new ArrayList<>();
        List<Integer> listMedium = new ArrayList<>();
        List<Integer> listBad = new ArrayList<>();
        for (int i = 0; i < employees.length; i++) {
            listEmployees.add(employees[i]);
            listExcellent.add(randomReport(0, 100));
            listGood.add(randomReport(0, 100));
            listNormal.add(randomReport(0, 100));
            listMedium.add(randomReport(0, 100));
            listBad.add(randomReport(0, 100));
        }
        emotionReport.setListEmployees(listEmployees);
        emotionReport.setListExcellent(listExcellent);
        emotionReport.setListGood(listGood);
        emotionReport.setListNormal(listNormal);
        emotionReport.setListMedium(listMedium);
        emotionReport.setListBad(listBad);

        return emotionReport;
    }

    public int randomReport(int minimum, int maximum) {
        return (minimum + (int) (Math.random() * maximum));
    }
}
