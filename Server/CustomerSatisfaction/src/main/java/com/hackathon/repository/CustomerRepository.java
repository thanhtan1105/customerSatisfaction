package com.hackathon.repository;

import com.hackathon.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
}
