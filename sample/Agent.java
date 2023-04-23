package sample;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Agent {

    /*
     * Agent's main function, decides which agent to run.
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
        Class<?> Target = Class.forName("sample.Target");

        // access Target's `private static String secretField`
        Object value = getField(Target, "secretField").get(Target);
        System.out.println(value);

        // access Target's `private static void secretFunction()`
        getMethod(Target, "secretFunction").invoke(Target);
    }

    /*
     * Executes custom code from a Minecraft 1.19.4 process.
     */
    public static void agent2(Instrumentation inst) throws Exception {
        Class<?> Minecraft = Class.forName(translate("net.minecraft.client.Minecraft"));
        Class<?> Component = Class.forName(translate("net.minecraft.network.chat.Component"));

        // Component.literal("text")
        Method literal = getMethod(Component, translate("literal"), String.class);
        Object component = literal.invoke(Component, "*hacker voice* I'm in.");

        // Minecraft.instance
        Object instance = getField(Minecraft, translate("instance")).get(Minecraft);

        // Minecraft.instance.player
        Object player = getField(Minecraft, translate("player")).get(instance);

        // Minecraft.instance.player.sendSystemMessage(Component.literal("text"));
        Method sendSystemMessage = getMethod(player.getClass(), translate("sendSystemMessage"), Component);
        sendSystemMessage.invoke(player, component);
    }

    /*
     * Dirty way to translate from Mojang Mappings to the Obfuscated version.
     */
    public static String translate(String name) {
        return switch (name) {
            case "net.minecraft.network.chat.Component" -> "tj";
            case "literal" -> "b";

            case "net.minecraft.client.Minecraft" -> "emh";
            case "instance" -> "F";
            case "player" -> "t";

            case "net.minecraft.client.player.LocalPlayer" -> "fhk";
            case "sendSystemMessage" -> "a";

            default -> name;
        };
    }

    /*
     * Get a method by name and force it to be public.
     */
    public static Method getMethod(Class<?> base, String name, Class<?>... params) throws Exception {
        Method m = base.getDeclaredMethod(name, params);
        m.setAccessible(true);
        return m;
    }

    /*
     * Get a field by name and force it to be public.
     */
    public static Field getField(Class<?> base, String name) throws Exception {
        Field f = base.getDeclaredField(name);
        f.setAccessible(true);
        return f;
    }
}
