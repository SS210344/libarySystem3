package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class fileHandling {
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

    public static void WriteToFile(ArrayList<ArrayList<String>> ArrayToWrite , File File) {
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
}
