package sample;

import java.io.File;

import com.sun.tools.attach.VirtualMachine;

public class Injector {

    /*
     * Handles agent attachment via the VirtualMachine API.
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage: `java sample/Injector <PID> <agentID> <jarpath>`");
        }
        String jarpath = new File(args[2]).getAbsolutePath();
        VirtualMachine vm = VirtualMachine.attach(args[0]);
        try {
            vm.loadAgent(jarpath, args[1]);
        } finally {
            vm.detach();
        }
    }
}
