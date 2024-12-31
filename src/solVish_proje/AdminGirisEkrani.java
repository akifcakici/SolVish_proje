package solVish_proje;

import javax.swing.*;
import java.awt.*;

public class AdminGirisEkrani extends JFrame {
    private JTextField kullaniciAdiField;
    private JPasswordField sifreField;
    private static final String ADMIN_KULLANICI = "akif";
    private static final String ADMIN_SIFRE = "123";

    public AdminGirisEkrani() {
        setTitle("Admin Girişi");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(null);
        JLabel label = new JLabel("Kullanıcı Adı:");
        label.setBounds(98, 35, 83, 16);
        panel.add(label);
        kullaniciAdiField = new JTextField(15);
        kullaniciAdiField.setBounds(191, 30, 83, 26);
        panel.add(kullaniciAdiField);
        JLabel label_1 = new JLabel("Şifre:");
        label_1.setBounds(98, 71, 32, 16);
        panel.add(label_1);
        sifreField = new JPasswordField(15);
        sifreField.setBounds(191, 66, 83, 26);
        panel.add(sifreField);
        JButton girisBtn = new JButton("Giriş");
        girisBtn.setBounds(112, 112, 75, 29);
        panel.add(girisBtn);

        girisBtn.addActionListener(e -> girisKontrol());

        getContentPane().add(panel);
    }

    private void girisKontrol() {
        String kullaniciAdi = kullaniciAdiField.getText();
        String sifre = new String(sifreField.getPassword());

        if (kullaniciAdi.equals(ADMIN_KULLANICI) && sifre.equals(ADMIN_SIFRE)) {
            new UrunYonetimEkrani().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Hatalı kullanıcı adı veya şifre!",
                "Hata",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}