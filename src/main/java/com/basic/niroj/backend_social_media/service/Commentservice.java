package com.basic.niroj.backend_social_media.service;

import com.basic.niroj.backend_social_media.Model.Comment;

public interface Commentservice {


    public Comment saveComment(Comment comment, Long postid,Long userid) throws Exception;

    public Comment likeComment(Long commentid, Long userid) throws Exception;


    public Comment getCommentById(Long commentid) throws Exception;

    public Boolean deleteComment(Long commentid, Long userid) throws Exception;


}
