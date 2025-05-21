import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.ResultSet;
import java.util.*;

public class transaction extends JFrame implements ActionListener {

    JButton deposit, fastCash, withdraw, statement, pinChange, balance, exit;
    String pin;

    public transaction(String pin) {
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

        // Başlık (beyaz), biraz daha aşağı çekildi (260)
        JLabel heading = new JLabel("Lütfen bir işlem seçiniz");
        heading.setBounds(220, 260, 400, 50);
        heading.setFont(new Font("SansSerif", Font.BOLD, 18));
        heading.setForeground(Color.WHITE);
        image.add(heading);

        // İşlem butonları (mavi vurgu), y'ler +10px
        deposit   = createButton("Para Yatır",     170, 320);
        withdraw  = createButton("Para Çek",       390, 320);
        fastCash  = createButton("Hızlı Nakit",    170, 370);
        statement = createButton("Mini Ekstre",    390, 370);
        pinChange = createButton("PIN Değiştir",   170, 420);
        balance   = createButton("Bakiye Sorgula", 390, 420);
        exit      = createButton("Çıkış",          390, 470);

        image.add(deposit);
        image.add(withdraw);
        image.add(fastCash);
        image.add(statement);
        image.add(pinChange);
        image.add(balance);
        image.add(exit);

        setSize(900, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setTitle("Gazi ATM İşlemler");
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 120, 25);
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new deposit(pin).setVisible(true);
        } else if (ae.getSource() == withdraw) {
            setVisible(false);
            new withdraw(pin).setVisible(true);
        } else if (ae.getSource() == fastCash) {
            setVisible(false);
            new FastCash(pin).setVisible(true);
        } else if (ae.getSource() == pinChange) {
            setVisible(false);
            new PinChange(pin).setVisible(true);
        } else if (ae.getSource() == balance) {
            setVisible(false);
            new BalanceEnq(pin).setVisible(true);
        } else if (ae.getSource() == statement) {
            new MiniStatement(pin).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new transaction("");
    }
}
