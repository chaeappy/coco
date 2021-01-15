package com.cafe.coco.repository;

import com.cafe.coco.domain.Customer;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JdbcCustomerRepository implements CustomerRepository {
    private final DataSource dataSource;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = null;

    public JdbcCustomerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Customer save(Customer customer) {
        sql = "INSERT INTO customer(id, password) VALUES (?, ?);";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                customer.setPk(resultSet.getLong(1));
            } else {
                throw new SQLException("id 조회실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
            return customer;
        }
    }


    @Override
    public boolean findById(String id) {
        sql = "SELECT * FROM customer where id = (?);";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return true;
    }

    @Override
    public Customer findByPassword(Long pk) {
        return null;
    }

    @Override
    public Customer findOne(String id, String password) {
        sql = "SELECT * FROM customer WHERE id = (?) and password = (?);";
        Customer customer = null;
        Long pk = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // resultSet은 != null 사용할 수 없음 주의
            if (resultSet.next()) {
                pk = resultSet.getLong("pk");
                id = resultSet.getString("id");
                password = resultSet.getString("password");
                customer = new Customer(pk, id, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
            return customer;
        }
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

//    private void close(Connection connection) throws SQLException {
//        DataSourceUtils.releaseConnection(connection, dataSource);
//
//    }


    private void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
