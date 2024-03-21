package LAB3;

@SuppressWarnings("rawtypes")
public class PredatorBot extends Robot {
    protected static double maxHealth = 3, maxAttack = 3, maxSpeed = 1;
    protected static double minHealth = 2, minAttack = 2, minSpeed = 0.5;

    public PredatorBot(boolean isRedTeam) {

        super(isRedTeam);
        this.name = "P" + (productionOrder);
    }

    @Override
    void attack(Simulation s) {
        Robot target = s.getHighestHealth(isRedTeam);
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
