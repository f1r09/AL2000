package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoviePanel extends JPanel {
    Image img;
    JButton btn;
    String title;

    MoviePanel(String title) {
        this.title = title;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        btn= new JButton();
        chargeImage();


        btn.addActionListener(e -> openMovieMenu());
        add(btn, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(titleLabel, BorderLayout.SOUTH);
        //setPreferredSize(new Dimension(120, 300));
    }

    void openMovieMenu(){
        CyberVideo.changeState(2);
    }
    void chargeImage(){
        try {
            img = new ImageIcon("./rsc/images/avatar.jpg").getImage();
            ImageIcon icon = new ImageIcon(img.getScaledInstance(160, 260, Image.SCALE_DEFAULT));
            btn.setIcon(icon);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public JButton getBtn() {
        return btn;
    }

    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
