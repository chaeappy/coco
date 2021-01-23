package com.cafe.coco.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Input {
    String name;
    int howMany;

    public Input(String name, int howMany) {
        this.name = name;
        this.howMany = howMany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    @Override
    public String toString() {
        return name +
                ", " + howMany;
    }
}
