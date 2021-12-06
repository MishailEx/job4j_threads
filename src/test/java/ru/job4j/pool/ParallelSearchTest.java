package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ParallelSearchTest {
    @Test
    public void whenIndexCorrectThen63() {
        int[] array = ParallelSearch.getInitArray(100);
        int result = ParallelSearch.searchIndex(array, 0, 99, 63);
        assertThat(63, is(result));
    }

    @Test
    public void whenIndexIncorrectThenMinusOne() {
        int[] array = ParallelSearch.getInitArray(100);
        int result = ParallelSearch.searchIndex(array, 0, 99, 120);
        assertThat(-1, is(result));
    }
}