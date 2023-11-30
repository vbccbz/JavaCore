package u6.MultiThread.s8.home_work.good;


import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages () { return stages; }
    public Race (Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
    }