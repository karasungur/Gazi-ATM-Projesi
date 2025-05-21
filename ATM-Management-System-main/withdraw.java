import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class withdraw extends JFrame implements ActionListener {

    JTextField amount;
    JButton back, withdraw;
    String pin;

    public withdraw(String pin) {
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

        // Başlık (beyaz, biraz daha aşağı çekildi)
        JLabel heading = new JLabel("Çekmek istediğiniz tutarı girin");
        heading.setBounds(220, 300, 400, 50);
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // Tutar alanı (mavi kenarlık)
        amount = new JTextField();
        amount.setFont(new Font("SansSerif", Font.BOLD, 18));
        amount.setBounds(200, 360, 280, 35);
        amount.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        image.add(amount);

        // ÇEK butonu (mavi vurgu)
        withdraw = new JButton("ÇEK");
        withdraw.setBounds(400, 430, 100, 35);
        withdraw.setBackground(new Color(0, 120, 215));
        withdraw.setForeground(Color.WHITE);
        withdraw.setFocusPainted(false);
        withdraw.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
        withdraw.addActionListener(this);
        image.add(withdraw);

        // GERİ butonu (gri vurgu)
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
        setTitle("Gazi ATM Para Çekme");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new transaction(pin).setVisible(true);
        } else if (ae.getSource() == withdraw) {
            int balance = 0;
            mysql c = new mysql();
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pin + "'");
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            String money = amount.getText().trim();
            Date date = new Date();
            if (money.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen çekilecek tutarı girin.");
            } else if (Integer.parseInt(money) > balance) {
                JOptionPane.showMessageDialog(null, "Yetersiz bakiye, bakiyenizi kontrol edin.");
                amount.setText("");
            } else {
                try {
                    String query = "insert into bank values('" + pin + "', '" + date + "', 'Withdraw', '" + money + "')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, money + " TL başarıyla çekildi.");
                    setVisible(false);
                    new transaction(pin).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        new withdraw("");
    }

}
