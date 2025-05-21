import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class SignUp3 extends JFrame implements ActionListener {

    private JButton submit, cancel;
    private JRadioButton savingAcc, currentAcc, fdAcc, reccAcc;
    private JCheckBox atmCard, internetBanking, mobileBanking, alerts, chequeBook, eStatement, declaration;
    private JPanel formPanel;
    private String formNo;

    public SignUp3(String formNo) {
        this.formNo = formNo;
        setLayout(null);
        getContentPane().setBackground(new Color(244, 244, 244));

        formPanel = new JPanel(null);
        formPanel.setBounds(100, 30, 620, 580);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        add(formPanel);

        JLabel title = new JLabel("Sayfa 3: Hesap ve Hizmet Seçimi");
        title.setBounds(160, 10, 400, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(50, 50, 50));
        formPanel.add(title);

        int y = 60, dy = 45;
        JLabel accLbl = new JLabel("Hesap Türü:");
        styleLabel(accLbl);
        accLbl.setBounds(60, y, 120, 25);
        formPanel.add(accLbl);

        savingAcc = createRadio("Vadesiz Hesap", 200, y);
        currentAcc = createRadio("Cari Hesap", 360, y);
        y += dy;
        fdAcc = createRadio("Vadeli Mevduat", 200, y);
        reccAcc = createRadio("Düzenli Vadeli", 360, y);
        ButtonGroup accGrp = new ButtonGroup();
        accGrp.add(savingAcc); accGrp.add(currentAcc); accGrp.add(fdAcc); accGrp.add(reccAcc);
        y += dy;

        addLabelValue("Kart Numarası:", "XXXX-XXXX-XXXX-4563", 60, y);
        y += dy;
        addLabelValue("PIN:", "XXXX", 60, y);
        y += dy;

        JLabel servLbl = new JLabel("Gerekli Hizmetler:");
        styleLabel(servLbl);
        servLbl.setBounds(60, y, 200, 25);
        formPanel.add(servLbl);
        y += dy;
        atmCard = createCheck("ATM Kartı", 60, y);
        internetBanking = createCheck("İnternet Bankacılığı", 260, y);
        y += dy;
        mobileBanking = createCheck("Mobil Bankacılık", 60, y);
        alerts = createCheck("E-Posta & SMS Uyarı", 260, y);
        y += dy;
        chequeBook = createCheck("Çek Defteri", 60, y);
        eStatement = createCheck("E-Hesap Özeti", 260, y);
        y += dy;

        declaration = new JCheckBox("Yukarıdaki bilgilerin doğru olduğunu beyan ederim");
        declaration.setBounds(60, y, 500, 25);
        declaration.setBackground(Color.WHITE);
        declaration.setForeground(new Color(80, 80, 80));
        declaration.setFont(new Font("SansSerif", Font.PLAIN, 14));
        formPanel.add(declaration);
        y += dy + 10;

        submit = new JButton("GÖNDER");
        stylePrimaryButton(submit);
        submit.setBounds(180, y, 120, 35);
        submit.addActionListener(this);
        formPanel.add(submit);

        cancel = new JButton("İPTAL");
        styleSecondaryButton(cancel);
        cancel.setBounds(340, y, 120, 35);
        cancel.addActionListener(e -> { setVisible(false); new LoginPage().setVisible(true); });
        formPanel.add(cancel);

        setSize(820, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gazi ATM Kayıt - Sayfa 3");
        setVisible(true);
    }

    private JRadioButton createRadio(String text, int x, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBounds(x, y, 140, 25);
        rb.setBackground(Color.WHITE);
        rb.setForeground(new Color(80, 80, 80));
        rb.setFont(new Font("SansSerif", Font.PLAIN, 14));
        formPanel.add(rb);
        return rb;
    }

    private JCheckBox createCheck(String text, int x, int y) {
        JCheckBox cb = new JCheckBox(text);
        cb.setBounds(x, y, 200, 25);
        cb.setBackground(Color.WHITE);
        cb.setForeground(new Color(80, 80, 80));
        cb.setFont(new Font("SansSerif", Font.PLAIN, 14));
        formPanel.add(cb);
        return cb;
    }

    private void addLabelValue(String label, String value, int x, int y) {
        JLabel lbl = new JLabel(label);
        styleLabel(lbl);
        lbl.setBounds(x, y, 140, 25);
        formPanel.add(lbl);
        JLabel val = new JLabel(value);
        val.setBounds(x + 160, y, 200, 25);
        val.setFont(new Font("SansSerif", Font.BOLD, 14));
        val.setForeground(new Color(0, 120, 215));
        formPanel.add(val);
    }

    private void styleLabel(JLabel lbl) {
        lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbl.setForeground(new Color(80, 80, 80));
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
    }

    private void styleSecondaryButton(JButton btn) {
        btn.setBackground(new Color(180, 180, 180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!declaration.isSelected()) {
            JOptionPane.showMessageDialog(this, "Lütfen beyanı işaretleyin.");
            return;
        }
        String accountType = savingAcc.isSelected()?"Vadesiz Hesap":currentAcc.isSelected()?"Cari Hesap":
                              fdAcc.isSelected()?"Vadeli Mevduat":reccAcc.isSelected()?"Düzenli Vadeli":"";
        String services="";
        if(atmCard.isSelected()) services+="ATM Kartı ";
        if(internetBanking.isSelected()) services+="İnternet Bankacılığı ";
        if(mobileBanking.isSelected()) services+="Mobil Bankacılık ";
        if(alerts.isSelected()) services+="E-Posta & SMS Uyarı ";
        if(chequeBook.isSelected()) services+="Çek Defteri ";
        if(eStatement.isSelected()) services+="E-Hesap Özeti ";

        Random ran=new Random();
        String cardNum=""+Math.abs((ran.nextLong()%9000000000000000L)+1000000000000000L);
        String pinNum=""+Math.abs((ran.nextLong()%9000L)+1000L);

        try {
            mysql c=new mysql();
            String q1 = "INSERT INTO signupthree (formno, account_type, card_number, pin_number, facilities) VALUES('"+
                        formNo+"','"+accountType+"','"+cardNum+"','"+pinNum+"','"+services.trim()+"')";
            String q2 = "INSERT INTO login (formno, cardNumber, PinNumber) VALUES('"+
                        formNo+"','"+cardNum+"','"+pinNum+"')";
            c.s.executeUpdate(q1);
            c.s.executeUpdate(q2);
            JOptionPane.showMessageDialog(null, "Kart Numaranız: " + cardNum + "\nPIN: " + pinNum);
            setVisible(false);
            new deposit(pinNum).setVisible(true);
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Hata: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignUp3("2912");
    }
}