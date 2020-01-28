package pgdp.threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {
    Comparable[] array = null;
    int low, high;

    public ParallelMergeSort(Comparable[] array) {
        // TODO
        this(array, 0, array.length);
    }

    public ParallelMergeSort(Comparable[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        // TODO
        if (low < high) {
            int mid = low + high / 2;
            invokeAll(new ParallelMergeSort(array, low, mid), new ParallelMergeSort(array, mid + 1, high));
            merge(mid);
        }
    }

    private void merge(int mid) {
        Comparable[] left = Arrays.copyOfRange(array, low, mid + 1);
        Comparable[] right = Arrays.copyOfRange(array, mid + 1, high + 1);
        int f = low;

        int li = 0, ri = 0;
        while (li < left.length && ri < right.length) {
            if (left[li].compareTo(right[ri]) <= 0) {
                array[f++] = left[li++];
            } else {
                array[f++] = right[ri++];
            }
        }
        while (li < left.length) {
            array[f++] = left[li++];
        }

        while (ri < right.length) {
            array[f++] = right[ri++];
        }
    }

}
