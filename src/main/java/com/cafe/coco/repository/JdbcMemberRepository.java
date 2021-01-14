package com.cafe.coco.repository;

import com.cafe.coco.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findByNum(Long num) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

}
