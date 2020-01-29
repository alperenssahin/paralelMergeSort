package pgdp.threads;

import java.util.concurrent.RecursiveAction;

public class BetterParallelMergeSort extends RecursiveAction {
    volatile static Comparable[] array = null;
    volatile static Comparable[] helper = null;
    int low, high;
    private final int LIMIT = 1024;

    public BetterParallelMergeSort(Comparable[] array) {
        // TODO
        this(array, 0, array.length-1);
    }
    public BetterParallelMergeSort(Comparable[] array, int low, int high){
        if (ParallelMergeSort.array == null) {
            ParallelMergeSort.array = array;
        }
        if (ParallelMergeSort.helper == null) {
            ParallelMergeSort.helper = new Comparable[ParallelMergeSort.array.length];
        }
        this.low = low;
        this.high = high;
    }
    @Override
    protected void compute() {
        // TODO
        // TODO
        if (low < high) {
            int size = high - low;
            if(size > LIMIT){
                int mid = low + high / 2;
//            invokeAll(new ParallelMergeSort(array, low, mid), new ParallelMergeSort(array, mid + 1, high));
                new ParallelMergeSort(array, low, mid).fork();
                new ParallelMergeSort(array, mid + 1, high).fork();
            }else{
                MergeSort.mergesort(array,helper,low,high);
            }
        }
    }
    private void merge(int middle) {
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft].compareTo(helper[helperRight]) <= 0) {
                array[current] = helper[helperLeft++];
            } else {
                array[current] = helper[helperRight++];
            }
            current++;
        }

        while (helperLeft <= middle) {
            array[current++] = helper[helperLeft++];
        }
    }

}
