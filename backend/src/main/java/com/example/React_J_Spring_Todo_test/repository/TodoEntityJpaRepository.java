package com.example.React_J_Spring_Todo_test.repository;

import com.example.React_J_Spring_Todo_test.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoEntityJpaRepository extends JpaRepository<TodoEntity, Long>{


    void deleteByTitle(String title);
    List<TodoEntity> findByTitle(String title);
}
