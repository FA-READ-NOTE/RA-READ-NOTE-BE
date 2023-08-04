package com.example.fa.service;

import com.example.fa.dto.BookDetailDto;
import com.example.fa.dto.BookTotalDto;
import com.example.fa.entity.Book;
import com.example.fa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private BookRepository bookRepository;
    @Value("${book.clientId}")
    private String client_id;

    @Value("${book.clientSecret}")
    private String client_secret;

    //책 검색
    public BookTotalDto search(String keyword){
        // Spring 제공 restTemplate
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity();

        //String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;    // JSON 결과
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", keyword)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, BookTotalDto.class).getBody();
    }

    private HttpEntity<String> getHttpEntity() { //헤더에 인증 정보 추가
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-Naver-Client-Id", client_id);
        httpHeaders.set("X-Naver-Client-Secret", client_secret);
        return new HttpEntity<>(httpHeaders);
    }

    //책 상세 정보
    public BookDetailDto searchInfo(String isbn){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> httpEntity = getHttpEntity();

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book_adv.json")
                .queryParam("d_isbn", isbn)
                .encode()
                .build()
                .toUri();
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, BookDetailDto.class).getBody();
    }
    //즐겨찾기 추가
    public Book like(BookDetailDto dto)  {

        Book book = dto.toEntity();
        System.out.println(book.getTitle());
        //bookRepository.save(book);
        return book;
    }

    //즐겨찾기 취소

}
