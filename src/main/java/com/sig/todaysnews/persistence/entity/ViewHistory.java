package com.sig.todaysnews.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "view_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long viewHistoryId;
    LocalDateTime regdate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @JoinColumn(name = "centroid_id")
    Article centroid;
}
