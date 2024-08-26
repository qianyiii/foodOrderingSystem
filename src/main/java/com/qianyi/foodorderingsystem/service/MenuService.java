package com.qianyi.foodorderingsystem.service;

import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Menu;

public class MenuService {
    private Menu menu;

    public MenuService() {
        this.menu = new Menu();
    }

    // 添加饮料到菜单
    public void addDrinkToMenu(Drink drink) {
        menu.addDrink(drink);
    }

    // 从菜单中移除饮料
    public void removeDrinkFromMenu(Drink drink) {
        menu.removeDrink(drink);
    }

    // 获取菜单
    public Menu getMenu() {
        return menu;
    }

    // 显示菜单
    public void displayMenu() {
        System.out.println(menu);
    }
}
