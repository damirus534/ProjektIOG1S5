package View;

import javax.swing.*;

public class UzytkownikWidok extends JFrame{
    public UzytkownikWidok(){
        initComponents();
    }

    private JPanel panel;
    private JButton button1;
    private JTextField loginTextField;

    private void initComponents() {//metoda do edycji

        button1 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(button1)
                                .addContainerGap(38, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }


}
