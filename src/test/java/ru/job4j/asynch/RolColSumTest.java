package ru.job4j.asynch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class RolColSumTest {

    @Test
    public void whenAsynchWork() throws ExecutionException, InterruptedException {
        int[][] twoDimArray = {{5, 7, 3}, {7, 0, 1}, {8, 1, 2}};
        RolColSum.Sums[] sums = RolColSum.asyncSum(twoDimArray);
        List<Integer> rsl = new ArrayList<>();
        for (RolColSum.Sums sum: sums) {
            rsl.add(sum.getRowSum());
            rsl.add(sum.getColSum());
        }
        List<Integer> check = List.of(15, 20, 8, 8, 11, 6);
        assertThat(rsl, is(check));
    }

    @Test
    public void whenStraightWork() {
        int[][] twoDimArray = {{5, 7, 3}, {7, 0, 1}, {8, 1, 2}};
        RolColSum.Sums[] sums = RolColSum.sum(twoDimArray);
        List<Integer> rsl = new ArrayList<>();
        for (RolColSum.Sums sum: sums) {
            rsl.add(sum.getRowSum());
            rsl.add(sum.getColSum());
        }
        List<Integer> check = List.of(15, 20, 8, 8, 11, 6);
        assertThat(rsl, is(check));
    }

}