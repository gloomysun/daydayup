package com.ly.txjta.repository;


import com.ly.txjta.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


}
