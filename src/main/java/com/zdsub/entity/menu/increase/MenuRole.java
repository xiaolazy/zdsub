package com.zdsub.entity.menu.increase;

import com.zdsub.entity.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-13 17:02
 **/
@Getter
@Setter
public class MenuRole {
    protected List<Menu> menus;
    protected List<String> ids;
}
