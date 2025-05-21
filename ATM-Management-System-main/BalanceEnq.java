import java.awt.*;
import java.sql.ResultSet;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class BalanceEnq extends JFrame implements ActionListener {

    String pin;
    JButton back;

    BalanceEnq(String pin) {
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

        // Bakiye hesaplama
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

        // “Mevcut hesap bakiyeniz” etiketi (beyaz, daha aşağıda)
        JLabel balance_money = new JLabel("Mevcut hesap bakiyeniz:");
        balance_money.setBounds(160, 320, 600, 30); // Y konumunu 320 yaptık
        balance_money.setFont(new Font("SansSerif", Font.BOLD, 18));
        balance_money.setForeground(Color.WHITE);
        image.add(balance_money);

        // Bakiye miktarı (biraz daha aşağı)
        JLabel moneyDisplay = new JLabel("TL " + balance);
        moneyDisplay.setBounds(160, 360, 600, 40); // Y konumunu 360 yaptık
        moneyDisplay.setFont(new Font("SansSerif", Font.BOLD, 24));
        moneyDisplay.setForeground(new Color(0, 120, 215));  // canlı mavi
        image.add(moneyDisplay);

        // Geri butonu
        back = new JButton("GERİ");
        back.setBounds(400, 460, 100, 35);
        back.setBackground(new Color(0, 120, 215));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
        back.addActionListener(this);
        image.add(back);

        setSize(900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setTitle("Gazi ATM Bakiyeniz");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new transaction(pin).setVisible(true);
    }

    public static void main(String args[]) {
        new BalanceEnq("");
    }
}
