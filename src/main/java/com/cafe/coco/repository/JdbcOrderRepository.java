package com.cafe.coco.repository;

import com.cafe.coco.domain.Drink;
import com.cafe.coco.domain.Input;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JdbcOrderRepository implements OrderRepository{
    DataSource dataSourceForDrink = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String sql = null;


    ArrayList<Drink> menus;
    HashMap<Long, Drink> drinks = new HashMap<>();
    ArrayList<Input> inputs = new ArrayList<>();

    public JdbcOrderRepository(DataSource dataSourceForDrink) {
        this.dataSourceForDrink = dataSourceForDrink;
    }

    /**
     * 메뉴저장
     */
    @Override
    public ArrayList<Drink> menu() {
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
                String type = resultSet.getString(4);
                Drink drink = new Drink(pk, name, price, type);
                if (drink != null) {
                    drinks.put(pk, drink);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
        return  menus = new ArrayList<Drink>(drinks.values());
    }

    /**
     * 메뉴선택
     */
    @Override
    public ArrayList<Input> selectMenu(Drink drink) {
        Long pk = drink.getPk();
        inputs.add(new Input(drink, 1));
        return inputs;
    }


    @Override
    public ArrayList<Input> modifyMenu(Input input, int howMany) {
        if (inputs.contains(input)) {
            inputs.remove(input);
            input.setHowMany(howMany);
            inputs.add(input);
        }
        return inputs;
    }

    @Override
    public Long total() {
      return null;
    }

    /**
     * 메뉴 HashMap 청소
     */
    private void cleanHashMap() {
        if (menus != null) {
            menus.clear();
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
