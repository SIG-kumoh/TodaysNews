package com.sig.todaysnews.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hot_cluster")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotCluster {
    @Id
    @Column(name = "cluster_id")
    private Long cluster_id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cluster_id")
    private Cluster cluster;
    private LocalDateTime regdate;
    private Integer size;
    private String roomName;
}
