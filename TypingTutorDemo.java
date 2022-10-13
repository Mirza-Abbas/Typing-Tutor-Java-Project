import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.lang.*;

class MyThread extends Thread{      

    int errors;
    double accuracy, speed;

    MyThread(int err, double acc, double sp){     

        errors=err;
        accuracy=acc;
        speed=sp;
    }
    public void run(){

        JOptionPane P=new JOptionPane();
        P.showMessageDialog(null,("Errors: " + errors + "\nAccuracy: " + new DecimalFormat("#0.00").format(accuracy) + "%" + "\nSpeed: " + new DecimalFormat("#00.00").format(speed) + " wpm"));

    }
}

class TypingTutor{

    private final int WIDTH = 610, LENGTH = 650;    

    private JTextArea promptBox, typeBox;
    private JButton ResetButton;
    private JLabel Name, wrongLabel, numErrorsLabel, accuracyLabel, wpmLabel;
    private ArrayList <JButton> buttons;
    private String qwerty, typedText, Text;
    private double accuracy, startTime, endTime, speed;
    private int currentChar, rowBreak1, rowBreak2, numErrors, numKeysTyped;

    JPanel P1=new JPanel();
    JLabel L1=new JLabel();

    TypingTutor(){

    }

    
}

class TypingTutorDemo{
    public static void main(String[] args) {
        TypingTutor ProjectDemo=new TypingTutor();
    }
}