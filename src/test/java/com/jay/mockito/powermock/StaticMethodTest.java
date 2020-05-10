package com.jay.mockito.powermock;

import com.sun.org.apache.xerces.internal.util.PropertyState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class StaticMethodTest {
    @Mock
    private Dependency dependency;

    @InjectMocks
    private SystemUnderTest systemUnderTest;

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        when(dependency.retrieveAllStats()).thenReturn(list);

        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(6)).thenReturn(150);

        int result = systemUnderTest.methodCallingAStaticMethod();

        assertThat(result, is(150));
        // Verify static method calls
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(6);
    }



}
