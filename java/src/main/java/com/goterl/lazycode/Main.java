package com.goterl.lazycode;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;


import static org.fusesource.jansi.Ansi.ansi;

public class Main {

    public static void main(String[] args) {
        // First setup
        setup();
        printTitle();

        if (args.length == 0) {
            printIntro();
        } else {
            // Run only if provided an argument
            String arg1 = args[0];

            try {
                Integer parsed = Integer.parseInt(arg1);
                Main main = new Main(parsed);
                main.run();
            } catch (Exception e) {
                AnsiConsole.system_err.println(
                        "Error: " + arg1 + " is not a valid number. " +
                        "Please provide a number of the operation you want to perform.");
            }
        }
    }

    private static void setup() {
        AnsiConsole.systemInstall();
    }

    private static void printTitle() {
        Ansi line = ansi()
                .fgBrightRed()
                .a(" ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                .reset();

        log();
        log(line);
        log("        Lazysodium for Java Examples       ");
        log(line);
        log();
    }

    private static void printIntro() {
        log("Please provide, as a command line argument, one of the following numbers:");
        log("1. Secret key: Perform encryption using a shared private key.");
        log("2. Public key: Encryption using public-private key.");
        log("3. Generic hashing: Hash arbitrarily.");
        log();
    }



    private Integer parsed;

    public Main(Integer parsed) {
        this.parsed = parsed;
    }


    public void run() {

    }




    // Helpers

    private static void log() {
        log("");
    }

    private static void log(String s) {
        System.out.println(s);
    }

    private static void log(Ansi s) {
        AnsiConsole.out().println(s);
    }

}
