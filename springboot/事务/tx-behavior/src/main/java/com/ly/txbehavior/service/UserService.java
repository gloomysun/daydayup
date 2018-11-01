package com.ly.txbehavior.service;

import com.ly.txbehavior.entity.Count;
import com.ly.txbehavior.entity.User;
import com.ly.txbehavior.repository.CountRepository;
import com.ly.txbehavior.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountService countService;

    @Transactional
    public void insert(){
        try {

            countService.insert();
//        this.insertCount();
         throw new RuntimeException("count error");

        }catch (Exception e){
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername("xiaoming");
        userRepository.save(user);
    }

    @Autowired
    private CountRepository countRepository;
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertCount(){
        Count count = new Count();
        count.setMoney(1000L);
        countRepository.save(count);
        throw new RuntimeException("count error");
    }
}
