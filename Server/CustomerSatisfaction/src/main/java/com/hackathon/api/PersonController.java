package com.hackathon.api;

import com.hackathon.model.CustomerModel;
import com.hackathon.service.DetectPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private DetectPersonService detectPersonService;

    @RequestMapping(value = "/detect", method = RequestMethod.POST)
    @ResponseBody
    public CustomerModel dectPerson(@RequestParam("image") MultipartFile imageFile) {
        try {

            CustomerModel customerModel = detectPersonService.detect(imageFile.getInputStream());
            return customerModel;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
