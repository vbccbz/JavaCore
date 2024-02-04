package u4.generics.s3.static_methods;

public class MainApp {
    public static void vv() {
    }

    public static void vi(int i) {
    }

    public static int iv() {
        return 2;
    }

    public static int ii(int i) {
        return 3;
    }

    public static void main(String[] args) {
        VoidFaceVoid vfv;
        VoidFaceInt vfi;
        IntFaceVoid ifv;
        IntFaceInt ifi;

        vfv = MainApp::vv;
        //!vfv = MainApp::vi;
        vfv = MainApp::iv;
        //!vfv = MainApp::ii;

        //!vfi = MainApp::vv;
        vfi = MainApp::vi;
        //!vfi = MainApp::iv;
        vfi = MainApp::ii;

        //!ifv = MainApp::vv;
        //!ifv = MainApp::vi;
        ifv = MainApp::iv;
        //!ifv = MainApp::ii;

        //!ifi = MainApp::vv;
        //!ifi = MainApp::vi;
        //!ifi = MainApp::iv;
        ifi = MainApp::ii;

    }
}
