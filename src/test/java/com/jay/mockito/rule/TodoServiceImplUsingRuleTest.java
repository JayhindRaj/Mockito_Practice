package com.jay.mockito.rule;

import com.jay.mockito.business.TodoBusinessImpl;
import com.jay.mockito.service.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class TodoServiceImplUsingRuleTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoBusinessImpl todoBusiness;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpring_UsingBDD() {
        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
        given(todoService.retrieveTodos("dummy")).willReturn(todos);

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
        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance");
        given(todoService.retrieveTodos("Dummy")).willReturn(todos);

        //When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //Then
//        verify(todoService, times(1)).deleteTodo("Learn to dance");
        then(todoService).should(times(1)).deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getValue(), is("Learn to dance"));
    }

    @Test
    public void deleteTodosNotRelatedToSpring_UsingBDD1_ArgumentCaptureMultiple() {
        //Given
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to dance", "Learn play piano");
        given(todoService.retrieveTodos("Dummy")).willReturn(todos);

        //When
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //Then
        then(todoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_EmptyList() {
        List<String> todos = Collections.emptyList();
        when(todoService.retrieveTodos("dummy")).thenReturn(todos);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(0, filteredTodos.size());
    }
}
