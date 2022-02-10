package com.example.vuebackboard.service;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.entity.dto.BoardDto;
import com.example.vuebackboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /* 게시글 목록 가져오기 */
    public List<BoardDto> getBoardList() {
        ArrayList<BoardDto> dtos = new ArrayList<>();
        List<BoardEntity> boardEntities = boardRepository.findAll();
        boardEntities.forEach(entity -> {
            dtos.add(BoardDto.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .author(entity.getAuthor())
                    .createdAt(entity.getCreatedAt())
                    .build());
        });
        Collections.reverse(dtos);  //역순 정렬
        return dtos;
    }

    /* 게시글 가져오기*/
    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return BoardDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    /* 게시글 등록 */
    public BoardEntity create(BoardDto boardDto) {
        BoardEntity entity = BoardEntity.builder()
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .author(boardDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepository.save(entity);
    }

    /* 게시글 수정 */
    public BoardEntity update(BoardDto boardDto) {
        BoardEntity entity = boardRepository.findById(boardDto.getIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setTitle(boardDto.getTitle());
        entity.setAuthor(boardDto.getAuthor());
        entity.setContents(boardDto.getContents());
        return boardRepository.save(entity);
    }

    /* 게시글 삭제 */
    public void delete(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
}

