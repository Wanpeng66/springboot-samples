package com.wp.service;

import com.wp.config.HdfsInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FilterFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

/**
 * @author: wp
 * @Title: HdfsService
 * @Description: TODO
 * @date 2020/6/18 20:22
 */
@Component
public class HdfsService {
    @Autowired
    private HdfsInfo hdfsInfo;

    public FileSystem getHdfs() throws Exception {
        FileSystem fileSystem = FileSystem.get( URI.create( hdfsInfo.getPath() ),
                new Configuration(  ),hdfsInfo.getUsername() );
        return fileSystem;
    }



}
