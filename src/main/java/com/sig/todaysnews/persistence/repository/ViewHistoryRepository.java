package com.sig.todaysnews.persistence.repository;

import com.sig.todaysnews.persistence.entity.User;
import com.sig.todaysnews.persistence.entity.ViewHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
    @Query("SELECT v FROM ViewHistory v WHERE FUNCTION('DATE', v.regdate) < :date AND v.user = :user ORDER BY v.regdate DESC")
    List<ViewHistory> findAllWithUser(@Param("user") User user, @Param("date") LocalDate date, Pageable pageable);
}
