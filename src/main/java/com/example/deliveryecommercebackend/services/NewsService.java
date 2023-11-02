package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.NewsCreateDTO;
import com.example.deliveryecommercebackend.DTO.NewsTypeDTO;
import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.model.NewsType;
import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.User;
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
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsTypeRepository newsTypeRepository;
    @Autowired
    private UserRepository userRepository;

    public List<NewsCreateDTO> getAllNewss() {
        try {
            var staffList = newsRepository.findNoneDeleteNews();
            List<NewsCreateDTO> res = new ArrayList<NewsCreateDTO>();
            for(News news : staffList){
                NewsCreateDTO temp = new NewsCreateDTO();
                temp.setData(news);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get news failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public NewsCreateDTO getNewsById(String id) {
        try {
            News news = newsRepository.findNewsById(id);
            return new NewsCreateDTO(news);
        } catch(Exception ex) {
            System.out.printf("Get news failed - Error: " + ex);
            return new NewsCreateDTO();
        }
    }

    public HttpStatus insertNews(NewsCreateDTO news) {
        System.out.println(news.getNews_type_id());
        NewsType newsType = newsTypeRepository.findById(news.getNews_type_id()).get();
        System.out.println(newsType);
        if(newsType == null) {
            return HttpStatus.BAD_REQUEST;
        }


        User user = userRepository.findNoneDeleteUserById(news.getUser_id());
        System.out.println(user);
        if(user == null) {
            return HttpStatus.BAD_REQUEST;
        }

        News newNews = new News();
        newNews.setDataCreate(news, newsType, user);

        try {
            News checkSave = newsRepository.save(newNews);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
            return HttpStatus.BAD_REQUEST;
        } catch(Exception ex) {
            System.out.printf("Create news failed - Error" + ex);
            return HttpStatus.BAD_GATEWAY;
        }
    }


    public HttpStatus updateNews(NewsCreateDTO newsDTO) {
        News news = newsRepository.findNewsById(newsDTO.getId());

        news.setDataUpdated(newsDTO);

        try {
            var checkSave = newsRepository.save(news);
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
