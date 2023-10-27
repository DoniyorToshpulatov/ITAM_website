package com.example.controller;

import com.example.dto.PartnerDTO;
import com.example.enums.Lang;
import com.example.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partner")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody PartnerDTO dto){
        partnerService.create(dto);
        return  ResponseEntity.ok("Created!!");
    }
    @PutMapping("/update")
    @PreAuthorize(("hasRole('ROLE_ADMIN')"))
    public  ResponseEntity<?> update(@RequestBody PartnerDTO dto,
                                     @RequestParam("id") Integer id){
        partnerService.update(dto,id);
        return  ResponseEntity.ok("update!!");
    }
    @PutMapping("/update/attach")
    @PreAuthorize(("hasRole('ROLE_ADMIN')"))
    public  ResponseEntity<?> updateAttach(@RequestParam("attachId")String attachId,
                                     @RequestParam("id") Integer id){
        partnerService.updateAttachId(attachId,id);
        return  ResponseEntity.ok("update partner attach!!");
    }
    @DeleteMapping("/delete")
    @PreAuthorize(("hasRole('ROLE_ADMIN')"))
    public  ResponseEntity<?> delete(@RequestParam("id") Integer id){
        partnerService.delete(id);
        return  ResponseEntity.ok("delete partner!!");
    }
    @PutMapping("/noDelete")
    @PreAuthorize(("hasRole('ROLE_ADMIN')"))
    public  ResponseEntity<?> noDelete(@RequestParam("id") Integer id){
        partnerService.noDelete(id);
        return  ResponseEntity.ok("delete partner tiklandi!!");
    }
    @GetMapping("/more")
    public  ResponseEntity<List<?>> more(@RequestParam("lang") Lang lang){
        List<PartnerDTO> more = partnerService.more(lang);
        return  ResponseEntity.ok(more);
    }

    @GetMapping("/byId")
    public  ResponseEntity<?> byId(@RequestParam("lang") Lang lang,
                                   @RequestParam("id")Integer id){
        PartnerDTO dto = partnerService.byId(id, lang);
        return  ResponseEntity.ok(dto);
    }

}
