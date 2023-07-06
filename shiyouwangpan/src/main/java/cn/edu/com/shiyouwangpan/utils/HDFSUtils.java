package cn.edu.com.shiyouwangpan.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;


public class HDFSUtils {
  static  FileSystem  fileSystem;
  static   Configuration conf;
   static Properties properties;
    // 获取HDFS连接对象
    static {

        //final URI uri hdfs:9000, 链接参数
    // final Configuration conf,  配置项参数
    //        final String user   用户名
     conf = new Configuration();
     conf.set("dfs.replication","1");
     //云服务器
     conf.set("dfs.client.use.datanode.hostname","true");
    conf.set("dfs.client.block.write.replace-datanode-on-failure.enable", "true");
    conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
    try {
            properties = new Properties();
           properties.load(HDFSUtils.class.getClassLoader().getResourceAsStream("application.properties"));
           fileSystem = FileSystem.get(URI.create(properties.getProperty("spring.data.hdfs.url")), conf, "root");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
 * 静态方法
 */
public static  FileSystem getFileSystem(){
    return fileSystem;
}


public static Configuration getConfiguration(){
    return conf;
}


public static Properties getProperties(){
    return properties;
}



}
