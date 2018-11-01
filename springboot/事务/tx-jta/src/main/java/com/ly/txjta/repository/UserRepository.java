package com.ly.txjta.repository;

import com.ly.txjta.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    @Modifying
    @Query("update User u set u.loginName=:loginName where u.id=:id")
    public int update(@Param("loginName")String loginName, @Param("id")int id);

    @Query("select t from User t ")
    public List<User> getList();

}
