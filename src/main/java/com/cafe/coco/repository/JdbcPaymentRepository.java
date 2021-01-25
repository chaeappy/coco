package com.cafe.coco.repository;

import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Input;
import com.cafe.coco.domain.Order;
import com.cafe.coco.domain.Payment;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;

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
            preparedStatement.setString(5, payment.getCash_receipt());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                System.out.println("Payment 저장완료");
                saveOrder(payment);
                close(connection, preparedStatement, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }

    public void saveOrder(Payment payment) {
        sql = "INSERT INTO orders(drink_pk, drink_name, ea, total) VALUES (?, ?, ?, ?);";
        Order order = payment.getOrder();
        ArrayList<Input> inputs = order.getInputs();
        try {
            connection = getConnection();
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < inputs.size(); i++) {
                Input input = inputs.get(i);
                Drink drink = input.getDrink();
                preparedStatement.setLong(1, drink.getPk());
                preparedStatement.setString(2, drink.getName());
                preparedStatement.setInt(3, input.getHowMany());
                preparedStatement.setInt(4, input.getTotal());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    System.out.println("pk : " + resultSet.getLong(1));
                } else {
                    System.out.println("오류");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
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
