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
                    System.out.println("Enter Correct Input\n");
                    scanner.nextLine();
                }

                switch (shapeNo){

                    case 1:
                        while (true){
                            System.out.print("Enter the height1: ");
                            double squareHeight;
                            try{
                                squareHeight = scanner.nextDouble();

                                if( 0 < squareHeight){
                                    Quadrilateral square = new Quadrilateral(squareHeight, squareHeight);

                                    double areaOfSquare = square.area();
                                    double perimeterOfSquare = square.perimeter();

                                    System.out.println("Perimeter of Square" + " is " + perimeterOfSquare);
                                    System.out.println("Area of Square" + " is " + areaOfSquare + "\n");

                                    for(int i = 0; i < squareHeight; i++){
                                        for(int j = 0; j < squareHeight; j++){
                                            System.out.print("*" + "  ");
                                        }
                                        System.out.println();
                                    }
                                    System.out.println();

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
                            System.out.print("Enter the height: ");
                            double squareHeight;
                            try{
                                squareHeight = scanner.nextDouble();

                                if( 0 < squareHeight){
                                    while (true){
                                        System.out.print("Enter the width: ");
                                       try {
                                           double squareWidth = scanner.nextDouble();
                                           if( 0 < squareWidth){
                                               Quadrilateral rectangle = new Quadrilateral(squareHeight, squareWidth);

                                               double areaOfRectangle = rectangle.area();
                                               double perimeterOfRectangle = rectangle.perimeter();

                                               System.out.println("Perimeter of Square" + " is " + perimeterOfRectangle);
                                               System.out.println("Area of Square" + " is " + areaOfRectangle + "\n");

                                               for(int i = 0; i < squareHeight; i++){
                                                   for(int j = 0; j < squareWidth; j++){
                                                       System.out.print("*" + " ");
                                                   }
                                                   System.out.println();
                                               }
                                               System.out.println();

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
                        break;

                    case 3:

                        while (true){
                            System.out.println("Enter no 1 for Perimeter");
                            System.out.println("Enter no 2 for Area");
                            System.out.println("Enter no 3 for Back to Main Menu\n");

                            System.out.print("Enter the number: ");

                            try{
                                int numberInTriangle = scanner.nextInt();
                                if(0 <= numberInTriangle && numberInTriangle < 4){

                                    if(numberInTriangle == 1){
                                        //----------
                                        while (true){
                                            System.out.print("Enter the value of base_1: ");
                                            try {
                                                double base_1 = scanner.nextDouble();
                                                if( 0 < base_1){
                                                    while (true){
                                                        System.out.print("Enter the value of base_2: ");
                                                        try {
                                                            double base_2 = scanner.nextDouble();
                                                            if( 0 < base_2){
                                                                while (true){
                                                                    System.out.print("Enter the value of base_3: ");
                                                                    try {
                                                                        double base_3 = scanner.nextDouble();
                                                                        if( 0 < base_3){
                                                                            Triangle triangle_1 = new Triangle(base_1,base_2,base_3);
                                                                            double perimeterOfTriangle = triangle_1.perimeter();
                                                                            String nameTriangle = triangle_1.toString();
                                                                            System.out.println("\nPerimeter of "+nameTriangle +" is "+ perimeterOfTriangle);
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
                                                }
                                                else
                                                    System.out.println("Enter Correct Input");

                                            }
                                            catch (InputMismatchException e){
                                                System.out.println("Enter Correct Input");
                                                scanner.nextLine();
                                            }

                                        }
                                    } else if (numberInTriangle == 2) {

                                        //-----------------------------------
                                        while (true){
                                            System.out.print("Enter the base: ");
                                            double baseOfTriangle;
                                            try{
                                                baseOfTriangle = scanner.nextDouble();

                                                if( 0 < baseOfTriangle){
                                                    while (true){
                                                        System.out.print("Enter the height: ");
                                                        try {
                                                            double heightOfTriangle = scanner.nextDouble();
                                                            if( 0 < heightOfTriangle){
                                                                Triangle triangle_2 = new Triangle(baseOfTriangle,heightOfTriangle);

                                                                double area =triangle_2.area();
                                                                String name = triangle_2.toString();
                                                                System.out.println("Area of "+name + " is " + area +"\n" );
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
                                                }
                                                else
                                                    System.out.println("Enter Correct Input");

                                            }
                                            catch (InputMismatchException e){
                                                System.out.println("Enter Correct Input ");
                                                scanner.nextLine();
                                            }

                                        }


                                        //-------------------------------------
                                    } else
                                        break;

                                    //----------
                                }
                                else{
                                    System.out.println("Enter Correct Number");

                                }
                            }
                            catch (InputMismatchException e){
                                System.out.println("Enter Correct Input");
                                scanner.nextLine();
                            }
                        }
                        break;
                    case 4:

                        while (true){
                            System.out.print("Enter the radius: ");
                            double radius;
                            try{
                                radius = scanner.nextDouble();

                                if( 0 < radius){
                                    Circle circle = new Circle(radius);
                                    double area = circle.area();
                                    double perimeter = circle.perimeter();
                                    String name = circle.toString();

                                    System.out.println("Area of "+ name + " is " + area);
                                    System.out.println("Perimeter of "+ name + " is " + perimeter);
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

                    case 5:
                        //-------------------------------

                        while (true){
                            System.out.println("Enter no 1 for Perimeter");
                            System.out.println("Enter no 2 for Area");
                            System.out.println("Enter no 3 for Back to Main Menu\n");

                            System.out.print("Enter the number: ");

                            try{
                                int numberInRhombus = scanner.nextInt();
                                if(0 <= numberInRhombus && numberInRhombus < 4){

                                    if(numberInRhombus == 1){
                                        //----------
                                        while (true){
                                            System.out.print("Enter the value of side: ");
                                            try {
                                                double side = scanner.nextDouble();
                                                if(0 < side){
                                                    Rhombus rhombus = new Rhombus(side);
                                                    double areaOfRhombus = rhombus.perimeter();
                                                    String name = rhombus.toString();

                                                    System.out.println("Perimeter of "+ name + " is "+ areaOfRhombus+"\n");
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
                                    } else if (numberInRhombus == 2) {

                                        //-----------------------------------
                                        while (true){
                                            System.out.print("Enter the side_1: ");
                                            double side_1;
                                            try{
                                                side_1 = scanner.nextDouble();

                                                if( 0 < side_1){
                                                    while (true){
                                                        System.out.print("Enter the side_2: ");
                                                        try {
                                                            double side_2 = scanner.nextDouble();
                                                            if( 0 < side_2){
                                                                Triangle triangle_2 = new Triangle(side_1,side_2);

                                                                double area =triangle_2.area();
                                                                String name = triangle_2.toString();
                                                                System.out.println("Area of "+name + " is " + area +"\n" );
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
                                                }
                                                else
                                                    System.out.println("Enter Correct Input");

                                            }
                                            catch (InputMismatchException e){
                                                System.out.println("Enter Correct Input ");
                                                scanner.nextLine();
                                            }

                                        }


                                        //-------------------------------------
                                    } else
                                        break;

                                    //----------
                                }
                                else{
                                    System.out.println("Enter Correct Number");

                                }
                            }
                            catch (InputMismatchException e){
                                System.out.println("Enter Correct Input");
                                scanner.nextLine();
                            }
                        }
                        break;

                        //--------------------------------

                    case 6:
                        //------------

                        while (true){
                            System.out.println("Enter no 1 for Area");
                            System.out.println("Enter no 2 for Perimeter");
                            System.out.println("Enter no 3 for Back to Main Menu\n");

                            System.out.print("Enter the number: ");

                            try{
                                int numberInTrapezoid = scanner.nextInt();
                                if(0 <= numberInTrapezoid && numberInTrapezoid < 4){

                                    if(numberInTrapezoid == 1){
                                        //----------
                                        while (true){
                                            System.out.print("Enter the parallelSide_1: ");
                                            double parallelSide_1;
                                            try{
                                                parallelSide_1 = scanner.nextDouble();

                                                if( 0 < parallelSide_1){
                                                    while (true){
                                                        System.out.print("Enter the parallelSide_2: ");
                                                        try {
                                                            double parallelSide_2 = scanner.nextDouble();
                                                            if( 0 < parallelSide_2){
                                                                while (true){
                                                                    System.out.print("Enter the height: ");
                                                                    try {
                                                                        double height = scanner.nextDouble();
                                                                        if(0 < height){

                                                                            Trapezoid trapezoid = new Trapezoid(parallelSide_1,parallelSide_2,height);
                                                                            double areaOfTrapezoid = trapezoid.area();
                                                                            String name = trapezoid.toString();

                                                                            System.out.println("Area of "+ name + " is "+ areaOfTrapezoid +"\n");
                                                                            break;
                                                                        }
                                                                        else
                                                                            System.out.println("Enter Correct Number");
                                                                    }
                                                                    catch (InputMismatchException e){
                                                                        System.out.println("Enter Correct Input");
                                                                        scanner.nextLine();
                                                                    }

                                                                }
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

                                                }
                                                else
                                                    System.out.println("Enter Correct Input");

                                            }
                                            catch (InputMismatchException e){
                                                System.out.println("Enter Correct Input ");
                                                scanner.nextLine();
                                            }

                                        }
                                    } else if (numberInTrapezoid == 2) {

                                        //-----------------------------------
                                        while (true){
                                            System.out.print("Enter the value of parallelSide_1: ");
                                            try {
                                                double parallelSide_1 = scanner.nextDouble();
                                                if( 0 < parallelSide_1){
                                                    while (true){
                                                        System.out.print("Enter the value of parallelSide_2: ");
                                                        try {
                                                            double parallelSide_2 = scanner.nextDouble();
                                                            if( 0 < parallelSide_2){
                                                                while (true){
                                                                    System.out.print("Enter the value of side_3: ");
                                                                    try {
                                                                        double side_3 = scanner.nextDouble();
                                                                        if( 0 < side_3){
                                                                            while (true){
                                                                                System.out.print("Enter the value of side_4: ");
                                                                                try {
                                                                                    double side_4 = scanner.nextDouble();
                                                                                    if(0 < side_4){

                                                                                        Trapezoid trapezoid = new Trapezoid(parallelSide_1,parallelSide_2,side_3,side_4);

                                                                                        double perimeterOfTrapezoid = trapezoid.perimeter();
                                                                                        String name = trapezoid.toString();
                                                                                        System.out.println("Perimeter of "+ name + " is "+ perimeterOfTrapezoid +"\n");
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

                                                }
                                                else
                                                    System.out.println("Enter Correct Input");

                                            }
                                            catch (InputMismatchException e){
                                                System.out.println("Enter Correct Input");
                                                scanner.nextLine();
                                            }


                                        }

                                        //-------------------------------------
                                    } else
                                        break;

                                    //----------
                                }
                                else{
                                    System.out.println("Enter Correct Number");

                                }
                            }
                            catch (InputMismatchException e){
                                System.out.println("Enter Correct Input");
                                scanner.nextLine();
                            }
                        }
                        break;

                    case 7:
                        System.out.println("Exit");
                        System.exit(0);


                        //------------

                }
            }

        }
    }
}