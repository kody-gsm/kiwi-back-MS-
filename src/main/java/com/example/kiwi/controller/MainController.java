package com.example.kiwi.controller;

import com.example.kiwi.domain.user.User;
import com.example.kiwi.domain.user.UserDTO;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.service.UserSer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.apache.ibatis.javassist.Loader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {

    private final UserSer userSer;
    private final UserRep userRep;
    private final JavaMailSenderImpl mailSender;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpSession session) {
        String email = userDTO.getEmail();
        UserDTO user = userSer.getDto(email);
        String username = user.getUsername();

        if(passwordEncoder.matches(userDTO.getPassword(),user.getPassword())) {
            session.setAttribute("username", username);

            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return ResponseEntity.ok("Hello " + username);
        }
        else if (username == null || userRep.findByUsername(username) == null) {
            return ResponseEntity.badRequest().body("이름이 없습니다.");
        }
        else {
            return ResponseEntity.badRequest().body("비밀번호가 다릅니다.");
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUP(@RequestBody UserDTO userDTO) {

        if(userDTO != null) {
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(userSer.encodePW(userDTO.getPassword()))
                    .ID(userDTO.getId())
                    .email(userDTO.getEmail())
                    .build();
            userRep.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("Successful Create" + user.getUsername());
        }
        else {
            return ResponseEntity.badRequest().body("값이 없습니다.");
        }
    }

//    @PostMapping("/add{option}")
//    public void addoptionP(@RequestBody Short id,@RequestBody @PathVariable String option){
//        userSer.addlate(id,option);
//    }
//
//    @GetMapping("/add{option}")
//    public void addoptionG(@RequestParam Short id,@RequestBody @PathVariable String option){
//        userSer.addlate(id,option);
//    }

    @PostMapping("/PW-check")
    public ResponseEntity<?> checkPW(@RequestBody UserDTO userDTO){
        String email = userDTO.getEmail();

        try {
            UserDTO information = userSer.getDto(email);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(information.getEmail());
            message.setSubject("비밀번호와 관련하여");
            message.setText("asdf");
            mailSender.send(message);
            return ResponseEntity.ok("성공적으로 보냈습니다.");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/mail")
//    public ResponseEntity<?> mailTest(@RequestParam String index){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo("kminsung0728@gmail.com");
//        message.setSubject("Test");
//        message.setText(index);
//        mailSender.send(message);
//        return ResponseEntity.ok(index);
//    }

    @PostMapping("/PW-check/change")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO userDTO, @RequestBody String new_password, @RequestBody String code) {
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();

        UserDTO information = userSer.getDto(email);
        if (passwordEncoder.matches(password,information.getPassword())) {
            if (!new_password.equals(password)) {
                User user = User.builder()
                        .password(userSer.encodePW(new_password))
                        .build();
                userRep.save(user);
                return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("예전 비밀번호랑 같습니다.");
            }
        }
        else {
            return ResponseEntity.badRequest().body("유저의 정보가 다릅니다.");
        }
    }
}
