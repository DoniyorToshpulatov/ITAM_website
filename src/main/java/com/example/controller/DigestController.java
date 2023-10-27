package com.example.controller;

import com.example.dto.DigestDTO;
import com.example.enums.Lang;
import com.example.service.DigestService;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/digest")
public class DigestController {

    @Autowired
    private DigestService digestService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody DigestDTO dto){
        digestService.create(dto);
        return ResponseEntity.ok("Created!!");
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  ResponseEntity<?> update(@RequestBody DigestDTO dto,
                                     @RequestParam("id") Integer id){
        digestService.update(dto,id);
        return ResponseEntity.ok("Updated!!");
    }
    @PutMapping("/update/attach")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateAttach(@RequestParam("attachId")String attachId,
                                          @RequestParam("id")Integer id){
        digestService.updateAttach(attachId,id);
        return ResponseEntity.ok("Update digest attach id!!!");
    }
    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@RequestParam("id")Integer id){
        digestService.delete(id);
        return ResponseEntity.ok("delete digest !!");
    }

    @PutMapping("/noDelete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> noDelete(@RequestParam("id")Integer id){
        digestService.noDelete(id);
        return ResponseEntity.ok("no delete digest !!");
    }

    @GetMapping("/more/categoryId")

    public  ResponseEntity<?> more(@RequestParam("lang") Lang lang,
                                   @RequestParam("categoryId")Integer  categoryId){
        return ResponseEntity.ok(digestService.more(categoryId,lang));
    }
}
