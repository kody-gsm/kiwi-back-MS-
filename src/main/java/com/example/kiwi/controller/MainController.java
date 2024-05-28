package com.example.kiwi.controller;

import com.example.kiwi.domain.user.User;
import com.example.kiwi.domain.user.UserDTO;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.service.UserSer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {
    private final UserSer userSer;
    private final UserRep userRep;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        if(userSer.login(username, userDTO.getPassword())) {
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

    @PostMapping("/addlate")
    public void addlate(@RequestBody Short id){
        userSer.addlate(id);
    }
    
    @PostMapping("/PW-check")
    public ResponseEntity<?> checkPassword(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String e_mail = userDTO.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("s23001@gsm.hs.kr");
        message.setTo(e_mail);
        message.setSubject("KIWI에서 비밀번호와 관련된 메일입니다.");
        if (userSer.getpass(username,e_mail) == null)
            message.setText("비밀번호가 없습니다. 문제가 생긴 가능성이 있으니 KIWI관련자에게 연락주세요.\n담당자:진건희");
        else
            message.setText("당신의 비밀번호는 "+userSer.getpass(username,e_mail)+"입니다.\n잊어버리지 않게 조심해주세요.");

        return ResponseEntity.ok(userSer.getpass(username, e_mail));
    }

    @PostMapping("/PW-check/change")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO userDTO, @RequestBody String new_password) {
        String username = userDTO.getUsername();
        String e_mail = userDTO.getEmail();
        String password = userDTO.getPassword();
        String old_password = userSer.getpass(username, e_mail);

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
