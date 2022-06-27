package com.vinsguru.cqrspattern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class MaterializedViewRefresher {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Scheduled(fixedRate = 5000L)
    public void refresh(){
        this.entityManager.createNativeQuery("call refresh_mat_view();").executeUpdate();
    }

}
