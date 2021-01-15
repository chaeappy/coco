package com.cafe.coco.repository;

import com.cafe.coco.domain.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findByPassword(Long pk);
    boolean findById(String id);
    Customer findOne(String id, String password);
    List<Customer> findAll();
}
