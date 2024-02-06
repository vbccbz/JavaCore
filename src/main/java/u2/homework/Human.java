package u2.homework;

public class Human implements Participant {
    private int runStatus;
    private int maxJump;
    private int maxRun;

    public Human(int maxJump, int maxRun/*, int maxSwim*/) {
        this.runStatus = 1;
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    @Override
    public boolean isRunning() {
        return (runStatus == 1);
    }

    @Override
    public void run(int length) {
        if (isRunning()) {
            if (length <= maxRun) {
                runStatus = 1;
                System.out.println(" run ");
            } else {
                runStatus = 0;
                System.out.println(" can't run ");
            }

        } else {
            System.out.println(" isn't running at all ");
        }
    }

    @Override
    public void jump(int length) {
        if (isRunning()) {

            if (length <= maxJump) {
                runStatus = 1;
                System.out.println(" jumped ");
            } else {
                runStatus = 0;
                System.out.println(" can't jumped ");
            }

        } else {
            System.out.println(" isn't running at all ");
        }
    }

}
