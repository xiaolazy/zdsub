package com.zdsub.controller.index;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.entername.increase.EnternameInc;
import com.zdsub.entity.policyfile.Policyfile;
import com.zdsub.entity.recruitment.Adver;
import com.zdsub.entity.supportTibet.DemandManage;
import com.zdsub.entity.university.School;
import com.zdsub.entity.work.Process;
import com.zdsub.entity.work.WorkDynamic;
import com.zdsub.service.entername.EnternameService;
import com.zdsub.service.policyfile.PolicyfileService;
import com.zdsub.service.recruitment.AdverService;
import com.zdsub.service.supportTibet.DemandManageService;
import com.zdsub.service.university.SchollService;
import com.zdsub.service.work.ProcessService;
import com.zdsub.service.work.WorkDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Autowired
    private PolicyfileService policyfileService;

    @Autowired
    private DemandManageService demandManageService;

    @Resource
    private EnternameService enternameService;

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
    @GetMapping("toWorkDynamicShow")
    public ResponseBean workToAdverShow(String id) {
        return ResponseBean.SUCCESS(workDynamicService.toAdverShow(id));
    }

    /**
     * 门户援藏政策的分页查询
     *
     * @param policyfilePage
     * @return
     */
    @PostMapping("policy")
    public ResponseBean policyPage(@RequestBody Page<Policyfile> policyfilePage) {
        return ResponseBean.SUCCESS(policyfileService.getPage(policyfilePage));
    }

    /**
     * 查看援藏政策
     *
     * @param id
     * @return
     */
    @GetMapping("toPolicyShow")
    public ResponseBean policyToAdverShow(String id) {
        return ResponseBean.SUCCESS(policyfileService.findById(id));
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
    @GetMapping("toProcessShow")
    public ResponseBean processToAdverShow(String id) {
        return ResponseBean.SUCCESS(processService.get(id));
    }

    /**
     * 门户人员招聘的分页查询
     *
     * @param adverPage
     * @return
     */
    @PostMapping("adver")
    public ResponseBean adverPage(@RequestBody Page<Adver> adverPage) {
        return ResponseBean.SUCCESS(adverService.page(adverPage));
    }

    /**
     * 查看人员招聘
     *
     * @param id
     * @return
     */
    @GetMapping("toAdverShow")
    public ResponseBean adverToAdverShow(String id) {
        return ResponseBean.SUCCESS(adverService.updAdverShow(id));
    }


    /**
     * 门户需求的分页查询
     *
     * @param page
     * @return
     */
    @PostMapping("need")
    public ResponseBean needPage(@RequestBody Page<DemandManage> page) {
        return ResponseBean.SUCCESS(demandManageService.page(page));
    }

    /**
     * 查看需求
     *
     * @param id
     * @return
     */
    @GetMapping("toNeedShow")
    public ResponseBean toNeedShow(String id) {
        return ResponseBean.SUCCESS(demandManageService.updAdverShow(id));
    }

    /**
     * 新增前台报名的人员
     *
     * @param enternameInc
     * @return
     */

    @PostMapping("addEnter")
    public ResponseBean enterNameAdd(@RequestBody EnternameInc enternameInc) {
        try {
            enternameService.save(enternameInc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.FAILD("新增错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("新增成功！");
    }

    /**
     * 门户学校人员的分页查询
     *
     * @param schoolPage
     * @return
     */
    @PostMapping("scholl")
    public ResponseBean schollPage(@RequestBody Page<School> schoolPage) {
        return ResponseBean.SUCCESS(schollService.page(schoolPage));
    }


    /**
     * 查看学校人员
     *
     * @param id
     * @return
     */
    @GetMapping("toSchollShow")
    public ResponseBean schollToAdverShow(String id) {
        return ResponseBean.SUCCESS(schollService.get(id));
    }
}
