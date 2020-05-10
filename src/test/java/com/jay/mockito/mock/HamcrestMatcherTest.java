package com.jay.mockito.mock;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class HamcrestMatcherTest {

    @Test
    public void testList() {
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);

        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(99, 105));
        assertThat(scores, hasItem(101));

        // everyItem > 95
        assertThat(scores, everyItem(greaterThan(95)));

        // everyItem < 106
        assertThat(scores, everyItem(lessThan(106)));
    }

    @Test
    public void testString() {
        // String Matcher
        assertThat("", isEmptyString());
        assertThat(null, isEmptyOrNullString());
    }

    @Test
    public void testArray() {
        // Arrays
        Integer[] marks = {43, 57, 65, 86};
        assertThat(marks, arrayWithSize(4));
        assertThat(marks, arrayContaining(43, 57, 65, 86));
        assertThat(marks, arrayContainingInAnyOrder(65, 43, 57,  86));
    }
}
