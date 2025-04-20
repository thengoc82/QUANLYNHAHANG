package controler;

import gui.frmDangNhap;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        JWindow manHinhCho = new JWindow();
        JPanel content = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Loading Application...", SwingConstants.CENTER);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        manHinhCho.setAlwaysOnTop(true);
        content.add(label, BorderLayout.CENTER);
        content.add(progressBar, BorderLayout.SOUTH);

        manHinhCho.add(content);
        manHinhCho.setSize(300, 150);
        manHinhCho.setLocationRelativeTo(null);
        manHinhCho.setVisible(true);
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(10);
                progressBar.setValue(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        manHinhCho.dispose();
        frmDangNhap dangNhap = new frmDangNhap();
        dangNhap.setVisible(true);
        dangNhap.setLocationRelativeTo(null);
//        dangNhap.setAlwaysOnTop(true);
    }
}
