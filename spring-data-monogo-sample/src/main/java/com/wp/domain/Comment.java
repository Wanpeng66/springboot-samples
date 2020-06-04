package com.wp.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: wp
 * @Title: Comment
 * @Description: TODO
 * @date 2020/6/3 20:29
 */
@Data
@Document(collection = "comment")
public class Comment implements Serializable {

    @Id
    private String id;
    @Field
    private String content;
    @Field
    private Date publishTime;
    @Field
    @Indexed
    private String userId;
    @Field
    @Indexed
    private String nickName;
    @Field
    private LocalDateTime createTime;
    @Field
    private Integer likeNum;
    @Field
    private Integer replyNum;
    @Field
    private String state;
    @Field
    private String parentId;
    @Field
    private String articleId;



}
