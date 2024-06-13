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
    private final UserRep userRep;
    private final JavaMailSenderImpl mailSender;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpSession session) {
        String email = userDTO.getEmail();
        UserDTO user = userSer.getDto(email);
        String username = user.getUsername();
        String password = user.getPassword();

        if(passwordEncoder.matches(userDTO.getPassword(),password)) {
            session.setAttribute("user",password);

            Cookie cookie = new Cookie("user",password);
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

        if (userRep.findByEmail(userDTO.getEmail()) != null) {
            return ResponseEntity.badRequest().body(userDTO.getEmail()+"은 이미 사용중인 이메일입니다.");
        }

        if (userRep.findById(userDTO.getId()) != null) {
            return ResponseEntity.badRequest().body(userDTO.getId()+"은 이미 사용중인 학번입니다.");
        }

        UserDTO DB = userSer.getDto(userDTO.getEmail());

        if(DB != null) {
            return ResponseEntity.badRequest().body(DB.getUsername()+"님의 정보가 이미 있습니다.");
        }

        if (userDTO.getEmail() != null && userDTO.getPassword() != null && userDTO.getUsername() != null) {
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(userSer.encodePW(userDTO.getPassword()))
                    .ID(userDTO.getId())
                    .email(userDTO.getEmail())
                    .build();
            userRep.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(user.getUsername() + "님의 정보를 성공적으로 생성했습니다.");
        } else {
            return ResponseEntity.badRequest().body("값이 없습니다.");
        }
    }

    @PostMapping("/PW-check")
    public ResponseEntity<?> checkPW(@RequestBody UserDTO userDTO){
        String email = userDTO.getEmail();
        StringBuffer strPwd = new StringBuffer();
        char[] str = new char[1];

        for (int i=0;i<10;i++){
            str[0] =  (char) ((Math.random() * 94)+33);
            strPwd.append(str[0]);
        }

        try {
            UserDTO information = userSer.getDto(email);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(information.getEmail());
            message.setSubject("비밀번호와 관련하여");
            message.setText("비밀번호를 바꾸시려면 이 코드를 작성하십시요.");
            mailSender.send(message);
            return ResponseEntity.ok("성공적으로 보냈습니다.");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/PW-check/change")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO userDTO, @RequestBody String new_password, @RequestBody String code) {
        String password = userDTO.getPassword();
        String email = userDTO.getEmail();
        String check = "";


        UserDTO information = userSer.getDto(email);
        if(code.equals(check)) {
            if (passwordEncoder.matches(password, information.getPassword())) {
                if (!new_password.equals(password)) {
                    User user = User.builder()
                            .ID(information.getId())
                            .password(userSer.encodePW(new_password))
                            .build();
                    userRep.save(user);
                    return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
                } else {
                    return ResponseEntity.badRequest().body("예전 비밀번호랑 같습니다.");
                }
            } else {
                return ResponseEntity.badRequest().body("사용자의 정보가 다릅니다.");
            }
        }
        else {
            return ResponseEntity.badRequest().body("입력하신 코드가 알맞지 않습니다.");
        }
    }
}
