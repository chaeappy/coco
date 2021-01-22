package com.cafe.coco.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Input {
    Drink drink;
    int howMany;

    public Input(Drink drink, int howMany) {
        this.drink = drink;
        this.howMany = howMany;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    @Override
    public String toString() {
        return drink +
                ", " + howMany;
    }
}
