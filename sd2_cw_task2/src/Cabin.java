import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Cabin {
    static int roomNum=1;
    static int newGuests=1;
    static int k=0;
    static int m=0;
    static int passengerExpense=0;
    static Scanner input = new Scanner(System.in);
    static String roomName;
    static String firstName;
    static String surName;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Passenger[] myShip = new Passenger[13];
        Passenger[][] sName = new Passenger[13][3];
        Passenger[][] fName = new Passenger[13][3];
        Passenger[][] myExpense = new Passenger[13][3];
        for (int i=0; i< myShip.length; i++){
            myShip[i] = new Passenger();
        }
        for (int i=0; i<13; i++){
            for (int j=0; j<3; j++){
                sName[i][j] = new Passenger();
                fName[i][j] = new Passenger();
                myExpense[i][j] = new Passenger();
            }
        }
        initialize(myShip, fName, myExpense);

        String choice;
        while (true){
            System.out.println("======================================================================");
            System.out.println("*            Hotel Management System                                 *");
            System.out.println("======================================================================");
            System.out.println("* V. View all the rooms                                              *");
            System.out.println("* A. Add customer to room                                            *");
            System.out.println("* E. Display Empty rooms                                             *");
            System.out.println("* D. Delete customer from room                                       *");
            System.out.println("* F. Find room from customer name                                    *");
            System.out.println("* S. Store program array data into a text file                       *");
            System.out.println("* L. Load program data back from the file                            *");
            System.out.println("* O. View rooms Ordered alphabetically by name                       *");
            System.out.println("* T. View expenses of each guest and total expenses of each cabin    *");
            System.out.println("* 3. Display the names of the first 3 customers                      *");
            System.out.println("* Q. Quit Program                                                    *");
            System.out.println("======================================================================");
            System.out.println("");

            System.out.println("Choose one of the options from above. (E.g: Type 'V' to view all the rooms)");

            do {

                System.out.println();
                System.out.print("Choice : ");
                choice = input.next();
                String selection = choice.toUpperCase(); //This will convert the input value to uppercase. this will help avoid case sensitive issues.

                switch (selection) {

                    case "V":
                        viewCabins(myShip,sName,fName, myExpense);
                        break;
                    case "A":
                        addCustomer(myShip,sName,fName,myExpense);
                        break;
                    case "E":
                        displayEmptyCabins(myShip, fName);
                        break;
                    case "D":
                        deleteCustomer(myShip,fName);
                        break;
                    case "F":
                        findCabin(myShip);
                        break;
                    case "O":
                        alphabeticalOrder(myShip);
                        break;
                    case "T":
                        totalExpenses(fName, myExpense);
                        break;
                    case "S":
                        storeData(fName);
                        break;
                    case "L":
                        loadData();
                        break;
                    case "Q":
                        System.out.println("Thanks");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid input! Please Enter one of these letters: V,A,E,D,F,S,L,O,Q");
                }

            }
            while (!(choice.equalsIgnoreCase("V") || choice.equalsIgnoreCase("A") || choice.equalsIgnoreCase("E") || choice.equalsIgnoreCase("D") ||
                    choice.equalsIgnoreCase("F") || choice.equalsIgnoreCase("S") || choice.equalsIgnoreCase("L") || choice.equalsIgnoreCase("O") || choice.equalsIgnoreCase("Q"))); //condition to only let valid range of inputs through.
        }
    }
    public static void initialize(Passenger[] myShip, Passenger fName[][], Passenger myExpense[][]) {
        for (int x = 1; x < 13; x++) {
            myShip[x] = new Passenger(); //Creating 12 Room Objects
            fName[x][0] = new Passenger();
            myExpense[x][0] = new Passenger();
        }
    }
    public static void viewCabins(Passenger myShip[], Passenger sName[][], Passenger fName[][], Passenger myExpense[][]) {
        while (m<37){
            for (int x = 1; x < 13; x++) {
                //This will display the room number and the current owner's name
                if (!fName[x][0].getFirstName().equals("e")) {
                    System.out.println("Cabin No. " + x + " occupied by " +fName[x][0].getFirstName()+" "+sName[x][0].getSurName());
                    for (int m=0; m<2; m++){
                        System.out.println("Extra passenger "+(m+1)+" is "+fName[x][m+1].getFirstName()+" "+sName[x][m+1].getSurName());
                    }
                    //This will display the cabins which are currently Empty
                } else {
                    System.out.println("Cabin No. " + x + " is empty");
                }


            }
            break;
        }
        m=m+2;
        System.out.println(m);
    }
    public static void addCustomer(Passenger myShip[], Passenger sName[][], Passenger fName[][], Passenger myExpense[][]) {
        boolean invalidRoomNumber;
        do {
            try {
                System.out.println("enter number (1-12)");
                roomNum = input.nextInt();
                //checks whether the cabin is already occupied or not
                if (!myShip[roomNum].getName().equals("e")) {
                    invalidRoomNumber = true;
                    System.out.println("This cabin is occupied by: Mr. " + myShip[roomNum].getName());
                    System.out.println("");
                    //checks whether the input is within the proper range
                } else if (roomNum >= 1 && roomNum < 13) {
                    invalidRoomNumber = false;
                    //Error message to be displayed
                } else {
                    invalidRoomNumber = true;
                    System.out.println("Invalid input! Please Enter a value between 1-12");
                    System.out.println("");
                }
                    //if the input is out of the range of the ship array this will catch it
            } catch (IndexOutOfBoundsException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-12");
                System.out.println("");
                //to deal with exceptions regarding null values
            } catch (NullPointerException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-12");
                System.out.println("");
                //to deal with inputs other than integers
            } catch (InputMismatchException e) {
                invalidRoomNumber = true;
                System.out.println("Invalid input! Please Enter a value between 1-12");
                System.out.println("");
                input.next();
            }
        } while (invalidRoomNumber);
        System.out.println("Enter the name of the customer to book the cabin:");
        roomName = input.next();
        myShip[roomNum].setName(roomName);
        System.out.println("Enter first name");
        firstName = input.next();
        fName[roomNum][0].setName(firstName);
        System.out.println("Enter surname");
        surName = input.next();
        sName[roomNum][0].setSurName(surName);
        System.out.println("Enter expenses in dollars");
        passengerExpense = input.nextInt();
        myExpense[roomNum][0].setExpense(passengerExpense);
        System.out.println("You can add up to two extra guests to this cabin");
        System.out.println("How many extra guests do you want to add (0-2)");
        newGuests = input.nextInt();
        for (int k=0; k<newGuests; k++){
            System.out.println("Enter first name of extra passenger "+(k+1));
            firstName = input.next();
            fName[roomNum][k+1].setFirstName(firstName);
            System.out.println("Enter surname");
            surName = input.next();
            sName[roomNum][k+1].setSurName(surName);
            System.out.println("Enter expenses in dollars");
            passengerExpense = input.nextInt();
            myExpense[roomNum][k+1].setExpense(passengerExpense);
        }
    }

    public static void displayEmptyCabins(Passenger myShip[], Passenger fName[][]) {
        //this method will display all the empty cabins
        for (int x = 1; x < 13; x++) {
            if (fName[x][0].getFirstName().equals("e")) {
                System.out.println("Cabin " + x + " is empty");
            }
        }
        System.out.println("");

    }

    private static void deleteCustomer(Passenger myShip[], Passenger fName[][]) {

        boolean invalidInput;

        do {
            invalidInput = false;
            try {
                System.out.println("please enter the cabin's number which you want to vacate");
                roomNum = input.nextInt();

                //if the cabin is not empty then this will delete the customer from that cabin
                if (!(fName[roomNum][0].getFirstName().equals("e"))) {
                    invalidInput = false;
                    fName[roomNum][0].setFirstName("e");

                    //if the cabin is already empty then this message will be displayed
                } else {
                    invalidInput = true;
                    System.out.println("Cabin " + roomNum + " is already Empty");
                    System.out.println("");
                }

                //if the input is not an integer value then this will catch it
            } catch (InputMismatchException e) {
                invalidInput = true;
                System.out.println("Invalid input! Please Enter a value between 1-10");
                System.out.println("");
                input.next();

                //if the input is out of the range of the ship array this will catch it
            } catch (IndexOutOfBoundsException e) {
                invalidInput = true;
                System.out.println("Invalid room number. Please enter a value between 1-10");
                input.next();
            }

        } while (invalidInput);//This will print the cabin number which has been successfully vacated
        System.out.println("Cabin " + roomNum + " has successfully been vacated");

        System.out.println("");

    }

    public static void findCabin(Passenger myShip[]) {
        System.out.println("Please enter the name of the customer");
        boolean found = false;
        String find = input.next();


        for (int n = 1; n < 13; n++) {
            //used equalsIgnoreCase to avoid case sensitive issues while searching for a customer
            //this method will find the cabin number which is currently being occupied by the mentioned customer
            if (myShip[n].getName().equalsIgnoreCase(find)) {
                found = true;
                System.out.println("Mr. " + find + " is staying in cabin No. " + n);
                System.out.println("");

            }
        }
        //this will let ou know if the customer is not there in the database
        if (found == false) {
            System.out.println(find + " doesn't exist on our database");
            System.out.println("");

        }
    }

    public static void alphabeticalOrder(Passenger myShip[]){
        int index = 0;
        String[] copy = new String[13];
        String[] names = new String[13];

        for (int y = 1; y < 13; y++) {
            copy[y] = myShip[y].getName();
        }
        for (int x = 1; x < 13; x++) {
            names[x] = myShip[x].getName().toLowerCase();//used this to avoid case sensitive issues.
        }
        boolean flag = true;
        for (int i = 1; i < names.length - 1; i++) {
            flag = false;
            for (int j = i + 1; j < names.length; j++) {
                if (names[j].compareTo(names[i]) < 0) {
                    String temp = names[j];
                    names[j] = names[i];
                    names[i] = temp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
        for (int x = 1; x < names.length; x++) {
            if (!(names[x].equals("e"))) {

                for (int i = 1; i < copy.length; i++) {
                    if (copy[i].toLowerCase().equals(names[x])) {
                        index = i;
                    }
                }
                System.out.println("Mr. " + names[x] + " is staying in cabin No. " + index);
            }
        }
        System.out.println("");

    }





    public static void totalExpenses(Passenger fName[][], Passenger myExpense[][]){
        //this will display the expenses
        for (int x=1; x<13; x++){
            if (!fName[x][0].getFirstName().equals("e")){
                System.out.println("Expenses for cabin no. "+x+" occupied by "+fName[x][0].getFirstName());
                System.out.println("Expenses for "+fName[x][0].getFirstName()+" is "+myExpense[x][0].getExpense());
                for (int m=0; m<2; m++){
                    System.out.println("Expenses for "+fName[x][m+1].getFirstName()+" is "+myExpense[x][m+1].getExpense());
                }
                System.out.println("total Expenses: "+(myExpense[x][0].getExpense()+myExpense[x][1].getExpense()+myExpense[x][2].getExpense()));
                //this will display empty rooms
            }else {
                System.out.println("Cabin no. "+x+" is empty.");
            }
        }
    }

    public static void storeData(Passenger fName[][]){
        //storing data to a text file
        try {
            String[][] storage = new String[13][3];
            for (int y=1; y<13; y++){
                storage[y][0] = fName[y][0].getFirstName();
            }
            FileWriter fileWriter = new FileWriter("ship.txt");
            for (int x=1; x<storage.length; x++){
                String file;
                file = storage[x][0];
                if (file.equals("e")){
                    fileWriter.write("cabin no. "+x+" is empty \n");
                }else {
                    fileWriter.write(file+" is in cabin no. "+x+". \n");
                }
                fileWriter.flush();
            }
        } catch (IOException e){
            System.err.println("file not found");
        }
        System.out.println("data successfully saved");
        System.out.println("");
    }

    public static void loadData(){
        try {
            File object = new File("ship.txt");
            Scanner reader = new Scanner(object);
            while (reader.hasNextLine()){
                String info = reader.nextLine();
                System.out.println(info);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.err.println("file not found");
            e.printStackTrace();
        }
    }
}



