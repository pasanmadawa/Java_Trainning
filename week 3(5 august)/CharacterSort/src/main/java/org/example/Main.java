package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean search = true;

        while(search){
            System.out.println("----------Array Sort----------");
            System.out.println("Enter no 1 for get array sorter");
            System.out.println("Enter no 2 for Exit");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the no: ");

            try{
                int number = scanner.nextInt();

                if(number > 0 && 3 > number){
                    switch (number){
                        case 1:
                            boolean getArray = true;
                            while (getArray){

                                System.out.print("Enter the length of array: ");
                                try {
                                    int length = scanner.nextInt();
                                    char[] array = new char[length];


                                    try{
                                        System.out.println("Enter the value: ");
                                        for(int i = 0; i < length; i++){
                                            array[i] = scanner.next().charAt(0);
                                        }

                                        for(char printArray : array){
                                            System.out.print(printArray + " ");
                                        }
                                        System.out.println();
                                        MergeSort mergeSort = new MergeSort();
                                        mergeSort.sort(array,0, array.length - 1 );

                                        for(char sortArray : array){
                                            System.out.print(sortArray + " ");
                                        }

                                        System.out.println();
                                        getArray = false;

                                    }
                                    catch (InputMismatchException e){
                                        System.out.println("Enter correct input");
                                        scanner.nextLine();
                                    }
                                }
                                catch (InputMismatchException e){
                                    System.out.println("Enter correct input");
                                    scanner.nextLine();
                                }

                            }
                            break;

                        case 2:
                            System.exit(0);
                    }
                }
                else {
                    System.out.println("Enter correct input");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter correct input");
            }
        }
    }
}