package com.cafe.coco.repository;

import com.cafe.coco.domain.Drink;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JdbcOrderRepository implements OrderRepository{
    DataSource dataSourceForDrink = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;

    HashMap<Long, Drink> menu = new HashMap<>();

    public JdbcOrderRepository(DataSource dataSourceForDrink) {
        this.dataSourceForDrink = dataSourceForDrink;
    }

    /**
     * 메뉴저장
     */
    @Override
    public HashMap menu() {
        sql = "SELECT * FROM drink;";
        // hashMap != null 모두 삭제하고 시작
        cleanHashMap();

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Long pk = resultSet.getLong(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);
                Drink drink = new Drink(pk, name, price);
                if (drink != null) {
                    menu.put(pk, drink);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return menu;
    }

    /**
     * 메뉴선택
     */
    @Override
    public HashMap input() {
        return null;
    }

    @Override
    public HashMap modifyInput() {
        return null;
    }

    private void cleanHashMap() {
        if (menu != null) {
            menu.clear();
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSourceForDrink);
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
