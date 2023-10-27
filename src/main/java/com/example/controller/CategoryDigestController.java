package com.example.controller;

import com.example.dto.CategoryDigestDTO;
import com.example.enums.Lang;
import com.example.service.CategoryDigestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryDigestController {
    @Autowired
    private CategoryDigestService categoryDigestService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> create(@RequestBody CategoryDigestDTO dto){
        categoryDigestService.create(dto);
        return  ResponseEntity.ok("created!!");
    }
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public  ResponseEntity<?> update(@RequestBody CategoryDigestDTO dto,
                                     @RequestParam("id") Integer id){
        categoryDigestService.update(dto,id);
        return  ResponseEntity.ok("Updated!!");
    }

    @PutMapping("/update/attach")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> updateAttach(@RequestParam("attachId")String attachId,
                                          @RequestParam("id")Integer id){
        categoryDigestService.updateAttach(attachId,id);
        return ResponseEntity.ok("Category attach is updated!!");
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public  ResponseEntity<?> delete(@RequestParam("id")Integer id){
        categoryDigestService.delete(id);
        return  ResponseEntity.ok("Category is deleted!!");
    }

    @PutMapping("/noDelete")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public  ResponseEntity<?> noDelete(@RequestParam("id")Integer id){
        categoryDigestService.noDelete(id);
        return  ResponseEntity.ok("Category is no delete!!");
    }

    @GetMapping("/more")
    public  ResponseEntity<List<?>> more(@RequestParam("lang") Lang lang){
        return ResponseEntity.ok(categoryDigestService.more(lang));
    }
}
