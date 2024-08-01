package org.example;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Manager {

    static LinkedList<Items> items = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Manager manager = new Manager();

        boolean search = true;
        while (search){

            System.out.println("\n---------------Cart Manager------------------");
            System.out.println("Enter No 1 for Add Items");
            System.out.println("Enter No 2 for delete Items");
            System.out.println("Enter No 3 for update");
            System.out.println("Enter No 4 for Display Items");
            System.out.println("Enter No 5 for Exit\n");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Number: ");

            try {
                int input = scanner.nextInt();
                if(input > 0 && input < 6){
                    switch (input){
                        case 1:
                            manager.itemsAdd();
                            break;

                        case 2:
                            manager.deleteItems();
                            break;

                        case 3:
                            break;

                        case 4:
                            manager.displayItems();
                            break;

                        case 5:
                            System.exit(0);
                    }
                }
                else{
                    System.out.println("Enter correct Number");
                    scanner.nextLine();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter correct number");
                scanner.nextLine();
            }
        }

    }

    public void itemsAdd(){

        int itemNumber = 0;
        boolean search = false;
        System.out.println("\nEnter number 1 for add Electronic items");
        System.out.println("Enter number 2 for add Clothing items");
        System.out.println("Enter number 3 for Educational items\n");
        while (!search){


            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Item number: ");

            try {
                itemNumber = scanner.nextInt();
                if( itemNumber < 0 || itemNumber > 4){
                    System.out.println("Enter correct Input");
                    continue;
                }
                search = true;
            }
            catch (InputMismatchException e){
                System.out.println("Enter correct Input");
                scanner.next();
            }
        }

        if (itemNumber == 1){

            String productId = "";
            boolean checker_1 = false;
            while (!checker_1){

                System.out.print("Enter Id of Electronic item: ");
                try{
                    int digit = scanner.nextInt();

                    if(digit < 0 || digit > 10000){
                        System.out.println("Enter valid productId");
                        continue;
                    }
                    productId = "E"+ String.valueOf(digit);
                    boolean productExit = false;
                    for(Items values : items ){
                        if(values.getProductId().equals(productId)){
                            System.out.println("Item already in the stock");
                            productExit = true;
                            break;
                        }
                    }
                    if(productExit){
                        continue;
                    }
                    checker_1 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct input");
                    scanner.nextLine();
                }

            }

            System.out.print("Enter Item name: ");
            String itemName = scanner.next();

            double price = 0;
            boolean checker_2 = false;
            while (!checker_2){

                System.out.print("Enter the price:");
                try {
                    price = scanner.nextDouble();
                    if(price < 0.0){
                        System.out.println("Enter correct input");
                        continue;
                    }
                    checker_2 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct values");
                    scanner.nextLine();
                }
            }

            System.out.print("Enter Item Brand: ");
            String brandName = scanner.next();

            int quantity = 0;
            boolean checker_3 = false;
            while (!checker_3){

                System.out.print("Enter the quantity:");
                try {
                    quantity = scanner.nextInt();
                    if(quantity < 0){
                        System.out.println("Enter correct input");
                        continue;
                    }
                    checker_3 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct values");
                    scanner.nextLine();
                }
            }

            System.out.print("Enter Warranty period: ");
            String warranty = scanner.next();

            Electronic electronic = new Electronic(productId,itemName,price,brandName,quantity,warranty);
            items.add(electronic);
            System.out.println("Item add successfully");

        }

        if (itemNumber == 2){

            String productId = "";
            boolean checker_1 = false;
            while (!checker_1){

                System.out.print("Enter Id of Clothing item: ");
                try{
                    int digit = scanner.nextInt();

                    if(digit < 0 || digit > 10000){
                        System.out.println("Enter valid productId");
                        continue;
                    }
                    productId = "C"+ String.valueOf(digit);
                    boolean productExit = false;
                    for(Items values : items ){
                        if(values.getProductId().equals(productId)){
                            System.out.println("Item already in the stock");
                            productExit = true;
                            break;
                        }
                    }
                    if(productExit){
                        continue;
                    }
                    checker_1 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct input");
                    scanner.nextLine();
                }

            }

            System.out.print("Enter Item name: ");
            String itemName = scanner.next();

            double price = 0;
            boolean checker_2 = false;
            while (!checker_2){

                System.out.print("Enter the price:");
                try {
                    price = scanner.nextDouble();
                    if(price < 0.0){
                        System.out.println("Enter correct input");
                        continue;
                    }
                    checker_2 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct values");
                    scanner.nextLine();
                }
            }

            System.out.print("Enter Item Brand: ");
            String brandName = scanner.next();

            int quantity = 0;
            boolean checker_3 = false;
            while (!checker_3){

                System.out.print("Enter the quantity:");
                try {
                    quantity = scanner.nextInt();
                    if(quantity < 0){
                        System.out.println("Enter correct input");
                        continue;
                    }
                    checker_3 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct values");
                    scanner.nextLine();
                }
            }

            System.out.print("Enter the color: ");
            String color = scanner.next();

            System.out.print("Enter the clothe type: ");
            String clotheType = scanner.next();

            System.out.print("Enter clothe size: ");
            String size = scanner.next();

            Clothing clothing = new Clothing(productId, itemName,price,brandName,quantity,color,clotheType,size);
            items.add(clothing);
            System.out.println("Item add successfully");

        }

        if (itemNumber == 3){

            String productId = "";
            boolean checker_1 = false;
            while (!checker_1){

                System.out.print("Enter Id of Educational item: ");
                try{
                    int digit = scanner.nextInt();

                    if(digit < 0 || digit > 10000){
                        System.out.println("Enter valid productId");
                        continue;
                    }
                    productId = "A"+ String.valueOf(digit);
                    boolean productExit = false;
                    for(Items values : items ){
                        if(values.getProductId().equals(productId)){
                            System.out.println("Item already in the stock");
                            productExit = true;
                            break;
                        }
                    }
                    if(productExit){
                        continue;
                    }
                    checker_1 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct input");
                    scanner.nextLine();
                }

            }

            System.out.print("Enter Item name: ");
            String itemName = scanner.next();

            double price = 0;
            boolean checker_2 = false;
            while (!checker_2){

                System.out.print("Enter the price:");
                try {
                    price = scanner.nextDouble();
                    if(price < 0.0){
                        System.out.println("Enter correct input");
                        continue;
                    }
                    checker_2 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct values");
                    scanner.nextLine();
                }
            }

            System.out.print("Enter Item Brand: ");
            String brandName = scanner.next();

            int quantity = 0;
            boolean checker_3 = false;
            while (!checker_3){

                System.out.print("Enter the quantity:");
                try {
                    quantity = scanner.nextInt();
                    if(quantity < 0){
                        System.out.println("Enter correct input");
                        continue;
                    }
                    checker_3 = true;
                }
                catch (InputMismatchException e){
                    System.out.println("Enter correct values");
                    scanner.nextLine();
                }
            }

            System.out.print("Enter the discount: ");
            int discount = scanner.nextInt();

            Educational educational = new Educational(productId, itemName,price,brandName,quantity,discount);
            items.add(educational);
            System.out.println("Item add successfully");

        }

    }

    public void deleteItems(){

        String productId = "";
        int digit = 0;
        boolean checker_4 = false;
        while (!checker_4){

            System.out.println("Enter product Id: ");

            try {
                productId = scanner.next();
                if(productId.charAt(0) == 'E' || productId.charAt(0) == 'C' || productId.charAt(0) == 'A'){
                    try{
                        digit = Integer.parseInt(productId.substring(1));
                        if(digit < 0 || digit > 10000){
                            System.out.println("Invalid Input");
                            continue;
                        }
                        checker_4 = true;
                    }
                    catch (InputMismatchException e){
                        System.out.println("Enter correct input");
                        scanner.nextLine();
                    }
                }
                else{
                    System.out.println("Enter correct Input");
                }
            }
            catch (InputMismatchException | StringIndexOutOfBoundsException e){
                System.out.println("Enter correct Input");
                System.out.println(e);
                scanner.nextLine();
            }
        }
        for(Items itemList : items){
            if(itemList.getProductId().equals(productId)){
                items.remove(itemList);
                System.out.println("Product delete successfully");
                return;
            }
            else {
                System.out.println("Product Id not found");
                scanner.nextLine();
            }
        }
    }

    public void displayItems(){

        for(Items itemList : items){
            if(itemList.getProductId().equals(" ")){
                System.out.println("Items are not Available");
            }
            else{
                System.out.print(itemList.toString() + " ");
                System.out.println();
            }

        }


    }
}
