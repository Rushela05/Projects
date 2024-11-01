import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    //Constructor
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //Creating a method save information in a file
    public void save() {
        //Generating the filename base on the row and seat number
        String FileName = "" + (char) ('A' + row) + seat + ".txt";

        try (FileWriter write = new FileWriter(FileName)) {
            // Writing the ticket information in to the file
            write.write("Ticket Information: \n");
            write.write("Row: " + (char) ('A' + row) + "\n");
            write.write("Seat: " + seat + "\n");
            write.write("Price: \u00a3"+ price + "\n"); // "\u00a3" as the pound sign
            write.write("\n");
            write.write("Person Information \n");
            write.write("Name: " + person.takeName() + "\n");
            write.write("Surname: " + person.takeSurname() + "\n");
            write.write("Email: " + person.takeEmail() + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getprice() {
        return price;
    }
    public void setprice(double price) {
        this.price = price;
    }

    public Person getperson() {
        return person;
    }
    public void setperson(Person person) {
        this.person = person;
    }

    // Method to print information
    public void Ticketinfo() {
        System.out.println("Ticket Information");

        //Converting index to A, B, C, D
        if (row == 0) {
            System.out.println("Row: A");
        }
        else if (row == 1) {
            System.out.println("Row: B");
        }
        else if (row == 2){
            System.out.println("Row: C");
        }
        else if (row == 3) {
            System.out.println("Row: D");
        }
        else {
            System.out.println();
        }

        System.out.println("Seat: " + seat);

        // Adding the prices for the Seats
        if (seat >= 1 && seat <= 5) {
            System.out.println("Price: \u00a3200");

        }
        else if (seat >= 6 && seat <= 9) {
            System.out.println("Price: \u00a3150");

        }
        else if ( seat >= 10 && seat <= 14 ) {
            System.out.println("Price: \u00a3180");
        }


        //using if condition to print the Person information
        if (person != null) {
            System.out.println("Person Information: ");
            person.Infor();
        }
        else {
            System.out.println("No information available for this ticket ");
        }
    }
}



