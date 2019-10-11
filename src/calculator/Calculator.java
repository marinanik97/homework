package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Calculator {

    public Calculator() {
        components();
    }

    //funkcija vraca true, ako string sadrzi specijalan znak
    //funkcija vraca false, ako string ne sadrzi specijalan znak
    private boolean specialCharacters(String s) {
        String specialChar = "[]!@#$%&*()_+=|<>?{}~-";
        int length = specialChar.length();
        char[] specialCharArray = specialChar.toCharArray();
        int i;
        for (i = 0; i < length; i++) {
            char specialCharacter = specialCharArray[i];
            int returnValue = s.indexOf(specialCharacter);
            if (returnValue != -1) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    //funkcija vraca true ako string ne sadrzi slova i specijalne znakove
    //a vraca false, ako sadrzi
    private boolean checkIfStringContainsNumbers(String s) {
        char[] newString = s.toCharArray();
        int count = 0;
        for (int i = 0; i < newString.length; i++) {
            if (specialCharacters(s)) {
                return false;
            } else {
                if ((newString[i] >= 'a' && newString[i] <= 'z')
                        || (newString[i] >= 'A' && newString[i] <= 'Z')) {
                    count++;
                }
            }

        }
        if (count == 0) {
            return true;
        }
        return false;
    }

    //ova funkcija je napravljena u slucaju da korisnik
    //unese npr broj 123 456, koji ima razmak
    private char[] toNumber(String s) {
        int length = s.length();
        char[] numbers = new char[length];
        char[] string = s.toCharArray();
        int count = 0;
        int i, j;
        for (i = 0; i < length; i++) {
            if (string[i] >= '0' && string[i] <= '9') {
                numbers[count] = string[i];
                count++;
            }
        }

        return numbers;
    }
    //funkcija pravi i vraca string brojeva bez razmaka
    private String stringWithoutSpace(String s) {
        char[] numbersArray = toNumber(s);
        String numbers = new String(numbersArray);
        return numbers;
    }
    //provera da li su oba stringa prazna
    private boolean bothStringEmpty(String s1, String s2) {
        if (s1.isEmpty() && s2.isEmpty()) {
            return true;
        }
        return false;
    }
    //provera da li je prazan prvi, drugi ili oba stringa
    private int checkIfStringIsEmpty(String s1, String s2) {
        if (bothStringEmpty(s1, s2)) {
            return 2;
        } else if (s1.isEmpty()) {
            return 0;
        } else if (s2.isEmpty()) {
            return 1;
        }

        return 3;
    }

    private void components() {
        JFrame frame = new JFrame("Calculator");

        JLabel jlabA = new JLabel("a:");
        JLabel jlabB = new JLabel("b:");
        JLabel jlabC = new JLabel("c:");
        JLabel jlabAlert = new JLabel("");

        JTextField jtxtA = new JTextField();
        JTextField jtxtB = new JTextField();
        JTextField jtxtC = new JTextField();

        JButton jbtnAdd = new JButton("Add");
        JButton jbtnSubtract = new JButton("Subtract");

        frame.add(jlabA);
        frame.add(jtxtA);

        frame.add(jlabB);
        frame.add(jtxtB);

        frame.add(jlabC);
        frame.add(jtxtC);

        frame.add(jbtnAdd);
        frame.add(jbtnSubtract);
        frame.add(jlabAlert);

        jlabA.setBounds(50, 50, 50, 50);
        jlabB.setBounds(50, 100, 50, 50);
        jlabC.setBounds(50, 150, 50, 50);

        jtxtA.setBounds(100, 60, 100, 30);
        jtxtB.setBounds(100, 110, 100, 30);
        jtxtC.setBounds(100, 160, 100, 30);

        jbtnAdd.setBounds(50, 250, 100, 40);
        jbtnSubtract.setBounds(200, 250, 100, 40);

        jlabAlert.setBounds(50, 300, 200, 40);

        jtxtC.setEditable(false);
        jlabAlert.setForeground(Color.red);

        //GridLayout layout = new GridLayout(2,3);
        //frame.setLayout(layout);
        frame.setLayout(null);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener al1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String A = jtxtA.getText();
                    String B = jtxtB.getText();
                    String C;
                    jlabAlert.setText("");
                    int number = checkIfStringIsEmpty(A, B);
                    if (number != 3) {
                        switch (number) {
                            case 0:
                                jlabAlert.setText("Field a is empty");
                                break;
                            case 1:
                                jlabAlert.setText("Field b is empty");
                                break;
                            case 2:
                                jlabAlert.setText("Both fields are empty");
                                break;
                        }
                    } else {

                        if (checkIfStringContainsNumbers(A) == true
                                && checkIfStringContainsNumbers(B) == true) {

                            String fieldA = stringWithoutSpace(A);
                            String fieldB = stringWithoutSpace(B);

                            int valueA = Integer.parseInt(fieldA.trim());
                            int valueB = Integer.parseInt(fieldB.trim());
                            int valueC = 0;

                            if (e.getSource() == jbtnAdd) {
                                valueC = valueA + valueB;
                            } else {
                                valueC = valueA - valueB;
                            }
                            C = Integer.toString(valueC);
                            jtxtC.setText(C);

                        } else {
                            jlabAlert.setText("You can't enter letters or special characters! ");
                        }
                    }
                } catch (Exception ee) {
                    System.out.println("Greska: " + ee);
                }

            }
        };

        jbtnAdd.addActionListener(al1);
        jbtnSubtract.addActionListener(al1);

    }

}
