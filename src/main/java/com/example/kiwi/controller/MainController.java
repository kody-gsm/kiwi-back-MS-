package com.example.kiwi.controller;

import com.example.kiwi.domain.user.SignUpRequest;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.domain.user.UserDTO;
import com.example.kiwi.domain.user.UserRole;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.service.UserSer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {

    private final UserSer userSer;
    private final JavaMailSenderImpl mailSender;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUP(@RequestBody SignUpRequest request) {
        if(userSer.checkLoginID(request.getId())){
            return ResponseEntity.badRequest().body("이미 그 학번은 사용중입니다.");
        }
        else if(userSer.checkEmail(request.getEmail())){
            return ResponseEntity.badRequest().body("이미 그 email은 사용입니다.");
        }
        else{
            userSer.signUp(request);
            return ResponseEntity.ok("asdf");
        }
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

//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody UserDTO userDTO) {
//    }
}