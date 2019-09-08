package com.zdsub.controller.index;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.work.Process;
import com.zdsub.entity.work.WorkDynamic;
import com.zdsub.service.work.ProcessService;
import com.zdsub.service.work.WorkDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: zdsub
 * @BelongsPackage: com.zdsub.controller.index
 * @Author: ly
 * @CreateTime: 2019-09-08 14:56
 * @Description: 此控制器专用于门户的控制器
 */
@RestController
@RequestMapping("/home/")
public class IndexController {

    @Autowired
    private WorkDynamicService workDynamicService;

    @Autowired
    private ProcessService processService;

    /**
     * 门户工作动态的分页查询
     *
     * @param workDynamicPage
     * @return
     */
    @PostMapping("work")
    public ResponseBean workDynamicPage (@RequestBody Page<WorkDynamic> workDynamicPage) {
        return ResponseBean.SUCCESS(workDynamicService.page(workDynamicPage));
    }

    /**
     * 查看工作动态并增加阅读数
     *
     * @param id
     * @return
     */
    @GetMapping("toAdverShow/{id}")
    public ResponseBean toAdverShow(@PathVariable  String id) {
        return ResponseBean.SUCCESS(workDynamicService.toAdverShow(id));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("toWorkShow{id}")
    public ResponseBean get(@PathVariable String id) {
        return ResponseBean.SUCCESS(workDynamicService.get(id));
    }

    /**
     *
     * @param processPage
     * @return
     */
    @PostMapping("process")
    public ResponseBean page(@RequestBody Page<Process> processPage) {
        return ResponseBean.SUCCESS(processService.page(processPage));
    }
}
