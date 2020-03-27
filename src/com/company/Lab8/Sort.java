package com.company.Lab8;

public class Sort<T extends Comparable<T>> {

    T[] arr;

    public Sort(T[] t) {
        arr = (T[]) new Comparable[t.length];
        for (int i = 0; i < t.length; i++) {
            arr[i] = t[i];
        }
    }

    public void printArray() {
        for (T t : arr) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public void printArrayByLine() {
        for (T t : arr) System.out.println(t);
    }

    public void selectionSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            swapByIndex(i, getMinimumIndex(i));
        }
    }

    public void bubbleSort() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) < 0) {
                    T temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }

    public void insertionSort() {
        int j;
        for (int i = 1; i < arr.length; i++) {
            for (j = 0; j < i; j++) {
                if (arr[i].compareTo(arr[j]) < 0) {
                    swapByIndex(i, j);
                    break;
                }
            }
            for (j = j + 1; j < i; j++) {
                swapByIndex(i, j);
            }
        }
    }

    private int getMinimumIndex(int start) {
        T temp = arr[start];
        int index = start;
        for (int i = start+1; i < arr.length; i++) {
            if(temp.compareTo(arr[i]) > 0) {
                index = i;
                temp = arr[i];
            }
        }
        return index;
    }

    private void swapByIndex(int i, int j) {
        T temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public void mergeSort() {
        mergeSort(0, arr.length - 1);
    }

    private void mergeSort(int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            mergeSort(first, middle);
            mergeSort(middle + 1, last);
            merge(first, middle, middle + 1, last);
        }
    }

    private void merge(int leftFirst, int leftLast, int rightFirst, int rightLast) {
        T[] temp = (T[]) new Comparable[arr.length];
        int index = leftFirst;
        int currentIndex = leftFirst;
        while (leftFirst<=leftLast && rightFirst <= rightLast) {
            if ((Integer) arr[leftFirst] % 2 == 0 && (Integer) arr[rightFirst] % 2 != 0) {
                temp[index] = arr[rightFirst];
                rightFirst++;
            } else if ((Integer) arr[leftFirst] % 2 != 0 && (Integer) arr[rightFirst] % 2 == 0) {
                temp[index] = arr[leftFirst];
                leftFirst++;
            } else {
                if(arr[leftFirst].compareTo(arr[rightFirst]) < 0) {
                    temp[index] = arr[leftFirst];
                    leftFirst++;
                } else {
                    temp[index] = arr[rightFirst];
                    rightFirst++;
                }
            }
            index++;
        }
        while (leftFirst <= leftLast) {
            temp[index] = arr[leftFirst];
            leftFirst++;
            index++;
        }
        while (rightFirst <= rightLast) {
            temp[index] = arr[rightFirst];
            rightFirst++;
            index++;
        }
        for (int i = currentIndex; i <= rightLast; i++) {
            arr[i] = temp[i];
        }
    }

    public void quickSort() {
        quickSort(0, arr.length - 1);
    }

    private void quickSort(int first, int last) {
        if (first < last) {
            int split = getSplitIndex(first, last);
            quickSort(first, split - 1);
            quickSort(split + 1, last);
        }
    }

    private int getSplitIndex(int first, int last) {
        T pivot = arr[last];
        int pIndex = first;

        for (int i = first; i < last; i++) {
            if (pivot instanceof String) {
                String[] elem1 = ((String)arr[i]).split(" : ");
                Integer PID1 = Integer.valueOf(elem1[0].replace("P",""));
                Double price1 = Double.valueOf(elem1[2]);

                String[] elem2 = ((String)pivot).split(" : ");
                Integer PID2 = Integer.valueOf(elem2[0].replace("P",""));
                Double price2 = Double.valueOf(elem2[2]);

                if (price1.compareTo(price2) < 0) {
                    swapByIndex(pIndex, i);
                    pIndex++;
                } else if (price1.compareTo(price2) == 0 && PID1.compareTo(PID2) < 0) {
                    swapByIndex(pIndex, i);
                    pIndex++;
                }
            } else {
                if (arr[i].compareTo(pivot) <= 0) {
                    swapByIndex(pIndex, i);
                    pIndex++;
                }
            }
        }
        swapByIndex(pIndex, last);
        return pIndex;
    }
}
