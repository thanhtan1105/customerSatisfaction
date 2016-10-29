package com.hackathon.controller;

import com.hackathon.constant.IViewConst;
import com.hackathon.constant.I_URI;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Controller
@RequestMapping(I_URI.WEB_EMPLOYEE_EMOTION)
public class EmotionControllerWeb {

    private Logger logger = Logger.getLogger(EmotionControllerWeb.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadCustomerEmotionView() {
        return IViewConst.CUSTOMER_EMOTION_VIEW;
    }
}
