package LAB3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int teamSize = 0, temp = 0;
        boolean isFinished = false;
        System.out.print(" Enter a Team size: ");

        while (!(isFinished)) {
            if (in.hasNextInt()) {
                temp = in.nextInt();
                if (temp > 0) {
                    teamSize = temp;
                    isFinished = true;
                } else {
                    System.out.println("Enter positive integer");
                    in.nextLine();
                }
            } else {
                System.out.println("Please enter integers");
                in.nextLine();
            }
        }

        Simulation simulation = new Simulation(teamSize);

        in.close();
    }
}