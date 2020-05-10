package com.jay.mockito.spy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SpyTest {

    @Test
    public  void test(){
        List list = mock(List.class);
        assertThat(list.size(), is(0));
        when(list.size()).thenReturn(5);
        list.add("Jayhind");
        assertThat(list.size(), is(5));
    }

    @Test
    public  void testListSpy(){
        List list = spy(ArrayList.class);
        list.add("Jayhind");
        assertThat(list.size(), is(1));
        list.remove("Jayhind");
        assertThat(list.size(), is(0));
        when(list.size()).thenReturn(5);

        assertThat(list.size(), is(5));

        verify(list, times(1)).add("Jayhind");
        verify(list, times(1)).remove("Jayhind");
        verify(list, never()).clear();
    }
}
