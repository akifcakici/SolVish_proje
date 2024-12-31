package solVish_proje;

import javax.swing.*;
import java.awt.*;

public class AnaEkran extends JFrame {
    private JButton kahvaltiBtn;
    private JButton sicakIceceklerBtn;
    private JButton sogukIceceklerBtn;
    private JButton urunYonetimBtn;

    public AnaEkran() {
        setTitle("Kafe Otomasyonu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        bilesenleriolustur();
    }

    private void bilesenleriolustur() {
        JPanel anaPanel = new JPanel();
        anaPanel.setLayout(new GridLayout(4, 1, 10, 10));
        anaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        anaPanel.setBackground(new Color(229, 157, 76));

        kahvaltiBtn = new JButton("Kahvaltılıklar");
        sicakIceceklerBtn = new JButton("Sıcak İçecekler");
        sogukIceceklerBtn = new JButton("Soğuk İçecekler");
        urunYonetimBtn = new JButton("Ürün Yönetimi");

        for (JButton btn : new JButton[]{kahvaltiBtn, sicakIceceklerBtn, sogukIceceklerBtn, urunYonetimBtn}) {
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setFocusPainted(false);
        }

        kahvaltiBtn.addActionListener(e -> new KahvaltiEkrani().setVisible(true));
        sicakIceceklerBtn.addActionListener(e -> new SicakIceceklerEkrani().setVisible(true));
        sogukIceceklerBtn.addActionListener(e -> new SogukIceceklerEkrani().setVisible(true));
        urunYonetimBtn.addActionListener(e -> new AdminGirisEkrani().setVisible(true));

        anaPanel.add(kahvaltiBtn);
        anaPanel.add(sicakIceceklerBtn);
        anaPanel.add(sogukIceceklerBtn);
        anaPanel.add(urunYonetimBtn);

        getContentPane().add(anaPanel);
    }
}