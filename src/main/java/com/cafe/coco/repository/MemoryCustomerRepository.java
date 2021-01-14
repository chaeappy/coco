package com.cafe.coco.repository;

import com.cafe.coco.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    public Optional<Customer> findByPk(Long pk) {
        return Optional.ofNullable(store.get(pk));
    }

    @Override
    public Optional<Customer> findById(String id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
