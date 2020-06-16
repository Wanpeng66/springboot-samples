package com.wp.dao;

import com.wp.model.ESPO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: wp
 * @Title: EspoRepo
 * @Description: TODO
 * @date 2020/6/16 17:00
 */
@Repository
public interface EspoRepo extends ElasticsearchRepository<ESPO,String> {

}
