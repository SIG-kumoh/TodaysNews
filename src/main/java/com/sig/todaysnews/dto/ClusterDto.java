package com.sig.todaysnews.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClusterDto {
    private Long clusterId;
    private String title;
    private String imgUrl;
    private String summary;
    private List<String> words;
    private Integer size;
    private List<ArticleDto> articleList;
    private LocalDateTime regdate;
    private String roomName;
    private List<ClusterDto> relatedClusters;
}
