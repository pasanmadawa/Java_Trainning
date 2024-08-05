package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LeapYearSearcher {

    public void getTheNumber(){

        boolean search = true;
        do{
            System.out.println("---------Leap Year Searcher---------\n");
            System.out.println("Enter no 1 for get the Leap Year Calculator");
            System.out.println("Enter no 2 for exit\n");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number: ");

            int inputNumber = 0;

            try{
                inputNumber = scanner.nextInt();
                if(inputNumber <= 0 || 2 < inputNumber){
                    System.out.println("Enter the correct number");
                    continue;
                }

            }
            catch (InputMismatchException e){
                System.out.println("Enter correct input\n");
                scanner.nextLine();
            }
            {
                switch (inputNumber){

                    case 1:
                        System.out.print("Enter the year: ");
                        int inputYear = 0;
                        try{
                            inputYear = scanner.nextInt();
//                            if(inputYear % 4 == 0 && inputYear > 0){
//                                System.out.println("leap year");
//                                continue;
//                            } else if (inputYear < 0) {
//                                System.out.println("Enter correct input");
//
//                            }
//                            else{
//                                System.out.println("Not a leap year.");
//                                continue;
//                            }

                            LeapYearLogic leapYearLogic = new LeapYearLogic();
                            if(leapYearLogic.isLeapYear(inputYear)){
                                System.out.println("leap year");
                                continue;
                            }
                            else{
                                System.out.println("not a leap year");
                                continue;
                            }

                        }
                        catch (InputMismatchException e){
                            System.out.println("Enter the correct Input");
                            scanner.nextLine();
                            continue;
                        }


                    case 2:
                        search = false;
                        break;
                }
            }
            break;

        }
        while (search);
    }
}