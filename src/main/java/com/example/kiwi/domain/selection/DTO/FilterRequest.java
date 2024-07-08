package com.example.kiwi.domain.selection.DTO;

import com.example.kiwi.domain.selection.SelectionMode;
import lombok.Data;

@Data
public class FilterRequest {
    private Short id;
    private SelectionMode mode;
}