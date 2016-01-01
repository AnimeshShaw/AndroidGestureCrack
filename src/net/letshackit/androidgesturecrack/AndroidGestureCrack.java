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
package net.letshackit.androidgesturecrack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * A Java tool to crack the Gesture Screenlock pattern of an Android Phone.
 * Android's pattern lock contains a SHA1 hash of the pattern in a gesture.key
 * file in /data/system folder in the internal memory of the android.
 *
 * The key is a SHA1 hash of the sequence of bytes of the pattern. The hash is
 * unsalted and hence very easy to crack. The code uses the dictionary which you
 * can download from here:=
 * http://www.android-forensics.com/tools/AndroidGestureSHA1.rar
 *
 * @author Psycho_Coder (psychocoder@outlook.com)
 * @version 2.1
 */
public class AndroidGestureCrack {

    private String gHash;
    private String gPat;

    public AndroidGestureCrack() {
        gHash = gPat = null;
    }

    private static String byteArraytoHexString(byte[] dataBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : dataBytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString().toUpperCase();
    }

    public String getGestureHash() {
        if (!gHash.isEmpty()) {
            return gHash;
        } else {
            return null;
        }
    }

    public String getGesturePattern() {
        if (!gPat.isEmpty()) {
            return gPat;
        } else {
            return null;
        }
    }

    public void crack(String pathGesture, String pathDictionary) throws IOException {
        Path gesturePath = Paths.get(pathGesture);
        Path dictionaryPath = Paths.get(pathDictionary);
        if (!Files.exists(gesturePath)) {
            throw new NoSuchFileException("gesture.key file not found.");
        } else if (Files.size(gesturePath) != 20) {
            throw new IllegalArgumentException("gesture.key file size is not in proper format.");
        }
        if (!Files.exists(dictionaryPath)) {
            throw new NoSuchFileException("dictionary file not found");
        }
        byte[] gestureKey = Files.readAllBytes(gesturePath);
        final String gestureHash = byteArraytoHexString(gestureKey);
        gHash = gestureHash;
        try (Stream<String> stream = Files.lines(dictionaryPath)) {
            stream.skip(5)
                    .map(line -> line.split(";"))
                    .filter(dictData -> gestureHash.equals(dictData[2]))
                    .findFirst()
                    .ifPresent(dictData -> gPat = dictData[0]);
        }
    }    
}