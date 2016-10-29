package com.hackathon.api;

import com.hackathon.common.BaseResponse;
import com.hackathon.constant.IContanst;
import com.hackathon.constant.I_URI;
import com.hackathon.model.BeaconFindPathResponse;
import com.hackathon.service.BeaconAlgorithm;
import com.hackathon.service.BeaconService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lethanhtan on 10/29/16.
 */

@RestController
@RequestMapping(I_URI.API_BEACON)
public class BeaconController {

    private Logger logger = LogManager.getLogger(BeaconController.class);

    @Autowired
    private BeaconService beaconService;

    @Autowired
    private BeaconAlgorithm algorithm;

    @RequestMapping(I_URI.API_BEACON_GET_BEACON_POINT)
    public BaseResponse getBeaconPoint() {
        try {
            logger.info(IContanst.BEGIN_METHOD_CONTROLLER + Thread.currentThread().getStackTrace()[1].getMethodName());
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setData(beaconService.getBeaconPoint());
            baseResponse.setSuccess(true);
            return baseResponse;
        } finally {
            logger.info(IContanst.END_METHOD_CONTROLLER);
        }
    }

    @RequestMapping(I_URI.API_BEACON_FIND_PATH)
    public BaseResponse findMinPath(@RequestParam("from") String beginVertex,
                                    @RequestParam("to") String endVertex) {
        try {
            logger.info(IContanst.BEGIN_METHOD_CONTROLLER + Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(String.format("from = '%s', to= '%s' ", beginVertex, endVertex));
            BeaconFindPathResponse response = algorithm.findShortPath(Long.parseLong(beginVertex), Long.parseLong(endVertex));
            if (response != null) {
                return new BaseResponse(true, response);
            } else {
                return new BaseResponse(false);
            }
        } catch (Exception e) {
            logger.error(e);
            return new BaseResponse(false, e.getMessage());
        } finally {
            logger.info(IContanst.END_METHOD_CONTROLLER);
        }
    }

}
