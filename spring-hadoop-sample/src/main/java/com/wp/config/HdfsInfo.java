package com.wp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: wp
 * @Title: HdfsInfo
 * @Description: TODO
 * @date 2020/6/18 20:18
 */
@Component
@ConfigurationProperties(prefix="hadoop.hdfs")
public class HdfsInfo {

    private String path;
    private String username;

    public String getPath() {
        return path;
    }

    public void setPath( String path ) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }
}
