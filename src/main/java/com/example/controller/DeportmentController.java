package com.example.controller;

import com.example.dto.DeportmentDTO;
import com.example.enums.Lang;
import com.example.service.DeportmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deportment")
public class DeportmentController {

    @Autowired
    private DeportmentService deportmentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DeportmentDTO dto){
        deportmentService.create(dto);
        return  ResponseEntity.ok(" Yaratildi!!");
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody DeportmentDTO dto,@RequestParam("id")Integer id){
        deportmentService.update(dto,id);
        return  ResponseEntity.ok("update buldi!!");
    }
    @PutMapping("/update/attach")
    public ResponseEntity<?> updateAttach(@RequestParam("attachId")String attachId,
                                          @RequestParam("id")Integer id){
        deportmentService.updateAttach(id,attachId);
        return  ResponseEntity.ok("update buldi attach!!");
    }
    @DeleteMapping("/delete")
    public  ResponseEntity<?> delete(@RequestParam("id")Integer id){
        deportmentService.delete(id);
        return  ResponseEntity.ok("Delete ok!!");
    }
    @PutMapping("/noDelete")
    public  ResponseEntity<?> noDelete(@RequestParam("id")Integer id){
        deportmentService.noDelete(id);
        return  ResponseEntity.ok("Tiklandi!!");
    }
    @GetMapping("/more")
    public  ResponseEntity<List<?>> more(@RequestParam("lang") Lang lang){
        return ResponseEntity.ok(deportmentService.more(lang));
    }
}
