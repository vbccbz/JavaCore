package u4.Generics.s3.Lambda.LambdaInterpretation;

public class MainApp {
    public static void vv() {
    }

    public static void vi(int i) {
    }

    public static int iv() {
        System.out.println("I AM RIGHT!");
        return 2;
    }

    public static int ii(int i) {
        return 3;
    }

    public static void main(String[] args) {
        /*****************************************************/
        VoidFaceVoid vfv;

        vfv = MainApp::vv;
        /*
        void action(){
            MainApp.vv();
        }
        */

//!     vfv = MainApp::vi;
        /*
        void action(){
            MainApp.vi(???);
        }
        */

        vfv = MainApp::iv;
        /*
        void action(){
            MainApp.iv();
        }
        */
        vfv.action();

//!     vfv = MainApp::ii;
        /*
        void action(){
            MainApp.ii(???);
        }
        */

        /*****************************************************/
        VoidFaceInt vfi;

//        vfi = MainApp::vv;
        vfi = MainApp::vi;
//        vfi = MainApp::iv;
        vfi = MainApp::ii;

        /*****************************************************/
        IntFaceVoid ifv;

//!     ifv = MainApp::vv;
        /*
        int action(){
            return MainApp.vv();
        }
        */

//!     ifv = MainApp::vi;
        /*
        int action(){
            return MainApp.vi();
        }
        */

        ifv = MainApp::iv;
        /*
        int action(){
            return MainApp.iv();
        }
        */

//!     ifv = MainApp::ii;
        /*
        int action(){
            return MainApp.ii(???);
        }
        */

        /*****************************************************/
        IntFaceInt ifi;

//        ifi = MainApp::vv;
        /*
        int action(int i){
            return vv(i);
        }
         */

//        ifi = MainApp::vi;
        /*
        int action(int i){
            return MainApp::vi(i);
        }
        */

//        ifi = MainApp::iv;
        /*
        int action(int i){
            return MainApp::iv(i);
        }
        */

        ifi = MainApp::ii;
        /*
        int action(int i){
            return MainApp.ii(i);
        }
         */

    }
}
