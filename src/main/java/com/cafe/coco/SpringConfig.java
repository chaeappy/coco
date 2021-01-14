package com.cafe.coco;

import com.cafe.coco.repository.JdbcCustomerRepository;
import com.cafe.coco.repository.CustomerRepository;
import com.cafe.coco.service.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public customerService memberService() {
        return new customerService(memberRepository());
    }

    @Bean
    public CustomerRepository memberRepository() {
//        return new MemoryMemberRepository();
        return new JdbcCustomerRepository(dataSource);
    }
}
