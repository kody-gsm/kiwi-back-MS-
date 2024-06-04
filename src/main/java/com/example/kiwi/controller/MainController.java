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
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {
    private final UserSer userSer;
    private final UserRep userRep;
    private final JavaMailSenderImpl mailSender;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpServletResponse response, HttpSession session) {
        String username = userDTO.getUsername();

        if(userSer.login(username, userDTO.getPassword())) {
            session.setAttribute("username", username);

            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return ResponseEntity.ok("Hello " + username);
        }
        else if (username == null || userRep.findByUsername(username) == null) {//이름이 그냥 null이거나 DB에 없으면 나오는 거
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이름이 없습니다.");
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 다릅니다.");
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUP(@RequestBody UserDTO userDTO) {
        if(userDTO != null) {
            User user = User.builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .ID(userDTO.getUser_id())
                    .email(userDTO.getEmail())
                    .gender(userDTO.getGender())
                    .build();
            userRep.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("값이 없는게 있습니다.");
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


//    @PostMapping("/PW-check")
//    public ResponseEntity<?> checkPassword(@RequestBody UserDTO userDTO) {
//        String username = userDTO.getUsername();
//        String e_mail = userDTO.getEmail();
//        String password = userSer.getpass1(username,e_mail);
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("s23001@gsm.hs.kr");
//        message.setTo(e_mail);
//        message.setSubject("KIWI에서 비밀번호와 관련된 메일입니다.");
//        if (password == null)
//            message.setText("비밀번호가 없습니다. 문제가 생긴 가능성이 있으니 KIWI관련자에게 연락주세요.\n담당자:진건희");
//        else
//            message.setText("사용자님의 비밀번호는 '"+password+"' 입니다.\n잊어버리지 않게 조심해주세요.");
//
//        try {
//            mailSender.send(message);
//            return ResponseEntity.ok("good");
//        }
//        catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("bad");
//        }
//    }

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
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not equals password");
        }
    }
}
