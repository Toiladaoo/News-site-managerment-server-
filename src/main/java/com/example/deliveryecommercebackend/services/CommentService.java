package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.CommentDTO;
import com.example.deliveryecommercebackend.model.Comment;
import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.NewsType;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.CommentRepository;
import com.example.deliveryecommercebackend.repository.NewsRepository;
import com.example.deliveryecommercebackend.repository.NewsTypeRepository;
import com.example.deliveryecommercebackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CommentDTO> getAllComment() {
        try {
            List<Comment> comments = commentRepository.findNoneDeleteComment();
            List<CommentDTO> res = new ArrayList<CommentDTO>();
            for(Comment comment : comments){
                CommentDTO temp = new CommentDTO();
                temp.setData(comment);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get comment failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public CommentDTO getCommentById(String id) {
        try {
            Comment comment = commentRepository.findCommentById(id);
            return new CommentDTO(comment);
        } catch(Exception ex) {
            System.out.printf("Get comment failed - Error: " + ex);
            return new CommentDTO();
        }
    }

    public HttpStatus createComment(CommentDTO commentDTO) {
        News news = newsRepository.findNewsById(commentDTO.getNews_id());
        if(news == null) {
            return HttpStatus.BAD_REQUEST;
        }

        User user = userRepository.findNoneDeleteUserById(commentDTO.getUser_id());
        if(user == null) {
            return HttpStatus.BAD_REQUEST;
        }

        Comment newComment = new Comment();
        newComment.setDataCreate(commentDTO, news, user);

        try {
            Comment checkSave = commentRepository.save(newComment);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
            return HttpStatus.BAD_REQUEST;
        } catch(Exception ex) {
            System.out.printf("Create comment failed - Error" + ex);
            return HttpStatus.BAD_GATEWAY;
        }
    }


    public HttpStatus updateComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findCommentById(commentDTO.getId());

        comment.setDataUpdated(commentDTO);

        try {
            var checkSave = commentRepository.save(comment);
            if (checkSave != null)
                return HttpStatus.OK;
            return HttpStatus.BAD_REQUEST;
        }catch (Exception ex) {
            System.out.printf("Error from service" + ex);
            return HttpStatus.BAD_GATEWAY;
        }
    }

    public HttpStatus deleteNews(String account) {
        News news = newsRepository.findNewsById(account);
        news.set_delete(true);
        try {
            var checkUpdate = newsRepository.save(news);
            if(checkUpdate == null) {
                return HttpStatus.BAD_REQUEST;
            }
            return HttpStatus.OK;
        } catch (Exception ex) {
            System.out.printf("Error from service", ex);
            return HttpStatus.BAD_GATEWAY;
        }
    }

}
