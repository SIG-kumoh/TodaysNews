package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.ArticleDto;
import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.persistence.entity.Article;
import com.sig.todaysnews.persistence.entity.Cluster;

import java.util.List;
import java.util.stream.Collectors;

public interface NewsService {
    default ClusterDto makeClusterDto(Cluster cluster, List<ArticleDto> articleDtoList) {
        ClusterDto clusterDto = ClusterDto.builder()
                .clusterId(cluster.getClusterId())
                .title(cluster.getTitle())
                .imgUrl(cluster.getImgUrl())
                .summary(cluster.getSummary())
                .articleList(articleDtoList)
                .regdate(cluster.getRegdate())
                .chatNamespace("")
                .relatedClusterId(cluster.getRelatedCluster().getClusterId())
                .build();
        return clusterDto;
    }

    default ArticleDto entityToDto(Article article) {
        ArticleDto articleDto = ArticleDto.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .imgUrl(article.getImgUrl())
                .url(article.getUrl())
                .press(article.getPress())
                .regdate(article.getRegdate())
                .build();
        return articleDto;
    }

    default List<ArticleDto> MakeArticleDtoList(List<Article> articleList) {
        List<ArticleDto> articleDtoList = articleList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

        return articleDtoList;
    }
}
