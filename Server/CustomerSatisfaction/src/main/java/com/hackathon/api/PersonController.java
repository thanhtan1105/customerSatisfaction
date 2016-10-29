package com.hackathon.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@RestController("/api/person")
public class PersonController {


    @RequestMapping("/detect")
    public void dectPerson(@RequestParam("image") MultipartFile imageFile){

    }
}
