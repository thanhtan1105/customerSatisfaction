package com.hackathon._config;

import com.hackathon.common.Pair;
import com.hackathon.constant.I_URI;
import com.hackathon.service.EmotionService;
import com.hackathon.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class Interceptor {
    @Autowired
    private EmotionService emotionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor");
        String url = request.getRequestURI();
                Pair<String, String> customerSession = EmotionSession.getValue(I_URI.SESSION_API_EMOTION_CUSTOMER_CODE);
                if (customerSession == null || ValidateUtil.isEmpty(customerSession.getKey())) {
                    CustomerServiceModel customerServiceModel = emotionService.beginTransaction();
                    EmotionSession.setValue(I_URI.SESSION_API_EMOTION_CUSTOMER_CODE, new Pair(customerServiceModel.getCustomerCode()));
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
