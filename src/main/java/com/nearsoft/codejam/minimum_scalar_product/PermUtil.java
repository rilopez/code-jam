package com.nearsoft.codejam.minimum_scalar_product;

import java.util.Arrays;

//see http://stackoverflow.com/questions/2799078/permutation-algorithm-without-recursion-java
public class PermUtil {
    private Integer[] arr;
    private int[] permSwappings;

    public PermUtil(Integer[] arr) {
        this(arr, arr.length);
    }

    public PermUtil(Integer[] arr, int permSize) {
        this.arr = arr.clone();
        this.permSwappings = new int[permSize];
        for (int i = 0; i < permSwappings.length; i++) {
            permSwappings[i] = i;
        }
    }

    public Integer[] next() {
        if (arr == null) {
            return null;
        }

        Integer[] res = Arrays.copyOf(arr, permSwappings.length);
        //Prepare next
        int i = permSwappings.length - 1;
        while (i >= 0 && permSwappings[i] == arr.length - 1) {
            swap(i, permSwappings[i]); //Undo the swap represented by permSwappings[i]
            permSwappings[i] = i;
            i--;
        }

        if (i < 0) {
            arr = null;
        } else {
            int prev = permSwappings[i];
            swap(i, prev);
            int next = prev + 1;
            permSwappings[i] = next;
            swap(i, next);
        }

        return res;
    }

    private void swap(int i, int j) {
        Integer tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
