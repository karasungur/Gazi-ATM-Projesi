import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;   
import java.util.*;
import java.text.*;

class MiniStatement extends JFrame {

    String pin;

    MiniStatement(String pin) {
        this.pin = pin;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String data = dateFormat.format(date);
        String str_date = data.substring(0, 10);
        String str_time = data.substring(11);

        setLayout(null);

        JLabel bankName = new JLabel("Gazi Bankası");
        bankName.setBounds(180, 20, 200, 25);
        bankName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        bankName.setForeground(new Color(156, 91, 194));
        add(bankName);

        JLabel d_date = new JLabel("TARİH");
        d_date.setBounds(100, 50, 100, 20);
        d_date.setFont(new Font("Times New Roman", Font.BOLD, 18));
        d_date.setForeground(new Color(156, 91, 194));
        add(d_date);

        JLabel d_time = new JLabel("SAAT");
        d_time.setBounds(300, 50, 100, 20);
        d_time.setFont(new Font("Times New Roman", Font.BOLD, 18));
        d_time.setForeground(new Color(156, 91, 194));
        add(d_time);

        JLabel dis_date = new JLabel(" " + str_date + " ");
        dis_date.setBounds(80, 80, 200, 20);
        dis_date.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(dis_date);

        JLabel dis_time = new JLabel("" + str_time);
        dis_time.setBounds(280, 80, 100, 20);
        dis_time.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(dis_time);

        JLabel cardNumDisp = new JLabel();
        cardNumDisp.setBounds(70, 120, 400, 30);
        cardNumDisp.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(cardNumDisp);

        JLabel types = new JLabel("TARİH               İŞLEM       TUTAR");
        types.setBounds(60, 220, 400, 30);
        types.setFont(new Font("Times New Roman", Font.BOLD, 16));
        types.setForeground(new Color(156, 91, 194));
        add(types);

        JLabel details = new JLabel();
        details.setBounds(50, 100, 400, 500);
        details.setFont(new Font("Times New Roman", Font.BOLD, 16));
        add(details);

        JLabel amount = new JLabel();
        amount.setBounds(50, 500, 300, 20);
        amount.setFont(new Font("Times New Roman", Font.BOLD, 20));
        amount.setForeground(new Color(156, 91, 194));
        add(amount);

        try {
            mysql c = new mysql();
            ResultSet rs = c.s.executeQuery("select cardNumber from login where PinNumber = '" + pin + "'");
            if (rs.next()) {
                String cn = rs.getString("cardNumber");
                cardNumDisp.setText("Kart No.: " + cn.substring(0, 4) + "XXXXXXXX" + cn.substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            int i = 0;
            mysql c = new mysql();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pin + "'");
            while (i < 5 && rs.next()) {
                if (i == 0) rs.last();
                else rs.previous();
                details.setText(details.getText()
                    + "<html>" + rs.getString("date")
                    + "&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type")
                    + "&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount")
                    + "<br><br><html>");
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            mysql c = new mysql();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pin + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            amount.setText("Bakiye: " + balance + " TL");
        } catch (Exception e) {
            System.out.println(e);
        }

        setSize(500, 600);
        setLocation(300, 50);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MiniStatement("");
    }
}