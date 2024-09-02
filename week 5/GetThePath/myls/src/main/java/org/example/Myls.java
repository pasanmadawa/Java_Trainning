package org.example;

import java.io.File;
import java.util.Scanner;

public class Myls {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Myls obj = new Myls();
        obj.getPath();
    }

    public void getPath(){

        boolean search = true;
        while(search){

            System.out.println(" ");

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
