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
        String username = userDTO.getUsername();
        String email = userDTO.getEmail();
        UserDTO user = userSer.getDto(username,email);

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
                    .ID(userDTO.getUser_id())
                    .email(userDTO.getEmail())
                    .gender(userDTO.getGender())
                    .build();
            userRep.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("Successful Create" + user.getUsername());
        }
        else {
            return ResponseEntity.badRequest().body("값이 없습니다.");
        }
    }

    @PostMapping("/add{option}")
    public void addoptionP(@RequestBody Short id,@RequestBody @PathVariable String option){
        userSer.addlate(id,option);
    }

    @GetMapping("/add{option}")
    public void addoptionG(@RequestParam Short id,@RequestBody @PathVariable String option){
        userSer.addlate(id,option);
    }

    @PostMapping("/PW-check/change")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO userDTO, @RequestBody String new_password) {
        String username = userDTO.getUsername();
        String e_mail = userDTO.getEmail();
        String password = userDTO.getPassword();
        String old_password = userSer.getpass1(username, e_mail);

        if(password.equals(old_password)) {
            User user = User.builder()
                    .password(new_password)
                    .build();
            userRep.save(user);
            return ResponseEntity.ok("change password");
        }
        else {
            return ResponseEntity.badRequest().body("Not equals password");
        }
    }

    @GetMapping("/asdf")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Asdf");
    }
}
