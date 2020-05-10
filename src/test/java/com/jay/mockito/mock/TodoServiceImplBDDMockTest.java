package com.jay.mockito.mock;

import com.jay.mockito.business.TodoBusinessImpl;
import com.jay.mockito.service.TodoService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoServiceImplBDDMockTest {
    private static TodoService todoService;

    @BeforeClass
    public static void setup() {
        todoService = mock(TodoService.class);
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_UsingBDD() {
        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
        given(todoService.retrieveTodos("dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        //When
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("dummy");

        //Then
        assertThat(filteredTodos.size(), is(2));
    }

    @Test
    public void deleteTodosNotRelatedToSpring_UsingBDD() {
        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
        given(todoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        //When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //Then
        verify(todoService, times(1)).deleteTodo("Learn to dance");
        verify(todoService, never()).deleteTodo("Learn Spring MVC");
        verify(todoService, never()).deleteTodo("Learn Spring");
    }

    @Test
    public void deleteTodosNotRelatedToSpring_UsingBDD1() {
        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
        given(todoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        //When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //Then
//        verify(todoService, times(1)).deleteTodo("Learn to dance");
        then(todoService).should(times(1)).deleteTodo("Learn to dance");
        then(todoService).should(never()).deleteTodo("Learn Spring MVC");
        then(todoService).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    public void deleteTodosNotRelatedToSpring_UsingBDD1_ArgumentCapture() {
        // Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
        given(todoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        //When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //Then
//        verify(todoService, times(1)).deleteTodo("Learn to dance");
        then(todoService).should(times(1)).deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to dance"));
    }

    @Test
    public void deleteTodosNotRelatedToSpring_UsingBDD1_ArgumentCaptureMultiple() {
        // Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance", "Learn play piano");
        given(todoService.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);

        //When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //Then
//        verify(todoService, times(1)).deleteTodo("Learn to dance");
        then(todoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_EmptyList() {
        List<String> todos = Collections.emptyList();
        when(todoService.retrieveTodos("dummy")).thenReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(0, filteredTodos.size());


    }
}
