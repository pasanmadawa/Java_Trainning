package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearSearcher {

    public void getTheNumber(){

        boolean search = true;
        while (search){
            System.out.println("---------Leap Year Searcher---------\n");

            while (true){
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the year: ");

                try {
                    int leapYear = scanner.nextInt();

                    if(leapYear % 4 == 0){
                        System.out.println(leapYear + " is a leap year.\n");
                        break;
                    }
                    else{
                        System.out.println(leapYear + " is not a leap year.\n");
                        break;
                    }


                }
                catch (InputMismatchException e) {
                    System.out.println("Enter the correct Input");

                }
            }

        }
    }
}
