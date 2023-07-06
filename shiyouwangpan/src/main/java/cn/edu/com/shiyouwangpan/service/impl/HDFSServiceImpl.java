package cn.edu.com.shiyouwangpan.service.impl;
import cn.edu.com.shiyouwangpan.entity.FileBean;
import cn.edu.com.shiyouwangpan.entity.ResultModel;
import cn.edu.com.shiyouwangpan.service.HDFSService;
import cn.edu.com.shiyouwangpan.utils.HDFSUtils;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
@Service
public class HDFSServiceImpl implements HDFSService {

    @Override
        public void mkdirFile(String fileName) {
        //获取连接对象
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        try {
            boolean mkdirs = fileSystem.mkdirs(new Path(fileName));
            if (mkdirs) {
                System.out.println("创建成功！");
            }else {
                System.out.println("创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<FileBean> getFileBeans(FileStatus[] fileStatuses) {
            ArrayList<FileBean> fileBeans = new ArrayList<>();
            for (FileStatus fs : fileStatuses) {
            FileBean fileBean = new FileBean();
            // 获得文件的上传时间
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = dateFormat.format(fs.getModificationTime());
            // 创建实体类
            fileBean.setFileName(fs.getPath().getName());
            fileBean.setFilePath(fs.getPath().toString());
            fileBean.setFileSize(fs.getLen() / 1024 + " kb");
            fileBean.setFileTime(format);
            fileBean.setIsDirectory(fs.isDirectory() ? 1 : 0);
            fileBeans.add(fileBean);
        }

        return fileBeans;
    }
    @Override
    public ArrayList<FileBean> findFileList() {
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        Properties properties = HDFSUtils.getProperties();
        ArrayList<FileBean> fileBeans1 = new ArrayList<>();
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(new Path(properties.getProperty("spring.data.hdfs.url")));
            //循环遍历,添加代码
            fileBeans1=getFileBeans(fileStatuses);
            } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return fileBeans1;
    }

    @Override
    public ArrayList<FileBean> searchFiles(String filePath) {
        ArrayList<FileBean> fileBeans1 = new ArrayList<>();
        FileSystem fileSystem = HDFSUtils.getFileSystem();

        Path parentPath = new Path(filePath);
        // 获取父目录下的所有文件和子目录
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(parentPath);
            //循环遍历
            fileBeans1=getFileBeans(fileStatuses);
        }
         catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fileBeans1;
    }

    /**
     * 搜索功能实现
     * @param inputValue
     * @return
     */
    @Override
    public ArrayList<FileBean> search(String inputValue) {
        ArrayList<FileBean> fileBean1 = new ArrayList<>();
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        ArrayList<FileBean> fileBeans = new ArrayList<>();
        // 根据传入的父目录路径构建Path对象
        Path parentPath = new Path("hdfs://47.99.169.103:9000/");
        // 获取父目录下的所有文件和子目录
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(parentPath);
            //循环遍历
            fileBean1=getFileBeans(fileStatuses);
            for (FileBean fileBean : fileBean1) {
                if(fileBean.getFilePath().contains(inputValue)){
                    fileBeans.add(fileBean);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileBeans;
    }

    @Override
    public ArrayList<FileBean> findmp3() {
        ArrayList<FileBean> fileBean1 = new ArrayList<>();
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        ArrayList<FileBean> mp3Files = new ArrayList<>();
        // 根据传入的父目录路径构建Path对象
        Path parentPath = new Path("hdfs://47.99.169.103:9000/");
        // 获取父目录下的所有文件和子目录
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(parentPath);
            //循环遍历
            fileBean1=getFileBeans(fileStatuses);
            for (FileBean fileBean : fileBean1) {
                if (fileBean.getFileName().endsWith(".mp3")) {
                    mp3Files.add(fileBean);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return mp3Files;
    }

    @Override
    public ArrayList<FileBean> findpic() {
        ArrayList<FileBean> fileBean1 = new ArrayList<>();
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        ArrayList<FileBean> JpgFiles = new ArrayList<>();
        // 根据传入的父目录路径构建Path对象
        Path parentPath = new Path("hdfs://47.99.169.103:9000/");
        // 获取父目录下的所有文件和子目录
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(parentPath);
            //循环遍历
            fileBean1=getFileBeans(fileStatuses);
            for (FileBean fileBean : fileBean1) {
                if (fileBean.getFileName().endsWith(".jpg")) {
                    JpgFiles.add(fileBean);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return JpgFiles;
    }

    @Override
    public ArrayList<FileBean> finddoc() {
        ArrayList<FileBean> fileBean1 = new ArrayList<>();
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        ArrayList<FileBean> DocxFiles = new ArrayList<>();
        // 根据传入的父目录路径构建Path对象
        Path parentPath = new Path("hdfs://47.99.169.103:9000/");
        // 获取父目录下的所有文件和子目录
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(parentPath);
            //循环遍历
            fileBean1=getFileBeans(fileStatuses);
            for (FileBean fileBean : fileBean1) {
                if (fileBean.getFileName().endsWith(".docx")) {
                    DocxFiles.add(fileBean);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DocxFiles;
    }

    @Override
    public ArrayList<FileBean> findmp4() {
        ArrayList<FileBean> fileBean1 = new ArrayList<>();
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        ArrayList<FileBean> Mp4Files = new ArrayList<>();
        // 根据传入的父目录路径构建Path对象
        Path parentPath = new Path("hdfs://47.99.169.103:9000/");
        // 获取父目录下的所有文件和子目录
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(parentPath);
            //循环遍历
            fileBean1=getFileBeans(fileStatuses);
            for (FileBean fileBean : fileBean1) {
                if (fileBean.getFileName().endsWith(".mp4")) {
                    Mp4Files.add(fileBean);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Mp4Files;
    }

    @Override
    public ArrayList<FileBean> findStore() {
        ArrayList<FileBean> fileBean1 = new ArrayList<>();
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        ArrayList<FileBean> Mp4Files = new ArrayList<>();
        // 根据传入的父目录路径构建Path对象
        Path parentPath = new Path("hdfs://47.99.169.103:9000/");
        // 获取父目录下的所有文件和子目录
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(parentPath);
            //循环遍历
            fileBean1=getFileBeans(fileStatuses);
            for (FileBean fileBean : fileBean1) {
                if (fileBean.getFileName().endsWith(".mp4")) {
                    Mp4Files.add(fileBean);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Mp4Files;
    }

    /**
     * 增加文件
     * @param filePath
     * @param fileName
     * @return
     */
    @Override
    public ResultModel<ArrayList<FileBean>> addHadoopFile(String filePath,String fileName)
    {
        ResultModel<ArrayList<FileBean>> resultModel=null;
        try {
            FileSystem fileSystem = HDFSUtils.getFileSystem();
            Properties properties = HDFSUtils.getProperties();
            boolean mkdirs = fileSystem.mkdirs(new Path(filePath+"/"+fileName));
            if (mkdirs) {
                System.out.println("创建成功！");
                resultModel = new ResultModel<>();
                resultModel.setCode(200);
                return resultModel;
            }else {
                System.out.println("创建失败");
            }
        }
        catch (IOException e) {
        // 异常处理
        e.printStackTrace();
         resultModel = new ResultModel<>();
        resultModel.setCode(500);
        return resultModel;
    }
        return resultModel;
}

    /**
     * 删除文件
     * @param filepath
     * @return
     */
    @Override
    public ResultModel<ArrayList<FileBean>> deleteFiles(String filepath) {
        ResultModel<ArrayList<FileBean>> resultModel=null;
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        try {
            boolean delete = fileSystem.delete(new Path(filepath), true);
            if (delete) {
                System.out.println("删除成功！");
                resultModel = new ResultModel<>();
                resultModel.setCode(200);
                return resultModel;
            }else {
                System.out.println("删除失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultModel = new ResultModel<>();
            resultModel.setCode(500);
            return resultModel;
        }
        return resultModel;
    }



    @Override
    public boolean alterFiles(String oldName, String newName) {
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        boolean rename = false;
        try {
             rename = fileSystem.rename(new Path(oldName), new Path(newName));
             return rename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rename;
    }

    @Override
    public void uploadFile(MultipartFile file, String filepath) {
        FileSystem fileSystem = HDFSUtils.getFileSystem();
        try {
            FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(filepath+ "/"+file.getOriginalFilename()));
            IOUtils.copyBytes(file.getInputStream(),fsDataOutputStream, HDFSUtils.getConfiguration(),true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}



