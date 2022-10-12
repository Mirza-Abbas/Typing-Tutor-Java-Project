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

}

class TypingTutorDemo{
    public static void main(String[] args) {
        TypingTutor ProjectDemo=new TypingTutor();
    }
}