package com.example.vuebackboard.repository;

import com.example.vuebackboard.entity.BoardEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @DisplayName("1. 게시글보기")
    @Test
    public void selectBoard() {
        List<BoardEntity> list = boardRepository.findAll();
        Optional<BoardEntity> boardEntity = boardRepository.findById(1L);
        System.out.println(boardEntity.get());
    }
}