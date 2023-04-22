package sample;

public class Target {

    /*
     * Unused private static field
     */
    private static String secretField = "where were u when club penguin die";

    /*
     * Target process example.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("PID: " + ProcessHandle.current().pid());

        while (!Thread.interrupted()) {
            Thread.sleep(2000);
        }
    }

    /*
     * Unused private static function
     */
    private static void secretFunction() {
        System.out.println("I was at house eating dorito");
    }

}
