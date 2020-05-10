package com.jay.mockito.business;

import com.jay.mockito.service.TodoService;

import java.util.List;

import static java.util.stream.Collectors.toList;

// TodoBusinessImpl SUT
// TodoService Dependency
public class TodoBusinessImpl {
    private TodoService todoService;

    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        List<String> filteredTodos = todos.stream().filter(t -> t.contains("Spring")).collect(toList());
        return filteredTodos;
    }

    public void deleteTodosNotRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodos(user);
        for(String todo: todos) {
            if(!todo.contains("Spring")) {
                todoService.deleteTodo(todo);
            }
        }
    }
}
