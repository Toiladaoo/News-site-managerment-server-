package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.NewsDTO;
import com.example.deliveryecommercebackend.model.Role;
import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.repository.NewsRepository;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<NewsDTO> getAllNewss() {
        try {
            var staffList = newsRepository.findNoneDeleteNews();
            List<NewsDTO> res = new ArrayList<NewsDTO>();
            for(News news : staffList){
                NewsDTO temp = new NewsDTO();
                temp.setData(news);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get news failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public NewsDTO getNewsById(String id) {
        try {
            News news = newsRepository.findNewsById(id);
            return new NewsDTO(news);
        } catch(Exception ex) {
            System.out.printf("Get news failed - Error: " + ex);
            return new NewsDTO();
        }
    }

    public HttpStatus createNews(NewsDTO news) {
        Role role = roleRepository.findById(news.getRole()).get();
        if(role == null) {
            role = roleRepository.findRoleByName(news.getFullName());
        }
        var checkValidAccount = newsRepository.findNewsByAccount(news.getAccount());
        var checkValidEmail = newsRepository.findNewssByEmail(news.getEmail());
        if(checkValidEmail != null) {
            return HttpStatus.FOUND;
        }
        if(checkValidAccount != null) {
            return HttpStatus.FOUND;
        }
        News newNews = new News();
        newNews.setCreated(Date.valueOf(LocalDate.now()));
        newNews.setUpdated(Date.valueOf(LocalDate.now()));
        newNews.setAccount(news.getAccount());
        newNews.setEmail(news.getEmail());
        newNews.setPassword(BCrypt.hashpw(news.getPassword(), BCrypt.gensalt(12)));
        newNews.setPhone(news.getPhone());
        newNews.setDes(news.getDes());
        newNews.setFullName(news.getFullName());
        newNews.setPurpose(news.getPurpose());
        newNews.setRole(role);

        try {
            News checkSave = newsRepository.save(newNews);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
        } catch(Exception ex) {
            System.out.printf("Create news failed - Error" + ex);
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public HttpStatus deleteNews(String account) {
        News news = newsRepository.findNewsById(account);
        news.set_delete(true);
        try {
            var checkUpdate = newsRepository.save(news);
            if(checkUpdate == null) {
                return HttpStatus.CONFLICT;
            }
            return HttpStatus.OK;
        } catch (Exception ex) {
            System.out.printf("Error from service", ex);
            return HttpStatus.BAD_REQUEST;
        }
    }

}
