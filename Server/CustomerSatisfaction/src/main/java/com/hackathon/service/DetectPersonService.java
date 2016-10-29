package com.hackathon.service;

import com.hackathon.accessMCS.FaceServiceMCSImpl;
import com.hackathon.common.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Service
@Component
public class DetectPersonService {

    @Autowired
    private FaceServiceMCSImpl faceServiceMCS;

    public void detect(InputStream image) throws IOException, URISyntaxException {
        BaseResponse response = faceServiceMCS.detect(image);
    }

}
