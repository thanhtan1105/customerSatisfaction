package com.hackathon.repository;

import com.hackathon.entity.BeaconEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lethanhtan on 10/29/16.
 */
@Repository
public interface BeaconRepo extends JpaRepository<BeaconEntity, Long> {


}
