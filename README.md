# AndroidGestureCrack

Cracks Android Screenlock Gesture Pattern using dictionary Attack

## Description

A Java tool to crack the Gesture Screenlock pattern of an Android Phone. Android's pattern lock contains a SHA1 hash of the pattern in a gesture.key file in /data/system folder in the internal memory of the android.

The key is a SHA1 hash of the sequence of bytes of the pattern. The hash is unsalted and hence very easy to crack. The code uses the dictionary which you can download from the link below:-

###### [Download Dictionary](http://www.android-forensics.com/tools/AndroidGestureSHA1.rar)

### Usage

You need to get the gesture.key file from android hidden system folder and extract the dictionary file from link above. Use them in the following way:

    javac androidgesturecrack\AndroidGestureCrack.java
    java androidgesturecrack.AndroidGestureCrack gesture.key AndroidGestureSHA1.txt

__Sample Run__

![sample run](http://imgur.com/gTkzJ6p.png)

![sample](http://i.imgur.com/rlnoKvL.png)
