package com.hackathon.accessMCS;

import com.hackathon._config.AppConfigKeys;
import com.hackathon.common.BaseResponse;
import com.hackathon.constant.IContanst;
import com.hackathon.modelMCS.PersonGroup;
import com.hackathon.modelMCS.PersonGroupTrainingStatus;
import com.hackathon.util.HTTPClientUtil;
import com.hackathon.util.JsonUtil;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class PersonGroupServiceMCSImpl {

    private Logger logger = LogManager.getLogger(PersonGroupServiceMCSImpl.class);

    private String rootPath = AppConfigKeys.getInstance().getApiPropertyValue("api.microsoft.cognitive.service.root.url")
            + AppConfigKeys.getInstance().getApiPropertyValue("api.person.group");


    public BaseResponse create(String groupId, String groupName, String groupData) throws URISyntaxException, IOException {

        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            String urlString = String.format("%s/%s", rootPath, groupId);

            /** entity*/
            Map<String, String> entity = new HashMap<String, String>();
            entity.put("name", groupName);
            entity.put("userData", groupData);
            String jsonEntity = JsonUtil.toJson(entity);


            return HTTPClientUtil.getInstanceFace().toPut(urlString, new StringEntity(jsonEntity, StandardCharsets.UTF_8));
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }

    public BaseResponse listAll(int start, int top) throws URISyntaxException, IOException {
        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            String urlString = rootPath;
            URIBuilder builder = new URIBuilder(urlString)
                    .addParameter("start", String.valueOf(start))
                    .addParameter("top", String.valueOf(top));
            return HTTPClientUtil.getInstanceFace().toGet(builder.build(), JsonUtil.LIST_PARSER, PersonGroup.class);
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }

    public BaseResponse trainGroup(String personGroupId) throws URISyntaxException, IOException {
        try {
            String urlString = rootPath;
            String urlAddition = AppConfigKeys.getInstance().getApiPropertyValue("api.person.group.train.person.addition");
            String url = String.format("%s/%s/%s", urlString, personGroupId, urlAddition);
            return HTTPClientUtil.getInstanceFace().toPost(url);
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }

    public BaseResponse trainPersonGroupStatus(String personGroupId) throws URISyntaxException, IOException {
        try {
            logger.info(IContanst.BEGIN_METHOD_SERVICE + Thread.currentThread().getStackTrace()[1].getMethodName());
            String urlString = rootPath;
            String urlAddition = AppConfigKeys.getInstance().getApiPropertyValue("api.person.group.train.status.addition");
            String url = String.format("%s/%s/%s", urlString, personGroupId, urlAddition);

            return HTTPClientUtil.getInstanceFace().toGet(url, JsonUtil.TIME_PARSER, PersonGroupTrainingStatus.class);
        } finally {
            logger.info(IContanst.END_METHOD_SERVICE);
        }
    }
}
