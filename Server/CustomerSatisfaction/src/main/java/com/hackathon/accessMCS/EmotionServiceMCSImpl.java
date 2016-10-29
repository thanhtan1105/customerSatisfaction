package com.hackathon.accessMCS;

import com.hackathon._config.AppConfigKeys;
import com.hackathon.common.BaseResponse;
import com.hackathon.constant.IContanst;
import com.hackathon.modelMCS.EmotionRecognizeResponse;
import com.hackathon.modelMCS.RectangleImage;
import com.hackathon.util.HTTPClientUtil;
import com.hackathon.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class EmotionServiceMCSImpl {

    private Logger logger = LogManager.getLogger(EmotionServiceMCSImpl.class);

    String rootPath = AppConfigKeys.getInstance().getApiPropertyValue("api.microsoft.cognitive.service.emotion.root.url");

    public BaseResponse recognize(String urlImg) throws URISyntaxException, IOException {

        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            String urlAddition = AppConfigKeys.getInstance().getApiPropertyValue("api.emotion.recognize");
            String url = rootPath + urlAddition;

            /*** url -> ${url}*/
            logger.info("url: " + url);


            /** entity*/
            Map<String, String> mapEntity = new HashMap<>();
            mapEntity.put("url", urlImg);
            String jsonEntity = JsonUtil.toJson(mapEntity);
            logger.info("json Entity: " + jsonEntity);

            return HTTPClientUtil.getInstanceEmotion().toPost(url, new StringEntity(jsonEntity, StandardCharsets.UTF_8), JsonUtil.LIST_PARSER, EmotionRecognizeResponse.class);
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }

    public BaseResponse recognize(InputStream inputStreamImg, RectangleImage ractangle) throws URISyntaxException, IOException {

        try {
            String urlAddition = AppConfigKeys.getInstance().getApiPropertyValue("api.emotion.recognize");
            String url = rootPath + urlAddition;
            logger.info(IContanst.BEGIN_METHOD_MCS + url);

            /*** url -> ${url}*/
            URIBuilder builder = new URIBuilder(url)
                    .addParameter("faceRectangles", ractangle.toFaceRectangle());
//            url = String.format("%s?%s=%s", url, "faceRectangles", ractangle.toFaceRectangle());

            /** entity*/
            byte[] byteEntity = IOUtils.toByteArray(inputStreamImg);
//            logger.info("json Entity: " + jsonEntity);

            return HTTPClientUtil.getInstanceEmotion().toPostOct(builder.build(), new ByteArrayEntity(byteEntity), JsonUtil.LIST_PARSER, EmotionRecognizeResponse.class);
        } finally {
            logger.info(IContanst.END_METHOD_MCS);
        }
    }

    public BaseResponse recognize(InputStream inputStreamImg) throws URISyntaxException, IOException {

        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            String urlAddition = AppConfigKeys.getInstance().getApiPropertyValue("api.emotion.recognize");
            String url = rootPath + urlAddition;

            /*** url -> ${url}*/
            logger.info("url: " + url);


            /** entity*/
            byte[] byteEntity = IOUtils.toByteArray(inputStreamImg);
//            logger.info("json Entity: " + jsonEntity);

            return HTTPClientUtil.getInstanceEmotion().toPostOct(url, new ByteArrayEntity(byteEntity), JsonUtil.LIST_PARSER, EmotionRecognizeResponse.class);
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }
}
