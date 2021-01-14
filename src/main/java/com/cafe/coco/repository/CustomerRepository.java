package com.cafe.coco.repository;

import com.cafe.coco.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findByPk(Long pk);
    Optional<Customer> findById(String id);
    List<Customer> findAll();
}
