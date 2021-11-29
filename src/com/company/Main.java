package com.company;

import javax.security.sasl.AuthorizeCallback;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

//myObj change the name and add try catch
public class Main {
    private static File libary = new File("books.txt");

    public static void main(String[] args) {
	// write your code here
        CreateFile();
        ArrayList<ArrayList<String>> books = new ArrayList<>();
        books = addBooks(books);
        WriteToFile(books);
        ReadFile();
        System.out.println("Do you want to delete this file now Y or N?");
        String userOption = userInput();
        if (userOption.equals("Y")) {
            DeleteFile();
        }


    }
//creates or finds the file
    public static void CreateFile() {
        try {
            if (libary.createNewFile()) {
                System.out.println("File created: " + libary.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
// takes a user input
    public static String userInput() {
        Scanner input = new Scanner(System.in);
        String UserInput = "";

        try {
            UserInput = input.next();

        } catch (Exception e) {
            System.out.println("an error occurred " + e);

        }

        return (UserInput);
    }
// wirter user inputs to the file
    public static void WriteToFile(ArrayList<ArrayList<String>> books ) {
        try {
            FileWriter myWriter = new FileWriter(libary.getName(), true); //True means append to file contents, False means overwrite
            System.out.println("This is the contents of the file:");
            // Overwrites everything in the file
            for (int i = 0; i < books.toArray().length; i++) {
                for (int j = 0; j < books.get(i).toArray().length; j++) {
                    myWriter.write(books.get(i).get(j)+"+ ");
                }
                myWriter.write("\n");


            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
// let the user add books to the 2d array list
    public static ArrayList<ArrayList<String>> addBooks(ArrayList<ArrayList<String>> books){
        System.out.println("number of books");
        int booksNum = Integer.parseInt(userInput());
        for (int i = 0; i < booksNum; i++) {
            ArrayList<String> currentBook=new ArrayList<>();
            System.out.println("Book title");
            currentBook.add(userInput());

            System.out.println("isbn");
            currentBook.add(userInput());

            System.out.println("author");
            currentBook.add(userInput());

            System.out.println("genre");
            currentBook.add(userInput());

            books.add(currentBook);
        }
        return books;

    }
// deleates the file
    public static void DeleteFile() {
        if (libary.delete()) {
            System.out.println("Deleted the file: " + libary.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
// reads line in a file
    public static void ReadFile() {
        try {
            Scanner myReader = new Scanner(libary);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
