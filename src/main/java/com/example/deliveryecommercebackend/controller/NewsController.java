package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.NewsCreateDTO;
import com.example.deliveryecommercebackend.DTO.NewsTypeDTO;
import com.example.deliveryecommercebackend.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?>getAllNews() {
        try {
            var listNews = newsService.getAllNewss();
            if (listNews.isEmpty()) {
                return ResponseEntity.ok().body("Empty list news.");
            } else {
                return ResponseEntity.ok().body(listNews);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }

    @GetMapping("{news_id}")
    public ResponseEntity<?>getNewsById(@PathVariable String news_id) {
        try {
            var news = newsService.getNewsById(news_id);
            if (news.getId() == null) {
                return ResponseEntity.ok().body("News not found.");
            } else {
                return ResponseEntity.ok().body(news);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody NewsCreateDTO news) {
        try {
            HttpStatus checkAdd = newsService.insertNews(news);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.ok("Insert success");
            } else {
                return ResponseEntity.status(checkAdd).body("Insert news failed");
            }
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from newsType");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateNews(@RequestBody NewsCreateDTO news) {
        try {
            HttpStatus check = newsService.updateNews(news);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Update data failed");
            return ResponseEntity.status(check).body("Update data successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error fom server" + ex);
        }
    }

    @PutMapping("{newsId}/action/{actionCode}")
    public ResponseEntity<?> setActionNews(@PathVariable String newsId, @PathVariable String actionCode) {
        try {
            HttpStatus check = newsService.setAction(newsId, actionCode);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Update data failed");
            return ResponseEntity.status(check).body("Update data successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error fom server" + ex);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> deleteNews(@PathVariable String id) {
        try {
            HttpStatus check = newsService.deleteNews(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete news success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete news failed");
    }
}
