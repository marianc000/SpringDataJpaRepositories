package sample;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Out {

    static PrintStream out = System.out;
    static ByteArrayOutputStream os = new ByteArrayOutputStream();

    static {
        System.setOut(new PrintStream(os));
    }

    static String getOutput() {
        try {
            return os.toString();
        } finally {
            os.reset();
        }
    }

    static PrintStream getOut() {
        return out;
    }

    static void printOut() {
        out.println(getOutput());
    }

    static void restoreOut() {
        System.setOut(out);
    }
}
