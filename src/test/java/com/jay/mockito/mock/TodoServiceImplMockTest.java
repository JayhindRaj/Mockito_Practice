package com.jay.mockito.mock;

import com.jay.mockito.business.TodoBusinessImpl;
import com.jay.mockito.service.TodoService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoServiceImplMockTest {
    private static TodoService todoService;

    @BeforeClass
    public static void setup() {
        todoService = mock(TodoService.class);
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_UsingMock() {
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
        when(todoService.retrieveTodos("dummy")).thenReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        List<String> s = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(2, s.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_EmptyList() {
        List<String> todos = Collections.emptyList();
        when(todoService.retrieveTodos("dummy")).thenReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        List<String> s = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(0, s.size());
    }
}
