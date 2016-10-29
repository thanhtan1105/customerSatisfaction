package com.hackathon.repository;

import com.hackathon.entity.ConnectionPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lethanhtan on 10/29/16.
 */
@Repository
public interface ConnectionRepo extends JpaRepository<ConnectionPointEntity, Long> {



}
