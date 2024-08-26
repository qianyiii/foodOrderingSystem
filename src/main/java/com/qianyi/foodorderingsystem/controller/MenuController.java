package com.qianyi.foodorderingsystem.controller;

import com.qianyi.foodorderingsystem.model.Drink;
import com.qianyi.foodorderingsystem.model.Menu;
import com.qianyi.foodorderingsystem.service.MenuService;

public class MenuController {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 添加饮料到菜单
    public void addDrinkToMenu(Drink drink) {
        menuService.addDrinkToMenu(drink);
    }

    // 从菜单中移除饮料
    public void removeDrinkFromMenu(Drink drink) {
        menuService.removeDrinkFromMenu(drink);
    }

    // 获取当前菜单
    public Menu getMenu() {
        return menuService.getMenu();
    }

    // 显示菜单
    public void displayMenu() {
        menuService.displayMenu();
    }
}
