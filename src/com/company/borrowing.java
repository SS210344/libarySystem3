package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class borrowing {


    public static ArrayList<ArrayList<String>> borrow(String username,ArrayList<ArrayList<String>> books){
        System.out.println("book name:");
        String BookName = userInput();
        for (int i = 0; i < books.toArray().length; i++) {
            if(BookName == books.get(i).get(0)){
                if(books.get(i).get(4)== "not borrowed") {
                    books.get(i).set(4, username);
                }
                else{
                    System.out.println("book is already borrowed");
                }
            }
        }
        return books;

    }
    public static ArrayList<ArrayList<String>> returnBook(String username,ArrayList<ArrayList<String>> books){
        System.out.println("book name:");
        String BookName = userInput();
        for (int i = 0; i < books.toArray().length; i++) {
            if(BookName == books.get(i).get(0)){
                if(username == books.get(i).get(4)) {
                    books.get(i).set(4, "not borrowed");
                }
                else{
                    System.out.println("you are not the borrower of this book");
                }
            }
        }
         return books;

    }
    private static String userInput() {
        Scanner input = new Scanner(System.in);
        String UserInput = "";

        try {
            UserInput = input.nextLine();

        } catch (Exception e) {
            System.out.println("an error occurred " + e);

        }

        return (UserInput);
    }



}
