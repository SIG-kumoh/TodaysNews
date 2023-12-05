package com.sig.todaysnews.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column(name = "cluster_id")
    private Long clusterId;
    private LocalDateTime regdate;
    private String imgUrl;
    private String title;
    private String summary;
    private Integer size;
    private String words;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @OneToOne
    @JoinColumn(name = "centroid_id")
    private Article centroid;
    @OneToOne
    @JoinColumn(name = "related_cluster_id")
    private Cluster relatedCluster;
    @OneToMany(mappedBy = "cluster")
    private List<Article> articles = new ArrayList<>();
}
