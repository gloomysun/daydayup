package com.ly.txbehavior.service;

import com.ly.txbehavior.entity.Count;
import com.ly.txbehavior.repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountService {

    @Autowired
    private CountRepository countRepository;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(){
        Count count = new Count();
        count.setMoney(1000L);
        countRepository.save(count);
//        throw new RuntimeException("count error");
    }
}
