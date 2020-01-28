package pgdp.threads;

import java.util.concurrent.RecursiveAction;

public class BetterParallelMergeSort extends RecursiveAction {
    Comparable[] array = null;
    public BetterParallelMergeSort(Comparable[] array) {
        // TODO
        this.array = array;
    }
    
    @Override
    protected void compute() {
        // TODO
    }
}
