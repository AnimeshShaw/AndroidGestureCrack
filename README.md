# AndroidGestureCrack

Cracks Android Screenlock Gesture Pattern using Dictionary Attack

## Description

A Java tool to crack the Gesture Screenlock pattern of an Android Phone. Android's pattern lock contains a SHA1 hash of the pattern in a gesture.key file in /data/system folder in the internal memory of the android. It has been tested on Android API Level 15 and up, but should work with lower versions as well. As long as you extract the __gesture.key__ file this tool will work

The key is a SHA1 hash of the sequence of bytes for the gesture pattern. The hash is unsalted and hence is easier to crack. The code uses the dictionary which you can download from the link below:-

###### [Download Dictionary](http://www.android-forensics.com/tools/AndroidGestureSHA1.rar)

### Usage

You need to get the gesture.key file from android hidden system folder and extract the dictionary file from link above. In the sample folder a gesture.key file has been provided for demo testing of this tool. Use them in the following way (--gui brings up the GUI for this tool):

    java -jar AndroidGestureCrack.jar --gui
                    or
    java -jar AndroidGestureCrack.jar gesture.key AndroidGestureSHA1.txt

Java Version 8 or higher is required to compile the code. You can download the latest release (executable jar) [from here](https://github.com/AnimeshShaw/AndroidGestureCrack/releases/download/v2.0-beta/AndroidGestureCrack.jar)

__Sample Run__

![sample run](https://i.imgur.com/czmRXUp.png)

![sample](http://i.imgur.com/rlnoKvL.png)
