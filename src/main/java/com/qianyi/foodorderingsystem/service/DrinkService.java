package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkService {
    private List<Drink> drinks;

    public DrinkService() {
        drinks = new ArrayList<>();
    }

    // 创建新饮料
    public Drink createDrink(Drink drink) {
        drinks.add(drink);
        return drink;
    }

    // 更新饮料信息
    public void updateDrink(Drink drink, String newName, double newPrice, String newSize) {
        drink.setName(newName);
        drink.setPrice(newPrice);
        drink.setSize(newSize);
    }

    // 删除饮料
    public void deleteDrink(Drink drink) {
        drinks.remove(drink);
    }

    // 获取所有饮料
    public List<Drink> getAllDrinks() {
        return drinks;
    }
}
