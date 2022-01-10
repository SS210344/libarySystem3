package com.company;


import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import com.company.fileHandling;
import com.company.logIn;
import com.company.objects.book;
//myObj change the name and add try catch
public class Main {
    private static File library = new File("books.txt");
    private static File users = new File("Users.txt");
    private static ArrayList<ArrayList<String>> books = new ArrayList<>();
    private static ArrayList<String> objectBooks = new ArrayList<>();



    public static void main(String[] args) {
        // write your code here
        // login
        boolean found = false;
        fileHandling.CreateFile(users);
        ArrayList<ArrayList<String>> userPasswords = new ArrayList<>();
        fileHandling.ReadFile(users, userPasswords);
        boolean admin = false;
        while(true) {
            System.out.println("options are 1:login  2: register 3:exit 4: admin login");
            int option = intValidate(1, 4);
            // log in
            if(option == 1){
                found = logIn.logIn(userPasswords);
            }
            // register user
            if(option == 2) {
                userPasswords=logIn.register( userPasswords);
                fileHandling.WriteToFile(userPasswords, users);
                fileHandling.ReadFile(users, userPasswords);



            }
            //exit
            if(option ==3){
                break;
            }
            // admin login
            if(option == 4){
                System.out.println("admin login");
                File adminUsers = new File("AdminUsers.txt");
                fileHandling.CreateFile(adminUsers);
                ArrayList<ArrayList<String>> adminPasswords = new ArrayList<>();
                fileHandling.ReadFile(adminUsers,adminPasswords);
                admin = logIn.logIn(adminPasswords);
                if(admin) {
                    // admin password is sg124htgz12d user name is admin@libary.com
                    found=true;

                }

            }


            if(found){
                break;
            }
        }

        // books
        if(found) {
            fileHandling.CreateFile(library);
            fileHandling.ReadFile(library, books);
            mainMenu(admin);
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
    // print books out
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
    // changes a single book
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
    // search books
    public static void searchBook(){
        boolean found = false;
        System.out.println("search by  0:book name 1:isbn 2: author 3: genre ");
        int searchValue = intValidate(0, 3);
        System.out.println("filled  name");
        String filedName =userInput();
        for (int i = 0; i < books.toArray().length; i++) {

            if (books.get(i).get(searchValue).equals(filedName)) {
                System.out.println("book found");
                found = true;
                for (int j = 0; j < books.get(i).toArray().length; j++) {
                    System.out.print(books.get(i).get(j)+"  ");
                }
                System.out.println(" ");
                break;

            }
        }
        if(!found) {
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

    private static void covertList(){
        for (int i = 0; i < books.toArray().length; i++) {
            books.get(i).get(0);

        }
    }


    public static void mainMenu(boolean admin){
        while(true){
            int option = 1;
            System.out.println("options are 1:read file  2: search book 3:exit");
            if(admin){
                System.out.println("admin options 4:add book 5:delete file 6:delete book 7:change book");
            }
            if (admin) {
                option = intValidate(1, 7);
            }
            else{
                option = intValidate(1, 3);
            }
            // read file
            if (option == 1){
                printBooks();
            }
            // search book
            if(option == 2) {
                searchBook();
            }
            // exit
            if(option == 3){
                fileHandling.WriteToFile(books,library);


                break;
            }
            // add book
            if(option == 4){
                addBooks(books);
                fileHandling.WriteToFile(books,library);

            }
            // delete file
            if(option == 5){
                System.out.println("Do you want to delete this file now Y or N?");
                String userOption = userInput();
                if (userOption.equalsIgnoreCase("y")) {
                    fileHandling.DeleteFile(library);
                    fileHandling.CreateFile(library);
                    fileHandling.ReadFile(library, books);
                }
            }
            //delete book
            if(option == 6){
                DeleteBook();
                fileHandling.WriteToFile(books,library);
                fileHandling.ReadFile(library, books);
            }
            // change book
            if(option == 7){
                changeBook();
                fileHandling.WriteToFile(books,library);
                fileHandling.ReadFile(library, books);
            }


        }


    }




}
