package u0.input_output;

import java.io.*;

public class FileApp {
    static class TabPrinter {
        private int count;

        TabPrinter() {
            count = 0;
        }

        public void printTab() {
            for (int i = 0; i < count; ++i) {
                System.out.print('\t');
            }
        }

        public void reset() {
            count = 0;
        }

        public void increase() {
            ++count;
        }

        public void decrease() {
            --count;
        }
    }

    private TabPrinter tabPrinter = new TabPrinter();// for tab printing

    public void scanAllDirs(String path) throws IOException {
        System.out.println("Found for \"" + path + "\":");
        scanAllDirsRecursion(path);
    }

    public void scanAllDirsRecursion(String path) throws IOException {
        File file = new File(path);// doesn't care about existance!!!
        if (!file.exists()) {
            throw new IOException("no exists");
        }
        if (!file.isDirectory()) {
            throw new IOException("isn't a directory");
        }

        File[] files = file.listFiles();
        if (files.length == 0) {
            return;
        } else {
            for (File f : files) {
                tabPrinter.printTab();
                if (f.isDirectory()) {
                    System.out.println(f.getName());
                    tabPrinter.increase();
                    scanAllDirsRecursion(f.getPath());
                    tabPrinter.decrease();
                } else if (f.isFile()) {
                    System.out.println(f.getName());

//                    FileFilter fileFilter = (pathname) -> {
//                        return pathname.getName().endsWith(".txt");
//                    };
//                    if (fileFilter.accept(f)) {
//                        System.out.print(f.getName());
//                    }
                }
            }
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        FileApp fileApp = new FileApp();
        String path = "C:\\Users\\User\\javaTerminal";
        fileApp.scanAllDirs(path);
    }
}
