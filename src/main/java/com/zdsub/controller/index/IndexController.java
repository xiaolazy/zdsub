package com.zdsub.controller.index;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.university.School;
import com.zdsub.entity.work.Process;
import com.zdsub.entity.work.WorkDynamic;
import com.zdsub.service.recruitment.AdverService;
import com.zdsub.service.university.SchollService;
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

    @Autowired
    private AdverService adverService;

    @Autowired
    private SchollService schollService;

    /**
     * 门户工作动态的分页查询
     *
     * @param workDynamicPage
     * @return
     */
    @PostMapping("work")
    public ResponseBean workDynamicPage(@RequestBody Page<WorkDynamic> workDynamicPage) {
        return ResponseBean.SUCCESS(workDynamicService.page(workDynamicPage));
    }

    /**
     * 查看工作动态并增加阅读数
     *
     * @param id
     * @return
     */
    @GetMapping("work/toAdverShow/{id}")
    public ResponseBean workToAdverShow(@PathVariable String id) {
        return ResponseBean.SUCCESS(workDynamicService.toAdverShow(id));
    }


    /**
     * 门户工作路径的分页查询
     *
     * @param processPage
     * @return
     */
    @PostMapping("process")
    public ResponseBean processPage(@RequestBody Page<Process> processPage) {
        return ResponseBean.SUCCESS(processService.page(processPage));
    }

    /**
     * 查看工作路径
     *
     * @param id
     * @return
     */
    @GetMapping("process/toAdverShow/{id}")
    public ResponseBean processToAdverShow(@PathVariable String id) {
        return ResponseBean.SUCCESS(processService.get(id));
    }

    /**
     * 门户援藏人员的分页查询
     *
     * @param adverPage
     * @return
     */
    @PostMapping("adver")
    public ResponseBean adverPage(@RequestBody Page<Adver> adverPage) {
        return ResponseBean.SUCCESS(adverService.page(adverPage));
    }

    /**
     * 查看援藏人员
     *
     * @param id
     * @return
     */
    @GetMapping("adver/toAdverShow/{id}")
    public ResponseBean adverToAdverShow(@PathVariable String id) {
        return ResponseBean.SUCCESS(adverService.get(id));
    }

    /**
     * 门户学校人员的分页查询
     *
     * @param schoolPage
     * @return
     */
    @PostMapping("scholl")
    public ResponseBean schollPage(@RequestBody Page<School> schoolPage) {

        return ResponseBean.SUCCESS(schollService.page(null));
    }

    /**
     * 查看学校人员
     *
     * @param id
     * @return
     */
    @GetMapping("scholl/toAdverShow/{id}")
    public ResponseBean schollToAdverShow(@PathVariable String id) {
        return ResponseBean.SUCCESS(schollService.get(id));
    }
}
