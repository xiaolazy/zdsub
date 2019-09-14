package com.zdsub.controller.policyfile;

import com.zdsub.common.ResultBean.ResponseBean;
import com.zdsub.service.university.SchollService;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import static com.zdsub.common.constant.Common.UPLOAD_PATH;

/**
 * @program: zdsub
 * @description:
 * @author: lyy
 * @generate: 2019-09-14 16:09
 **/
@RestController
@RequestMapping("/home/")
public class DownloadControl {
    @Resource
    private SchollService schollService;
    /*@description：文件下载
     *@Date：2019/9/12 13:50
     *@Param：
     *@Return：
     *@Author： lyy
     */
    @RequestMapping("download")
    public ResponseBean download(String path, HttpServletRequest req, HttpServletResponse res) throws IOException {
        if(path == "" || path == null)
            return ResponseBean.FAILD("文件名不存在！！");

        String oldPath =req.getSession().getServletContext().getRealPath("/") + UPLOAD_PATH;
        String realPath = oldPath + path;
        File file = new File(oldPath,path);
        if (!file.exists()) {
            return ResponseBean.FAILD("文件不存在！！");
        }
        path = URLEncoder.encode(path, "UTF-8");
        res.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        res.setHeader("Content-Disposition", "attachment;filename=" + path);
        res.setContentLength((int) file.length());
        byte[] b = FileUtils.readFileToByteArray(file);
        ServletOutputStream out = res.getOutputStream();
        out.write(b);
        out.flush();
        out.close();
        return ResponseBean.SUCCESS("下载成功！！");
    }
    @GetMapping("listAllSch")
    public ResponseBean listAll() {
        return ResponseBean.SUCCESS(schollService.listAll());
    }
}
