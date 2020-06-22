package com.wp.hdfs;

import com.wp.service.HdfsService;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author: wp
 * @Title: HdfsApiTest
 * @Description:
 * @date 2020/6/18 20:31
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HdfsApiTest {

    @Autowired
    private HdfsService hdfsService;

    private FileSystem fileSystem;

    @BeforeAll
    public void before() throws Exception {
        fileSystem = hdfsService.getHdfs();
    }

    @Test
    public void createDir() throws Exception {
        FSDataOutputStream fsDataOutputStream = fileSystem.create( new Path( "/wp" ) );
        fsDataOutputStream.close();
    }

    @Test
    public void existDir() throws IOException {
        boolean exists = fileSystem.exists( new Path( "/wp" ) );
        if(exists){
            System.out.println("/wp is existed...");
        }else{
            System.out.println("/wp is not exist...");
        }
    }

    @AfterAll
    public void after() throws IOException {
        fileSystem.close();
    }

}
