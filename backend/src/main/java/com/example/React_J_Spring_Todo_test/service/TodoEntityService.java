package com.example.React_J_Spring_Todo_test.service;
import com.example.React_J_Spring_Todo_test.entity.TodoEntity;
import com.example.React_J_Spring_Todo_test.repository.TodoEntityJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoEntityService {
    private final TodoEntityJpaRepository todoEntityJpaRepository;

    @Transactional
    public String addTodoEntity(String title) {
        List<TodoEntity> existingTodos = todoEntityJpaRepository.findByTitle(title);
        // If a TodoEntity with the same title exists, return a message or handle as needed
        if (!existingTodos.isEmpty()) {
            return "A todo with title '" + title + "' already exists.";
        }
        TodoEntity todoEntity = new TodoEntity(title, false);
        todoEntityJpaRepository.save(todoEntity);
        return title;
    }

    public List<TodoEntity> showTodoEntity() {
        List<TodoEntity> todos = todoEntityJpaRepository.findAll();
        return todos;
    }

    @Transactional
    public String deleteByTitleTodoEntity(String title) {
        List<TodoEntity> existTodo = todoEntityJpaRepository.findByTitle(title);
        if (existTodo.isEmpty()) {
            return "Todo content " + title + "is not existed";
        }
        todoEntityJpaRepository.deleteByTitle(title);
        return "Todo content" + title + " is successfully deleted";
    }

    @Transactional
    public List<TodoEntity> deleteAllTodoEntity() {
        todoEntityJpaRepository.deleteAll();
        return null;
    }

    public String updateCompletedByTitleTodoEntity(String title){
        List<TodoEntity> existTodo = todoEntityJpaRepository.findByTitle(title);
        if (existTodo.isEmpty()) {
            return "Todo content' " + title + " 'that you want to update is not existed";
        }
        for (TodoEntity todo : existTodo) {
            todo.setCompleted(!todo.isCompleted());
            todoEntityJpaRepository.save(todo);
        }
        return "Todo content " + title + " is successfully updated";
    }
}
