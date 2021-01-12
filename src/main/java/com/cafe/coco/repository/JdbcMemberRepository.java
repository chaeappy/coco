package com.cafe.coco.repository;

import com.cafe.coco.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Member save(Member member) {
        String sql = "insert into customer values(?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getId());
            ps.setString(2, member.getPw());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                member.setNum(rs.getLong(1));
            } else {
                throw new SQLException("실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ps, rs);
        }

    }

    @Override
    public Optional<Member> findByNum(Long num) {
        String sql = "select * from customer where email = (?)";
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, num);
            rs = ps.executeQuery();

            if (rs.next()) {
                Member member = new Member();
                member.setNum(rs.getLong("num"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }

        } catch (Exception e) {

        }
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

//    private Connection getConnection() {
//        return DataSourceUtils.getConnection(dataSource);
//    }

    private void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
