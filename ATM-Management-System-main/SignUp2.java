import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class SignUp2 extends JFrame implements ActionListener {

    private JComboBox<String> segmentBox, experienceBox, incomeBox, qualBox, occupationBox;
    private JTextField vknField, tcField;
    private JRadioButton seniorYes, seniorNo, existYes, existNo;
    private JButton next, back;
    private JPanel formPanel;
    private String formNo;

    public SignUp2(String formNo) {
        this.formNo = formNo;
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(244, 244, 244));

        formPanel = new JPanel(null);
        formPanel.setBounds(100, 20, 620, 580);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        getContentPane().add(formPanel);

        JLabel title = new JLabel("Sayfa 2: Ek Bilgiler");
        title.setBounds(200, 10, 300, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(50, 50, 50));
        formPanel.add(title);

        String[] segments = {"Bireysel", "KOBİ", "Kurumsal", "Yabancı Müşteri"};
        String[] experiences = {"Yeni Başlayan (0–1 yıl)", "Orta Deneyimli (1–5 yıl)", "Tecrübeli (5+ yıl)"};
        String[] incomes = {"Yok", "<150.000 TL", "<250.000 TL", "<500.000 TL", "≤1.000.000 TL"};
        String[] quals = {"Lisans Mezunu Değil", "Lisans Mezunu", "Yüksek Lisans", "Doktora", "Diğer"};
        String[] occs = {"Çalışan", "Serbest Meslek", "İşletme Sahibi", "Öğrenci", "Emekli", "Diğer"};

        int y = 60, dy = 50;
        addLabelAndCombo(formPanel, "Müşteri Segmenti:", segments,   60, y, segmentBox    = new JComboBox<>(segments)); y += dy;
        addLabelAndCombo(formPanel, "Deneyim Seviyesi:", experiences, 60, y, experienceBox = new JComboBox<>(experiences)); y += dy;
        addLabelAndCombo(formPanel, "Gelir:",             incomes,     60, y, incomeBox     = new JComboBox<>(incomes)); y += dy;
        addLabelAndCombo(formPanel, "Eğitim Durumu:",     quals,       60, y, qualBox       = new JComboBox<>(quals)); y += dy;
        addLabelAndCombo(formPanel, "Meslek:",            occs,        60, y, occupationBox = new JComboBox<>(occs)); y += dy;

        addLabelAndField(formPanel, "VKN No:",            60, y, vknField = new JTextField());     y += dy;
        addLabelAndField(formPanel, "T.C. No:",           60, y, tcField  = new JTextField());     y += dy;

        JLabel seniorLbl = new JLabel("Yaşlı Vatandaş:");
        styleLabel(seniorLbl);
        seniorLbl.setBounds(60, y, 140, 25);
        formPanel.add(seniorLbl);
        seniorYes = createRadio(formPanel, "Evet", 220, y);
        seniorNo  = createRadio(formPanel, "Hayır",300, y);
        ButtonGroup grpSen = new ButtonGroup(); grpSen.add(seniorYes); grpSen.add(seniorNo); y += dy;

        JLabel existLbl = new JLabel("Mevcut Hesap:");
        styleLabel(existLbl);
        existLbl.setBounds(60, y, 140, 25);
        formPanel.add(existLbl);
        existYes = createRadio(formPanel, "Evet", 220, y);
        existNo  = createRadio(formPanel, "Hayır",300, y);
        ButtonGroup grpExist = new ButtonGroup(); grpExist.add(existYes); grpExist.add(existNo); y += dy + 10;

        next = new JButton("İLERİ");
        stylePrimaryButton(next);
        next.setBounds(200, y, 120, 35);
        next.addActionListener(this);
        formPanel.add(next);

        back = new JButton("GERİ");
        styleSecondaryButton(back);
        back.setBounds(350, y, 120, 35);
        back.addActionListener(e -> { setVisible(false); new SignUp1().setVisible(true); });
        formPanel.add(back);

        setSize(820, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gazi ATM Kayıt - Sayfa 2");
        setVisible(true);
    }

    private void addLabelAndCombo(JPanel p, String text, String[] vals, int x, int y, JComboBox<String> cb) {
        JLabel lbl = new JLabel(text);
        styleLabel(lbl);
        lbl.setBounds(x, y, 140, 25);
        p.add(lbl);
        cb.setBounds(x + 140, y, 300, 30);
        cb.setBackground(Color.WHITE);
        p.add(cb);
    }

    private void addLabelAndField(JPanel p, String text, int x, int y, JTextField f) {
        JLabel lbl = new JLabel(text);
        styleLabel(lbl);
        lbl.setBounds(x, y, 140, 25);
        p.add(lbl);
        f.setBounds(x + 140, y, 300, 30);
        f.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        p.add(f);
    }

    private void styleLabel(JLabel l) {
        l.setFont(new Font("SansSerif", Font.PLAIN, 16));
        l.setForeground(new Color(80, 80, 80));
    }

    private JRadioButton createRadio(JPanel p, String text, int x, int y) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBounds(x, y, 100, 25);
        rb.setBackground(Color.WHITE);
        rb.setForeground(new Color(80, 80, 80));
        p.add(rb);
        return rb;
    }

    private void stylePrimaryButton(JButton b) {
        b.setBackground(new Color(0, 120, 215)); b.setForeground(Color.WHITE); b.setFocusPainted(false);
        b.setBorder(new LineBorder(new Color(0, 120, 215), 2, true));
    }

    private void styleSecondaryButton(JButton b) {
        b.setBackground(new Color(180, 180, 180)); b.setForeground(Color.WHITE); b.setFocusPainted(false);
        b.setBorder(new LineBorder(new Color(180, 180, 180), 2, true));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String segment     = (String) segmentBox.getSelectedItem();
        String experience  = (String) experienceBox.getSelectedItem();
        String income      = (String) incomeBox.getSelectedItem();
        String qualification = (String) qualBox.getSelectedItem();
        String occupation   = (String) occupationBox.getSelectedItem();
        String vkn         = vknField.getText().trim();
        String tc          = tcField.getText().trim();
        String senior      = seniorYes.isSelected() ? "Evet" : "Hayır";
        String existing    = existYes.isSelected() ? "Evet" : "Hayır";

        try {
            mysql c = new mysql();
            String q = "INSERT INTO signuptwo (formno, segment, experience, income, qualification, occupation, vkn_number, tc_number, senior_citizen, existing_account) VALUES('" +
                       formNo + "','" + segment + "','" + experience + "','" + income + "','" + qualification + "','" + occupation + "','" +
                       vkn + "','" + tc + "','" + senior + "','" + existing + "')";
            c.s.executeUpdate(q);
            setVisible(false);
            new SignUp3(formNo).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hata: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUp2("2912");
    }
}
