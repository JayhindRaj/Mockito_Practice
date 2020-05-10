package com.jay.mockito.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class PrivateMethodTest {
    @Mock
    private Dependency dependency;

    @InjectMocks
    private SystemUnderTest systemUnderTest;

    @Test
    public void test() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3);
        when(dependency.retrieveAllStats()).thenReturn(list);
        long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");
        assertThat(result, is(6L));
    }
}
