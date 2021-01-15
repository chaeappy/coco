package com.cafe.coco.repository;

import com.cafe.coco.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemoryCustomerRepository implements CustomerRepository {
    private static HashMap<Long, Customer> store = new HashMap<Long, Customer>();
    private static long sequence = 0L;

    @Override
    public Customer save(Customer customer) {
        customer.setPk(++sequence);
        store.put(customer.getPk(), customer);
        return customer;
    }

    @Override
    public Customer findByPassword(Long pk) {
        return null;
    }

    @Override
    public boolean findById(String id) {
        return true;
    }

    @Override
    public Customer findOne(String id, String password) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
