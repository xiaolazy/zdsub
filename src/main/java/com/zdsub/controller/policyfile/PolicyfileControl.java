package com.zdsub.controller.policyfile;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.component.annotion.ValidLog;
import com.zdsub.component.hibernate.Page;
import com.zdsub.entity.policyfile.Policyfile;
import com.zdsub.service.policyfile.PolicyfileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;
import static com.zdsub.common.constant.Common.*;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-12 13:31
 **/
@RestController
@RequestMapping("/policy/")
public class PolicyfileControl {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private PolicyfileService policyfileService;
    /*@description：分页查询
     *@Date：2019/9/12 13:33
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("getPage")
    public ResponseBean getPage(@RequestBody Page<Policyfile> page){
        return ResponseBean.PAGESUCCESS(policyfileService.getPage(page));
    }
    /*@description：据ID查询
     *@Date：2019/9/12 13:43
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @GetMapping("findById")
    public ResponseBean findById(String id){
        return ResponseBean.SUCCESS(policyfileService.findById(id));
    }
    /*@description：新增
     *@Date：2019/9/12 14:39
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("add")
    @ValidLog
    public ResponseBean add(@Valid @RequestBody Policyfile policyfile, BindingResult b) {
        try{
            policyfileService.add(policyfile);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("新增错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("新增成功！");
    }
    /*@description：修改
     *@Date：2019/9/12 14:39
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @PostMapping("update")
    @ValidLog
    public ResponseBean update(@Valid @RequestBody Policyfile policyfile, BindingResult b) {
        try{
            policyfileService.update(policyfile);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.FAILD("修改错误！请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("修改成功！");
    }
    @GetMapping("delById")
    public ResponseBean delById(String id){
        try{
            policyfileService.delById(id);
        }catch (Exception e){
            return ResponseBean.FAILD("删除失败，请稍候重试或联系管理员");
        }
        return ResponseBean.SUCCESS("修改成功！");
    }
    /*@description：上传附件 可公用，请求路径需为(- 代指随意,?指在磁盘创建的路径也就是请求的子包路径):
     *@Date：2019/9/12 13:49
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @RequestMapping("upload")
    public ResponseBean upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest req){
        String prefix = UUID.randomUUID().toString().split("-")[0] + new Date().getTime();
        String filename;
        String savefilepath;
        try {
            filename = prefix +"_"+ file.getOriginalFilename();
            savefilepath = req.getSession().getServletContext().getRealPath("/") + UPLOAD_PATH  + filename;
            System.out.println(savefilepath+"=========="+filename);
            File newfile = new File(savefilepath);
            if(!newfile.getParentFile().exists()){
                newfile.getParentFile().mkdirs();
            }
            file.transferTo(newfile);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException e 上传文件异常：编码异常");
            return ResponseBean.FAILD("上传文件异常！！");
        } catch (IllegalStateException e) {
            logger.error("IllegalStateException e 上传文件异常：无效状态异常或在不合理的时间唤醒上些方法如：flush");
            return ResponseBean.FAILD("上传文件异常！！");
        } catch (IOException e) {
            logger.error("IOException e 上传文件异常：读取文件异常");
            return ResponseBean.FAILD("上传文件异常！！");
        }
        return ResponseBean.SUCCESS(savefilepath);
    }
    /*@description：文件下载
     *@Date：2019/9/12 13:50
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @RequestMapping("download")
    public ResponseBean download(String path, HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println("path-------"+path);
        if(path == "" || path == null)
            return ResponseBean.FAILD("文件名不存在！！");
        String realPath =req.getSession().getServletContext().getRealPath("/") + UPLOAD_PATH +path;
        File file = new File(req.getSession().getServletContext().getRealPath("/") + UPLOAD_PATH, path);
        if (!file.exists()) {
            return ResponseBean.FAILD("文件不存在！！");
        }
        path = URLEncoder.encode(path, "UTF-8");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + path);
        res.setContentLength((int) file.length());
        byte[] b = FileUtils.readFileToByteArray(file);
        ServletOutputStream out = res.getOutputStream();
        out.write(b);
        out.flush();
        out.close();
        return ResponseBean.SUCCESS("下载成功！！");
    }
    /*@description：删除上传的文件
     *@Date：2019/9/12 13:53
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @RequestMapping("uploadRemove")
    public ResponseBean uploadRemove(@RequestParam String relaPath , HttpServletRequest req){
        String filePath = req.getSession().getServletContext().getRealPath("/")+UPLOAD_PATH+relaPath;
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
            return ResponseBean.SUCCESS("删除成功");
        }
        return ResponseBean.FAILD("删除失败，文件不存在");
    }
}
