import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kalkulator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator = "";
    private double num1 = 0, num2 = 0;

    public Kalkulator() {
        // Set frame
        setTitle("Kalkulator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.LEFT); // <-- tampil di kiri
        add(display, BorderLayout.NORTH);

        // Panel tombol
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] tombol = {
            "7","8","9","+",
            "4","5","6","-",
            "1","2","3","x",
            "0","%","=","C"
        };

        for (String t : tombol) {
            JButton btn = new JButton(t);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) { // tombol angka
            if (display.getText().equals("0")) {
                display.setText(input); // ganti 0 awal
            } else {
                display.setText(display.getText() + input); // tambah angka
            }
        } else if (input.equals("C")) { // clear
            display.setText("0");
            num1 = num2 = 0;
            operator = "";
        } else if (input.equals("=")) { // hitung
            try {
                num2 = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "x": result = num1 * num2; break;
                    case "%": result = num1 % num2; break;
                }
                // tampilkan tanpa .0 jika bulat
                if (result == (long) result) {
                    display.setText(String.valueOf((long) result));
                } else {
                    display.setText(String.valueOf(result));
                }
                operator = "";
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else { // operator
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = input;
                display.setText("0"); // reset untuk input berikutnya
            }
        }
    }

    public static void main(String[] args) {
        new Kalkulator();
    }
}
