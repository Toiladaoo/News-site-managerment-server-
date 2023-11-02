package com.example.deliveryecommercebackend.services;

import com.example.deliveryecommercebackend.DTO.NewsTypeDTO;
import com.example.deliveryecommercebackend.DTO.NewsTypeDTO;
import com.example.deliveryecommercebackend.model.News;
import com.example.deliveryecommercebackend.model.NewsType;
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
public class NewsTypeService {

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    public List<NewsTypeDTO> getAllNewsType() {
        try {
            var staffList = newsTypeRepository.findNoneDeleteNewsType();
            List<NewsTypeDTO> res = new ArrayList<NewsTypeDTO>();
            for(NewsType news : staffList){
                NewsTypeDTO temp = new NewsTypeDTO();
                temp.setData(news);
                res.add(temp);
            }

            return res;
        } catch(Exception ex) {
            System.out.printf("Get news failed - Error: " + ex);
            return Collections.emptyList();
        }
    }

    public NewsTypeDTO getNewsTypeById(String id) {
        try {
            NewsType news = newsTypeRepository.findNewsTypeById(id);
            return new NewsTypeDTO(news);
        } catch(Exception ex) {
            System.out.printf("Get news failed - Error: " + ex);
            return new NewsTypeDTO();
        }
    }

    public HttpStatus createNewsType(NewsTypeDTO newsTypeDTO) {
        NewsType newNewsType = new NewsType();

        newNewsType.setDataCreate(newsTypeDTO);

        try {
            NewsType checkSave = newsTypeRepository.save(newNewsType);
            if(checkSave != null) {
                return HttpStatus.OK;
            }
            return HttpStatus.BAD_REQUEST;
        } catch(Exception ex) {
            System.out.printf("Create news failed - Error" + ex);
            return HttpStatus.BAD_GATEWAY;
        }
    }


    public HttpStatus updateNewsType(NewsTypeDTO newsDTO) {
        NewsType news = newsTypeRepository.findNewsTypeById(newsDTO.getId());
        news.setDataUpdate(newsDTO);
        try {
            var checkSave = newsTypeRepository.save(news);
            if (checkSave != null)
                return HttpStatus.OK;
            return HttpStatus.BAD_REQUEST;
        }catch (Exception ex) {
            System.out.printf("Error from service" + ex);
            return HttpStatus.BAD_GATEWAY;
        }
    }

    public HttpStatus deleteNewsType(String id) {
        NewsType news = newsTypeRepository.findNewsTypeById(id);
        news.set_delete(true);
        try {
            NewsType checkUpdate = newsTypeRepository.save(news);
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
