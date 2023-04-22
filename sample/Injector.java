package sample;

import com.sun.tools.attach.VirtualMachine;

public class Injector {

    /*
     * Handles agent attachment via the VirtualMachine API.
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: `java sample/Inspector <PID> <agentID>`");
        }

        VirtualMachine vm = VirtualMachine.attach(args[0]);
        try {
            vm.loadAgent("agent.jar", args[1]);
        } finally {
            vm.detach();
        }
    }
}
