package com.example.controller;

import com.example.dto.AuthorizationDTO;
import com.example.dto.AuthorizationResponseDTO;
import com.example.dto.RegistrationDTO;
import com.example.enums.Lang;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationDTO dto){
        System.out.println("keldi bu yerga");
        authService.registration(dto);
        return ResponseEntity.ok().build();
    }
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody AuthorizationDTO dto,
                                   @RequestHeader(value = "Accept-Language",defaultValue = "uz") Lang lang) {

        AuthorizationResponseDTO response = authService.authorization(dto,lang);
        return ResponseEntity.ok(response);
    }



}
