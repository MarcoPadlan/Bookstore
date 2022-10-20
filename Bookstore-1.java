package com.company;
import java.util.Scanner;
public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        BookstoreBook[] bookstoreBooks = new BookstoreBook[100];
        LibraryBook[] libraryBooks = new LibraryBook[200];
        int bookstoreBooksCount = 0;
        int libraryBooksCount = 0;
        System.out.println("Welcome to the book program!");
        String ans = "yes";
        do{
            System.out.printf("Would you like to create a book object? (yes/no):");
            ans = sc.nextLine().toLowerCase();
            //checks if not "no" or "yes"
            while (!(ans.equals("no") || ans.equals("yes"))) {
                System.out.print("I’m sorry but yeah isn’t a valid answer. Please enter either yes or no:");
                ans = sc.nextLine().toLowerCase();
                System.out.println();
            }
            if (ans.equals("no")) {
                System.out.println("Sure!");
                break;
            }

            if(ans.equals("yes")) {
                System.out.printf("Please enter the author, title and the isbn of the book separated by/:");
                String bookInfo = sc.nextLine();
                String[] bookInfoArray = bookInfo.split("/");
                String author = bookInfoArray[0];
                String title = bookInfoArray[1];
                String isbn = bookInfoArray[2];
                System.out.println("Got it!");
                System.out.printf("Now, tell me if the book is a bookstore book or a library book (enter BB for bookstore book and LB for library book):");
                String book_Type = sc.nextLine().toUpperCase();
                while (!book_Type.equals("BB") && !book_Type.equals("LB")) {
                    System.out.printf("Oops! That's not a valid entry. Please try again: ");
                    book_Type = sc.nextLine().toUpperCase();;
                }
                System.out.println("Got it!");
                if (book_Type.equals("BB")) {
                    System.out.printf("Please enter the list price of " + title + " by " + author + ":");
                    double listPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.printf("Is it on sale? (y/n):");
                    String sale = sc.nextLine();
                    //ask for sale price
                    boolean on_sale = false;
                    String discount;
                    double percent = 0;
                    if (sale.equals("y")) {
                        System.out.printf("Deduction percentage: ");
                        discount = sc.nextLine();
                        discount = discount.replaceAll("[^0-9]", "");
                        percent = Double.parseDouble(discount);
                        on_sale = true;
                    }
                    System.out.println("Got it!");

                    boolean onSale = false;
                    bookstoreBooks[bookstoreBooksCount] = new BookstoreBook(author, title, isbn, listPrice, onSale, percent);
                    System.out.println("");
                    System.out.println("Here is your library book information");
                    System.out.println(bookstoreBooks[bookstoreBooksCount].toString());
                    bookstoreBooksCount++;
                } else {
                    // create a new library book object
                    libraryBooks[libraryBooksCount] = new LibraryBook(author, title, isbn);
                    // display the library book information
                    System.out.println("");
                    System.out.println("Here is your library book information");
                    System.out.println(libraryBooks[libraryBooksCount].toString());
                    // increment the count of the number of books in the libraryBooks array
                    libraryBooksCount++;
                }
                System.out.println();


            }
        }while(ans.equals("yes"));
        System.out.println("Here are all your books...");
        System.out.println("Library Books (" +libraryBooksCount +")");
        for(int i = 0; i<libraryBooksCount; i++) {
            System.out.println("\t" + libraryBooks[i].toString());
        }
        System.out.println("____");
        System.out.println("Bookstore Books (" +bookstoreBooksCount +")");
        for(int i = 0; i < bookstoreBooksCount; i++){

            System.out.println("\t" + bookstoreBooks[i].toString());

        }
        System.out.println("____");
        System.out.println("Take care now!");

        sc.close();

    }
    static class LibraryBook {
        // private data members
        private String author;
        private String title;
        private String isbn;
        private String callNumber;
        private static int numOfBooks;
        // a int variable to store the floor number in which the book will be located

        private int floorNumber;
        // constructor with 3 parameters
        public LibraryBook(String author, String title, String isbn) {

            // set all the data members

            this.author = author;
            this.title = title;
            this.isbn = isbn;
            // generate the floor number and set the floor number
            floorNumber = (int) (Math.random() * 99 + 1);
            //call the generateCallNumber method to generate the call number and set the returned value to the callNumber
            this.callNumber = generateCallNumber();
            numOfBooks++;

        }
        // constructor with 2 parameters where the isbn is not passed

        public LibraryBook(String author, String title) {

            this(author, title, "notavailable");

        }
        // constructor with no parameters (default constructor)
        public LibraryBook() {
            this("notavailable", "notavailable", "notavailable");
        }
        // function to generate the call number
        private String generateCallNumber() {
            if (floorNumber < 10) {
                return "0" + floorNumber + "." + author.substring(0, 3).toUpperCase() + "." + isbn.charAt(isbn.length() - 1);
            } else {
                return floorNumber + "." + author.substring(0, 3).toUpperCase() + "." + isbn.charAt(isbn.length() - 1);
            }
        }// getters and setters
        // getter author
        public String getAuthor() {
            return author;
        }
        // setter author
        public void setAuthor(String author) {
            this.author = author;
        }
        // getter to get the title
        public String getTitle() {
            return title;
        }
        // setter to set the title
        public void setTitle(String title) {
            this.title = title;
        }
        // getter to get the isbn
        public String getIsbn() {
            return isbn;
        }
        // setter to set the isbn
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
        // getter callNumber
        public String getCallNumber() {
            return callNumber;
        }
        // setter callNumber
        public void setCallNumber(String callNumber) {
            this.callNumber = callNumber;
        }
        // getter  numOfBooks
        public static int getNumOfBooks() {
            return numOfBooks;
        }
        // setter numOfBooks
        public static void setNumOfBooks(int numOfBooks) {
            LibraryBook.numOfBooks = numOfBooks;
        }
        public String toString() {

            return "[" + isbn + "-" + title + " by " + author + "-" + callNumber + "]";
        }
    }
    static class BookstoreBook {
        //private data members
        private String author;
        private String title;
        private String isbn;
        private double price;
        private boolean onSale;
        private double discount;
        private static int numOfBooks = 0;
        // constructor with 6 parameters
        public BookstoreBook(String author, String title, String isbn, double price, boolean onSale, double discount) {
            // set all the data members
            this.author = author;
            this.title = title;
            this.isbn = isbn;
            this.price = price;
            this.onSale = onSale;
            this.discount = discount;
        }
        public BookstoreBook(String author, String title, String isbn, double price) {
            // call the constructor with 6 parameters with the values false and 0  (onSale, discount)
            this(author, title, isbn, price, false, 0);
        }
        public BookstoreBook(String author, String title, String isbn) {
            // call the constructor with 4 parameters
            // set the price to 0 ( price is not set yet)
            this(author, title, isbn, 0);
        }
        // getter to get the author
        public String getAuthor() {
            return author;
        }
        // setter to set the author
        public void setAuthor(String author) {
            this.author = author;

        }
        // getterto get the title
        public String getTitle() {

            return title;

        }
        public void setTitle(String title) {
            this.title = title;

        }
        // getter to get the isbn
        public String getIsbn() {
            return isbn;
        }
        // setter to set the isbn
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
        // getter to get the price
        public double getPrice() {
            return price;
        }
        // setter to set the price
        public void setPrice(double price) {
            this.price = price;
        }
        // getter to get the onSale
        public boolean isOnSale() {
            return onSale;
        }
        // setter to set the onSale
        public void setOnSale(boolean onSale) {
            this.onSale = onSale;
        }
        // getter to get the discount
        public double getDiscount() {
            return discount;

        }
        // setter to set the discount
        public void setDiscount(double discount) {
            this.discount = discount;
        }
        // get price after discount
        public double getPriceAfterDiscount() {
            return price - (price * discount / 100);
        }
        // toString method to display the book information
        public String toString(){
            return "[" + isbn + "-" + title + " by " + author + ", $" + String.format("%.2f", price) + " listed for $" + String.format("%.2f", getPriceAfterDiscount()) + "]";
        }
    }
}


