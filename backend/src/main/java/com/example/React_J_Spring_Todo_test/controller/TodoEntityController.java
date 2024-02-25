package com.example.React_J_Spring_Todo_test.controller;

import com.example.React_J_Spring_Todo_test.entity.TodoEntity;
import com.example.React_J_Spring_Todo_test.service.TodoEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todoEntity")
@RequiredArgsConstructor
public class TodoEntityController {
    private final TodoEntityService todoEntityService;

    @PostMapping("/{title}")
    public String addTodoEntity(@PathVariable("title") String title) {
        return todoEntityService.addTodoEntity(title);
    }

    @GetMapping("/titles")
    public List<TodoEntity> showTodoEntities() {
        return todoEntityService.showTodoEntity();
    }

    @DeleteMapping("/{title}/delete")
    public String deleteByTitleTodoEntity(@PathVariable("title") String title) {
        return todoEntityService.deleteByTitleTodoEntity(title);
    }

    @PutMapping("/{title}/update")
    public String updateByTitleTodoEntity(@PathVariable("title") String title) {
        return todoEntityService.updateCompletedByTitleTodoEntity(title);
    }
}
