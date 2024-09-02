package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    LinkedList list = new LinkedList();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Main linkedList = new Main();

        boolean search = true;

        while(search){

            System.out.println("\n==============================");
            System.out.println("       CustomLinked List     ");
            System.out.println("==============================");
            System.out.println("1 - Insert number at the beginning");
            System.out.println("2 - Insert number at the end");
            System.out.println("3 - Delete the given index number");
            System.out.println("4 - Delete the given value");
            System.out.println("5 - Search the given index number");
            System.out.println("6 - Search the given value");
            System.out.println("7 - Display");
            System.out.println("8 - Exit");
            Scanner scanner = new Scanner(System.in);

            System.out.print("Your Choice: ");
            try{
                int input = scanner.nextInt();
                if(input > 0 && input < 9){
                    switch (input){
                        case 1:
                            linkedList.insertFirstValue();
                            break;

                        case 2:
                            linkedList.insertLastValue();
                            break;

                        case 3:
                            linkedList.deleteByGivenIndex();
                            break;

                        case 4:
                            linkedList.deleteByGivenValue();
                            break;


                        case 5:
                            linkedList.searchByGivenIndex();
                            break;

                        case 6:
                            linkedList.searchByGivenValue();
                            break;

                        case 7:
                            linkedList.display();
                            break;


                        case 8:
                            System.exit(0);
                    }
                }
                else {
                    System.out.println("Enter correct number");
                    scanner.nextLine();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter correct Input");
                scanner.nextLine();
            }
        }

    }

    public void insertFirstValue(){

        String input;
        boolean search   = false;

        while(!search){

            System.out.println("Enter 's' for stop");
            System.out.print("Enter the number: ");

            input = scanner.next();

            if(input.equalsIgnoreCase("s")){
                break;
            }

            try {
                int number = Integer.parseInt(input);
                list.firstInsert(number);
            }
            catch (NumberFormatException e){
                System.out.println("Enter correct Integer Input");
                scanner.nextLine();
            }
        }
    }

    public void insertLastValue(){
        String input;
        boolean search   = false;

        while(!search){

            System.out.println("Enter 's' for stop");
            System.out.print("Enter the number: ");

            input = scanner.next();

            if(input.equalsIgnoreCase("s")){
                break;
            }

            try {
                int number = Integer.parseInt(input);
                list.lastInsert(number);
            }
            catch (NumberFormatException e){
                System.out.println("Enter correct Integer Input");
                scanner.nextLine();
            }
        }
    }

//    public void deleteFirstValue(){
//
//        list.deleteFirst();
//    }
//
//    public void deleteLastValue(){
//        list.deleteLast();
//    }



    public void deleteByGivenIndex(){
        int input;
        boolean search   = false;

        while(!search){
            System.out.print("Enter the index: ");


            try {
                input = scanner.nextInt();

                if( input >= 0 && input < list.getSize()){

                    list.deleteIndex(input);
                    System.out.println("Successfully Deleted item in index " + input);
                    break;
                }
                else{
                    System.out.println("Enter correct input");
                    scanner.nextLine();
                }

            }
            catch (InputMismatchException | IndexOutOfBoundsException e){
                System.out.println("Enter correct Input");
                scanner.nextLine();
            }
        }
    }

    public void deleteByGivenValue(){
        int input;
        boolean search   = false;

        while(!search){
            System.out.print("Enter the value: ");


            try {
                input = scanner.nextInt();

                if( input != list.searchValue(0)){

                    list.deleteValue(input);
                    System.out.println("Successfully Deleted value "+ input);
                    break;
                }
                else{
                    System.out.println("The value not inside the list");
                    scanner.nextLine();
                }

            }
            catch (InputMismatchException e){
                System.out.println("Enter correct Input");
                scanner.nextLine();
            }
            catch (NullPointerException e){
                System.out.println("The value not inside the list");
                scanner.nextLine();
            }
        }
    }



    public void searchByGivenValue(){
        int input;
        boolean search   = false;

        while(!search){
            System.out.print("Enter the value: ");


            try {
                input = scanner.nextInt();
                int validIndex = list.search(input);

                if( validIndex >= 0){
                    int index = list.search(input);
                    System.out.println("The " + input + " at index " + index );

                    break;
                }
                else{
                    System.out.println("The value not inside the list");
                    scanner.nextLine();
                }

            }
            catch (InputMismatchException e){
                System.out.println("Enter correct Input");
                scanner.nextLine();
            }
            catch (NullPointerException e){
                System.out.println("The value not inside the list");
                scanner.nextLine();
            }
        }
    }


    public void searchByGivenIndex(){
        int input;
        boolean search   = false;

        while(!search){
            System.out.print("Enter the index: ");


            try {
                input = scanner.nextInt();

                    if(input >= 0 && input < list.getSize()){
                        int value = list.searchIndex(input);
                        System.out.println("The "+ value + " is " +" at index " +input );
                        search = true;
                    }
                    else{
                        System.out.println("The index out of array size");
                        scanner.nextLine();
                    }

            }
            catch (InputMismatchException | IndexOutOfBoundsException e){
                System.out.println("Enter correct Input");
                scanner.nextLine();
            }
        }

    }

    public void display(){

        list.display();
    }


}