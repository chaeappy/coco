package com.cafe.coco.repository;

import com.cafe.coco.domain.*;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                Long payment_pk = resultSet.getLong(1);
                payment.setPk(payment_pk);
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
        sql = "INSERT INTO orders(payment_pk, drink_pk, drink_name, ea, total) VALUES (?, ?, ?, ?, ?);";
        Order order = payment.getOrder();
        ArrayList<Input> inputs = order.getInputs();
        try {
            connection = getConnection();
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < inputs.size(); i++) {
                Input input = inputs.get(i);
                Drink drink = input.getDrink();
                preparedStatement.setLong(1, payment.getPk());
                preparedStatement.setLong(2, drink.getPk());
                preparedStatement.setString(3, drink.getName());
                preparedStatement.setInt(4, input.getHowMany());
                preparedStatement.setInt(5, input.getTotal());
                int success = preparedStatement.executeUpdate();

                if (success != 0) {
                    System.out.println("성공");
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
    public Map<String, Object> printReceipt(Customer customer, Long pk) {
        HashMap<String, Object> payments = new HashMap<>();
        Payment payment = suchPayment(customer, pk);
        payments.put("pk", payment.getPk());
        payments.put("date", payment.getDate());
        payments.put("customer", payment.getCustomer().getId());
        payments.put("payment_way", payment.getPayment_way());
        payments.put("cash_receipt", payment.getCash_receipt());
        payments.put("inputs", payment.getOrder().getInputs());

        return payments;
    }

    public Payment suchPayment(Customer customer, Long payment_pk) {
        Order order = getOrderInfo(customer, payment_pk);
        Payment payment = getPaymentInfo(payment_pk, order);

        return payment;
    }
    private Payment getPaymentInfo(Long pk, Order order) {
        sql = "SELECT * FROM payment WHERE pk = (?);";
        Payment payment = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, pk);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long payment_pk = resultSet.getLong("pk");
                String payment_date = resultSet.getString("payment_date");
                String payment_way = resultSet.getString("payment_way");
                String cash_receipt = resultSet.getString("cash_receipt");
                payment = new Payment(payment_pk, payment_date, order.getCustomer(), payment_way, cash_receipt, order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
            return payment;
        }
    }


    private Order getOrderInfo(Customer customer, Long pk) {
        sql = "SELECT * FROM orders WHERE payment_pk = (?);";
        ArrayList<Input> inputs = new ArrayList<>();
        Order order = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, pk);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long payment_pk = resultSet.getLong("payment_pk");
                Long drink_pk = resultSet.getLong("drink_pk");
                int howMAny = resultSet.getInt("ea");
                Drink drink = getDrinkInfo(drink_pk);
                Input input = new Input(drink, howMAny);
                inputs.add(input);
            }
            order = new Order(customer, inputs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return order;
    }

    private Drink getDrinkInfo(Long drink_pk) {
        sql = "SELECT * FROM drink WHERE pk = (?);";
        Drink drink = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, drink_pk);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long pk = resultSet.getLong("pk");
                String name = resultSet.getString("name");
                String price = resultSet.getString("price");
                String type = resultSet.getString("type");
                drink = new Drink(pk, name, Integer.parseInt(price), type);
                return drink;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
            return drink;
        }
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
