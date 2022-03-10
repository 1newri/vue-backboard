package com.example.vuebackboard.repository;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.model.SearchCondition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class BoardRepositoryCustomTest {

    @Autowired
    private BoardRepositoryCustom boardRepositoryCustom;


    @Test
    void boardCustomTest(){
        SearchCondition sc = SearchCondition.builder()
                .sk("author")
                .sv("작성자1")
                .build();

        Page<BoardEntity> list = boardRepositoryCustom.findAllBySearchCondition(PageRequest.of(0,20), sc);

        list.forEach(System.out::println);
    }
}