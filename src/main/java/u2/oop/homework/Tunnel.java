package u2.oop.homework;

public class Tunnel extends Obstacle {

    public Tunnel(int dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public void action(Participant p) {
        p.jump(dimensions);
    }
}



