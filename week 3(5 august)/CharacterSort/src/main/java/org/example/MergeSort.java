package org.example;

import java.util.Arrays;

public class MergeSort {

//    void merge(int[] arr, int left, int middle, int right){
//
//        int leftArraySize = middle - left + 1;
//        int rightArraySize = right - middle;
//
//        int[] leftArray = new int[leftArraySize];
//        int[] rightArray = new int[rightArraySize];
//
//        for( int i = 0; i < leftArraySize; i++){
//            leftArray[i] = arr[left + i];
//        }
//        for(int j = 0; j < rightArraySize; j++){
//            rightArray[j] = arr[middle + 1 + j];
//        }
//
//
//
//        int i = 0, j = 0;
//
//        int k = 0;
//        while(i < leftArraySize && j < rightArraySize ){
//            if(leftArray[i] <= rightArray[j]){
//                arr[k] = leftArray[i];
//                i++;
//            }
//            else {
//               arr[k] = rightArray[j];
//               j++;
//
//            }
//            k++;
//        }
//        while (i < leftArraySize){
//            arr[k] = leftArray[i];
//            i++;
//            k++;
//        }
//        while (j < rightArraySize){
//            arr[k] = rightArray[j];
//            j++;
//            k++;
//        }
//
//    }
//
//    void  sort(int[] arr, int first, int last){
//
//        if (first < last){
//            int middle = (first + (last - 1))/ 2;
//            sort(arr,first,middle);
//            sort(arr, middle + 1, last);
//
//            merge(arr, first, middle,last);
//        }
//    }

    public int[] mergeSort(int[] arr){

        if(arr.length == 1){
            return arr;
        }

        int mid = arr.length / 2;

        int[] leftArray = mergeSort(Arrays.copyOfRange(arr, 0,mid));
        int[] rightArray = mergeSort(Arrays.copyOfRange(arr, mid,arr.length));

        return merge(leftArray, rightArray);
    }

    private  int[] merge(int[] firstArray, int[] secondArray){

        int[] sortedArray = new int[firstArray.length + secondArray.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while( i < firstArray.length && j < secondArray.length){

            if( firstArray[i] < secondArray[j]){
                sortedArray[k] = firstArray[i];
                i++;
            }
            else{
                sortedArray[k] = secondArray[j];
                j++;
            }
            k++;

        }

        while ( i < firstArray.length){
            sortedArray[k] = firstArray[i];
            i++;
            k++;
        }

        while (j < secondArray.length){
            sortedArray[k] = secondArray[j];
            j++;
            k++;

        }

        return sortedArray;

    }

}

