package LAB3;

@SuppressWarnings("rawtypes")
public class SpreadBot extends Robot {
    protected static double maxHealth = 3, maxAttack = 2, maxSpeed = 1.5;
    protected static double minHealth = 2, minAttack = 1, minSpeed = 0.5;

    public SpreadBot(boolean isRedTeam) {
        super(isRedTeam);
        this.name = "K" + (productionOrder);
    }

    @Override
    void attack(Simulation s) {
        Robot[] targets = s.getLowestSpeed3(isRedTeam);
        System.out.println(this + " attacks following: " + arrayToString(targets));
        for (Robot target : targets) {
            System.out.printf(
                    this + " attacks " + target + "\n" + target + " recieves %.3f  damage -> remaining health %.3f\n",
                    this.attack, (this.attack >= target.getHealth() ? 0 : target.getHealth() - this.attack));
            if (target.getHitAndIsDestroyed(this.attack)) {
                s.removeRobot(target);
            }
        }
    }

    @Override
    double getMaxAttack() {
        return maxAttack;
    }

    @Override
    double getMaxHealth() {
        return maxHealth;
    }

    @Override
    double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    double getMinAttack() {
        return minAttack;
    }

    @Override
    double getMinHealth() {
        return minHealth;
    }

    @Override
    double getMinSpeed() {
        return minSpeed;
    }
}
