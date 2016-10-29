package com.hackathon.repository;


import com.hackathon.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    @Query("SELECT c FROM TransactionEntity c WHERE c.CustomerCode = :customerCode")
    public TransactionEntity findByCustomerCode(@Param("customerCode") String customerCode);
}
