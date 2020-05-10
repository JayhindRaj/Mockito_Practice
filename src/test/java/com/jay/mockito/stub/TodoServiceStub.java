package com.jay.mockito.stub;

import com.jay.mockito.service.TodoService;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {

    /*Problem
     *
     * @param user
     * @return
     */
    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn Spring MVC", "",  "Learn Spring", "Learn to dance");
    }

    @Override
    public void deleteTodo(String todo) {

    }
}
