package com.wp;

import com.wp.model.ESPO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.UUID;


@SpringBootTest
class SpringDataEsSampleApplicationTests {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Autowired
    private ElasticsearchOperations operations;

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
        //ESPO po = new ESPO( UUID.randomUUID().toString(),"wp",25 );
        //ESPO save = restTemplate.save( po );


        SearchHits<ESPO> hits = restTemplate.search( Query.findAll(), ESPO.class );
        System.out.println( "hits = " + hits );

        //String delete = restTemplate.delete( po );
        //System.out.println( delete );


        Query query = new NativeSearchQueryBuilder().withQuery( QueryBuilders.termQuery( "name", "wp" ) ).build();
        SearchHits<ESPO> search = restTemplate.search( query, ESPO.class );
        System.out.println( "search = " + search );


    }

}
