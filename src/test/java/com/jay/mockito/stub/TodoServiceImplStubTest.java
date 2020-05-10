package com.jay.mockito.stub;

import com.jay.mockito.business.TodoBusinessImpl;
import com.jay.mockito.service.TodoService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TodoServiceImplStubTest {

    @Test
    public void testRetrieveTodosRelatedToSpring_UsingAStub() {
        TodoService todoService = new TodoServiceStub();
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        List<String> s = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(2, s.size());
    }
}
