package com.example.kiwi.controller;

import com.example.kiwi.domain.user.SignUpRequest;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.domain.user.UserDTO;
import com.example.kiwi.service.UserSer;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {

    private final UserSer userSer;
    private final JavaMailSenderImpl mailSender;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUP(@Valid @RequestBody SignUpRequest request, BindingResult bindingResult) {
        if (request.getId() == null){
            bindingResult.addError(new FieldError("SignUpRequest", "id", "학번이 비어있습니다."));
        }
        if (userSer.checkLoginID(request.getId())){
            bindingResult.addError(new FieldError("SignUpRequest","id","학번이 중복됩니다."));
        }

        if (request.getEmail() == null){
            bindingResult.addError(new FieldError("SignUpRequest","email","이메일이 비어있습니다."));
        }
        if (userSer.checkEmail(request.getEmail())){
            bindingResult.addError(new FieldError("SignUpRequest","email","이메일이 중복됩니다."));
        }

        if (request.getPassword() == null) {
            bindingResult.addError(new FieldError("SignUpRequest","password","비밀번호가 비어있습니다."));
        }
        if (request.getUsername() == null){
            bindingResult.addError(new FieldError("SignUpRequest","username","이름이 비어있습니다."));
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        userSer.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/PW-check")
    public ResponseEntity<?> checkPW(@RequestBody UserDTO userDTO){
        String email = userDTO.getEmail();
        StringBuffer strPwd = new StringBuffer();
        char[] str = new char[1];

        for (int i=0;i<10;i++){
            str[0] =  (char) ((Math.random() * 26)+65);
            strPwd.append(str[0]);
        }

        System.out.println(strPwd);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("비밀번호와 관련하여");
            message.setText("비밀번호를 바꾸시려면 이 코드"+strPwd+"를 작성하십시요.");
            mailSender.send(message);
            return ResponseEntity.ok("성공적으로 보냈습니다.");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/asdf")
    public String asdf(){
        return "asdf";
    }

}