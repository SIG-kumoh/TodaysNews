package com.sig.todaysnews.controller;

import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.sevice.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private NewsService newsService;

    @GetMapping("/proposal")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<ClusterDto>> getProposal() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/section")
    public ResponseEntity<List<ClusterDto>> getSection(
            Long sid,
            Date date
    ) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/hottopic")
    public ResponseEntity<List<ClusterDto>> getHotClusters() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/cluster")
    public ResponseEntity<ClusterDto> getCluster(
            Long cid
    ) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/cluster")
    public ResponseEntity<Void> deleteCluster(
            Long cid
    ) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/article")
    public ResponseEntity<Void> deleteArticle(
            Long aid
    ) {
        return ResponseEntity.ok().build();
    }
}
