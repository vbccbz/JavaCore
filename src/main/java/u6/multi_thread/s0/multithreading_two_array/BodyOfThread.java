package u6.multi_thread.s0.multithreading_two_array;

public class BodyOfThread implements Runnable {
    private float[] floats;

    public BodyOfThread(float[] data) {
        floats = data;
    }

    public void calculate(){
        for (int i = 0; i < floats.length; i++) {
            floats[i] = (float) (floats[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    @Override
    public void run() {
        calculate();
    }
}
