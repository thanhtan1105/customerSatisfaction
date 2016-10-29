package com.hackathon.model;

import com.hackathon.entity.CustomerEntity;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class CustomerModel {

    private Integer id;

    private String name;

    private String personCode;

    private Timestamp createTime;

    private Set<TransactionModel> transactions;

    public CustomerModel() {
    }

    public CustomerModel(CustomerEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.personCode = entity.getPersonCode();
            this.createTime = entity.getCreateTime();
            if (entity.getTransactions() != null && entity.getTransactions().size() > 0) {
                this.transactions = entity.getTransactions().stream().map(TransactionModel::new).collect(Collectors.toSet());
            }

        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Set<TransactionModel> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<TransactionModel> transactions) {
        this.transactions = transactions;
    }
}
