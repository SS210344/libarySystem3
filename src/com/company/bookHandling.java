package com.company;

import com.company.objects.book;

import java.util.ArrayList;
import java.util.Scanner;

public class bookHandling {
    book CurrentBook = new book ("book name","isbn","author","genre");
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

            currentBook.add("not borrowed");


            books.add(currentBook);
        }


    }
    // print books out
    public static void printBooks(ArrayList<ArrayList<String>> books){
        System.out.println("book name     isbn     author      genre      borrower");
        for (int i = 0; i < books.toArray().length; i++) {
            for (int j = 0; j < books.get(i).toArray().length; j++) {
                if(j == 4){
                    break;
                }
                System.out.print(books.get(i).get(j)+"  ");
            }
            if(books.get(i).get(4) != "not borrowed"){
                System.out.println("borrowed");
            }
            else{
                System.out.println("not borrowed");
            }
            System.out.println(" ");
        }



    }
    // deletes a book
    public static ArrayList<ArrayList<String>> DeleteBook(ArrayList<ArrayList<String>> books){
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
        return books;



    }
    // changes a single book
    public static ArrayList<ArrayList<String>> changeBook(ArrayList<ArrayList<String>> books){

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
        return books;
    }
    // search books
    public static void searchBook(ArrayList<ArrayList<String>> books){
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
    // takes a user input
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

}
