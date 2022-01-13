package com.company;

import com.company.objects.user;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class logIn {
    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);



    // validates email

    private static boolean isValidEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    // validates password
    private static boolean validatePassword(String password){
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

}
