package com.example.vuebackboard.web;

import com.example.vuebackboard.entity.BoardEntity;
import com.example.vuebackboard.entity.dto.BoardDto;
import com.example.vuebackboard.model.Header;
import com.example.vuebackboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public Header<List<BoardDto>> boardList(
            @PageableDefault(sort = {"idx"}) Pageable pageable) {
        return boardService.getBoardList(pageable);
    }

    @GetMapping("/board/{id}")
    public BoardDto getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

    @PostMapping("/board")
    public BoardEntity create(@RequestBody BoardDto boardDto){
        return boardService.create(boardDto);
    }

    @PatchMapping("/board")
    public BoardEntity update(@RequestBody BoardDto boardDto){
        return boardService.update(boardDto);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id){
        boardService.delete(id);
    }
}
