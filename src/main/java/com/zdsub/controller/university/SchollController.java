package com.zdsub.controller.university;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.entity.PageCondition;
import com.zdsub.entity.university.School;
import com.zdsub.entity.university.increase.SchoolInc;
import com.zdsub.service.university.SchollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.controller.university
 * @Author: ly
 * @CreateTime: 2019-09-06 19:08
 * @Description:
 */
@RestController
@RequestMapping("/school/")
public class SchollController {

    @Autowired
    private SchollService schollService;

    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@RequestBody @Valid SchoolInc schoolInc, BindingResult bindingResult) {
        schollService.add(schoolInc);
        return ResponseBean.SUCCESS();
    }

    @PostMapping("edit")
    @ValidLog
    public ResponseBean edit(@RequestBody @Valid SchoolInc schoolInc, BindingResult bindingResult) {
        schollService.edit(schoolInc);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("remove/{id}")
    public ResponseBean remove(@PathVariable String id) {
        schollService.remove(id);
        return ResponseBean.SUCCESS();
    }

    @GetMapping("get/{id}")
    public ResponseBean get(@PathVariable String id) {
        return ResponseBean.SUCCESS(schollService.get(id));
    }

    @GetMapping("listAll")
    public ResponseBean listAll(HttpServletRequest req, HttpServletResponse res) {
        return ResponseBean.SUCCESS(schollService.listAll());
    }

    @PostMapping("page")
    public ResponseBean page(@RequestBody Page<School> page, HttpSession session,
                             HttpServletRequest req, HttpServletResponse res) {
        Cookie[] allCookies = req.getCookies();
        System.out.println("-----------------------------------");
        int i = 0;
        //如果allCookies不为空...
        if (allCookies != null) {

            //从中取出cookie
            for (i = 0; i < allCookies.length; i++) {
                System.out.println("-----------------------------------");
                //依次取出
                Cookie temp = allCookies[i];
                if (temp.getName().equals("color1")) {

                    //得到cookie的值
                    String val = temp.getValue();

                    System.out.println("color1=====================" + val);
                    break;

                }
            }

        }
        PageCondition pageCondition=new PageCondition();
        pageCondition.setPage(page);
        return ResponseBean.SUCCESS(schollService.page(pageCondition));
    }

}


