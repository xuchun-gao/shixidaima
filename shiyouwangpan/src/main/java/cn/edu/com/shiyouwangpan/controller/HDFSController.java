package cn.edu.com.shiyouwangpan.controller;

import cn.edu.com.shiyouwangpan.entity.FileBean;
import cn.edu.com.shiyouwangpan.entity.ResultModel;
import cn.edu.com.shiyouwangpan.entity.User;
import cn.edu.com.shiyouwangpan.service.HDFSService;
import cn.edu.com.shiyouwangpan.utils.HDFSUtils;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@RestController
public class HDFSController {

    @Autowired
    HDFSService hdfsService;

    /**
     * 获取到所有的目录
     */
    @RequestMapping("/lists")
    @ResponseBody
    public ResultModel<ArrayList<FileBean>> getfindAllUsers() {

        ArrayList<FileBean> fileList = hdfsService.findFileList();
        ResultModel<ArrayList<FileBean>> result = null;
        //判断
        if (fileList != null) {
            result = ResultModel.success("获取数据成功", fileList);
        } else {
            result = ResultModel.error("获取数据失败");
        }
        //System.out.println(fileList);
        return result;
    }

    /**
     * 查找子目录
     * @param filePath
     * @return
     */
    @GetMapping("/searchFiles")
    public ResultModel<ArrayList<FileBean>> searchFiles(@RequestParam String filePath) {
        ResultModel<ArrayList<FileBean>> result = null;
        try {
            ArrayList<FileBean> fileList = hdfsService.searchFiles(filePath);
            if (fileList != null) {
                result = ResultModel.success("获取数据成功", fileList);
            } else {
                result = ResultModel.error("获取数据失败");
            }
            System.out.println(fileList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建目录
     * @param filepath
     * @param filename
     * @return
     */
    @GetMapping("/addFile")
    public ResultModel<ArrayList<FileBean>> createFile(@RequestParam String filepath,
                                                       @RequestParam String filename)
    {
        System.out.println(filename);
        System.out.println(filepath);
        ResultModel<ArrayList<FileBean>> result = null;
        ResultModel<ArrayList<FileBean>> addHadoopFile = hdfsService.addHadoopFile(filepath,filename);

        if (addHadoopFile!=null) {
            result = ResultModel.success("创建成功");
        } else {
            result = ResultModel.error("获取数据失败");
        }
        // 返回响应结果
        return result;
    }

    /**
     * 修改文件名称
     * @param filePath
     * @param fileName
     * @param fileName1
     * @return
     */
    @GetMapping("/alterFiles")
    public boolean alterFiles(@RequestParam String filePath,
                              @RequestParam String fileName,
                              @RequestParam String fileName1) {
        boolean result = false;
        try {
            String oldName=filePath+"/"+fileName;
            String newName=filePath+"/"+fileName1;
            result = hdfsService.alterFiles(oldName,newName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除文件
     * @param deleteFilepath
     * @return
     */
    @GetMapping("/deleteFiles")
    public ResultModel<ArrayList<FileBean>> deleteFiles(@RequestParam String deleteFilepath) {
        ResultModel<ArrayList<FileBean>> result = null;
        ResultModel<ArrayList<FileBean>> addHadoopFile = hdfsService.deleteFiles(deleteFilepath);
        if (addHadoopFile!=null) {
            result = ResultModel.success("创建成功");
        } else {
            result = ResultModel.error("获取数据失败");
        }
        // 返回响应结果
        return result;
    }



    /**
     * 上传文件
     * @param file
     * @param filepath
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public void upLoad(MultipartFile file, @RequestParam String filepath ){
        hdfsService.uploadFile(file,filepath);
    }
    /**
     * 下载数据1
     */
    @RequestMapping("/download")
    @ResponseBody
    public String  getload(@RequestParam String filePath){
        System.out.println(filePath);
        String sub= filePath.replace("hdfs://47.99.169.103:9000","");
        String line="http://47.99.169.103:50070/webhdfs/v1"+sub+"?op=OPEN";
        return line;
    }

    //搜索
    @GetMapping("/search")
    public ResultModel<ArrayList<FileBean>> search(@RequestParam String inputValue) {
        ResultModel<ArrayList<FileBean>> result = null;
        try {
            ArrayList<FileBean> fileList = hdfsService.search(inputValue);
            if (fileList != null) {
                result = ResultModel.success("获取数据成功", fileList);
            } else {
                result = ResultModel.error("获取数据失败");
            }
            //System.out.println(fileList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查找MP3类型文件
     * @return
     */
    @RequestMapping("/mp3lists")
    public ResultModel<ArrayList<FileBean>> getfindAllmp3() {
        ArrayList<FileBean> fileList = hdfsService.findmp3();
        ResultModel<ArrayList<FileBean>> result = null;
        //判断
        if (fileList != null) {
            result = ResultModel.success("获取数据成功", fileList);
        } else {
            result = ResultModel.error("获取数据失败");
        }

        return result;
    }
    @RequestMapping("/stores")
    public ResultModel<ArrayList<FileBean>> getStore() {
        ArrayList<FileBean> fileList = hdfsService.findStore();
        ResultModel<ArrayList<FileBean>> result = null;
        //判断
        if (fileList != null) {
            result = ResultModel.success("获取数据成功", fileList);
        } else {
            result = ResultModel.error("获取数据失败");
        }

        return result;
    }
    @RequestMapping("/piclists")
    @ResponseBody
    public ResultModel<ArrayList<FileBean>> getfindAllpic() {
        //System.out.println("dksgvdlivhdsoiogdi");
        //
        ArrayList<FileBean> fileList = hdfsService.findpic();
        ResultModel<ArrayList<FileBean>> result = null;
        //判断
        if (fileList != null) {
            result = ResultModel.success("获取数据成功", fileList);
        } else {
            result = ResultModel.error("获取数据失败");
        }
        //System.out.println(fileList);
        return result;
    }

    /**
     * 文档分类查询
     * @return
     */
    @RequestMapping("/doclists")
    @ResponseBody
    public ResultModel<ArrayList<FileBean>> getfindAlldoc() {
        //System.out.println("dksgvdlivhdsoiogdi");
        //
        ArrayList<FileBean> fileList = hdfsService.finddoc();
        ResultModel<ArrayList<FileBean>> result = null;
        //判断
        if (fileList != null) {
            result = ResultModel.success("获取数据成功", fileList);
        } else {
            result = ResultModel.error("获取数据失败");
        }
        //System.out.println(fileList);
        return result;
    }

    @RequestMapping("/mp4lists")
    @ResponseBody
    public ResultModel<ArrayList<FileBean>> getfindAllmp4() {

        ArrayList<FileBean> fileList = hdfsService.findmp4();
        ResultModel<ArrayList<FileBean>> result = null;
        //判断
        if (fileList != null) {
            result = ResultModel.success("获取数据成功", fileList);
        } else {
            result = ResultModel.error("获取数据失败");
        }
        //System.out.println(fileList);
        return result;
    }

}
