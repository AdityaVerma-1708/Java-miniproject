import javax.swing.*; //inbuilt class which has Jframe 
import javax.swing.border.EmptyBorder;
import java.awt.*; //inbuilt class which has all colors
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.net.*;

public class server implements ActionListener {

    JTextField text;// since we have to use text field to extract text hence we have to use it
                    // outside the constructor hence we defining it globally(line number 90)
    static JPanel p2; // for the same reason, we have to print the extracted text to the panel 2

    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;

    server() { // frame coding should be here only
        f.setLayout(null); // we are till now not using any inbuilt layout will design afterwards

        JPanel p1 = new JPanel(); // for top panel
        p1.setBackground(new Color(7, 94, 84)); // background color of panel
        p1.setBounds(0, 0, 500, 70); // layout of panel
        p1.setLayout(null);
        f.add(p1); // adding the panel into the interface

        // -----------------------------go back -----------------------------------------------
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        // -------------------------------------------dp-----------------------------------------------------------------
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/dp2.png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);

        // -------------------------------------------video---------------------------------------------------------------
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(350, 20, 30, 30);
        p1.add(video);

        // -------------------------------------------phone---------------------------------------------------------------
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(400, 20, 30, 30);
        p1.add(phone);

        // -------------------------------------------3dot-------------------------------------------------------------------
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3dot.png"));
        Image i14 = i13.getImage().getScaledInstance(20, 30, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel dot = new JLabel(i15);
        dot.setBounds(450, 20, 20, 30);
        p1.add(dot);
        // ----------------------------------------------------------------------------------------------------------------

        JLabel name = new JLabel("Aditya Verma");
        name.setBounds(110, 13, 200, 25);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        p1.add(name);

        JLabel status = new JLabel("Active now");
        status.setBounds(120, 37, 200, 20);
        status.setForeground(Color.gray);
        status.setFont(new Font("system-ui", Font.PLAIN, 15));
        p1.add(status);

        // --------------------------------------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------------------------------------

        p2 = new JPanel();
        p2.setBounds(1, 71, 498, 655);
        f.add(p2);

        text = new JTextField();    //where server will type the
        text.setBounds(5, 740, 380, 50);
        text.setFont(new Font("system-ui", Font.PLAIN, 20));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(390, 740, 100, 50);
        send.setBackground(new Color(53, 94, 59));//bg color of send button
        send.setFont(new Font("system-ui", Font.PLAIN, 20));
        send.setForeground(Color.WHITE);
        send.addActionListener(this); // for performing the action
        f.add(send);

        f.setSize(500, 800); // setting the size of frame
        f.setLocation(200, 10); // location of frame as x y in the screen
        f.setUndecorated(true);
        f.getContentPane().setBackground(new Color(7, 94, 84));
        f.setVisible(true); // this function should always be last since this is the visible function

    }


    public void actionPerformed(ActionEvent ae) {

        try {
            String serverout = text.getText();
            JPanel p3 = formatLabel(serverout); // calling the formatLabel function which returns JPanel

            p2.setLayout(new BorderLayout()); // border layout used to place the text on top right,top left,bottom both
                                              // and at center

            JPanel right = new JPanel(new BorderLayout()); // msg will align rightside from line no.127,128
            right.add(p3, BorderLayout.LINE_END);
            vertical.add(right); // if multiple msgs is there then they will align vertically
            vertical.add(Box.createVerticalStrut(10)); // 15 is gap b/w two vertical msgs

            p2.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(serverout);

            // since every time user type a msg that interface should update or reload for
            // that we can use these methods of Jframe class

            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JPanel formatLabel(String serverout) { // function for formating the text label its return type if
                                                         // Jpanel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(175, 225, 175)); // color of time panel

        JLabel out = new JLabel("<html><p style=\"width: 150px\">" + serverout + "</p></html>");
        out.setFont(new Font("Tohoma", Font.PLAIN, 20));
        out.setBackground(new Color(175, 225, 175)); // color of text output panel
        out.setOpaque(true);
        out.setBorder(new EmptyBorder(7, 7, 7, 150));

        panel.add(out);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }

    public static JPanel formatLabelout(String serverout) { // function for formating the text label its return type if
                                                         // Jpanel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(170, 225, 0)); // color of time panel

        JLabel out = new JLabel("<html><p style=\"width: 150px\">" + serverout + "</p></html>");
        out.setFont(new Font("Tohoma", Font.PLAIN, 20));
        out.setBackground(new Color(170, 225, 0)); // color of text output panel
        out.setOpaque(true);
        out.setBorder(new EmptyBorder(7, 7, 7, 150));

        panel.add(out);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new server();

        try {
            ServerSocket skt = new ServerSocket(1234); // created new server
            while (true) { // infinitly accepting the msgs basically accept all the msg coming from the
                           // client
                Socket s = skt.accept(); // storing all the data in socket s
                DataInputStream din = new DataInputStream(s.getInputStream()); // taking input from the client
                dout = new DataOutputStream(s.getOutputStream());

                while (true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabelout(msg);
                
                    JPanel left = new JPanel(new BorderLayout());
                    
                    // left.setBackground(new Color(12,12,12));
                    // panel.setBackground(new Color(12,12,12));     //neww*
                    
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);

                    vertical.add(Box.createVerticalStrut(10));

                    f.validate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
