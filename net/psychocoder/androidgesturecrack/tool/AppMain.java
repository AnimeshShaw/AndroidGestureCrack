/*
 * Copyright 2015 Psycho_Coder.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.psychocoder.androidgesturecrack.tool;

import java.io.IOException;
import javax.swing.SwingUtilities;
import net.psychocoder.androidgesturecrack.AndroidGestureCrack;

/**
 *
 * @author Psycho_Coder
 */
public class AppMain {

    public static void main(String[] args) throws IOException {
        if (args.length == 1 && args[0].equalsIgnoreCase("--gui")) {
            SwingUtilities.invokeLater(() -> {
                new AGCGui(500, 350).setVisible(true);
            });
        } else if (args.length == 2 && !args[0].equalsIgnoreCase("--gui")) {
            AndroidGestureCrack agc = new AndroidGestureCrack();
            agc.crack(args[0], args[1]);
            System.out.println("\nYour Gesture Hash is: " + agc.getGestureHash());
            System.out.println("\nYour Gesture Unlock Code is: " + agc.getGesturePattern());
        } else {
            System.out.println(System.getProperty("user.dir"));
            System.err.println("Required exactly two command line arguments\n");
            System.out.print("Usage: java -jar AndroidGestureCrack.jar gesture.key AndroidGestureSHA1.txt\n\t\tor");
            System.out.print("\nUsage: java -jar AndroidGestureCrack.jar --gui\n");
        }
    }

}
