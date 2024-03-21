package LAB3;

import LAB3.SpreadBot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Simulation {
    protected ArrayList<Robot> redTeam;
    protected ArrayList<Robot> blueTeam;
    protected int redTeamIndex;
    protected int blueTeamIndex;
    protected boolean isFinished;

    public Simulation(int aTeamSize) {
        this.isFinished = false;
        initialize(aTeamSize);
        simulate();

    }

    /*
     * TODO Check the interface is working properly
     */
    public void initialize(int teamSize) {
        redTeam = new ArrayList<>();
        blueTeam = new ArrayList<>();
        redTeamIndex = 0;
        blueTeamIndex = 0;
        for (int i = 0; i < teamSize; i++) {
            redTeam.add(getRandomRobot(true));
            blueTeam.add(getRandomRobot(false));
        }
        Collections.sort(redTeam);
        Collections.sort(blueTeam);
    }

    public void simulate() {
        double redTeamSum = redTeam.stream().mapToDouble(Robot::getSpeed).sum();
        double blueTeamSum = blueTeam.stream().mapToDouble(Robot::getSpeed).sum();
        System.out.println("Red team:");
        printTeam(redTeam);
        System.out.println("Blue team:");
        printTeam(blueTeam);
        System.out.println("Speed sum of Red Team: " + redTeamSum + "\nSpeed sum of Blue Team: " + blueTeamSum);
        System.out.println(redTeamSum > blueTeamSum ? "Red Team" : "Blue Team" + " starts first!");
        while (!redTeam.isEmpty() && !blueTeam.isEmpty()) {
            if (redTeamSum >= blueTeamSum) {
                redTurn();
                if (!blueTeam.isEmpty()) {
                    blueTurn();
                } else {
                    break;
                }
            } else {
                blueTurn();
                if (!redTeam.isEmpty()) {
                    redTurn();
                } else {
                    break;
                }

            }

        }
        if (redTeam.isEmpty()) {
            System.out.println("Blue team wins, remaining robots:");
            printTeam(blueTeam);
        } else {
            System.out.println("Red team wins, remaining robots:");
            printTeam(redTeam);
        }
    }

    private void redTurn() {
        //System.out.println("\nRed teams turn\n");
        for (Robot redRobot : redTeam) {
            redRobot.attack(this);
            if (didTheGameFinish()) {
                break;
            }
        }
    }

    private void blueTurn() {
        //System.out.println("\nBlue teams turn\n");
        for (Robot blueRobot : blueTeam) {
            blueRobot.attack(this);
            if (didTheGameFinish()) {
                break;
            }
        }
    }

    private void printTeam(ArrayList<Robot> team) {
        for (Robot robot : team) {
            System.out.println(robot.getName() + " Health: " + String.format("%.3f", robot.getHealth()) +
                    " Attack: " + String.format("%.3f", robot.getAttack()) +
                    " Speed: " + String.format("%.3f", robot.getSpeed()));
        }
    }

    private Robot getRandomRobot(boolean isRedTeam) {
        Random rand = new Random();
        int randNum = rand.nextInt(6);
        switch (randNum) {
            case 0:
                return new SimpleBot(isRedTeam);
            case 1:
                return new PredatorBot(isRedTeam);
            case 2:
                return new DefenceBot(isRedTeam);
            case 3:
                return new SpeedBot(isRedTeam);
            case 4:
                return new SpreadBot(isRedTeam);
            case 5:
                return new OneBot(isRedTeam);
            default:
                return null;
        }
    }

    public Robot getRandomTarget(boolean isRedTeam) {
        Random rand = new Random();
        ArrayList<Robot> team = isRedTeam ? blueTeam : redTeam;
        return team.size() > 1 && team.size() != 0 ? team.get(rand.nextInt(team.size())) : team.get(0);
    }

    public Robot getHighestHealth(boolean isRedTeam) {
        ArrayList<Robot> team = isRedTeam ? blueTeam : redTeam;
        return Collections.max(team, Comparator.comparing(Robot::getHealth));
    }

    public Robot getLowestHealth(boolean isRedTeam) {
        ArrayList<Robot> team = isRedTeam ? blueTeam : redTeam;
        return team.size() > 11 && team.size() != 0 ? Collections.min(team, Comparator.comparing(Robot::getHealth))
                : team.get(0);
    }

    public Robot getLowestSpeed(boolean isRedTeam) {
        ArrayList<Robot> team = isRedTeam ? blueTeam : redTeam;
        return Collections.min(team, Comparator.comparing(Robot::getSpeed));
    }

    public Robot getLowestAttack(boolean isRedTeam) {
        ArrayList<Robot> team = isRedTeam ? blueTeam : redTeam;
        return Collections.min(team, Comparator.comparing(Robot::getAttack));
    }

    public Robot[] getLowestSpeed3(boolean isRedTeam) {
        ArrayList<Robot> team = isRedTeam ? blueTeam : redTeam;
        int size = Math.min(team.size(), 3);
        Robot[] lowestSpeedRobots = new Robot[size];
        ArrayList<Robot> sortedTeam = new ArrayList<>(team);
        sortedTeam.sort(Comparator.comparing(Robot::getSpeed));// might be unnecesary but i am still new to interfaces
                                                               // so stays here
        for (int i = 0; i < size; i++) {
            lowestSpeedRobots[i] = sortedTeam.get(i);
        }
        return lowestSpeedRobots;
    }

    public int getRedTeamIndex() {
        return redTeamIndex;
    }

    public int getBlueTeamIndex() {
        return blueTeamIndex;
    }

    public void removeRobot(Robot r) {
        ArrayList<Robot> team = r.isRedTeam() ? redTeam : blueTeam;
        team.remove(r);
        System.out.println(r + " destroyed.");
    }

    public boolean didTheGameFinish() {
        return redTeam.isEmpty() || blueTeam.isEmpty();
    }
}
