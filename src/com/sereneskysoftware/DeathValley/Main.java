// The ZX81 Death Valley Game!
// Run in a terminal with
// java -jar .\DeathValley.jar

// Deliberately unimproved, and structured sort-of-like-BASIC-ish
// for historical an authenticity reasons...

package com.sereneskysoftware.DeathValley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("DEATH VALLEY");
//        No of goes
        int S = 0;
//        Winning target
        int M = 200;
        System.out.println("WIDTH?");
        int W = Integer.parseInt(reader.readLine());

        W = W / 2;

//        Keep track of whether we have crashed yet
        boolean weCrashed = false;
        int L = 10;
        int Y = W;
        int R = W;
        int D;

        //        Loop until we crash or get to the target
        do { Thread.sleep(250);
            do {
                D = ThreadLocalRandom.current().nextInt(-1, 2);
            } while (L + D < 0 || L + D > 20);

            L = L + D;
            Y = Y - D;
            R = R + D;


            int N = L;
            goSub1000(N);
            System.out.print("I");

            N = Y;
            goSub1000(N);
            System.out.print("*");

            N = R;
            goSub1000(N);
            System.out.println("I");

// q = 113, p = 112, <- = 57419, -> = 57421
//            Ctrl+C:

//            RawConsoleInput GC ;//= new RawConsoleInput();

            int I = RawConsoleInput.read(false);
//            System.out.println("I from SIR " + I);
//            Handle a ctrl+c by crashing the spaceship!
            if (I == 3) weCrashed = true;
            if (I == 113) {
                Y--;
                R++;
            } else if (I == 112) {
                Y++;
                R--;
            }

            if (Y < 1 || R < 1) {
//            crashRoutine();
                weCrashed = true;
            } else {
                S++;
            }
        } while (S < M && !weCrashed);

        if (!weCrashed) {
            System.out.println("WELL DONE-YOU MADE IT");
            System.out.println("THROUGH DEATH VALLEY");
        } else {
            System.out.println("YOU CRASHED INTO THE WALL");
            System.out.println("AND DISINTEGRATED");
        }
    }

    private static void goSub1000(int N) {
        for (int I = 1; I < N; I++) {
            System.out.print(" ");
        }
    }

}