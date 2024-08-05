package org.example;

public class MergeSort {

    void merge(char[] arr, int left, int middle, int right){

        int leftArraySize = middle - left + 1;
        int rightArraySize = right - middle;

        char[] leftArray = new char[leftArraySize];
        char[] rightArray = new char[rightArraySize];

        for( int i = 0; i < leftArraySize; i++){
            leftArray[i] = arr[left + i];
        }
        for(int j = 0; j < rightArraySize; j++){
            rightArray[j] = arr[middle + 1 + j];
        }



        int i = 0, j = 0;

        int k = 0;
        while(i < leftArraySize && j < rightArraySize ){
            if(leftArray[i] <= rightArray[j]){
                arr[k] = leftArray[i];
                i++;
            }
            else {
               arr[k] = rightArray[j];
               j++;

            }
            k++;
        }
        while (i < leftArraySize){
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightArraySize){
            arr[k] = rightArray[j];
            j++;
            k++;
        }

    }

    void  sort(char[] arr, int first, int last){

        if (first < last){
            int middle = (first + (last - 1))/ 2;
            sort(arr,first,middle);
            sort(arr, middle + 1, last);

            merge(arr, first, middle,last);
        }
    }

//    static void printArray(char[] array){
//
//        for (char sortedArray : array) {
//            System.out.println(sortedArray + " ");
//        }
//        System.out.println();
//    }
}
