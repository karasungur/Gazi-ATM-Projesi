import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

class LoginPage extends JFrame implements ActionListener {

    JButton signin, signup, clear;
    JTextField cardfield;
    JPasswordField pinfield;
    JPanel formPanel;

    LoginPage() {
        setLayout(null);
        getContentPane().setBackground(new Color(244, 244, 244));

        formPanel = new JPanel(null);
        formPanel.setBounds(140, 100, 520, 360);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        add(formPanel);

        try {
            ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/bank1.png"));
            Image scaled = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(scaled));
            logo.setBounds(220, 0, 80, 80);
            formPanel.add(logo);
        } catch (Exception e) {}

        JLabel title = new JLabel("Gazi ATM'ye Hoşgeldin");
        title.setBounds(110, 90, 300, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(50, 50, 50));
        formPanel.add(title);

        JLabel card = new JLabel("Kart No:");
        card.setBounds(50, 150, 100, 25);
        card.setFont(new Font("SansSerif", Font.PLAIN, 16));
        card.setForeground(new Color(80, 80, 80));
        formPanel.add(card);

        cardfield = new JTextField();
        cardfield.setBounds(160, 150, 280, 30);
        cardfield.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        formPanel.add(cardfield);

        JLabel pin = new JLabel("PIN:");
        pin.setBounds(50, 200, 100, 25);
        pin.setFont(new Font("SansSerif", Font.PLAIN, 16));
        pin.setForeground(new Color(80, 80, 80));
        formPanel.add(pin);

        pinfield = new JPasswordField();
        pinfield.setBounds(160, 200, 280, 30);
        pinfield.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        formPanel.add(pinfield);

        signin = new JButton("GİRİŞ");
        signin.setBounds(80, 260, 120, 35);
        signin.setBackground(new Color(0, 120, 215));
        signin.setForeground(Color.WHITE);
        signin.setFocusPainted(false);
        signin.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
        signin.addActionListener(this);
        formPanel.add(signin);

        clear = new JButton("SIFIRLA");
        clear.setBounds(200, 260, 120, 35);
        clear.setBackground(new Color(180, 180, 180));
        clear.setForeground(Color.WHITE);
        clear.setFocusPainted(false);
        clear.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));
        clear.addActionListener(this);
        formPanel.add(clear);

        signup = new JButton("KAYIT OL");
        signup.setBounds(320, 260, 120, 35);
        signup.setBackground(new Color(0, 150, 136));
        signup.setForeground(Color.WHITE);
        signup.setFocusPainted(false);
        signup.setBorder(new LineBorder(new Color(0, 150, 136), 2, true));
        signup.addActionListener(this);
        formPanel.add(signup);

        setSize(800, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gazi ATM Sistemi");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signin) {
            mysql c = new mysql();
            String cardnumber = cardfield.getText();
            String pinnumber = String.valueOf(pinfield.getPassword());
            try {
                ResultSet res = c.s.executeQuery(
                    "select * from login where cardNumber='" + cardnumber +
                    "' and PinNumber='" + pinnumber + "'"
                );
                if (res.next()) {
                    setVisible(false);
                    new transaction(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Geçersiz bilgiler");
                    cardfield.setText("");
                    pinfield.setText("");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == clear) {
            cardfield.setText("");
            pinfield.setText("");
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignUp1().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new LoginPage();
    }
}
