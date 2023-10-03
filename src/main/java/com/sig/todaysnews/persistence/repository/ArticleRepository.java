package com.sig.todaysnews.persistence.repository;

import com.sig.todaysnews.persistence.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
