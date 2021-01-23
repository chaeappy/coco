package com.cafe.coco;

import com.cafe.coco.repository.*;
import com.cafe.coco.service.CustomerService;
import com.cafe.coco.service.OrderService;
import com.cafe.coco.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final DataSource dataSourceForDrink;
    private final DataSource dataSourceForPayment;

    @Autowired
    public SpringConfig(DataSource dataSource, DataSource dataSourceForDrink, DataSource dataSourceForPayment) {
        this.dataSource = dataSource;
        this.dataSourceForDrink = dataSourceForDrink;
        this.dataSourceForPayment = dataSourceForPayment;
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

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(paymentRepository());
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new JdbcPaymentRepository(dataSourceForPayment);
    }

}
