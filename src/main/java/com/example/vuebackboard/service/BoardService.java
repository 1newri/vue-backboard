package com.example.vuebackboard.service;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.entity.dto.BoardDto;
import com.example.vuebackboard.model.Header;
import com.example.vuebackboard.model.Pagination;
import com.example.vuebackboard.model.SearchCondition;
import com.example.vuebackboard.repository.BoardRepository;
import com.example.vuebackboard.repository.BoardRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardRepositoryCustom boardRepositoryCustom;

    /* 게시글 목록 가져오기 */
    public Header<List<BoardDto>> getBoardList(Pageable pageable, SearchCondition searchCondition) {
        ArrayList<BoardDto> dtos = new ArrayList<>();
        //Page<BoardEntity> boardEntities = boardRepository.findAllByOrderByIdxDesc(pageable);
        //List<BoardEntity> boardEntities = boardRepository.findAll();
        Page<BoardEntity> boardEntities = boardRepositoryCustom.findAllBySearchCondition(pageable, searchCondition);
        boardEntities.forEach(entity -> {
            dtos.add(BoardDto.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .author(entity.getAuthor())
                    .createdAt(entity.getCreatedAt())
                    .build());
        });
        Pagination pagination = new Pagination(
                (int) boardEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );
        return Header.OK(dtos, pagination);
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

