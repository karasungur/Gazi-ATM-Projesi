import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton back, deposit;
    String pin;

    public deposit(String pin) {
        this.pin = pin;
        setLayout(null);

        // Genel arka plan
        getContentPane().setBackground(new Color(244, 244, 244));

        // Resim paneli
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/atm.jpg"));
        Image i1 = img.getImage().getScaledInstance(900, 800, Image.SCALE_SMOOTH);
        JLabel image = new JLabel(new ImageIcon(i1));
        image.setBounds(0, 0, 900, 800);
        add(image);

        // Başlık
        JLabel heading = new JLabel("Yatırmak istediğiniz tutarı girin");
        heading.setBounds(220, 300, 400, 50);
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // Tutar alanı, biraz aşağı kaydırdım
        amount = new JTextField();
        amount.setFont(new Font("SansSerif", Font.BOLD, 18));
        amount.setBounds(200, 350, 280, 35);
        amount.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        image.add(amount);

        // YATIR butonu
        deposit = new JButton("YATIR");
        deposit.setBounds(400, 430, 100, 35);
        deposit.setBackground(new Color(0, 120, 215));
        deposit.setForeground(Color.WHITE);
        deposit.setFocusPainted(false);
        deposit.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
        deposit.addActionListener(this);
        image.add(deposit);

        // GERİ butonu
        back = new JButton("GERİ");
        back.setBounds(400, 480, 100, 35);
        back.setBackground(new Color(180, 180, 180));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));
        back.addActionListener(this);
        image.add(back);

        setSize(900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setTitle("Gazi ATM Para Yatırma");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new transaction(pin).setVisible(true);
        } else if (ae.getSource() == deposit) {
            String money = amount.getText().trim();
            Date date = new Date();
            if (money.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen yatırılacak tutarı girin.");
            } else {
                try {
                    mysql c = new mysql();
                    String query = "insert into bank values('"+pin+"', '"+date+"', 'Deposit', '"+money+"')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, money + " TL başarıyla yatırıldı.");
                    setVisible(false);
                    new transaction(pin).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new deposit("");
    }
}
