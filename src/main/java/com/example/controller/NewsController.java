package com.example.controller;

import com.example.dto.NewsDTO;
import com.example.enums.Lang;
import com.example.service.NewsService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody NewsDTO dto){
      return ResponseEntity.ok(newsService.create(dto));
     }

    @PutMapping("/update")
    public  ResponseEntity<?> update(@RequestBody NewsDTO dto,@RequestParam("id")Integer id){
        newsService.update(dto,id);
        return ResponseEntity.ok("Update buldi!!");
      }

    @PutMapping("/update/attach")
    public  ResponseEntity<?> update(@RequestParam("attachId")String attachId,
                                     @RequestParam("id")Integer id){
        System.out.println("keldimiiii");
        newsService.updateAttachId(id,attachId);
        return ResponseEntity.ok("Newsni Attach update buldi!!");
      }

   @DeleteMapping("/delete")
    public  ResponseEntity<?> delete(@RequestParam("id")Integer id){
        newsService.delete(id);
        return ResponseEntity.ok("Delete buldi");
    }

    @GetMapping("/top3")
    public  ResponseEntity<List<?>> top3(@RequestParam("lang") Lang lang) {
        return ResponseEntity.ok(newsService.top3(lang));
    }

    @GetMapping("/more")
    public  ResponseEntity<List<?>> more(@RequestParam("lang")Lang lang){
        return ResponseEntity.ok(newsService.more(lang));
    }

    @GetMapping("/more/admin")
    public ResponseEntity<List<?>> more(){
        return ResponseEntity.ok(newsService.moreAdmin());
    }

    @PutMapping("/noDelete")
    public  ResponseEntity<?> noDelete(@RequestParam("id")Integer id){
        newsService.noDelete(id);
        return ResponseEntity.ok("Delete bulgan news qaytarildi!!");
    }
    @GetMapping("/morePage")
    @PermitAll
    public  ResponseEntity<Page<?>> morePage(@RequestParam("page")Integer page,
                                             @RequestParam("size")Integer size,
                                             @RequestParam("lang")Lang lang){
        return ResponseEntity.ok(newsService.morePage(page,size,lang));
    }
}
