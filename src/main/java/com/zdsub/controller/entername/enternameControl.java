package com.zdsub.controller.entername;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.entername.Entername;
import com.zdsub.service.entername.EnternameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-12 22:41
 **/
@RestController
@RequestMapping("/enterName/")
public class enternameControl {
    @Resource
    private EnternameService enternameService;
    /*@description：分页查询
     *@Date：2019/9/12 23:19
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("getPage")
    public ResponseBean getPage(@RequestBody Page<Entername> page){
        return ResponseBean.PAGESUCCESS(enternameService.getPage(page));
    }
}
