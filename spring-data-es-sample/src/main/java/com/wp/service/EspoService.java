package com.wp.service;

import com.wp.dao.EspoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: wp
 * @Title: EspoService
 * @Description: TODO
 * @date 2020/6/16 17:02
 */
@Service
public class EspoService {

    @Autowired
    private EspoRepo espoRepo;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;





}
