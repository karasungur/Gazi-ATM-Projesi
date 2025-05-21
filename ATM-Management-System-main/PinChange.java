import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField newPin, rePin;
    JButton change, back;
    String pin;

    public PinChange(String pin) {
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

        // Başlık (beyaz)
        JLabel heading = new JLabel("PIN DEĞİŞTİRME");
        heading.setBounds(220, 260, 400, 50);   // biraz daha aşağı
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // "Yeni PIN:" etiketi (beyaz, başlıkla aynı renk)
        JLabel newpinLbl = new JLabel("Yeni PIN:");
        newpinLbl.setBounds(180, 340, 150, 25);  // aşağı kaydırıldı
        newpinLbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        newpinLbl.setForeground(Color.WHITE);
        image.add(newpinLbl);

        // Yeni PIN alanı (mavi kenarlık)
        newPin = new JPasswordField();
        newPin.setFont(new Font("SansSerif", Font.BOLD, 18));
        newPin.setBounds(350, 340, 150, 25);
        newPin.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        image.add(newPin);

        // "Yeni PIN Tekrar:" etiketi (beyaz)
        JLabel pinconfirmLbl = new JLabel("Yeni PIN Tekrar:");
        pinconfirmLbl.setBounds(180, 390, 170, 25);  // aşağı kaydırıldı
        pinconfirmLbl.setFont(new Font("SansSerif", Font.BOLD, 18));
        pinconfirmLbl.setForeground(Color.WHITE);
        image.add(pinconfirmLbl);

        // Yeni PIN tekrar alanı (mavi kenarlık)
        rePin = new JPasswordField();
        rePin.setFont(new Font("SansSerif", Font.BOLD, 18));
        rePin.setBounds(350, 390, 150, 25);
        rePin.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        image.add(rePin);

        // DEĞİŞTİR butonu (mavi vurgu), biraz aşağı
        change = new JButton("DEĞİŞTİR");
        change.setBounds(400, 470, 120, 35);
        change.setBackground(new Color(0, 120, 215));
        change.setForeground(Color.WHITE);
        change.setFocusPainted(false);
        change.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
        change.addActionListener(this);
        image.add(change);

        // GERİ butonu (gri vurgu), biraz aşağı
        back = new JButton("GERİ");
        back.setBounds(250, 470, 100, 35);
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
        setTitle("Gazi ATM PIN Değiştirme");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new transaction(pin).setVisible(true);
        } else {
            try {
                String num1 = String.valueOf(newPin.getPassword());
                String num2 = String.valueOf(rePin.getPassword());

                if (num1.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen yeni PIN girin");
                    return;
                }
                if (num2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen PIN'i tekrar girin");
                    return;
                }
                if (!num1.equals(num2)) {
                    JOptionPane.showMessageDialog(null, "PIN eşleşmiyor");
                    newPin.setText("");
                    rePin.setText("");
                    return;
                }

                mysql c = new mysql();
                String q1 = "update bank set pin='"+num1+"' where pin='"+pin+"'";
                String q2 = "update login set PinNumber='"+num1+"' where PinNumber='"+pin+"'";
                String q3 = "update signupthree set pin_number='"+num1+"' where pin_number='"+pin+"'";
                c.s.executeUpdate(q1);
                c.s.executeUpdate(q2);
                c.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN başarıyla değiştirildi");
                JOptionPane.showMessageDialog(null, "Yeni PIN ile tekrar giriş yapın");
                setVisible(false);
                new LoginPage().setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new PinChange("");
    }
}
