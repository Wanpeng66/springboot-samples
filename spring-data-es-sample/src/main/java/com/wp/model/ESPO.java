package com.wp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author: wp
 * @Title: ESPO
 * @Description: TODO
 * @date 2020/6/16 9:31
 */
@Document( indexName = "springboot_es_index")
@Data
@AllArgsConstructor
public class ESPO {
    @Id
    private String id;
    private String name;
    private int age;


}
