package com.hackathon.repository;

import com.hackathon.entity.QuantityEmotionEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lethanhtan on 10/29/16.
 */
public interface QuantityRepo extends JpaRepository<QuantityEmotionEnity, Long> {

    @Query("SELECT q.name FROM QuantityEmotionEnity  q WHERE (?1 BETWEEN q.fromValue and q.toValue)")
    public List<String> findQuantity(Double value);

}
