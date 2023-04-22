package sample;

public class Target {

    /*
     * Target process example.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("PID: " + ProcessHandle.current().pid());

        while (!Thread.interrupted()) {
            Thread.sleep(2000);
        }
    }
}
