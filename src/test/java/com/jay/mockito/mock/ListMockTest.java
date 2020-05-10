package com.jay.mockito.mock;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListMockTest {

    @Test
    public void letsMockListSizeMethod() {
        List list = mock(List.class);
        when(list.size()).thenReturn(3);
        assertEquals(3, list.size());
    }


    @Test
    public void letsMockListSizeMethod_UsingBDD() {
        //Given
        List list = mock(List.class);
        //When
        given(list.size()).willReturn(3);
        //Then
        assertThat(list.size(), is(3));
    }
    @Test
    public void letsMockListSizeMethod_ReturnMultipleValues() {
        List list = mock(List.class);
        when(list.size()).thenReturn(3).thenReturn(5);
        assertEquals(3, list.size());
        assertEquals(5, list.size());
    }

    @Test
    public void letsMockListGetMethod() {
        List list = mock(List.class);
        when(list.get(0)).thenReturn("Jayhind").thenReturn("Rajpoot");
        assertEquals("Jayhind", list.get(0));
        assertEquals("Rajpoot", list.get(0));
        assertEquals(null, list.get(1));
        assertEquals(null, list.get(2));
    }

    @Test
    public void letsMockListGetMethod_WithNullValues() {
        List list = mock(List.class);
        assertEquals(null, list.get(1));
        assertEquals(null, list.get(0));
    }

    @Test
    public void letsMockListGetMethod_ArgumentMatcher() {
        List list = mock(List.class);
        when(list.get(anyInt())).thenReturn("Jayhind");
        assertEquals("Jayhind", list.get(0));
        assertEquals("Jayhind", list.get(1));
        assertEquals("Jayhind", list.get(2));
    }

    @Test
    public void letsMockListGetMethod_ArgumentMatcherBDD() {
        // Given
        List list = mock(List.class);

        //When
        given(list.get(anyInt())).willReturn("Jayhind");

        //Then
        assertThat(list.get(0), is("Jayhind"));
    }

    @Test(expected = RuntimeException.class)
    public void letsMockListGetMethod_ThrowAnException() {
        List list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Something went wrong"));
        list.get(0);
    }
}
