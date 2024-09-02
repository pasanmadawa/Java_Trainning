package org.example;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FolderPath {

    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        boolean search = true;
        Scanner scanner = new Scanner(System.in);

        while(search){

            System.out.println("\n----------------");
            System.out.println("1 - Get the folder and file list");
            System.out.println("2 - Exit");


            System.out.print("Enter the number: ");
            try{
                int input = scanner.nextInt();
                if(input > 0 && input < 3){
                    switch (input){
                        case 1:
                            FolderPath obj = new FolderPath();
                            obj.getPath();
                            break;

                        case 2:
                            System.exit(0);
                            break;

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

    public void getPath(){

        boolean search = true;
        while(search){

            System.out.print("Enter the correct path: ");

            try{
                String inputPath = scanner.nextLine();
                File folder = new File(inputPath);
                File[] files = folder.listFiles();

                for(File file: files){
                    if(file.isFile()){
                        System.out.println("File: "+ file.getName());

                    }
                    else
                    if(file.isDirectory()){
                        System.out.println("Folder: " + file.getName());
                    }
                }
                break;
            }
            catch (NullPointerException e){
                System.out.println("Enter correct path");
            }
        }

    }
}
