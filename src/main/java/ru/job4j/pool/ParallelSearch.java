package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {

    private final int[] array;
    private final int from;
    private final int to;
    private int numSearch;

    public ParallelSearch(int[] array, int from, int to, int numSearch) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.numSearch = numSearch;
    }

    @Override
    protected Integer compute() {
        if (to - from > 10) {
            int mid = (from + to) / 2;
            ParallelSearch task1 = new ParallelSearch(array, from, mid, numSearch);
            ParallelSearch task2 = new ParallelSearch(array, mid + 1, to, numSearch);
            task1.fork();
            task2.fork();
            int left = task1.join();
            int right = task2.join();
            return Math.max(left, right);
        } else {
            return search();
        }
  }

    private Integer search() {
        int index = -1;
        for (int i = from; i <= to; i++) {
            if (array[i] == numSearch) {
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int a = forkJoinPool.invoke(new ParallelSearch(
                ParallelSearch.getInitArray(100), 0, 99, 63));
        System.out.println(a);
    }

    public static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = i;
        }
        return array;
    }
}
