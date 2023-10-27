package com.example.controller;
import com.example.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

  @PostMapping("/upload")
  public ResponseEntity<?> create (@RequestParam("file")MultipartFile file){
      return ResponseEntity.ok(attachService.saveToSystem(file));
  }

     @GetMapping(value = "/load", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] loadImage(@RequestParam("fileName") String fileName) {
        byte[] response = attachService.loadImage(fileName);
        return response;
    }
    @GetMapping(value = "/open/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] openGeneral(@PathVariable String fileName) {

        byte[] response = attachService.openGeneral(fileName);
        return response;
    }
    @GetMapping(value = "/open/img_db/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {

        return this.attachService.loadImage(fileName);
    }

    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<?> download(@PathVariable String fileName) {
        return attachService.download(fileName);

    }

    @GetMapping ("/list/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> listAdmin(@RequestParam("size") Integer size, @RequestParam("page") Integer page) {

        return ResponseEntity.ok(attachService.getList(page,size));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete( @PathVariable String id)
    {
        return  ResponseEntity.ok(attachService.delete(id));
    }

}
