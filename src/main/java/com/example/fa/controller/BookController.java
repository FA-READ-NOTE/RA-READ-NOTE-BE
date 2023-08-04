package com.example.fa.controller;

import com.example.fa.dto.BookDetailDto;
import com.example.fa.entity.Book;

import com.example.fa.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/search")
    public ResponseEntity<?> search(@RequestParam String keyword){
        return new ResponseEntity<>(bookService.search(keyword), HttpStatus.OK);
    }

    @GetMapping("/book/detail")
    public ResponseEntity<?> searchDetail(@RequestParam String isbn){
        return new ResponseEntity<>(bookService.searchInfo(isbn), HttpStatus.OK);
    }

    @PostMapping("/book/like")
    public Book like(@RequestBody BookDetailDto dto) {
        return bookService.like(dto);
    }
}
