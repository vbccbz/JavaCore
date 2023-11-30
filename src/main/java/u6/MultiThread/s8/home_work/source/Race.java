package u6.MultiThread.s8.home_work.source;

import java.util.ArrayList;
import java.util.Arrays;

class Race {
    private ArrayList<Stage> stages;

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
}
