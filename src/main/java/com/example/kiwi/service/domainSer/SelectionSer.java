package com.example.kiwi.service.domainSer;

import com.example.kiwi.domain.selection.DTO.FilterResponse;
import com.example.kiwi.domain.selection.Selection;
import com.example.kiwi.domain.selection.SelectionMode;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.SelectionRep;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.repository.mapper.FilterMapper;
import com.example.kiwi.repository.mapper.SelectionMapper;
import com.example.kiwi.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SelectionSer {
    private final SelectionRep selectionRep;
    private final UserRep userRep;
    private final UserMapper userMapper;
    private final SelectionMapper selectionMapper;
    private final FilterMapper filterMapper;

    public void create(Short id){
        User user = userRep.findUserById(id);
        Selection selection = Selection.builder()
                .user(user)
                .build();
        selectionRep.save(selection);
    }

    public List<FilterResponse> findByIdAndMode(Short id, SelectionMode mode){
        if(id == null && mode == null){
            return null;
        } else if (mode == null) {
            if(id / 100 % 10 != 0){
                return userMapper.getClassUser(id);
            }
            else {
                return userMapper.getGradeUser(id);
            }
        } else if (id == null) {
            return selectionMapper.getModeUser(mode);
        }
        else {
            if (id / 100 % 10 != 0){
                return filterMapper.getFilterClass(mode,id);
            }
            else {
                return filterMapper.getFilterGrade(mode,id);
            }
        }
    }
}
