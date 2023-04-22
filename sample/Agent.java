package sample;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Agent {

    /*
     * Decides which agent to run.
     */
    public static void agentmain(String args, Instrumentation inst) throws Exception {
        switch (args) {
            case "agent1":
                agent1(inst);
                break;

            case "agent2":
                agent2(inst);
                break;

            default:
                agent0();
        }
    }

    /*
     * Prints something into the target process.
     */
    public static void agent0() {
        System.out.println("*hacker voice* I'm in.");
    }

    /*
     * Calls a private function from the target process.
     */
    public static void agent1(Instrumentation inst) throws Exception {
        Class<?> clazz = Class.forName("sample.Target");

        // access Target's `private static String secretField`
        Field f = clazz.getDeclaredField("secretField");
        f.setAccessible(true);
        System.out.println(f.get(clazz));

        // access Target's `private static void secretFunction()`
        Method m = clazz.getDeclaredMethod("secretFunction");
        m.setAccessible(true);
        m.invoke(clazz);
    }

    /*
     * Executes custom code from a Minecraft process.
     */
    public static void agent2(Instrumentation inst) {
    }
}
