package LAB3;

@SuppressWarnings("rawtypes")
public class SpeedBot extends Robot {
    protected static double maxHealth = 2, maxAttack = 2, maxSpeed = 5;
    protected static double minHealth = 1, minAttack = 1, minSpeed = 4;

    public SpeedBot(boolean isRedTeam) {
        super(isRedTeam);
        this.name = "X" + (productionOrder);
    }

    @Override
    void attack(Simulation s) {
        Robot target = s.getLowestHealth(isRedTeam);
        System.out.printf(
                this + " attacks " + target + "\n" + target + " recieves %.3f  damage -> remaining health %.3f\n",
                this.attack, (this.attack >= target.getHealth() ? 0 : target.getHealth() - this.attack));

        if (target.getHitAndIsDestroyed(this.attack)) {
            s.removeRobot(target);
        } else {
            System.out.println(target + " survives this attack");
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
