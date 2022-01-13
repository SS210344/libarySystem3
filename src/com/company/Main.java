package com.company;


import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import com.company.fileHandling;
import com.company.logIn;
import com.company.objects.book;
import com.company.objects.user;
import com.company.borrowing;


//myObj change the name and add try catch
public class Main {
    private static File library = new File("books.txt");
    private static File users = new File("Users.txt");
    private static ArrayList<ArrayList<String>> books = new ArrayList<>();


    private static ArrayList<String> objectBooks = new ArrayList<>();
    book CurrentBook = new book ("book name","isbn","author","genre");



    public static void main(String[] args) {
        // write your code here

        // login
        boolean found = false;
        fileHandling.CreateFile(users);
        ArrayList<ArrayList<String>> userPasswords = new ArrayList<>();
        userPasswords = fileHandling.ReadFile(users, userPasswords);
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
                userPasswords=fileHandling.ReadFile(users, userPasswords);



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
                userPasswords=fileHandling.ReadFile(adminUsers,adminPasswords);
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
            books = fileHandling.ReadFile(library, books);
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






    public static void mainMenu(boolean admin){
        user currentUser = new user("name","password");
        System.out.println("name:");
        String userName=userInput();
        currentUser.setUsername(userName);
        while(true){
            int option = 1;
            System.out.println("options are 1:read file  2: search book 3:borrow book 4:return book 5:exit");
            if(admin){
                System.out.println("admin options 6:add book 7:delete file 8:delete book 9:change book");
            }
            if (admin) {
                option = intValidate(1, 9);
            }
            else{
                option = intValidate(1, 5);
            }
            // read file
            if (option == 1){
                bookHandling.printBooks(books);
            }
            // search book
            if(option == 2) {
                bookHandling.searchBook(books);
            }
            // borrow book
            if(option == 3) {
                books = borrowing.borrow(userName,books);

            }
            // return book
            if(option == 4) {
                books = borrowing.returnBook(userName,books);

            }
            // exit
            if(option == 5){
                fileHandling.WriteToFile(books,library);


                break;
            }
            // add book
            if(option == 6){
                bookHandling.addBooks(books);
                fileHandling.WriteToFile(books,library);

            }
            // delete file
            if(option == 7){
                System.out.println("Do you want to delete this file now Y or N?");
                String userOption = userInput();
                if (userOption.equalsIgnoreCase("y")) {
                    fileHandling.DeleteFile(library);
                    fileHandling.CreateFile(library);
                    books = fileHandling.ReadFile(library, books);
                }
            }
            //delete book
            if(option == 8){
                books = bookHandling.DeleteBook(books);
                fileHandling.WriteToFile(books,library);
                fileHandling.ReadFile(library, books);
            }
            // change book
            if(option == 9){
                books = bookHandling.changeBook(books);
                fileHandling.WriteToFile(books,library);
                books = fileHandling.ReadFile(library, books);
            }


        }


    }




}
