import java.util.Scanner;

public class Cinema {
    // int rows = Integer.parseInt(scanner.nextLine());
    public static void main(String[] args) {
        System.out.println("Enter the number of rows");
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row");
        int seats = scanner.nextInt();
        String[][] cinemaPlan = cinema(rows, seats);
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = calculateTotalIncome(rows, seats);
        while (true) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int inputNum = scanner.nextInt();
            if (inputNum == 0) {
                break;
            }
            else if (inputNum == 1) {
                System.out.println();
                printCinemaPlan(rows, seats, cinemaPlan);
            }
            else if (inputNum == 2) {
                while (true) {
                    System.out.println();
                    System.out.println("Enter a row number: ");
                    int rowNumber = scanner.nextInt();
                    System.out.println("Enter a seat number in that row: ");
                    int seatNumber = scanner.nextInt();
                    if (rowNumber > rows || rowNumber < 0 || seatNumber > seats || seatNumber < 0) {
                        System.out.println("Wrong input!");
                    } else if (cinemaPlan[rowNumber][seatNumber].equals("B ")) {
                        System.out.println("That ticket has already been purchased!");
                    } else {
                        System.out.println();
                        System.out.println("Ticket price: $" + calculateSum(rowNumber, seatNumber, rows, seats));
                        cinemaPlan[rowNumber][seatNumber] = "B ";
                        currentIncome += calculateSum(rowNumber, seatNumber, rows, seats);
                        purchasedTickets++;
                        break;
                    }
                }
            }
            else if (inputNum == 3) {
                System.out.println();
                System.out.println("Number of purchased tickets: " + purchasedTickets);
                System.out.printf("Percentage: %.2f%%", calculatePercentage(rows, seats, purchasedTickets));
                System.out.println();
                System.out.printf("Current income: $%d", currentIncome);
                System.out.println();
                System.out.printf("Total income: $%d", totalIncome);
                System.out.println();
            }
        }
    }

    public static void printCinemaPlan(int rows, int seats, String[][] cinemaPlan) {
        System.out.println("Cinema:");
        for (int a = 0; a <= rows; a++) {
            for (int i = 0; i <= seats; i++ ) {
                System.out.print(cinemaPlan[a][i]);
            }
            System.out.println();
        }
    }

    public static int calculateSum(int a, int b, int c, int d) {
        int sum = 0;
        if (c*d <= 60)
            sum = 10;
        else if (c*d > 60 && c % 2 == 0) {
            if (a <= c/2) sum = 10;
            else sum = 8;
        }
        else if (c*d > 60 && c % 2 != 0) {
            if (a <= c/2) sum = 10;
            else sum = 8;
        }
        return sum;
    }
    public static String[][] cinema(int rows, int seats) {
        String[][] cinemaPlan = new String[rows+1][seats+1];
        for (int j = 0; j <= rows; j++) {
            for (int i = 0; i <= seats; i++) {
                if (j == 0 && i == 0) cinemaPlan[j][i] = ("  ");
                else if (j == 0) cinemaPlan[j][i] = (i + " ");
                else if (i == 0) cinemaPlan[j][i] = (j + " ");
                else cinemaPlan[j][i] = ("S" + " ");
            }
        }
        return cinemaPlan;
    }
    public static int calculateTotalIncome(int rows, int seats) {
        int totIn = 0;
        if (rows*seats <= 60)
            totIn = (rows*seats) * 10;
        else if (rows*seats > 60 && rows % 2 == 0) {
            totIn = ((((rows/2)*seats) * 8) + (((rows/2)*seats)*10));
        }
        else if (rows*seats > 60 && rows % 2 != 0) {
            totIn = (((((rows/2)+1)*seats) * 8) + (((rows/2)*seats)*10));
        }
        return totIn;
    }
    public static float calculatePercentage(int rows, int seats, int current) {
        return ((float) current * 100)/( (float) rows*seats);
    }
}