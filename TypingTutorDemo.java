import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.lang.*;

class MyThread extends Thread{      

    int errors;
    double accuracy, speed;

    MyThread(int err, double acc, double sp){      //Contsructor

        errors=err;
        accuracy=acc;
        speed=sp;
    }
    public void run(){

        JOptionPane P=new JOptionPane();
        P.showMessageDialog(null,("Errors: " + errors + "\nAccuracy: " + new DecimalFormat("#0.00").format(accuracy) + "%" + "\nSpeed: " + new DecimalFormat("#00.00").format(speed) + " wpm"));

    }
}

class TypingTutor extends JFrame implements KeyListener, ActionListener{

    private final int WIDTH = 610, LENGTH = 650;        //JFrame dimensions

    private JTextArea promptBox, typeBox;
    private JButton ResetButton;
    private JLabel Name, wrongLabel, numErrorsLabel, accuracyLabel, wpmLabel;
    private ArrayList <JButton> buttons;
    private String qwerty, typedText, Text;
    private double accuracy, startTime, endTime, speed;
    private int currentChar, rowBreak1, rowBreak2, numErrors, numKeysTyped;

    JPanel P1=new JPanel();
    JLabel L1=new JLabel();

    TypingTutor(){      //Constructor

        this.setLayout(null);
        this.addKeyListener(this);
        this.setFocusable(true);
        //this.requestFocusInWindow();

        qwerty = "qwertyuiopasdfghjkl;'zxcvbnm,./ ";
        Text = new String("The average typing speed of a computer typist is just 36wpm. Touch typists are generally faster, averaging a quick 48 words per minute. The fastest typing speed ever recorded is currently an extreme 216 words per minute! Average accuracy is 92%. Bananas");
        //Text=new String("a b c");
        typedText = "> ";
        rowBreak1 = 10;
        rowBreak2 = 21;
        numErrors = 0;
        currentChar = 0;
        numKeysTyped = 0;
        
        promptBox = new JTextArea(Text);
        typeBox = new JTextArea(typedText);
        buttons = new ArrayList <JButton> ();
        wrongLabel = new JLabel("Wrong!");
        numErrorsLabel = new JLabel("Errors: ");
        accuracyLabel = new JLabel("Accuracy: ");
        wpmLabel = new JLabel("Speed: ");
        ResetButton=new JButton("Reset");

        P1.setLayout(null);
        P1.setBounds(0, 0, 610, 100);
        P1.setBackground(Color.BLUE);
        add(P1);

        L1.setText("Welcome To Typing Tutor");
        L1.setBounds(100,0,400,70);
        //L1.setText("Typing Tutor");
        //L1.setBounds(185,0,400,70);
        L1.setFont(new Font("Arial", Font.PLAIN, 30));
        L1.setForeground(Color.WHITE);
        P1.add(L1);

        L1=new JLabel("By: Mirza Muhammad Abbas (21SW101)");
        L1.setBounds(138,20,300,100);
        L1.setFont(new Font("Arial", Font.PLAIN, 15));
        L1.setForeground(Color.WHITE);
        P1.add(L1);

        promptBox.setSize(400, 100);
        promptBox.setLocation(60, 110);
        promptBox.setFocusable(false);
        promptBox.setBackground(null);
        promptBox.setLineWrap(true);
        promptBox.setWrapStyleWord(true);

        typeBox.setSize(400, 140);
        typeBox.setLocation(60, 210);
        typeBox.setFocusable(false);
        typeBox.setLineWrap(true);
        typeBox.setWrapStyleWord(true);

        wrongLabel.setSize(100, 50);
        wrongLabel.setLocation(500, 120);
        wrongLabel.setVisible(false);

        numErrorsLabel.setSize(100, 30);
        numErrorsLabel.setLocation(500, 190);
        numErrorsLabel.setVisible(true);

        ResetButton.addActionListener(this);
        ResetButton.setBounds(490,260,80,50);
        ResetButton.setVisible(true);
        ResetButton.setFocusable(false);

        Container pane = getContentPane();

        pane.add(promptBox);
        pane.add(typeBox);
        pane.add(wrongLabel);
        pane.add(numErrorsLabel);
        pane.add(ResetButton);

        addButtons();       //calls addButtons Method
        addSpacebar();      //calls addSpacebar Method

        setVisible(true);                   //Jframe visibility
        setSize(WIDTH, LENGTH);               //Jframe dimesnions
        setResizable(false);          //stops frame from resizing
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //closes Jframe

    }

    @Override
        public void actionPerformed(ActionEvent e) {        //When Reset Button is clicked

            resetTypeBox();         // Calls resetTypeBox() Method
        }

    public void addButtons() {      //addButtons Method Defination

        for (int i = 0; i < qwerty.length()-1; ++i) {

            buttons.add(new JButton(Character.toString(qwerty.charAt(i))));
            buttons.get(i).setSize(50, 50);

            setPosition(i);     //calls setPosition Method

            this.getContentPane().add(buttons.get(i));
            buttons.get(i).setFocusable(false);
        }
    }

    public void addSpacebar() {     //addSpacebar Method Defination

        JButton spacebar = new JButton();
        buttons.add(spacebar);
        spacebar.setSize(250, 50);
        spacebar.setLocation(180, 520);
        this.getContentPane().add(spacebar);
    }

    public void setPosition(int i) {        //setPosition Method Defination

        if (i <= 9) {       //First Row Keys

            buttons.get(i).setLocation(10 + 50*i, 370);
        }
        else if (i >= 10 && i < rowBreak2) {        //Second Row Keys

            buttons.get(i).setLocation(30 + 50*(i-rowBreak1), 420);    //rowbreak1=10, rowbreak2==21
        }
        else if (i >= rowBreak2) {      //Third row Keys

            buttons.get(i).setLocation(50 + 50*(i-rowBreak2), 470);
        }
        else {

            System.out.println("error\n");
        }
    }

    public void setButtonColor(KeyEvent key, int i) {   //SetButtonColor Method Defination

        if (key.getKeyChar() == Text.charAt(currentChar)) {     //When correct key's pressed

            buttons.get(i).setBackground(Color.green);
        }
        else if (key.getKeyChar() != Text.charAt(currentChar)) {    //When wrong key's pressed

            buttons.get(i).setBackground(Color.red);
        }
    }

    @Override
    public void keyPressed (KeyEvent e) {      

        int i = qwerty.indexOf(e.getKeyChar());
        setButtonColor(e, i);  //calls setButtonColor Method
    }

    @Override
    public void keyReleased (KeyEvent e) {
    
        int i = qwerty.indexOf(e.getKeyChar());
        buttons.get(i).setBackground(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
        numKeysTyped++;
        if(e.getKeyChar() == Text.charAt(currentChar)){     //When correct key's pressed

            if(numKeysTyped==1){

                startTime=System.nanoTime();
            }
            wrongLabel.setVisible(false);
            typedText+= e.getKeyChar();
            typeBox.setText(typedText);
            ++currentChar;
        }
        else if (e.getKeyChar() != Text.charAt(currentChar)) {      //When Worng Key's Pressed

            wrongLabel.setVisible(true);
            ++numErrors;
            numErrorsLabel.setText("Errors: " + numErrors);
        }
        else{

            System.out.println("Something Went Wrong");
        }
        
        if (currentChar == Text.length()) {   

            endTime = System.nanoTime();
            calculateWPM();         //Calls calculateWPM method 
            displayFinalScreen();   //Calls displayFinalScreenMethod
        }
    }

    public void calculateWPM() {        //calculateWPM Method Defination

        double timeElapsed = endTime - startTime;
        int numWords = typedText.length() - 
                                typedText.replaceAll(" ", "").length() - 1;
        speed = numWords / (timeElapsed / 1000000000) * 60; 
    }

    public void displayFinalScreen() {      //displayFinalScreen MEthod Defination
        
        accuracy = 100 - ((double)numErrors * 100 / numKeysTyped);
        MyThread m=new MyThread(numErrors,accuracy, speed);     //Mythread Object
        m.start();      
    }

    void resetcolor(char c){        //resetcolor Method Defination

        int i = qwerty.indexOf(c);
        buttons.get(i).setBackground(null);
    }

    public void resetTypeBox() {        //resetTypeBox Method defination

        resetcolor(Text.charAt(currentChar-1));
        numErrorsLabel.setText("Errors: " );
        wrongLabel.setVisible(false);
        typedText = "> ";
        numErrors = 0;
        currentChar = 0;
        numKeysTyped = 0;
        typeBox.setText(typedText);
    }
}

class TypingTutorDemo{  //Main Class
    public static void main(String[] args) {        //Main Method

        TypingTutor P1=new TypingTutor();       //TypingTutor Object
    }
}
