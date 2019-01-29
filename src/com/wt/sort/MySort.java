package com.wt.sort;

public class MySort {

    public static void main(String[] args) {
        int[] test = getArray(10000000);

        // bubble sort
        long begin = System.currentTimeMillis();
        // int[] result1 = bubbleSort(test);
        // for (int r : result1) {
        // System.out.println(r);
        // }
        long end = System.currentTimeMillis();
        System.out.println("bubble sort use time:" + (end - begin));

        // insertion sort
        begin = System.currentTimeMillis();
        int[] result2 = insertionSort(test);
        // for (int r : result2) {
        // System.out.println(r);
        // }
        end = System.currentTimeMillis();
        System.out.println("insertion sort use time:" + (end - begin));

        // merge sort
        begin = System.currentTimeMillis();
        mergeSort(test, new int[test.length], 0, test.length - 1);
        // for (int r : test) {
        // System.out.println(r);
        // }
        end = System.currentTimeMillis();
        System.out.println("use time:" + (end - begin));

    }

    private static int[] getArray(int length) {
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = (int) (Math.random() * 100);
        }

        return result;
    }

    /**
     * 归并排序
     * 
     * @param array
     * @return
     */
    public static void mergeSort(int[] array, int[] temp, int left, int right) {
        if (left >= right) {
            return;
        }

        int center = (left + right) / 2;

        mergeSort(array, temp, left, center);
        mergeSort(array, temp, center + 1, right);
        merge(array, temp, left, center + 1, right);
    }

    private static void merge(int[] array, int[] tmp, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int num = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (array[leftPos] <= array[rightPos]) {
                tmp[tmpPos++] = array[leftPos++];
            } else {
                tmp[tmpPos++] = array[rightPos++];
            }
        }

        while (leftPos <= leftEnd) {
            tmp[tmpPos++] = array[leftPos++];
        }

        while (rightPos <= rightEnd) {
            tmp[tmpPos++] = array[rightPos++];
        }

        for (int i = 0; i < num; i++, rightEnd--) {
            array[rightEnd] = tmp[rightEnd];
        }
    }

    /**
     * 冒泡排序
     * 
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        int len = array.length - 1;

        if (len < 0) {
            return array;
        }

        while (len >= 1) {
            for (int i = 0; i < len; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
            len--;
        }

        return array;
    }

    /**
     * 插入排序
     * 
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        int len = array.length;

        if (len <= 0) {
            return array;
        }

        int j;

        for (int i = 1; i < len; i++) {
            int tmp = array[i];

            for (j = i; j > 0 && tmp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }

            array[j] = tmp;
        }

        return array;
    }
}
