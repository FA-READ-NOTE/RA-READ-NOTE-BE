package com.example.fa.dto;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookTotalDto {
    public Integer total;
    List<Items> items = new ArrayList<>();

    static class Items{
        public String title;
        public String image;
        public String author;

        public String isbn;

    }



}
