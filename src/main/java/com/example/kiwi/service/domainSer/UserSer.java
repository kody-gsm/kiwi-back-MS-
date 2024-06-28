package com.example.kiwi.service.domainSer;

import com.example.kiwi.domain.user.DTO.SignUpRequest;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserSer {

    private final UserRep userRep;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private User user;

    public String encodePW(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkLoginID(Short id){
        return userRep.existsById(id);
    }

    public boolean checkEmail(String email){
        return userRep.existsByEmail(email);
    }

    public void signUp(SignUpRequest req){
        userRep.save(req.toEntity(encodePW(req.getPassword())));
    }

    public Optional<User> checkPEI(String email, String name, Short id){
        return userRep.findByUsernameAndIdAndEmail(name,id,email);
    }

    public Optional<User> getUserByEmail(String email){
        return userRep.findByEmail(email);
    }

    public void createUser(SignUpRequest request, BindingResult bindingResult){
        if (request.getId() == null){
            bindingResult.addError(new FieldError("SignUp", "id", "학번이 비어있습니다."));
        }
        else if(request.getId() <= 1101 || request.getId() >= 3418){
            bindingResult.addError(new FieldError("SignUp","id","잘못된 값입니다."));
        }

        if (checkLoginID(request.getId())){
            bindingResult.addError(new FieldError("SignUp","id","학번이 중복됩니다."));
        }

        if (request.getEmail() == null){
            bindingResult.addError(new FieldError("SignUp","email","이메일이 비어있습니다."));
        }
        if (checkEmail(request.getEmail())){
            bindingResult.addError(new FieldError("SignUp","email","이메일이 중복됩니다."));
        }

        if (request.getPassword() == null) {
            bindingResult.addError(new FieldError("SignUp","password","비밀번호가 비어있습니다."));
        }
        else if(request.getPasswordCheck() == null){
            bindingResult.addError(new FieldError("SignUp","passwordCheck","비밀번호 확인칸이 비어있습니다."));
        }
        else if(!request.getPassword().equals(request.getPasswordCheck())){
            bindingResult.addError(new FieldError("SignUP","passwordCheck","비밀번호와 일치하지 않습니다."));
        }

        if (request.getGender() == null){
            bindingResult.addError(new FieldError("SignUp","gender","성별이 비어있습니다."));
        }
        else if (!request.getGender().toString().equals("MAN") && !request.getGender().toString().equals("WOMAN")) {
            bindingResult.addError(new FieldError("Signup","gender","잘못된 값입니다."));
        }

        if (request.getUsername() == null){
            bindingResult.addError(new FieldError("SignUp","username","이름이 비어있습니다."));
        }
    }
}
