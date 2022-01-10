package com.company.objects;

public class book {
    private String bookName;
    private String isbn;
    private String author;
    private String genre;

    public book(String bookName, String isbn, String author, String genre) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }
    @Override
    public String toString(){
        return bookName+";"+isbn+";"+author+";"+genre+";";
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
