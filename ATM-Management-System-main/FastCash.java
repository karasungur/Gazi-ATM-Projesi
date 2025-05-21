import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class FastCash extends JFrame implements ActionListener {

    JButton tl100, tl500, tl1000, tl2000, tl5000, tl10000, back;
    String pin;

    public FastCash(String pin) {
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

        // Başlık (beyaz, biraz sola kaydırıldı)
        JLabel heading = new JLabel("HIZLI NAKİT ÇEKİM TUTARINI SEÇİN");
        heading.setBounds(160, 300, 500, 50);  // X 190→160
        heading.setFont(new Font("SansSerif", Font.BOLD, 20));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // Hızlı tutar butonları (mavi vurgu), sola kaydırıldı, hafif yukarı alındı
        tl100   = createAmountButton("100 TL",   160, 360);  // (180,370)→(160,360)
        tl500   = createAmountButton("500 TL",   380, 360);  // (400,370)→(380,360)
        tl1000  = createAmountButton("1000 TL",  160, 400);  // (180,400)→(160,400)
        tl2000  = createAmountButton("2000 TL",  380, 400);  // (400,400)→(380,400)
        tl5000  = createAmountButton("5000 TL",  160, 440);  // (180,430)→(160,440)
        tl10000 = createAmountButton("10000 TL", 380, 440);  // (400,430)→(380,440)

        image.add(tl100);
        image.add(tl500);
        image.add(tl1000);
        image.add(tl2000);
        image.add(tl5000);
        image.add(tl10000);

        // GERİ butonu, yukarı alındı
        back = new JButton("GERİ");
        back.setBounds(400, 480, 100, 35);  // Y 500→480
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
        setTitle("Gazi ATM Hızlı Nakit Çekim");
        setVisible(true);
    }

    // Buton oluşturma metodu
    private JButton createAmountButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 100, 35);
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new transaction(pin).setVisible(true);
        } else {
            String money = ((JButton) ae.getSource()).getText().split(" ")[0];
            Date date = new Date();
            mysql c = new mysql();
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pin + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (Integer.parseInt(money) > balance) {
                    JOptionPane.showMessageDialog(null, "Yetersiz bakiye");
                    setVisible(false);
                    new transaction(pin).setVisible(true);
                    return;
                }
                String query = "insert into bank values('"+pin+"', '"+date+"', 'Withdraw', '"+money+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, money + " TL başarıyla çekildi.");
                setVisible(false);
                new transaction(pin).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
