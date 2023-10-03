package com.sig.todaysnews.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long articleId;
    LocalDateTime regdate;
    String imgUrl;
    String url;
    String press;
    String title;
    String content;
    String writer;
    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section;
    @ManyToOne
    @JoinColumn(name = "cluster_id")
    Cluster cluster;
}
