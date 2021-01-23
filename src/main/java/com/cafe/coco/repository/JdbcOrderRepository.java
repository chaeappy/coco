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
    int index = -1;

    // 메뉴 생성을 위한 드링크 기본 목록
    HashMap<Long, Drink> drinks = new HashMap<>();
    // 메뉴 출력을 위한 단순 리스트
    ArrayList<Drink> menus;
    // 맵 sand에 인덱스 넘버와, 수량을 위한 임시 그릇 (list 특정값 호출을 위한 편법)
    HashMap<Long, Integer> checkInputs = new HashMap<>();
    // Map_sand의 오브젝트타입 밸류로 담기위한 그릇
    ArrayList<Input> inputs = new ArrayList<>();
    // 실질적으로 서버에 전송되는 맵
    HashMap<String, Object> send = new HashMap<>();

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
        clean();

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
    public HashMap<String, Object> selectMenu(Long pk) {
        Drink drink = drinks.get(pk);
        if (!checkInputs.containsKey(pk)) {
            ++index;
            checkInputs.put(pk, index);
            inputs.add(new Input(drink.getName(), 1));
        } else {
            // 수량변경
            modifyMenu(pk);
        }
            send.put("inputs", inputs);
//        inputs.add(new Input(drink.getName(), 1));
        return send;
    }


    @Override
    public HashMap<String, Object> modifyMenu(Long pk) {
        // index 넘버
        int value = checkInputs.get(pk);
        Input input = inputs.get(value);
        int howMany = input.getHowMany();
        input.setHowMany(++howMany);
        inputs.set(value, input);
        send.put("inputs", inputs);
//        if (inputs.contains(input)) {
//            inputs.remove(input);
//            input.setHowMany(howMany);
//            inputs.add(input);
//        }
        return send;
    }

    @Override
    public Long total() {
      return null;
    }

    /**
     * 메뉴 HashMap 청소
     */
    private void clean() {
        if (menus != null) {
            menus.clear();
        }
        if (inputs != null) {
            inputs.clear();
        }
        if (drinks != null) {
            drinks.clear();
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
