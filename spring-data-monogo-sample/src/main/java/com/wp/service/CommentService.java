package com.wp.service;

import com.mongodb.client.result.UpdateResult;
import com.wp.dao.CommentRepo;
import com.wp.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author: wp
 * @Title: CommentService
 * @Description: TODO
 * @date 2020/6/3 20:41
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveComment( Comment comment ){
        commentRepo.save( comment );
    }

    public void updateComment(Comment comment){
        commentRepo.save( comment );
    }

    public void deleteCommentById(String id){
        commentRepo.deleteById( id );
    }

    public List<Comment> findCommentList(){
        return commentRepo.findAll();
    }

    public Comment findCommentById(String id){
        return commentRepo.findById( id ).get();
    }

    public Page<Comment> findCommentByArticleId( String id, int pageNum, int pageSize){
        return commentRepo.findByArticleId( id, PageRequest.of( pageNum-1,pageSize ) );
    }

    public void incrCommentLikeNum(String id){
        Query query = Query.query( Criteria.where( "articleId" ).is( id ) );
        Update update = new Update();
        update.inc( "lineNum" );
        UpdateResult result = mongoTemplate.updateFirst( query, update, Comment.class );
    }
}
