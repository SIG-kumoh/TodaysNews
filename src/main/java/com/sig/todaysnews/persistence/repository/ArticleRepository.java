package com.sig.todaysnews.persistence.repository;

import com.sig.todaysnews.persistence.entity.Article;
import com.sig.todaysnews.persistence.entity.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.cid = :cid")
    List<Article> findArticlesByCid(@Param("cid") Long cid);
}
