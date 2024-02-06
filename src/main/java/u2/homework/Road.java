package u2.homework;

public class Road extends Obstacle {

    public Road(int dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void action(Participant p) {
        p.run(dimensions);
    }

}
