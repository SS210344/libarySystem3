package com.company;

import javax.security.sasl.AuthorizeCallback;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

//myObj change the name and add try catch
public class Main {
    private static File library = new File("books.txt");
    private static ArrayList<ArrayList<String>> books = new ArrayList<>();

    public static void main(String[] args) {
        // write your code here
        CreateFile();
        ReadFile();
        mainMenu();



    }
    //creates or finds the file
    public static void CreateFile() {
        try {
            if (library.createNewFile()) {
                System.out.println("File created: " + library.getName());
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
            UserInput = input.nextLine();

        } catch (Exception e) {
            System.out.println("an error occurred " + e);

        }

        return (UserInput);
    }
    // writes user inputs to the file
    public static void WriteToFile(ArrayList<ArrayList<String>> books ) {
        try {
            FileWriter myWriter = new FileWriter(library.getName(), false); //True means append to file contents, False means overwrite
            // Overwrites everything in the file
            for (int i = 0; i < books.toArray().length; i++) {
                for (int j = 0; j < books.get(i).toArray().length; j++) {
                    myWriter.write(books.get(i).get(j)+";");
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
    public static void addBooks(ArrayList<ArrayList<String>> books){
        System.out.println("number of books");
        int booksNum;
        while(true) {
            try {
                booksNum = Integer.parseInt(userInput());
                if(booksNum>0){
                    break;
                }
                else{
                    System.out.println("number to be greater than 0");
                }
            }
            catch(Exception e){
                System.out.println("invalid input "+e);
            }
        }
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


    }
    // delete the file
    public static void DeleteFile() {
        if (library.delete()) {
            System.out.println("Deleted the file: " + library.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    // reads line in a file
    public static void ReadFile() {
        try {
            Scanner myReader = new Scanner(library);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] book =data.split(";");
                ArrayList<String> values =new ArrayList<>();
                for (int i = 0; i < book.length; i++) {
                    values.add(book[i]);
                }
                books.add(values);


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void printBooks(){
        System.out.println("book name     isbn     author      genre ");
        for (int i = 0; i < books.toArray().length; i++) {
            for (int j = 0; j < books.get(i).toArray().length; j++) {
                System.out.print(books.get(i).get(j)+"  ");
            }
            System.out.println(" ");
        }



    }
    // deletes a book
    public static void DeleteBook(){
        System.out.println("book name:");
        String bookName = userInput();
        boolean found = false;
        for (int i = 0; i < books.toArray().length; i++) {

            if (books.get(i).get(0).equals(bookName)) {
                found=true;

                books.remove(i);

            }
        }
        if(found){
            System.out.println("found and deleted book");
        }
        else{
            System.out.println("book not found");
        }



    }

    public static void changeBook(){

        System.out.println("what to change 0:book name 1:isbn 2: author 3: genre ");
        int change = intValidate(0, 3);
        System.out.println("search by  0:book name 1:isbn 2: author 3: genre ");
        int searchValue = intValidate(0, 3);
        System.out.println("filled  name");
        String filedName =userInput();
        boolean found = false;
        for (int i = 0; i < books.toArray().length; i++) {

            if (books.get(i).get(searchValue).equals(filedName)) {
                System.out.println("book found");
                System.out.println("current value is ");
                System.out.println(books.get(i).get(change));
                System.out.println("new value:");
                String newValue = userInput();
                books.get(i).set(change,newValue);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("book not found");
        }
    }

    private static int intValidate(int min,int max) {
        int option;
        while(true) {
            try {
                option = Integer.parseInt(userInput());
                if ((option >= min) && (option <= max)) {
                    break;
                } else {
                    System.out.println("number to be greater than or equal to "+min+" and less than or equal to "+max);
                }
            } catch (Exception e) {
                   System.out.println("invalid input " + e);
            }
        }
        return option;
    }


    public static void mainMenu(){
        while(true){
            System.out.println("options are 1:read file 2:add book 3:delete file 4:delete book 5:change book 6:exit");
            int option = intValidate(1, 6);


            if (option == 1){

                printBooks();
            }
            if(option == 2){
                addBooks(books);
                WriteToFile(books);

            }
            if(option == 3){
                System.out.println("Do you want to delete this file now Y or N?");
                String userOption = userInput();
                if (userOption.equalsIgnoreCase("y")) {
                    DeleteFile();
                    CreateFile();
                }
            }
            if(option == 4){
                DeleteBook();
            }
            if(option == 5){
                changeBook();
                WriteToFile(books);
            }
            if(option == 6){
                WriteToFile(books);

                break;
            }

        }


    }




}
