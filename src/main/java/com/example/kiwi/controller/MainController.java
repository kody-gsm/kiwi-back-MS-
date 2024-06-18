package com.example.kiwi.controller;

import com.example.kiwi.domain.user.PwCheckRequest;
import com.example.kiwi.domain.user.SignUpRequest;
import com.example.kiwi.domain.user.User;
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

import java.util.Optional;


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
            bindingResult.addError(new FieldError("SignUp", "id", "학번이 비어있습니다."));
        }
        else if(request.getId() <= 1101 || request.getId() >= 3418){
            bindingResult.addError(new FieldError("SignUp","id","잘못된 값입니다."));
        }

        if (userSer.checkLoginID(request.getId())){
            bindingResult.addError(new FieldError("SignUp","id","학번이 중복됩니다."));
        }

        if (request.getEmail() == null){
            bindingResult.addError(new FieldError("SignUp","email","이메일이 비어있습니다."));
        }
        if (userSer.checkEmail(request.getEmail())){
            bindingResult.addError(new FieldError("SignUp","email","이메일이 중복됩니다."));
        }

        if (request.getPassword() == null) {
            bindingResult.addError(new FieldError("SignUp","password","비밀번호가 비어있습니다."));
        }
        if (request.getUsername() == null){
            bindingResult.addError(new FieldError("SignUp","username","이름이 비어있습니다."));
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        userSer.signUp(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("생성되었습니다.");
    }

    @PostMapping("/PW-check")
    public ResponseEntity<?> checkPW(@RequestBody PwCheckRequest request){
        String email = request.getEmail();
        String name = request.getName();
        Short id = request.getId();

        Optional<User> userdata = userSer.checkPEI(email, name, id);

        if (userdata.isPresent()){
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email);
                message.setSubject("비밀번호와 관련하여");
                message.setText("비밀번호를 바꾸시려면 이 링크에"+"들어가십시요.");
                mailSender.send(message);
                return ResponseEntity.ok("성공적으로 보냈습니다.");
            }
            catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        else {
            return ResponseEntity.badRequest().body("데이터와 맞는 값이 없습니다.");
        }
    }

//    @GetMapping("/test")
//    public String test(@RequestParam String password){
//        if (passwordEncoder.matches("",password)) {
//            return "asdf";
//        }
//        else {
//            return "qwer";
//        }
//    }

}