package com.cafe.coco.repository;

import com.cafe.coco.domain.Payment;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;

public class JdbcPaymentRepository implements PaymentRepository {
    private final DataSource dataSourceForPayment;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = null;

    public JdbcPaymentRepository(DataSource dataSourceForPayment) {
        this.dataSourceForPayment = dataSourceForPayment;
    }

    @Override
    public void save(Payment payment) {
        sql = "INSERT INTO payment(payment_date, customer_id, payment_way, total, cash_receipt) VALUES (?, ?, ?, ?, ?);";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, payment.getDate());
            preparedStatement.setString(2, payment.getCustomer().getId());
            preparedStatement.setString(3, payment.getPayment_way());
            preparedStatement.setInt(4, payment.getTotal());
            preparedStatement.setString(5, payment.get);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void printReceipt() {

    }

    @Override
    public void cancelPayment() {

    }

    @Override
    public void logout() {

    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSourceForPayment);
    }



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
