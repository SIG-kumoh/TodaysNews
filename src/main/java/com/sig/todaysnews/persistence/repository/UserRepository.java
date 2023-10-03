package com.sig.todaysnews.persistence.repository;

import com.sig.todaysnews.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
