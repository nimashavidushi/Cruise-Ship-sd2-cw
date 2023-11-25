import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CruiseShip {
    // global variables
    static Scanner scan = new Scanner(System.in);
    static String[] myship = new String[12];
    static String[] ship = new String[12];
    char userChoice;
    static int roomNum=0;
    static String customerName;
    public static void main(String[] args){
        String[] ship = new String[12];
        initialise(myship);
        char userChoice;
        for (int x=0; x<12; x++){
            ship[x]="e";
        }
        // Menu output
        while (true){
            System.out.println("Enter V: to view all cabins");
            System.out.println("Enter A: to add customers to cabin");
            System.out.println("Enter E: to display empty cabins");
            System.out.println("Enter D: to delete customer from cabin");
            System.out.println("Enter F: to find cabin from customer name");
            System.out.println("Enter S: to store program data into file");
            System.out.println("Enter L: to load program data from file");
            System.out.println("Enter O: to  view passengers ordered alphabetically by name");
            System.out.println("Enter Q: to exit program");

            System.out.println();
            System.out.println("enter your choice");
            userChoice = scan.next().charAt(0);




            switch (userChoice){
                case 'V':
                    viewCabins(myship);
                    break;
                case 'A':
                    addCustomer(myship);
                    break;
                case 'E':
                    emptyCabins(myship);
                    break;
                case 'D':
                    deleteCustomer(myship);
                    break;
                case 'F':
                    findCustomer(myship);
                    break;
                case 'O':
                    orderedCustomer(myship);
                    break;
                case 'S':
                    storeData(myship);
                    break;
                case 'L':
                    loadData();
                    break;

                case 'Q':
                    System.out.println("Thank you");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }
    public static void initialise(String ship[]){
        for (int x=0; x<12; x++){
            ship[x]="e";
        }
        System.out.println("initialise");

    }
    public static void viewCabins(String[] ship){
        System.out.println("----------------------OUR ROOMS WITH GUESTS-----------------------");
        System.out.println();
        for (int x = 0; x < ship.length; x++) {
            if (ship[x].equals("e")) {
                System.out.println("Room number " + (x + 1) + " is empty");
            } else {
                System.out.println("Room number " + (x + 1) + " is occupied by " + ship[x]);


            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------");

    }

    public static void addCustomer(String[] ship){

        System.out.println("------------------ADD CUSTOMERS---------------");
        System.out.println();
        System.out.println("Enter a cabin number between (1-12)");
        roomNum = scan.nextInt();
        while (true){
            if (roomNum >= 1 && roomNum <= 12) {
                break;
            }else {
                System.out.println("Invalid cabin number");
                System.out.println("Enter cabin number between (1-12)");
                roomNum = scan.nextInt();
                continue;

            }
        }
        boolean cabinFull = false;
        for (String s : ship){
            if (s.equals("e")){
                cabinFull = false;
                break;
            }else {
                cabinFull = true;
            }
        }
        if (!cabinFull){
            System.out.println("Enter a name to book the cabin no. "+roomNum);
            ship[roomNum-1] = scan.next();

            System.out.println();
            System.out.println("Customer details have been added");
        } else {
            System.out.println();
            System.out.println("Cabins are full");
        }


    }
    public static void emptyCabins(String[] ship) {
        System.out.println("|----------------------EMPTY ROOMS-----------------------|");
        System.out.println();
        for (int x = 0; x < ship.length; x++) {
            if (ship[x].equals("e")) {
                System.out.println("Room number " + (x + 1) + " is empty");
            }
        }
        System.out.println();
        System.out.println("|--------------------------------------------------------|");
    }
    public static void deleteCustomer(String[] ship) {
        System.out.println("|--------------------REMOVE GUEST---------------------|");
        System.out.println();
        while (true) {
            System.out.print("Enter which room do you want to delete a customer from: ");
            roomNum = scan.nextInt();
            if (roomNum >= 1 || roomNum <= 12){
                ship[roomNum - 1] = "e";
                System.out.println("Customer deleted from the room number " + (roomNum) + ".");
                System.out.println();
                System.out.println("|-----------------------------------------------------|");
                break;
            }else {
                System.out.println("Invalid Room number! Try again.");
                continue;
            }


        }

    }
    public static void findCustomer(String[] ship) {
        System.out.println("|-----------------------FIND GUEST------------------------|");
        System.out.println();
        System.out.print("Enter Customer's name: ");
        customerName = scan.next();
        while (true) {
            // Checks whether customerName contains alphabetical letters or not.
            if (customerName.matches("^[a-z A-Z]*$")) {
                break;
            } else {
                System.out.println("Invalid Input");
                System.out.print("Enter Customer's name: ");
                customerName = scan.next();
            }
        }
        // Traverses through the array and checks for the customer name.
        boolean found = true;
        for (int x = 0; x < 12; x++) {
            if (ship[x].equals(customerName)) {
                System.out.println(customerName + " is in the room number " + (x + 1));
                found = true;
                break;
            } else {
                found = false;
            }
        }
        if (!found) {
            System.out.println("Entered Person " + customerName + " was Not found.");
        }
        System.out.println();
        System.out.println("|-----------------------------------------------------------|");
    }
    public static void orderedCustomer(String[] ship) {
        System.out.println("|-------------ALPHABETICALLY ORDERED LIST OF GUESTS--------------|");
        System.out.println();
        String[] temp_arr = new String[ship.length];
        for (int i = 0; i < ship.length; i++) { // to get array clone without referencing
            temp_arr[i] = ship[i];
        }
        // sorting the array
        String temp;
        for (int j = 0; j < temp_arr.length; j++) {
            for (int i = 1; i < temp_arr.length; i++) {
                if (temp_arr[i - 1].compareToIgnoreCase(temp_arr[i]) > 0) {
                    temp = temp_arr[i - 1];
                    temp_arr[i - 1] = temp_arr[i];
                    temp_arr[i] = temp;
                }
            }
        }
        // leaving the empty rooms and Printing the rooms in customers only.
        for (String s: temp_arr){
            if (!s.equals("e")){
                for (int y = 0; y < temp_arr.length; y++) {
                    if (s.equals(ship[y])) {
                        System.out.println(s + " has occupied room number " + (y + 1));
                    }
                }
            }
        }
        System.out.println();
        System.out.println("|----------------------------------------------------------------|");
    }
    public static void storeData(String[] ship){
        try {
            FileWriter myWriter = new FileWriter("mydata.txt");
            myWriter.write("\n");
            for (int x=0; x<12; x++){
                if (ship[x].equals("e")) {
                    myWriter.write("---------------------------------------------------------------" + "\n");
                    myWriter.write("Room number " + (x + 1) + " is empty \n");
                }else {
                    myWriter.write("---------------------------------------------------------------" + "\n");
                    myWriter.write("Room number " + (x + 1) + " is occupied by " + ship[x] + "\n");
                }
                myWriter.write("---------------------------------------------------------------" + "\n");
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully Stored in a file");
        }catch (IOException e){
            System.out.println("an error occurred");
            e.printStackTrace();
        }
    }
    public static void loadData(){
        System.out.println("-----load data-----");
        try {
            File myObj = new File("mydata.txt");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("an error occurred file not found");
            e.printStackTrace();
        }
    }

}
