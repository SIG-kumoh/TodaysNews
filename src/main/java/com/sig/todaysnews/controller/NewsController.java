package com.sig.todaysnews.controller;

import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.sevice.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

public class NewsController {
    NewsService newsService;

    @GetMapping("/news/proposal")
    public ResponseEntity<List<ClusterDto>> getProposal() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/news/section")
    public ResponseEntity<List<ClusterDto>> getSection(Long sid, Date date) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/news.hottopic")
    public ResponseEntity<List<ClusterDto>> getHotClusters() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/news/cluster")
    public ResponseEntity<ClusterDto> getCluster(Long cid) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/news/cluster")
    public ResponseEntity<Void> deleteCluster(Long cid) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("//news/article")
    public ResponseEntity<Void> deleteArticle(Long aid) {
        return ResponseEntity.ok().build();
    }
}
