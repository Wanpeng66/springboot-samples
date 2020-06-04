package com.wp.dao;

import com.wp.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: wp
 * @Title: CommentRepo
 * @Description: TODO
 * @date 2020/6/3 20:41
 */
@Repository
public interface CommentRepo extends MongoRepository<Comment,String> {

    Page<Comment> findByArticleId( String id, Pageable pageable );
}
