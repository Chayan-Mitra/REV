package com.rev;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.Font;

public class Init{
    JFrame frame;
    JLabel image=new JLabel(new ImageIcon("REV-logo.gif"));
    JProgressBar progressBar=new JProgressBar();
    JLabel message=new JLabel();

    Init() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        createGUI();
        addImage();
        addProgressBar();
        addMessage();
        runningPBar();
    }
    public void createGUI(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);

    }
    public void addImage(){
        image.setSize(550,300);
        frame.add(image);
    }
    public void addMessage()
    {
        message.setBounds(100,320,200,40);
        message.setForeground(Color.BLUE);
        message.setFont(new Font("Helvetica",Font.BOLD,16));
        frame.add(message);
    }
    public void addProgressBar(){
        progressBar.setBounds(100,350,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLUE);
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    private void load(int time)
    {
        if (time != 40 && time >= 40)
        {
            message.setText("Initialising Interface...");
        }
        else
        {
            message.setText("Configuring Modules...");
        }
    }
    private static void createWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("REV - Revserse everything...");
        Image icon = Toolkit.getDefaultToolkit().getImage("REV-logo_icon.png");
        frame.setIconImage(icon);
        JPanel main_panel = new JPanel();
        main_panel.setLayout(new BoxLayout(main_panel , BoxLayout.Y_AXIS));
        JPanel title = new JPanel(new GridLayout(1 , 1 , 2 , 2));
        JPanel panel = new JPanel(new GridLayout(1,1,10,10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btn = new JButton("Reverse a string");
        JButton btn1 = new JButton("Reverse a number");
        JLabel lable = new JLabel("Welcome to REV..." , JLabel.CENTER);
        lable.setFont(new Font("Helvetica", Font.BOLD, 18));
        title.add(lable);
        panel.add(btn);
        panel.add(btn1);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input =  JOptionPane.showInputDialog("Enter a string...");
                String in = input.toLowerCase();
                try {
                    Init.displayTray(input);
                } catch (AWTException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                try {
                    Thread.sleep(5000);
                    inverse(in);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input =  JOptionPane.showInputDialog("Enter the number...");
                String in = input.toLowerCase();
                try {
                    Init.displayTrayNumber(input);
                } catch (AWTException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                try {
                    Thread.sleep(5000);
                    inverseNumber(in);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.setLocationRelativeTo(null);
        main_panel.add(title);
        main_panel.add(panel);
        frame.add(main_panel);
        frame.setSize(900 , 500);
        frame.setVisible(true);
    }
    public void runningPBar() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        int i=0;

        while(i != 100)
        {
            try{
                Thread.sleep(150);
                progressBar.setValue(i);
                load(i);
                i++;
                if(i==100)
                    frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        createWindow();
    }

    public static void displayTray(String input) throws AWTException, MalformedURLException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "REV..");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("REV..");
        tray.add(trayIcon);
        trayIcon.displayMessage("String reversed", "The string " + input + " was revered successfully!", TrayIcon.MessageType.INFO);
    }
    public static void displayTrayNumber(String input) throws AWTException, MalformedURLException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "REV..");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("REV..");
        tray.add(trayIcon);
        trayIcon.displayMessage("Number reversed", "The number " + input + " was revered successfully!", TrayIcon.MessageType.INFO);
    }
    public static void inverse(String input)
    {
        char reverse;
        String rev_string = "";
        for (int x = 0 ; x < input.length() ; x++)
        {
            reverse = input.charAt(x);
            rev_string = reverse + rev_string;
        }
        Object[] options = {"Okay" , "Copy the reversed string.."};
        int choose = JOptionPane.showOptionDialog(null , "The string was reversed to " + rev_string + "." , "REV - Reverse everything..." , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.QUESTION_MESSAGE , null , options , options[1]);
        if (choose == 1)
        {
            StringSelection string = new StringSelection(rev_string);
            Clipboard copy = Toolkit.getDefaultToolkit().getSystemClipboard();
            copy.setContents(string , null);
        }
    }
    public static void inverseNumber(String input)
    {
        char reverse;
        String rev_string = "";
        for (int x = 0 ; x < input.length() ; x++)
        {
            reverse = input.charAt(x);
            rev_string = reverse + rev_string;
        }
        Object[] options = {"Okay" , "Copy the reversed number.."};
        int choose = JOptionPane.showOptionDialog(null , "The number was reversed to " + rev_string + "." , "REV - Reverse everything..." , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE , null , options , options[1]);
        if (choose == 1)
        {
            StringSelection string = new StringSelection(rev_string);
            Clipboard copy = Toolkit.getDefaultToolkit().getSystemClipboard();
            copy.setContents(string , null);
        }
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new Init();
    }
}