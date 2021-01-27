package com.cafe.coco.domain;

/**
 * :: 입력 또는 선택 클래스
 * 1. pk - 음료넘버
 * 2. name - 음료명
 * 3. price - 음료가격
 * 4. howMany - 수량
 * 5. total - 음료 * 수량 = 가격 ( ** 해당 주문의 토탈금액 아님 )
 *
 * 고객이 선택한 한가지 종류의 음료와 수량을 객체화한다 for ( order클래스 요소로 들어감 )
 * ㄴ Receipt 객체 생성시 필요한 ( 요소 담고있음 )
 */

public class Input {
    private Drink drink;
    private int howMany;
    private int total;

    public Input(Drink drink, int howMany) {
        this.drink = drink;
        this.howMany = howMany;
        setTotal();
    }

    public Drink getDrink() {
        return drink;
    }

//    public void setDrink(Drink drink) {
//        this.drink = drink;
//    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
        setTotal();
    }

    public void addHowMany() {
        this.howMany = ++this.howMany;
        setTotal();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = (this.drink.getPrice()) * this.howMany;
    }

    @Override
    public String toString() {
        return this.drink.getName() +
                ", " + howMany;
    }
}
