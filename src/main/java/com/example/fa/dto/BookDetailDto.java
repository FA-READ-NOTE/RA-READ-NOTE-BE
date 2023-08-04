package com.example.fa.dto;
import com.example.fa.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailDto {

    List<Items> items = new ArrayList<>();

    @Getter
    public static class Items{
        public String title;
        public String image;
        public String author;

        public String isbn;

    }

    @Builder
    public Book toEntity() {
        return Book.builder()
                .title(items.get(0).title)
                .writer(items.get(0).author)
                .cover(items.get(0).image)
                .build();
    }

}
