package com.example.MoneyManage.Repo;

import com.example.MoneyManage.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<TestEntity, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<TestEntity> findByEmail(String email);

}

