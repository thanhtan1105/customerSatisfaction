package com.hackathon.common;

import com.hackathon._config.AppConfigKeys;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */

public interface IContanst {
    String BEGIN_METHOD = "---begin---";
    String END_METHOD = "----end----";

    String BEGIN_METHOD_SERVICE = "--- Begin Service : ";
    String END_METHOD_SERVICE = "---- End Service ----";

    String BEGIN_METHOD_MCS = "--- Begin MCS API : ";
    String END_METHOD_MCS = "---- End MCS API ----";


    String BEGIN_METHOD_CONTROLLER = "--- Begin Controller : ";
    String END_METHOD_CONTROLLER = "---- End Controller ----";
    String LOGGER_ERROR = "--- ERROR";

    String ERROR_LOGGER = "-- ERROR: ";

    String API_COGN_MICROSOFT_PER_GROUP_FORMAT_TIME = "MM/DD/YYYY h:mm:ss a";

    int HTTP_CLIENT_KEY_FACE = 0;
    int HTTP_CLIENT_KEY_EMOTION = 1;

    String EXTENSION_FILE_IMAGE = "jpg";
    int PAGE_PAGE_I = 0;
    int PAGE_SIZE_I = 10;
    int PAGE_SIZE_CONTENT = 10;
    int PAGE_SIZE_AUTO_COMPLETE = 1000;
    String PAGE_PAGE = 0 + "";
    String PAGE_SIZE = 10 + "";
    String DEFAULT_INT = -1 + "";
    double MCS_PERSON_DETECT_CONFIDINCE_CORRECT = AppConfigKeys.getInstance().getApiPropertyDouble("detect.person.indetify.confidence");

    int AGE_AMOUNT = 2;
    int SEND_SMS = 0;


    /**
     * Side-bar
     */
    String SIDE_BAR_ADMIN_MANAGEMENT_DEPART = "ManagementDepart";
    String SIDE_BAR_ADMIN_MANAGEMENT_ACC = "ManagementAcc";
    String SIDE_BAR_MANAGER_CHECK_IN = "Checkin";
    String SIDE_BAR_MANAGER_TIMEKEEPING = "Timekeeping";
    String SIDE_BAR_MANAGER_MANAGEMENT_REMINDER = "ManagementReminder";
    String SIDE_BAR_MANAGER_CUSTOMER_SATISFACTION = "CustomerSatisfaction";
    String SIDE_BAR_EMPLOYEE_ATTENDANCE = "Attendance";
    String SIDE_BAR_EMPLOYEE_CUSTOMER_EMOTION = "CustomerEmotion";


    /**
     * CHECKIN
     */
    String TIME_CHECK_IN_SYSTEM = AppConfigKeys.getInstance().getApiPropertyValue("checkin.time.system");


    /**
     * suggestion model
     **/
    String QUANLITY_EMOTION_DEFAULT = "một ít";
    double EXCEPTION_VALUE = 0.05;
    String SUGGESTION_1_EMOTION = "%s cảm thấy %s.";
    String SUGGESTION_2_EMOTION = "%s cảm thấy %s và %s.";
    String SUGGESTION_3_EMOTION = "%s cảm thấy %s, %s và %s.";
    String SUGGESTION_BOTH_2_1_EMOTION = "%s cảm thấy %s và %s nhưng %s.";
    String SUGGESTION_BOTH_1_2_EMOTION = "%s cảm thấy %s nhưng %s và %s.";

    /** Message Competition*/
}