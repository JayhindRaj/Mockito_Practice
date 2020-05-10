package com.jay.mockito.service;

import java.util.List;

public interface TodoService {
    List<String> retrieveTodos(String user);
    void deleteTodo(String todo);
}
