package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.NewsCreateDTO;
import com.example.deliveryecommercebackend.DTO.NewsDisplayDTO;
import com.example.deliveryecommercebackend.DTO.NewsTypeDTO;
import com.example.deliveryecommercebackend.DTO.UserDTO;
import com.example.deliveryecommercebackend.model.Action;
import com.example.deliveryecommercebackend.model.NewsType;
import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.User;
import com.example.deliveryecommercebackend.repository.ActionRepository;
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
    @Autowired
    private ActionRepository actionRepository;

    public List<NewsDisplayDTO> getAllNewss() {
        try {
            var newsList = newsRepository.findNoneDeleteNews();
            List<NewsDisplayDTO> res = new ArrayList<NewsDisplayDTO>();
            for(News news : newsList){
                NewsDisplayDTO temp = new NewsDisplayDTO();
                temp.setData(news);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get news failed - Error: " + ex);
            return Collections.emptyList();
        }
    }
    public List<NewsDisplayDTO> getNewssByActionCode(String actionCode) {
        Action action = actionRepository.findByCode(actionCode);

        if(action == null) {
            return Collections.emptyList();
        }

        try {
            List<News> newsList = newsRepository.findNoneDeleteNewsByAction(action);
            List<NewsDisplayDTO> res = new ArrayList<NewsDisplayDTO>();
            for(News news : newsList){
                NewsDisplayDTO temp = new NewsDisplayDTO();
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
        System.out.println(news.getNews_type_code());
        NewsType newsType = newsTypeRepository.findByCode(news.getNews_type_code());
        if(newsType == null) {
            return HttpStatus.BAD_REQUEST;
        }


        User user = userRepository.findNoneDeleteUserById(news.getUser_id());
        if(user == null) {
            return HttpStatus.BAD_REQUEST;
        }

        Action action = actionRepository.findById(0).get();
        if(action == null) {
            return HttpStatus.BAD_REQUEST;
        }

        News newNews = new News();
        newNews.setDataCreate(news, newsType, user);
        newNews.setAction(action);

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

    public HttpStatus setAction(String newsId, String actionCode) {
        Action action = actionRepository.findByCode(actionCode);
        if(action == null) {
            return HttpStatus.BAD_REQUEST;
        }

        News news = newsRepository.findById(newsId).get();
        if(news == null) {
            return HttpStatus.BAD_REQUEST;
        }

        news.setAction(action);

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
