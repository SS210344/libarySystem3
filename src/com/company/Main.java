package com.company;

import javax.security.sasl.AuthorizeCallback;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//myObj change the name and add try catch
public class Main {
    private static File library = new File("books.txt");
    private static File users = new File("Users.txt");
    private static ArrayList<ArrayList<String>> books = new ArrayList<>();
    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);


    public static void main(String[] args) {
        // write your code here
        // login
        boolean found = false;
        CreateFile(users);
        ArrayList<ArrayList<String>> userPasswords = new ArrayList<>();
        ReadFile(users, userPasswords);
        boolean admin = false;
        while(true) {
            System.out.println("options are 1:login  2: register 3:exit 4: admin login");
            int option = intValidate(1, 4);
            if(option == 1){
                found = logIn(userPasswords);
            }
            if(option == 2) {
                userPasswords=register( userPasswords);
                WriteToFile(userPasswords, users);
                ReadFile(users, userPasswords);



            }
            if(option ==3){
                break;
            }
            if(option == 4){
                found = logIn(userPasswords);
                if(found) {
                    // admin password is sg124htgz12d
                    System.out.println("admin password");
                    String password = userInput();
                    password = Integer.toString(password.hashCode());
                    if (password.equals("2002291397")) {
                        admin = true;
                    } else {
                        System.out.println("you are not an admin");
                    }
                }

            }


            if(found){
                break;
            }
        }

        // books
        if(found) {
            CreateFile(library);
            ReadFile(library, books);
            mainMenu(admin);
        }



    }
    public static ArrayList<ArrayList<String>> register(ArrayList<ArrayList<String>> userPasswords){
        ArrayList<String> currentUser=new ArrayList<>();
        while(true) {
            System.out.println("email:");
            String email = userInput();
            boolean validEmail = isValidEmail(email);
            currentUser.add(email);
            System.out.println("password: needs 2 numbers and 2 letters and at least 8 letter long");
            String password = userInput();
            boolean validPassword = validatePassword(password);
            password = Integer.toString(password.hashCode());
            currentUser.add(password);
            if(validEmail && validPassword){
                break;
            }

        }

        userPasswords.add(currentUser);
        return userPasswords;
    }
    // validates email

    public static boolean isValidEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    // validates password
    public static boolean validatePassword(String password){
        boolean valid = false;
        int charCount = 0;
        int numCount = 0;
        if(password.length()>= 8 ){
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if((ch >= '0') && (ch <= '9')){
                    numCount++;
                }
                else{
                    ch = Character.toUpperCase(ch);
                    if((ch >= 'A') && (ch <= 'Z')){
                        charCount++;
                    }
                }

            }
            if((charCount >= 2) && (numCount >= 2)){
                valid=true;
            }

        }
        else{
            System.out.println("pass word needs to be at least 8 Chars long");
        }
        return valid;
    }

    public static boolean  logIn(ArrayList<ArrayList<String>> userPasswords){

        boolean found = false;
        int chances = 0;
        while(true) {
            System.out.println("userName:");
            String UserName = userInput();
            System.out.println("Password:");
            String password = Integer.toString(userInput().hashCode());
            for (int i = 0; i < userPasswords.toArray().length; i++) {

                if (userPasswords.get(i).get(0).equals(UserName)) {
                    if (userPasswords.get(i).get(1).equals(password)) {
                        found = true;
                        break;
                    }
                }
            }
            if(found){
                System.out.println("valid user");
                break;
            }
            else{
                System.out.println("invalid user name or wrong password");
            }
            chances +=1;
            if(chances>3){
                System.out.println("guess to many time try again later ");
                break;
            }


        }
        return found;


    }
    //creates or finds the file
    public static void CreateFile(File NewFile) {
        try {
            if (NewFile.createNewFile()) {
                System.out.println("File created: " + NewFile.getName());
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
    public static void WriteToFile(ArrayList<ArrayList<String>> ArrayToWrite ,File File) {
        try {
            FileWriter myWriter = new FileWriter(File.getName(), false); //True means append to file contents, False means overwrite
            // Overwrites everything in the file
            for (int i = 0; i < ArrayToWrite.toArray().length; i++) {
                for (int j = 0; j < ArrayToWrite.get(i).toArray().length; j++) {
                    myWriter.write(ArrayToWrite.get(i).get(j)+";");
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
    public static void DeleteFile(File File) {
        if (File.delete()) {
            System.out.println("Deleted the file: " + File.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    // reads line in a file
    public static void ReadFile(File File,ArrayList<ArrayList<String>> ArrayToRead) {
        try {
            Scanner myReader = new Scanner(File);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] book =data.split(";");
                ArrayList<String> values =new ArrayList<>();
                for (int i = 0; i < book.length; i++) {
                    values.add(book[i]);
                }
                ArrayToRead.add(values);


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
                WriteToFile(books,library);


                break;
            }
            // add book
            if(option == 4){
                addBooks(books);
                WriteToFile(books,library);

            }
            // delete file
            if(option == 5){
                System.out.println("Do you want to delete this file now Y or N?");
                String userOption = userInput();
                if (userOption.equalsIgnoreCase("y")) {
                    DeleteFile(library);
                    CreateFile(library);
                    ReadFile(library, books);
                }
            }
            //delete book
            if(option == 6){
                DeleteBook();
                WriteToFile(books,library);
                ReadFile(library, books);
            }
            // change book
            if(option == 7){
                changeBook();
                WriteToFile(books,library);
                ReadFile(library, books);
            }


        }


    }




}
