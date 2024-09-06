import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ScientificCalculator {
    private JFrame frame;
    private JTextField textField;
    private String operator;
    private double num1, num2, result;

    public ScientificCalculator() {
        frame = new JFrame("Scientific Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 40, 340, 40);
        frame.add(textField);

        String[] buttonText = {
            "7", "8", "9", "/", "sqrt",
            "4", "5", "6", "*", "x^2",
            "1", "2", "3", "-", "1/x",
            "0", ".", "=", "+", "C"
        };

        JButton[] buttons = new JButton[20];

        int x = 30, y = 100;
        for (int i = 0; i < 20; i++) {
            buttons[i] = new JButton(buttonText[i]);
            buttons[i].setBounds(x, y, 60, 40);
            frame.add(buttons[i]);
            x += 70;
            if ((i + 1) % 5 == 0) {
                x = 30;
                y += 50;
            }
        }

        for (int i = 0; i < 15; i++) {
            buttons[i].addActionListener(new NumberActionListener());
        }

        buttons[15].addActionListener(new ActionListener() {  // 0 button
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + "0");
            }
        });

        buttons[16].addActionListener(new ActionListener() {  // decimal button
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + ".");
            }
        });

        buttons[17].addActionListener(new OperatorActionListener("="));  // equals
        buttons[18].addActionListener(new OperatorActionListener("+"));  // plus
        buttons[19].addActionListener(new ActionListener() {  // clear button
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        frame.setVisible(true);
    }

    class NumberActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String value = e.getActionCommand();
            textField.setText(textField.getText() + value);
        }
    }

    class OperatorActionListener implements ActionListener {
        private String op;

        public OperatorActionListener(String op) {
            this.op = op;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                if (!op.equals("=")) {
                    num1 = Double.parseDouble(textField.getText());
                    operator = op;
                    textField.setText("");
                } else {
                    num2 = Double.parseDouble(textField.getText());

                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0)
                                result = num1 / num2;
                            else
                                textField.setText("Cannot divide by 0");
                            return;
                        case "sqrt":
                            result = Math.sqrt(num1);
                            break;
                        case "x^2":
                            result = Math.pow(num1, 2);
                            break;
                        case "1/x":
                            if (num1 != 0)
                                result = 1 / num1;
                            else
                                textField.setText("Cannot divide by 0");
                            return;
                    }
                    textField.setText(String.valueOf(result));
                }
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}
