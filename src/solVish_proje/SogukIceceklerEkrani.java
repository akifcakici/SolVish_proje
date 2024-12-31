package solVish_proje;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class SogukIceceklerEkrani extends JFrame {
    private SiparisYoneticisi siparisYoneticisi;
    private JLabel toplamLabel;
    private Color temaRengi = new Color(255, 222, 173); 
    private Color butonRengi = new Color(210, 180, 140); 
    private Font baslikFont = new Font("Segoe UI", Font.BOLD, 24);
    private Font urunFont = new Font("Segoe UI", Font.PLAIN, 14);
    private Font butonFont = new Font("Segoe UI", Font.BOLD, 14);

    public SogukIceceklerEkrani() {
        setTitle("Soğuk İçecekler Menüsü");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(temaRengi);
        
        siparisYoneticisi = SiparisYoneticisi.getInstance();
        ekranOlustur();
    }

    private void ekranOlustur() {
        JPanel anaPanel = new JPanel(new BorderLayout(15, 15));
        anaPanel.setBackground(temaRengi);
        anaPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel baslikPanel = new JPanel();
        baslikPanel.setLayout(new BoxLayout(baslikPanel, BoxLayout.Y_AXIS));
        baslikPanel.setBackground(temaRengi);
        
        JLabel baslikLabel = new JLabel("❄ Soğuk İçecekler Menüsü");
        baslikLabel.setFont(baslikFont);
        baslikLabel.setForeground(new Color(139, 69, 19));
        baslikLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(139, 69, 19));
        separator.setMaximumSize(new Dimension(500, 2));
        
        baslikPanel.add(baslikLabel);
        baslikPanel.add(Box.createVerticalStrut(10));
        baslikPanel.add(separator);
        baslikPanel.add(Box.createVerticalStrut(10));
        
        anaPanel.add(baslikPanel, BorderLayout.NORTH);

        JPanel urunlerPanel = new JPanel();
        urunlerPanel.setLayout(new BoxLayout(urunlerPanel, BoxLayout.Y_AXIS));
        urunlerPanel.setBackground(Color.WHITE);
        urunlerPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(butonRengi, 2, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        for (Urun urun : siparisYoneticisi.getSogukIcecekler()) {
            JPanel urunPanel = new JPanel();
            urunPanel.setLayout(new BoxLayout(urunPanel, BoxLayout.X_AXIS));
            urunPanel.setBackground(Color.WHITE);
            urunPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            urunPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            
            JLabel urunLabel = new JLabel(urun.toString());
            urunLabel.setFont(urunFont);
            
            JSpinner adetSpinner = new JSpinner(new SpinnerNumberModel(urun.getAdet(), 0, 10, 1));
            adetSpinner.setMaximumSize(new Dimension(70, 30));
            adetSpinner.setPreferredSize(new Dimension(70, 30));
            
            JComponent editor = adetSpinner.getEditor();
            if (editor instanceof JSpinner.DefaultEditor) {
                ((JSpinner.DefaultEditor)editor).getTextField().setHorizontalAlignment(JTextField.CENTER);
                ((JSpinner.DefaultEditor)editor).getTextField().setFont(urunFont);
            }
            
            adetSpinner.addChangeListener(e -> {
                urun.setAdet((int) adetSpinner.getValue());
                toplamGuncelle();
            });

            urunPanel.add(urunLabel);
            urunPanel.add(Box.createHorizontalGlue());
            urunPanel.add(adetSpinner);
            
            urunPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    urunPanel.setBackground(new Color(255, 248, 220));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    urunPanel.setBackground(Color.WHITE);
                }
            });

            urunlerPanel.add(urunPanel);
            urunlerPanel.add(Box.createVerticalStrut(5));
        }

        JScrollPane scrollPane = new JScrollPane(urunlerPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        anaPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel altPanel = new JPanel(new BorderLayout(10, 10));
        altPanel.setBackground(temaRengi);
        
        JPanel butonlarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        butonlarPanel.setBackground(temaRengi);

        JButton kahvaltiBtn = new JButton("🍳 Kahvaltılıklar");
        JButton sicakIceceklerBtn = new JButton("☕ Sıcak İçecekler");
        JButton siparisButton = new JButton("✓ Siparişi Tamamla");
        JButton geriButton = new JButton("← Ana Menü");

        for (JButton btn : new JButton[]{kahvaltiBtn, sicakIceceklerBtn, siparisButton, geriButton}) {
            stilVerButon(btn);
            butonlarPanel.add(btn);
        }

        kahvaltiBtn.addActionListener(e -> {
            new KahvaltiEkrani().setVisible(true);
            dispose();
        });
        
        sicakIceceklerBtn.addActionListener(e -> {
            new SicakIceceklerEkrani().setVisible(true);
            dispose();
        });
        
        siparisButton.addActionListener(e -> siparisiTamamla());
        
        geriButton.addActionListener(e -> {
            new AnaEkran().setVisible(true);
            dispose();
        });

        JPanel toplamPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        toplamPanel.setBackground(temaRengi);
        toplamLabel = new JLabel(String.format("Toplam: %.2f TL", siparisYoneticisi.getToplamTutar()));
        toplamLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        toplamLabel.setForeground(new Color(139, 69, 19));
        toplamPanel.add(toplamLabel);

        altPanel.add(butonlarPanel, BorderLayout.CENTER);
        altPanel.add(toplamPanel, BorderLayout.SOUTH);
        anaPanel.add(altPanel, BorderLayout.SOUTH);

        add(anaPanel);
    }

    private void stilVerButon(JButton buton) {
        buton.setFont(butonFont);
        buton.setBackground(butonRengi);
        buton.setForeground(Color.WHITE);
        buton.setFocusPainted(false);
        buton.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(butonRengi.darker(), 1, true),
            new EmptyBorder(8, 15, 8, 15)
        ));
        buton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        buton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buton.setBackground(butonRengi.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buton.setBackground(butonRengi);
            }
        });
    }

    private void toplamGuncelle() {
        toplamLabel.setText(String.format("Toplam: %.2f TL", siparisYoneticisi.getToplamTutar()));
    }

    private void siparisiTamamla() {
        if (siparisYoneticisi.getToplamTutar() > 0) {
            int secim = JOptionPane.showConfirmDialog(
                this,
                siparisYoneticisi.getSiparisOzeti(),
                "Siparişi Onayla",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
            );

            if (secim == JOptionPane.OK_OPTION) {
                JOptionPane.showMessageDialog(
                    this,
                    "Siparişiniz başarıyla alınmıştır.",
                    "Sipariş Tamamlandı",
                    JOptionPane.INFORMATION_MESSAGE
                );
                siparisYoneticisi.siparisiSifirla();
                getContentPane().removeAll();
                ekranOlustur();
                revalidate();
                repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Lütfen en az bir ürün seçiniz!", 
                "Uyarı", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
}