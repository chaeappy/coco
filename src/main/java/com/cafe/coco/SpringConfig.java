package com.cafe.coco;

import com.cafe.coco.repository.JdbcCustomerRepository;
import com.cafe.coco.repository.CustomerRepository;
import com.cafe.coco.repository.JdbcOrderRepository;
import com.cafe.coco.repository.OrderRepository;
import com.cafe.coco.service.CustomerService;
import com.cafe.coco.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final DataSource dataSourceForDrink;

    @Autowired
    public SpringConfig(DataSource dataSource, DataSource dataSourceForDrink) {
        this.dataSource = dataSource;
        this.dataSourceForDrink = dataSourceForDrink;
    }

    @Bean
    public CustomerService memberService() {
        return new CustomerService(memberRepository());
    }

    @Bean
    public CustomerRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcCustomerRepository(dataSource);
    }

    @Bean
    public OrderService orderService() {
        return new OrderService(orderRepository());
    }
    @Bean
    public OrderRepository orderRepository() {
        return new JdbcOrderRepository(dataSourceForDrink);
    }

}
