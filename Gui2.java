import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigDecimal;

/**
 * Created by NIK on 26.09.15.
 */
public class Gui2 extends JFrame {


    private BigDecimal bigDecimal;
    private JPasswordField jPassField;
    private JButton button;

    private JTextField label1;
    private char[] pass;
    private String password;
    private FileWriter wr= null;


    public Gui2() throws IOException {

        // main frame
        setTitle("Byte Strength");
        setSize(450, 100);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // panel for button and fields
        label1 = new JTextField("");
        label1.setEditable(false);
        add(label1);

        //passField
        jPassField = new JPasswordField();

        add(jPassField, BorderLayout.NORTH);

        //button
        button = new JButton("CHECK");
        add(button, BorderLayout.CENTER);
        //answer
        add(label1, BorderLayout.SOUTH);
        setVisible(true);


        //output stream
        File file = new File("text2.txt");
        wr = new FileWriter(file);


        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //passField read
                password = jPassField.getText();
                //make passField and textField empty
                jPassField.setText("");
                label1.setText("");
                //passField go to Array
                //char[] pass1 = jPassField.getPassword();
                pass = password.toCharArray();

                double bitstyrke = (pass.length * (Math.log(95) / Math.log(2)));
                bigDecimal= BigDecimal.valueOf(Math.pow(2, bitstyrke));
                label1.setText("ByteStrength= " +  (int)bitstyrke + "  Variants: " +  bigDecimal);


                try {
                    for (int j = 0; j < pass.length; j++) {
                        wr.write(pass[j]);
                    }
                    wr.write("" + "\n"); // cr lf
                    wr.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    try {
                        wr.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }


            }
        });




    }



}




