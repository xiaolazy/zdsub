package com.zdsub.entity.menu.increase;

import com.zdsub.entity.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-08 15:15
 **/
@Getter
@Setter
public class MenuInc extends Menu {
    protected List<MenuInc> childrens;
}
