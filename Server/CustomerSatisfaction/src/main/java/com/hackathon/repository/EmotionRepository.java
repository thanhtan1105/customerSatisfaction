package com.hackathon.repository;

import com.hackathon.entity.EmotionCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Repository
public interface EmotionRepository extends JpaRepository<EmotionCustomerEntity, Integer> {
    @Query(value = "select * from mydb.emotion c where customer_id = :customer_id order by c.create_time desc limit 1;", nativeQuery = true)
    public EmotionCustomerEntity findByCustomerIdLeast(@Param("customer_id") Integer customerId);

}
