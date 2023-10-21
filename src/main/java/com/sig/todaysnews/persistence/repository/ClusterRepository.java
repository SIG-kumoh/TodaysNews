package com.sig.todaysnews.persistence.repository;

import com.sig.todaysnews.persistence.entity.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface ClusterRepository extends JpaRepository<Cluster, Long> {
    @Query("SELECT c FROM Cluster c WHERE c.sid = :sid AND c.date = :date")
    List<Cluster> findClustersBySidAndDate(@Param("sid") Long sid, @Param("date") Date date);
}
