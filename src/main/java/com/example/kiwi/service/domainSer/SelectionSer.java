package com.example.kiwi.service.domainSer;

import com.example.kiwi.domain.selection.Selection;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.SelectionRep;
import com.example.kiwi.repository.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SelectionSer {
    private final SelectionRep selectionRep;
    private final UserRep userRep;

    public void create(Short id){
        User user = userRep.findUserById(id);
        Selection selection = Selection.builder()
                .user(user)
                .build();
        selectionRep.save(selection);
    }
}
