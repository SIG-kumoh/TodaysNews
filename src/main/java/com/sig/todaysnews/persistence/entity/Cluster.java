package com.sig.todaysnews.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cluster")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cluster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long clusterId;
    LocalDateTime regdate;
    String imgUrl;
    String title;
    String summary;
    @ManyToOne
    @JoinColumn(name = "section_id")
    Section section;
    Cluster relatedCluster;
    List<Article> articles;
}
