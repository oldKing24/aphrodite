package com.oldking.aphrodite.web;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author wangzhiyong
 */
@SuppressWarnings("serial")
public class IndexSwing extends JFrame implements ActionListener {
    JPanel contentPane;
    JLabel name_lab = new JLabel();
    JLabel age_lab = new JLabel();
    JTextField name_jtfel = new JTextField();
    JTextField age_jtfel = new JTextField();
    JButton login = new JButton();
    JButton register = new JButton();

    public IndexSwing() {
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(null);
        name_lab.setText("姓名：");
        name_lab.setBounds(new Rectangle(27, 68, 42, 15));
        age_lab.setText("年龄");
        age_lab.setBounds(new Rectangle(27, 113, 42, 15));
        name_jtfel.setBounds(new Rectangle(99, 67, 77, 20));
        age_jtfel.setBounds(new Rectangle(99, 109, 77, 20));
        login.setText("登录");
        register.setText("注册");
        login.setBounds(new Rectangle(30, 200, 60, 15));
        register.setBounds(new Rectangle(110, 200, 60, 15));
        contentPane.add(name_lab);
        contentPane.add(age_lab);
        contentPane.add(name_jtfel);
        contentPane.add(age_jtfel);
        contentPane.add(login);
        contentPane.add(register);
        this.setTitle("用户登录界面");
        this.setSize(new Dimension(400, 300));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//设置可见
    }

    public static void main(String[] args) {
        IndexSwing indexSwing = new IndexSwing();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}
