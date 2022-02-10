package com.example.vuebackboard.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardDto {
    private Long idx;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdAt;
}
