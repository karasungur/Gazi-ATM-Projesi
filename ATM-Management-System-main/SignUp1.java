import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class SignUp1 extends JFrame implements ActionListener {

    private JTextField nameField, fnameField, emailField, addressField, cityField, stateField, pincodeField;
    private JButton next, back;
    private JRadioButton maleBtn, femaleBtn, otherGenderBtn, marriedBtn, singleBtn, otherMaritalBtn;
    private JDateChooser dobField;
    private JPanel formPanel;
    private String formNo = "2912";

    public SignUp1() {
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(244, 244, 244));

        formPanel = new JPanel(null);
        formPanel.setBounds(100, 30, 620, 592);
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        getContentPane().add(formPanel);

        JLabel heading = new JLabel("Başvuru Formu No. " + formNo);
        heading.setBounds(180, 10, 400, 30);
        heading.setFont(new Font("SansSerif", Font.BOLD, 24));
        heading.setForeground(new Color(50, 50, 50));
        formPanel.add(heading);

        JLabel pageNo = new JLabel("Sayfa 1: Kişisel Bilgiler");
        pageNo.setBounds(200, 45, 300, 25);
        pageNo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        pageNo.setForeground(new Color(80, 80, 80));
        formPanel.add(pageNo);

        int y = 90, dy = 45;
        addLabelAndField(formPanel, "Adı:", 60, y, nameField = new JTextField()); y += dy;
        addLabelAndField(formPanel, "Baba Adı:", 60, y, fnameField = new JTextField()); y += dy;

        JLabel dobLbl = new JLabel("Doğum Tarihi:");
        styleLabel(dobLbl);
        dobLbl.setBounds(60, y, 120, 25);
        formPanel.add(dobLbl);
        dobField = new JDateChooser(new Date(), "dd/MM/yyyy");
        dobField.setBounds(200, y, 300, 30);
        dobField.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        formPanel.add(dobField);
        y += dy;

        JLabel genderLbl = new JLabel("Cinsiyet:");
        styleLabel(genderLbl);
        genderLbl.setBounds(60, y, 100, 25);
        formPanel.add(genderLbl);
        maleBtn = createRadio("Erkek", 200, y, formPanel);
        femaleBtn = createRadio("Kadın", 300, y, formPanel);
        otherGenderBtn = createRadio("Diğer", 400, y, formPanel);
        ButtonGroup genderGrp = new ButtonGroup();
        genderGrp.add(maleBtn);
        genderGrp.add(femaleBtn);
        genderGrp.add(otherGenderBtn);
        y += dy;

        addLabelAndField(formPanel, "E-posta:", 60, y, emailField = new JTextField()); y += dy;

        JLabel maritalLbl = new JLabel("Medeni Durum:");
        styleLabel(maritalLbl);
        maritalLbl.setBounds(60, y, 120, 25);
        formPanel.add(maritalLbl);
        marriedBtn = createRadio("Evli", 200, y, formPanel);
        singleBtn = createRadio("Bekar", 300, y, formPanel);
        otherMaritalBtn = createRadio("Diğer", 400, y, formPanel);
        ButtonGroup maritalGrp = new ButtonGroup();
        maritalGrp.add(marriedBtn);
        maritalGrp.add(singleBtn);
        maritalGrp.add(otherMaritalBtn);
        y += dy;

        addLabelAndField(formPanel, "Adres:", 60, y, addressField = new JTextField()); y += dy;
        addLabelAndField(formPanel, "İl:", 60, y, cityField = new JTextField()); y += dy;
        addLabelAndField(formPanel, "İlçe:", 60, y, stateField = new JTextField()); y += dy;
        addLabelAndField(formPanel, "Posta Kodu:", 60, y, pincodeField = new JTextField()); y += dy + 10;

        next = new JButton("İLERİ");
        stylePrimaryButton(next);
        next.setBounds(200, y, 120, 35);
        next.addActionListener(this);
        formPanel.add(next);

        back = new JButton("GERİ");
        styleSecondaryButton(back);
        back.setBounds(350, y, 120, 35);
        back.addActionListener(e -> {
            setVisible(false);
            new LoginPage().setVisible(true);
        });
        formPanel.add(back);

        setSize(820, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gazi ATM Kayıt");
        setVisible(true);
    }

    private void addLabelAndField(JPanel panel, String text, int x, int y, JTextField field) {
        JLabel lbl = new JLabel(text);
        styleLabel(lbl);
        lbl.setBounds(x, y, 120, 25);
        panel.add(lbl);
        field.setBounds(x + 140, y, 300, 30);
        field.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 2, true));
        panel.add(field);
    }

    private void styleLabel(JLabel lbl) {
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(new Color(80, 80, 80));
    }

    private JRadioButton createRadio(String text, int x, int y, JPanel panel) {
        JRadioButton rb = new JRadioButton(text);
        rb.setBounds(x, y, 100, 25);
        rb.setBackground(Color.WHITE);
        rb.setForeground(new Color(80, 80, 80));
        panel.add(rb);
        return rb;
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
    public void actionPerformed(ActionEvent ae) {
        String name    = nameField.getText().trim();
        String fname   = fnameField.getText().trim();
        String dob     = ((JTextField) dobField.getDateEditor().getUiComponent()).getText().trim();
        String gender  = maleBtn.isSelected() ? "Erkek" : femaleBtn.isSelected() ? "Kadın" : "Diğer";
        String email   = emailField.getText().trim();
        String marital = marriedBtn.isSelected() ? "Evli" : singleBtn.isSelected() ? "Bekar" : "Diğer";
        String address = addressField.getText().trim();
        String city    = cityField.getText().trim();
        String state   = stateField.getText().trim();
        String pincode = pincodeField.getText().trim();

        if (name.isEmpty() || fname.isEmpty() || dob.isEmpty() || email.isEmpty()
            || address.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun.");
            return;
        }

        try {
            mysql c = new mysql();
            String query = "INSERT INTO signup "
                         + "(formno, name, father_name, dob, gender, email, marital, address, city, state, pincode) "
                         + "VALUES('"
                         + formNo + "','" + name + "','" + fname + "','" + dob + "','" 
                         + gender + "','" + email + "','" + marital + "','" + address + "','" 
                         + city + "','" + state + "','" + pincode + "')";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "Kayıt başarılı.");
            setVisible(false);
            new SignUp2(formNo).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Kayıt sırasında hata: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignUp1();
    }
}
