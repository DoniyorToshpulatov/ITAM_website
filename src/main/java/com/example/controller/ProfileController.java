package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.enums.Lang;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping ("/update")
    public ResponseEntity<?> update(@RequestBody ProfileDTO dto){
        profileService.update(dto);
        return ResponseEntity.ok().body("Update buldi");
    }
    @PutMapping ("/update/attach")
    public ResponseEntity<?> update(@RequestParam("attachId") String attachId,
                                    @RequestParam("id") Integer id){
        profileService.updateAttachId(attachId,id);
        return ResponseEntity.ok().body("Update buldi");
    }

    @GetMapping("/deportment")
    public ResponseEntity<?> byDeport(@RequestParam("deportmentId")Integer deportmentId,
                                      @RequestParam("lang") Lang lang){
        return ResponseEntity.ok(profileService.byDeportment(lang,deportmentId));
    }

    @GetMapping("/byId")
    public  ResponseEntity<?> getById(@RequestParam("lang")Lang lang,@RequestParam("id")Integer id){
        return ResponseEntity.ok(profileService.byId(id,lang));
    }

}
