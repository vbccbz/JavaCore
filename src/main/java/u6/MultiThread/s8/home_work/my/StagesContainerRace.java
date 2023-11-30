package u6.MultiThread.s8.home_work.my;

import u6.MultiThread.s8.home_work.my.Stages.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class StagesContainerRace {
    private ArrayList<Stage> stages;
    private String winner;

    public StagesContainerRace(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public synchronized void setWinner(String name){
        if (winner == null) {
            winner = name;
            System.out.println("Победитель " + winner);
        }
    }
}
