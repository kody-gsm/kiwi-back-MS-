package com.example.kiwi.domain.notice.DTO;

import jakarta.validation.constraints.NotBlank;


public class NoticeRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    // 생성자, getter, setter 등 필요한 메서드 추가

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}