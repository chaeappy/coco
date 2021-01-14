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
    public Long join(Customer customer) {

        vaildateDuplicateCustomer(customer); // 중복회원 검사
        customerRepository.save(customer);
        return customer.getPk();
    }

    private void vaildateDuplicateCustomer(Customer customer) {
        customerRepository.findById(customer.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원조회
     */
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findOne(Long customerPk) {
        return customerRepository.findByPk(customerPk);
    }
}
