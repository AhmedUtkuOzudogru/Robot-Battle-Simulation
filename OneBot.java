package LAB3;

@SuppressWarnings("rawtypes")
public class OneBot extends Robot {
    protected static double maxHealth = 1, maxAttack = 5, maxSpeed = 1;
    protected static double minHealth = 0.5, minAttack = 4, minSpeed = 0.5;

    public OneBot(boolean isRedTeam) {
        super(isRedTeam);
        this.name = "O" + (productionOrder);
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
        return this.maxAttack;
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
