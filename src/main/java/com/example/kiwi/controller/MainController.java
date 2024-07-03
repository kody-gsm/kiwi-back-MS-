package com.example.kiwi.controller;

import com.example.kiwi.domain.selection.DTO.FilterRequest;
import com.example.kiwi.domain.user.*;
import com.example.kiwi.domain.user.DTO.CheckRequest;
import com.example.kiwi.domain.user.DTO.PwCheckRequest;
import com.example.kiwi.domain.user.DTO.SignUpRequest;
import com.example.kiwi.service.domainSer.AttendSer;
import com.example.kiwi.service.domainSer.CheckSer;
import com.example.kiwi.service.domainSer.SelectionSer;
import com.example.kiwi.service.domainSer.UserSer;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "false", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {

    private final UserSer userSer;
    private final AttendSer attendSer;
    private final SelectionSer selectionSer;
    private final JavaMailSenderImpl mailSender;
    private final CheckSer checkSer;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUP(@Valid @RequestBody SignUpRequest request, BindingResult bindingResult) {
        userSer.createUser(request, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        userSer.signUp(request);
        attendSer.CreateAttendance(request.getId());
        selectionSer.create(request.getId());

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
                message.setText("비밀번호를 바꾸시려면 이 링크"+"에"+"들어가십시요.");
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

    @GetMapping("/check")
    public ResponseEntity<?> check(Authentication auth){
        if(auth == null){
            return ResponseEntity.badRequest().body(null);
        }
        else {
            String email = auth.getName();
            Optional<User> userdata = userSer.getUserByEmail(email);
            if (userdata.isPresent()){
                String name = userdata.get().getUsername();
                Short id = userdata.get().getId();
                Optional<CheckRequest> userAttend = attendSer.SelectAttendance(id);
                UserGender gender = userdata.get().getGender();
                return ResponseEntity.ok(checkSer.checkSelcet(userAttend,id,name,gender));
            }
            return ResponseEntity.badRequest().body("DB에 존재하지 않습니다.");
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody FilterRequest request){
        return ResponseEntity.ok(selectionSer.findByIdAndMode(request.getId(),request.getMode()));
    }
}