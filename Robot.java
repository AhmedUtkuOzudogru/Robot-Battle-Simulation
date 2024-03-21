package LAB3;

import java.util.Random;

public abstract class Robot implements Comparable<Robot> {
    protected double health,attack,speed;
    protected  double maxHealth, maxAttack,maxSpeed;
    protected   double minHealth, minAttack, minSpeed;
    protected String name;
    protected boolean isRedTeam;
    protected static int productionOrder = 0;

    public Robot(boolean isRedTeam) {
        Random rand = new Random();
        this.health = rand.nextDouble() * (getMaxHealth() - getMinHealth()) + getMinHealth();
        this.attack = rand.nextDouble() * (getMaxAttack() - getMinAttack()) + getMinAttack();
        this.speed = rand.nextDouble() * (getMaxSpeed() - getMinSpeed()) + getMinSpeed();
        this.isRedTeam = isRedTeam;
        incrementProductionOrder();

    }

    abstract void attack(Simulation s);

    boolean getHitAndIsDestroyed(double damage) {
        health -= damage;
        return health <= 0;
    }

    abstract double getMaxHealth();

    abstract double getMinHealth();

    abstract double getMaxAttack();

    abstract double getMinAttack();

    abstract double getMaxSpeed();

    abstract double getMinSpeed();

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public boolean isRedTeam() {
        return isRedTeam;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public int compareTo(Robot other) {
        // Compare by speed in descending order
        return Double.compare(other.getSpeed(), this.speed);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementProductionOrder() {
        this.productionOrder++;
    }

    public int getProductionOrder() {
        return productionOrder;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String arrayToString(Robot[] robots) {
        StringBuilder sb = new StringBuilder();
        for (Robot robot : robots) {
            sb.append(" " + robot.toString() + " ");
        }
        return sb.toString();
    }

}
