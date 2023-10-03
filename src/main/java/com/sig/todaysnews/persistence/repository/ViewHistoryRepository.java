package com.sig.todaysnews.persistence.repository;

import com.sig.todaysnews.persistence.entity.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
}
