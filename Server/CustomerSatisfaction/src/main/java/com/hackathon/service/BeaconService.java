package com.hackathon.service;

import com.hackathon.entity.BeaconEntity;
import com.hackathon.entity.CoordinateEntity;
import com.hackathon.model.BeaconModel;
import com.hackathon.model.CoordinateModel;
import com.hackathon.repository.BeaconRepo;
import com.hackathon.repository.CoordinateRepo;
import com.hackathon.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lethanhtan on 10/29/16.
 */
public class BeaconService {

    @Autowired
    private CoordinateRepo coordinateRepo;

    @Autowired
    private BeaconRepo beaconRepo;


    public void findShortPath(CoordinateEntity beginPoint, CoordinateEntity entityPoint) {

    }

    public List<CoordinateModel> getRoomPoint() {
        List<CoordinateEntity> list = coordinateRepo.findAllPointRoom();
        if (ValidateUtil.isEmpty(list)) {
            return null;
        } else {
            return list.stream().map(CoordinateModel::new).collect(Collectors.toList());
        }
    }

    public List<BeaconModel> getBeaconPoint() {
        List<BeaconEntity> list = beaconRepo.findAll();
        if (ValidateUtil.isEmpty(list)) {
            return null;
        } else {
            return list.stream().map(BeaconModel::new).collect(Collectors.toList());
        }
    }

    public List<CoordinateModel> getPoint() {
        List<CoordinateEntity> list = coordinateRepo.findAll();
        if (ValidateUtil.isEmpty(list)) {
            return null;
        } else {
            return list.stream().map(CoordinateModel::new).collect(Collectors.toList());
        }
    }
}
