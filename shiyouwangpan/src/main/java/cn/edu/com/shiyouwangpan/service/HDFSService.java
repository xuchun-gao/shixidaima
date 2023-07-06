package cn.edu.com.shiyouwangpan.service;

import cn.edu.com.shiyouwangpan.entity.FileBean;
import cn.edu.com.shiyouwangpan.entity.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;


public interface HDFSService {
    /**
     * 新增目录
     */
    void mkdirFile(String fileName);


    /**
     * 删除
     */


    /**
     * 查询
     */
    ArrayList<FileBean> findFileList();
   //ArrayList<FileBean> sonFileList(String filePath);


    ArrayList<FileBean> searchFiles(String filePath);

    ResultModel<ArrayList<FileBean>> addHadoopFile(String filePath,String filename);
    ResultModel<ArrayList<FileBean>> deleteFiles(String filePath);




    boolean alterFiles(String filePath, String newName);

    void uploadFile(MultipartFile file, String filepath);

    ArrayList<FileBean> search(String inputValue);

    ArrayList<FileBean> findmp3();

    ArrayList<FileBean> findpic();

    ArrayList<FileBean> finddoc();

    ArrayList<FileBean> findmp4();

    ArrayList<FileBean> findStore();
}
