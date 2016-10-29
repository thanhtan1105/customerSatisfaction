package com.hackathon.api;

import com.hackathon.common.BaseResponse;
import com.hackathon.constant.I_URI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lethanhtan on 10/29/16.
 */

@RestController
@RequestMapping(I_URI.API_CUSTOMER)
public class CustomerSatisfactionController {

    public BaseResponse customerEmotionCamera(@RequestParam(value = "image") MultipartFile fileImg) {
        BaseResponse baseResponse = new BaseResponse();
        return null;
    }

}
