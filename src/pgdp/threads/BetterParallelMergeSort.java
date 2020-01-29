package pgdp.threads;

import java.util.concurrent.RecursiveAction;

public class BetterParallelMergeSort extends RecursiveAction {
     static volatile Comparable[] array = null;
     static volatile Comparable[] helper = null;
    int low, high;
    private final int LIMIT = 10240;

    public BetterParallelMergeSort(Comparable[] array) {
        // TODO
        this(array, 0, array.length-1);
    }
    public BetterParallelMergeSort(Comparable[] array, int low, int high){
        if (BetterParallelMergeSort.array == null) {
            BetterParallelMergeSort.array = array;
        }
        if (BetterParallelMergeSort.helper == null) {
            BetterParallelMergeSort.helper = new Comparable[BetterParallelMergeSort.array.length];
        }
        this.low = low;
        this.high = high;
    }
    @Override
    protected void compute() {
        // TODO
        if (low < high) {
            int size = high - low;
            if(size > LIMIT){
                int mid = low + high / 2;
//            invokeAll(new ParallelMergeSort(BetterParallelMergeSort.array, low, mid), new ParallelMergeSort(BetterParallelMergeSort.array, mid + 1, high));
                new BetterParallelMergeSort(array, low, mid).fork();
                new BetterParallelMergeSort(array, mid + 1, high).fork();
            }else{
                MergeSort.mergesort(BetterParallelMergeSort.array,BetterParallelMergeSort.helper,low,high);
            }
        }
    }

}
