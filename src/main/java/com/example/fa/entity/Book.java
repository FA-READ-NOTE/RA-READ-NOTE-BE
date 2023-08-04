package com.example.fa.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long book_id;

    private String title;

    private String writer;
    @Column(columnDefinition="LONGTEXT")
    private String cover;



}
