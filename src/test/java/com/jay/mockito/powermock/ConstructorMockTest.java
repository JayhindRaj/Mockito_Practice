package com.jay.mockito.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class ConstructorMockTest {

    @InjectMocks
    private SystemUnderTest systemUnderTest;

    @Mock
    private ArrayList mockList;

    @Test
    public void test() throws Exception {
        when(mockList.size()).thenReturn(10);
        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
        int size = systemUnderTest.methodUsingAnArrayListConstructor();
        assertEquals(10, size);
    }

}
