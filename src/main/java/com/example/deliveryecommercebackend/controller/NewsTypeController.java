package com.example.deliveryecommercebackend.controller;


import com.example.deliveryecommercebackend.DTO.NewsTypeDTO;
import com.example.deliveryecommercebackend.services.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsType")
public class NewsTypeController {

    @Autowired
    private NewsTypeService newsTypeService;

    public NewsTypeController(NewsTypeService newsTypeService) {
        this.newsTypeService = newsTypeService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?>getAllNewsType() {
        try {
            var listNewsType = newsTypeService.getAllNewsType();
            if (listNewsType.isEmpty()) {
                return ResponseEntity.ok().body("Empty list newsType.");
            } else {
                return ResponseEntity.ok().body(listNewsType);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }

    @GetMapping("{newsType_id}")
    public ResponseEntity<?>getNewsTypeById(@PathVariable String newsType_id) {
        try {
            var newsType = newsTypeService.getNewsTypeById(newsType_id);
            if (newsType.getId() == null) {
                return ResponseEntity.ok().body("NewsType not found.");
            } else {
                return ResponseEntity.ok().body(newsType);
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error from server");
        }

    }

    @PostMapping
    public ResponseEntity<?> createNewsType(@RequestBody NewsTypeDTO newsType) {
        try {
            HttpStatus checkAdd = newsTypeService.createNewsType(newsType);
            if(checkAdd == HttpStatus.OK) {
                return ResponseEntity.ok("Insert success");
            } else {
                return ResponseEntity.status(checkAdd).body("Insert newsType failed");
            }
        } catch (Exception ex) {
            System.out.println("Error from server, Error:" + ex);
            return ResponseEntity.badRequest().body("Error from newsType");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateNewsType(@RequestBody NewsTypeDTO newsType) {
        try {
            HttpStatus check = newsTypeService.updateNewsType(newsType);
            if(check != HttpStatus.OK)
                return ResponseEntity.status(check).body("Update data failed");
            return ResponseEntity.status(check).body("Update data successfully");
        } catch (Exception ex) {
            System.out.printf("Error from controller" + ex);
            return ResponseEntity.badRequest().body("Error fom server" + ex);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> deleteNewsType(@PathVariable String id) {
        try {
            HttpStatus check = newsTypeService.deleteNewsType(id);
            if (check == HttpStatus.OK) {
                return ResponseEntity.status(check).body("Delete newsType success");
            }
        } catch (Exception ex) {
            System.out.printf("Error from server" + ex);
        }
        return ResponseEntity.badRequest().body("Delete newsType failed");
    }
}
