package com.hackathon.service;

import com.hackathon.model.EmotionReport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Service
@Component
public class EmotionService {

    public EmotionReport reportCustomerSatisfaction() {
        EmotionReport emotionReport = new EmotionReport();
        return emotionReport;
    }
}
