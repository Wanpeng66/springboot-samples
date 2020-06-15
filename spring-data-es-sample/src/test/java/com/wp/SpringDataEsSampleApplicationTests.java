package com.wp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;


@SpringBootTest
class SpringDataEsSampleApplicationTests {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    void testCreateIndex(){
        boolean flag = restTemplate.createIndex( "index_springboot" );
        System.out.println( flag );
    }

    @Test
    void  testIndexExist(){
        IndexOperations indexOps = restTemplate.indexOps( IndexCoordinates.of( "index_springboot" ) );
        boolean exists = indexOps.exists();
        indexOps.delete();
    }

    @Test
    void crud(){


    }

}
