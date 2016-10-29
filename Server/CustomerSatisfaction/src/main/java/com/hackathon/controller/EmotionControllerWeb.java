package com.hackathon.controller;

import com.hackathon.constant.IViewConst;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Controller
@RequestMapping
public class EmotionControllerWeb {

    private Logger logger = Logger.getLogger(EmotionControllerWeb.class);

    @RequestMapping(value = {"/emotion", "/", ""}, method = RequestMethod.GET)
    public String loadCustomerEmotionView(Model model) {
        // get current date
        Date currentDate = new Date();
        //current date
        model.addAttribute("CurrentDate", currentDate);

        return IViewConst.CUSTOMER_EMOTION_VIEW;
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String loadReportView() {

        return IViewConst.MANAGER_REPORT;
    }
}
