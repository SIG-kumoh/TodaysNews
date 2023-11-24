package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.ArticleDto;
import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.persistence.entity.Article;
import com.sig.todaysnews.persistence.entity.Cluster;
import com.sig.todaysnews.persistence.entity.HotCluster;

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

    default ClusterDto makeHotClusterDto(HotCluster hotCluster, List<ArticleDto> articleDtoList) {
        ClusterDto clusterDto = ClusterDto.builder()
                .clusterId(hotCluster.getCluster().getClusterId())
                .title(hotCluster.getCluster().getTitle())
                .imgUrl(hotCluster.getCluster().getImgUrl())
                .summary(hotCluster.getCluster().getSummary())
                .articleList(articleDtoList)
                .regdate(hotCluster.getCluster().getRegdate())
                .chatNamespace(hotCluster.getNamespace())
                .relatedClusterId(hotCluster.getCluster().getRelatedCluster().getClusterId())
                .build();
        return clusterDto;
    }

    default ArticleDto entityToDto(Article article) {
        String content = article.getContent().substring(0, Math.min(50, article.getContent().length()-1)) + "...";
        ArticleDto articleDto = ArticleDto.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .imgUrl(article.getImgUrl())
                .url(article.getUrl())
                .content(content)
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
