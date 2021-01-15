package com.cafe.coco.service;

import com.cafe.coco.domain.Customer;
import com.cafe.coco.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class customerService {
    private final CustomerRepository customerRepository;

    public customerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * 회원가입
     */
    public Customer join(Customer customer) {
        // 아이디 중복검사
        if (customerRepository.findById(customer.getId())) {
            customerRepository.save(customer);
        }
        return customer;
    }

    /**
     * 로그인
     */
    public Customer login(Customer customer) {
        checkForLogin(customer);
        return null;
    }

    private void checkForLogin(Customer customer) {
//        customerRepository.findById();
    }


    /**
     * 전체 회원조회
     */
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public Customer findOne(String id, String password) {
        return customerRepository.findOne(id, password);
    }
}
