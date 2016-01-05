/*
 * Copyright 2015-16 Animesh Shaw ( a.k.a. Psycho_Coder)
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
package net.letshackit.androidgesturecrack.tool;

import net.letshackit.androidgesturecrack.AndroidGestureCrack;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Psycho_Coder
 */
public class AGCGui extends JFrame {

    private JPanel panel;

    private JLabel titleLabel;
    private JLabel chooseGestureKeyLabel;
    private JLabel chooseDictionaryLabel;
    private JLabel gestureHashLabel;
    private JLabel gesturePatLabel;
    private JLabel developer;

    private JTextField gestureFileLocTxtField;
    private JTextField dictionaryFileLocTxtField;

    private JButton gestureBrowseButton;
    private JButton dictionaryBrowseButton;
    private JButton crackButton;

    private final JFileChooser fileChooser;

    private final int WIDTH;
    private final int HEIGHT;

    public AGCGui(int WIDTH, int HEIGHT) throws HeadlessException {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        fileChooser = new JFileChooser();
        initComponents();
    }

    private void initComponents() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Android Gesture Crack v2.1");
        URL iconUrl = getClass().getResource("images/andro_crack.jpg");
        setIconImage(new ImageIcon(iconUrl).getImage());

        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Unable to get System Look and Feel.");
        }

        FileFilter dictionaryFilter = new FileNameExtensionFilter("Dictionary File (.txt)", "txt");
        FileFilter gestureKeyFilter = new FileNameExtensionFilter("Key File (.key)", "key");

        panel = new JPanel();
        panel.setSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        titleLabel = new JLabel("Android Gesture Crack Tool v2.1");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(10, 10, 350, 40);
        panel.add(titleLabel);

        chooseGestureKeyLabel = new JLabel("Browse for gesture.key file ");
        chooseGestureKeyLabel.setBounds(20, 60, 400, 20);
        chooseGestureKeyLabel.setForeground(Color.GREEN);
        panel.add(chooseGestureKeyLabel);

        gestureFileLocTxtField = new JTextField();
        gestureFileLocTxtField.setEditable(false);
        gestureFileLocTxtField.setBounds(20, 85, 400, 25);
        panel.add(gestureFileLocTxtField);

        gestureBrowseButton = new JButton("...");
        gestureBrowseButton.setBounds(430, 85, 40, 25);
        gestureBrowseButton.setBackground(Color.RED);
        gestureBrowseButton.setForeground(Color.WHITE);
        gestureBrowseButton.addActionListener((ActionEvent ae) -> {
            fileChooser.addChoosableFileFilter(gestureKeyFilter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    Path gesturePath = fileChooser.getSelectedFile().toPath();
                    if (Files.size(gesturePath) != 20) {
                        JOptionPane.showMessageDialog(this, "gesture.key file is corrupt or different."
                                + "file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    gestureFileLocTxtField.setText(gesturePath.toString());
                } catch (IOException ex) {
                    Logger.getLogger(AGCGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        panel.add(gestureBrowseButton);

        chooseDictionaryLabel = new JLabel("Browse for Dictionary file ");
        chooseDictionaryLabel.setBounds(20, 120, 400, 20);
        chooseDictionaryLabel.setForeground(Color.GREEN);
        panel.add(chooseDictionaryLabel);

        dictionaryFileLocTxtField = new JTextField();
        dictionaryFileLocTxtField.setEditable(false);
        dictionaryFileLocTxtField.setBounds(20, 145, 400, 25);
        panel.add(dictionaryFileLocTxtField);

        dictionaryBrowseButton = new JButton("...");
        dictionaryBrowseButton.setBounds(430, 145, 40, 25);
        dictionaryBrowseButton.setBackground(Color.RED);
        dictionaryBrowseButton.setForeground(Color.WHITE);
        dictionaryBrowseButton.addActionListener((ActionEvent ae) -> {

            fileChooser.removeChoosableFileFilter(gestureKeyFilter);
            fileChooser.addChoosableFileFilter(dictionaryFilter);
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Path dictionaryPath = fileChooser.getSelectedFile().toPath();
                dictionaryFileLocTxtField.setText(dictionaryPath.toString());
            }
        });
        panel.add(dictionaryBrowseButton);

        gestureHashLabel = new JLabel();
        gestureHashLabel.setBounds(20, 180, 450, 25);
        gestureHashLabel.setForeground(Color.YELLOW);
        panel.add(gestureHashLabel);

        gesturePatLabel = new JLabel();
        gesturePatLabel.setBounds(20, 210, 450, 25);
        gesturePatLabel.setForeground(Color.YELLOW);
        panel.add(gesturePatLabel);

        crackButton = new JButton("Start Cracking!");
        crackButton.setBounds(160, 240, 180, 40);
        crackButton.setBackground(Color.RED);
        crackButton.setForeground(Color.WHITE);
        crackButton.addActionListener((ActionEvent ae) -> {
            if (gestureFileLocTxtField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Choose the gesture.key "
                        + "file", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (dictionaryFileLocTxtField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Choose the dictionary "
                        + "file", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                AndroidGestureCrack agc = new AndroidGestureCrack();
                try {
                    agc.crack(gestureFileLocTxtField.getText(), dictionaryFileLocTxtField.getText());
                    gestureHashLabel.setText("Gesture Hash is: " + agc.getGestureHash());
                    gesturePatLabel.setText("Gesture Unlock Pattern is: " + agc.getGesturePattern());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Unable to crack the file. Internal Error!"
                            + "file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(crackButton);

        developer = new JLabel("Programmer: Psycho_Coder");
        developer.setForeground(Color.WHITE);
        developer.setFont(new Font("Courier New", Font.ITALIC, 15));
        developer.setBounds(250, 280, 230, 40);
        panel.add(developer);

        setContentPane(panel); //Add Panel to JFrame
    }
}
