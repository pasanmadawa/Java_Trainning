package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while (true){
            System.out.println("------Shapes------\n");
            System.out.println("Enter no: 1 for Square");
            System.out.println("Enter no: 2 for Rectangle");
            System.out.println("Enter no: 3 for Triangle");
            System.out.println("Enter no: 4 for Circle");
            System.out.println("Enter no: 5 for Rhombus");
            System.out.println("Enter no: 6 for Trapezoid");
            System.out.println("Enter no: 7 for Exit\n");

            Scanner scanner = new Scanner(System.in);
            boolean shape = false;

            while (!shape){

                System.out.print("Enter the shape no: ");

                int shapeNo = 0;
                try {
                    shapeNo = scanner.nextInt();

                    if( shapeNo <= 0 || 8 < shapeNo){
                        System.out.println("Enter Correct Number");
                        continue;
                    }
                    shape = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter Correct Input");
                    scanner.nextLine();
                }

                switch (shapeNo){

                    case 1:
                        while (true){
                            System.out.print("Enter the height1: ");
                            int squareHeight;
                            try{
                                squareHeight = scanner.nextInt();

                                if( 0 < squareHeight){
                                    Quadrilateral obj1 = new Quadrilateral(squareHeight, squareHeight);

                                    int area1 = obj1.area();
                                    int perimeter1 = obj1.perimeter();

                                    System.out.println("Perimeter of Square" + " is " + perimeter1);
                                    System.out.println("Area of Square" + " is " + area1 + "\n");
                                    break;
                                }
                                else
                                    System.out.println("Enter Correct Input");

                            }
                            catch (InputMismatchException e){
                                System.out.println("Enter Correct Input");
                                scanner.nextLine();
                            }
                        }
                        break;
                    case 2:
                        while (true){
                            System.out.print("Enter the height2: ");
                            int squareHeight;
                            try{
                                squareHeight = scanner.nextInt();

                                if( 0 < squareHeight){
                                    while (true){
                                        System.out.print("Enter the width: ");
                                       try {
                                           int squareWidth = scanner.nextInt();
                                           if( 0 < squareWidth){
                                               Quadrilateral obj2 = new Quadrilateral(squareHeight, squareWidth);

                                               int area1 = obj2.area();
                                               int perimeter1 = obj2.perimeter();

                                               System.out.println("Perimeter of Square" + " is " + perimeter1);
                                               System.out.println("Area of Square" + " is " + area1 + "\n");
                                               break;
                                           }
                                           else
                                               System.out.println("Enter Correct Input");

                                       }
                                       catch (InputMismatchException e){
                                           System.out.println("Enter Correct Input");
                                           scanner.nextLine();
                                       }
                                    }
                                }
                                else
                                    System.out.println("Enter Correct Input");

                            }
                            catch (InputMismatchException e){
                                System.out.println("Enter Correct Input");
                                scanner.nextLine();
                            }
                            break;
                        }

                    case 3:

                }
            }

        }
    }
}