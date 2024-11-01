import java.util.Scanner;
public class PlaneManagement {
    static Scanner input = new Scanner(System.in);
    static int[][] Seats = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
    };
    static Ticket[] tickets = new Ticket[Seats.length * Seats[0].length];
    public static void main(String[] args){
        // Printing the Welcome message
        System.out.println("");
        System.out.println("Welcome to the Plane Manag1ement Application");
        System.out.println("");

        while (true) {
            //Displaying the menu using switch
            Menu();
            int Option = input.nextInt();

            switch(Option) {
                case 1:
                    buy_Seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_seat();
                    break;
                case 4:
                    Show_Seat_plan();
                    break;
                case 5:
                    print_ticket_info();
                    break;
                case 6:
                    Search_Ticket();
                    break;
                case 0:
                    System.out.println("Terminating the Program");
                    System.out.println("Thank you for using Plane Management Application.");
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please try again.");
                    System.out.println();
                    return;
            }
        }
    }

    //Task 3 Buying A seat
    public static void buy_Seat() {
        System.out.println("Enter the row letter (A - D): ");
        char rowL = input.next().toUpperCase().charAt(0);
        int row = -1;

        //Converting the letters to index using switch
        switch (rowL) {
            case 'A':
                row = 0;
                break;
            case 'B':
                row = 1;
                break;
            case 'C':
                row = 2;
                break;
            case 'D':
                row = 3;
                break;
            default:
                System.out.println("Invalid Row letter. Please Enter A, B, C, or D");
                return;

        }

        System.out.println("Enter the Seat number ( 1 - 14): ");
        int seat = input.nextInt();

        //Checking if the seat number is valid and available

        Person person = null;
        if (seat >= 1 && seat <= 14 && Seats[row][seat - 1] == 0) {
            //Asking for the personal information of the buyer
            System.out.println("Enter Name: ");
            String Name = input.next();
            System.out.println("Enter Surname: ");
            String Surname = input.next();
            System.out.println("Enter Email: ");
            String Email = input.next();
            System.out.println();

            person = new Person(Name, Surname, Email);
            Ticket ticket = new Ticket(row, seat, price(0), person);
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] == null) {
                    tickets[i] = ticket;
                    break;
                }
            }
            Seats[row][seat - 1] = 1; // Marking the seat as bought
            System.out.println("You have successfully bought Seat " + (char) (rowL) + " - " + seat);
        } else {
            System.out.println("Sorry, the seat is already booked or invalid seat number. Please choose another one. Thank you ");
        }
        //Saving the information a file
        Ticket ticket = new Ticket(row, seat, price(seat), person);
        ticket.save();
    }



    // Task 4 Canceling a Seat
    public static void cancel_seat() {
        System.out.println("Enter the row letter (A - D): ");
        char rowL = input.next().toUpperCase().charAt(0);
        int row = -1;

        //Converting the letters to index using switch
        switch (rowL) {
            case 'A':
                row = 0;
                break;
            case 'B':
                row = 1;
                break;
            case 'C':
                row = 2;
                break;
            case 'D':
                row = 3;
                break;
            default:
                System.out.println("Invalid Row letter. Please Enter A, B, C, or D");
                return;

        }

        System.out.println("Enter the Seat number ( 1 - 14): ");
        int seat = input.nextInt();

        //Checking if the seat number is valid and available
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null && tickets[i].getRow()== row && tickets[i].getSeat() == seat){
                tickets[i] = null;
                Seats[row][seat-1]=0; // Cancelling the seat and making it available
                System.out.println("You have successfully cancelled Seat " + (char)(rowL) + " - " + seat );
                return;
            }
        }

        System.out.println("Sorry, the seat hasn't been booked or invalid seat number. Please Enter again . Thank you ");


    }

    //Task 5 - Finding the first available seat
    public static void find_first_seat() {
        char[] rows = {'A', 'B', 'C', 'D'};
        for (char rowL : rows) {
            int row = rowL - 'A';
            for (int seat = 0; seat < Seats[row].length; seat++) {
                if (Seats[row][seat]==0){
                    System.out.println("First available seat is " + rowL + " - " + (seat + 1));
                    return;
                }
            }
        }
    }

    // Task 6 Showing the seating plan
    public static void Show_Seat_plan() {
        System.out.println("Seating plan: ");

        for (int i = 0; i < Seats.length; i++) {
            for (int j = 0; j < Seats[i].length; j++) {
                if (Seats[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    //Task 10 Printing ticket information
    public static void print_ticket_info() {
        int totalSales = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                ticket.Ticketinfo();
                System.out.println();
                totalSales += price(ticket.getSeat());
            }
        }
        System.out.println("Total Sales: \u00a3" + totalSales );
    }

    // Creating a method to calculate the total sales
    public static int price(int seat) {

        if (seat >= 1 && seat <= 5) {
            return 200;
        }
        else if (seat >= 6 && seat <= 9) {
            return 150;
        }
        else if ( seat >= 10 && seat <= 14 ) {
            return 180;
        }
        else {
            return 0;
        }
    }

    // Task 11 - Searching for ticket information
    public static void Search_Ticket() {
        System.out.println("Enter the row letter (A - D): ");
        char rowL = input.next().toUpperCase().charAt(0);
        int row = -1;

        //Converting the letters to index using switch
        switch (rowL) {
            case 'A':
                row = 0;
                break;
            case 'B':
                row = 1;
                break;
            case 'C':
                row = 2;
                break;
            case 'D':
                row = 3;
                break;
            default:
                System.out.println("Invalid Row letter. Please Enter A, B, C, or D");
                return;
        }

        System.out.println("Enter the Seat number ( 1 - 14): ");
        int seat = input.nextInt();

        // Check if the seat is valid
        if (seat < 1 || seat > 14) {
            System.out.println("Invalid seat number. Enter a seat number between 1 - 14");
            return;
        }

        //Checking if the seat is booked
        for (Ticket ticket : tickets) {
            if (ticket!=null && ticket.getRow() == row && ticket.getSeat() == seat) {
                ticket.Ticketinfo();
                return;
            }
        }

        // If the seat isn't booked
        System.out.println("This Seat is Available");
    }

    public static void Menu() {
        // Menu11

        System.out.println();
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("*                        Menu  Options                        *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    1) - Buy a Seat");
        System.out.println("    2) - Cancel Seat ");
        System.out.println("    3) - Find First Available Seat");
        System.out.println("    4) - Show Seat Plan");
        System.out.println("    5) - Print Tickets information and total sales");
        System.out.println("    6) - Search Ticket");
        System.out.println("    0 - Quit");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("");
        System.out.println("Please Select an option");
        System.out.println("");
    }

    // Showing the Seats
    public static void Seat_Plan() {
        for (int[]row : Seats){
            for (int seat : row){
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }
}