package com.example.controller;

import com.example.dto.ResultDTO;
import com.example.enums.Lang;
import com.example.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody ResultDTO dto){
        resultService.create(dto);
        return ResponseEntity.ok("Created!!");
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody ResultDTO dto,
                                    @RequestParam("id")Integer id){
        resultService.update(dto,id);
        return ResponseEntity.ok("update!!");
    }

    @PutMapping("/update/attach")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestParam("attachId")String attachId,
                                    @RequestParam("id")Integer id){
        resultService.updateAttach(attachId,id);
        return ResponseEntity.ok("update attach!!");
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@RequestParam("id")Integer id){
        resultService.delete(id);
        return ResponseEntity.ok("delete !!");
    }

    @PutMapping("/noDelete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> noDelete(@RequestParam("id")Integer id){
        resultService.noDelete(id);
        return ResponseEntity.ok("delete tiklandi !!");
    }
    @GetMapping("/top")
    public  ResponseEntity<List<?>> top(@RequestParam("lang") Lang lang){
        return ResponseEntity.ok(resultService.top3(lang));
    }
    @GetMapping("/more")
    public  ResponseEntity<Page<?>> more(@RequestParam("lang") Lang lang,
                                         @RequestParam("page")Integer page,
                                         @RequestParam("size")Integer size){
        return ResponseEntity.ok(resultService.more(lang,size,page));
    }
    @GetMapping("/byId")
    public ResponseEntity<?> byId(@RequestParam("lang")Lang lang,
                                  @RequestParam("id")Integer id){
        return ResponseEntity.ok(resultService.byId(id,lang));
    }


}
