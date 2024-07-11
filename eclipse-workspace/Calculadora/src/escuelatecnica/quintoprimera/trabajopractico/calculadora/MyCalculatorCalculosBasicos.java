package escuelatecnica.quintoprimera.trabajopractico.calculadora;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyCalculatorCalculosBasicos extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1653246675001722787L;
	private JPanel contentPane;
    private JTextField txtPantalla;

    // Variables
    List<Double> numeros;
    List<String> operaciones;
    boolean errorState; 
    List<JButton> buttons;
    
    public MyCalculatorCalculosBasicos() {
        numeros = new ArrayList<>();
        operaciones = new ArrayList<>();
        errorState = false;
        buttons = new ArrayList<>();

        setTitle("CalculosBasicos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 341, 380); // Aumentar el tamaño para acomodar nuevos botones
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        

        txtPantalla = new JTextField();
        txtPantalla.setEditable(false);  // Desactivar la edición manual
        txtPantalla.setHorizontalAlignment(SwingConstants.RIGHT);
        txtPantalla.setFont(new Font("Arial", Font.BOLD, 18));
        txtPantalla.setBounds(10, 11, 300, 47);
        contentPane.add(txtPantalla);
        txtPantalla.setColumns(10);

        ActionListener numberListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!errorState) {
                    String IngreseNumero = txtPantalla.getText() + ((JButton) e.getSource()).getText();
                    txtPantalla.setText(IngreseNumero);
                }
            }
        };
        
        JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Arial", Font.BOLD, 15));
		btnBack.setBounds(134, 294, 175, 47);
		getContentPane().add(btnBack);
        
        String[] buttonLabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        int[] buttonBounds = {
            10, 69, 52, 47,   72, 69, 52, 47,   134, 69, 52, 47,
            10, 122, 52, 47,  72, 122, 52, 47,  134, 122, 52, 47,
            10, 178, 52, 47,  72, 178, 52, 47,  134, 178, 52, 47,
            10, 236, 52, 47
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.addActionListener(numberListener);
            button.setFont(new Font("Arial", Font.BOLD, 15));
            button.setBounds(buttonBounds[i * 4], buttonBounds[i * 4 + 1], buttonBounds[i * 4 + 2], buttonBounds[i * 4 + 3]);
            contentPane.add(button);
            buttons.add(button); // Añadir el botón a la lista
        }

        // Botón de coma decimal
        addButton(".", 72, 236, 52, 47, new Color(0, 0, 0), e -> addDecimal());

        // Botones de operaciones
        addButton("C", 196, 69, 114, 47, new Color(255, 0, 0), e -> clearScreen());
        addButton("+", 196, 122, 52, 47, new Color(255, 0, 0), e -> setOperation("+"));
        addButton("-", 258, 122, 52, 47, new Color(255, 0, 0), e -> setOperation("-"));
        addButton("\u00D7", 196, 178, 52, 47, new Color(255, 0, 0), e -> setOperation("*"));
        addButton("\u00F7", 258, 178, 52, 47, new Color(255, 0, 0), e -> setOperation("/"));
        addButton("^", 196, 236, 52, 47, new Color(255, 0, 0), e -> setOperation("^"));
        addButton("\u221A", 258, 236, 52, 47, new Color(255, 0, 0), e -> calculateSquareRoot());

        // Botón igual
        addButton("=", 134, 236, 52, 47, new Color(255, 0, 0), e -> calculateResult());

        // Botones de paréntesis
        addButton("(", 10, 294, 52, 47, new Color(0, 0, 0), e -> addParenthesis("("));
        addButton(")", 72, 294, 52, 47, new Color(0, 0, 0), e -> addParenthesis(")"));
    }

    private void addButton(String text, int x, int y, int width, int height, Color color, ActionListener listener) {
        JButton button = new JButton(text);
        button.setForeground(color);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBounds(x, y, width, height);
        button.addActionListener(listener);
        contentPane.add(button);
        if (!text.equals("C")) { // Añadir a la lista si no es el botón "C"
            buttons.add(button);
        }
    }

    private void setOperation(String op) {
        if (!errorState) {
            String currentText = txtPantalla.getText().trim();
            if (!currentText.isEmpty() && !currentText.endsWith(" ") && !isOperator(currentText.charAt(currentText.length() - 1))) {
                txtPantalla.setText(currentText + " " + op + " ");
            }
        }
    }

    private void calculateResult() {
        if (!errorState) {
            try {
                String input = txtPantalla.getText();
                double result = evaluateExpression(input);
                txtPantalla.setText(String.valueOf(result));
            } catch (ArithmeticException e) {
                txtPantalla.setText("Error: Divide por otro numero");
                errorState = true;
                setButtonsEnabled(false);
            } catch (Exception e) {
                txtPantalla.setText("Error");
                errorState = true;
                setButtonsEnabled(false);
            }
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void calculateSquareRoot() {
        if (!errorState) {
            try {
                double numero = Double.parseDouble(txtPantalla.getText().split(" ")[txtPantalla.getText().split(" ").length - 1]);
                double resultado = Math.sqrt(numero);
                txtPantalla.setText(String.valueOf(resultado));
            } catch (NumberFormatException e) {
                txtPantalla.setText("Error");
                errorState = true;
                setButtonsEnabled(false);
            }
        }
    }

    private void addDecimal() {
        if (!errorState) {
            String currentText = txtPantalla.getText().trim();
            if (currentText.isEmpty() || currentText.endsWith(" ") || !currentText.substring(currentText.lastIndexOf(' ') + 1).contains(".")) {
                txtPantalla.setText(currentText + (currentText.endsWith(" ") ? "0." : "."));
            }
        }
    }

    private void clearScreen() {
        txtPantalla.setText("");
        numeros.clear();
        operaciones.clear();
        errorState = false;
        setButtonsEnabled(true);
    }

    private void addParenthesis(String parenthesis) {
        if (!errorState) {
            String currentText = txtPantalla.getText().trim();
            if (parenthesis.equals("(")) {
                if (currentText.isEmpty() || lastCharIsOperator(currentText.charAt(currentText.length() - 1))) {
                    txtPantalla.setText(currentText + parenthesis);
                }
            } else if (parenthesis.equals(")")) {
                long openCount = currentText.chars().filter(ch -> ch == '(').count();
                long closeCount = currentText.chars().filter(ch -> ch == ')').count();
                if (openCount > closeCount && !lastCharIsOperator(currentText.charAt(currentText.length() - 1))) {
                    txtPantalla.setText(currentText + parenthesis);
                }
            }
        }
    }

    private boolean lastCharIsOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(';
    }

    private double evaluateExpression(String expression) {
        List<String> tokens = tokenize(expression);
        List<String> rpn = infixToPostfix(tokens);
        return evaluateRPN(rpn);
    }

    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        char[] chars = expression.toCharArray();
        StringBuilder number = new StringBuilder();
        
        for (char c : chars) {
            if (Character.isDigit(c) || c == '.') {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    tokens.add(number.toString());
                    number.setLength(0);
                }
                if (c != ' ') {  // Añadir todos los caracteres no numéricos excepto los espacios
                    tokens.add(String.valueOf(c));
                }
            }
        }
        if (number.length() > 0) {
            tokens.add(number.toString());
        }
        return tokens;
    }

    private List<String> infixToPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (isNumeric(token)) {
                output.add(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(token)) {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            output.add(stack.pop());
        }
        return output;
    }

    private int precedence(String op) {
        switch (op) {
            case "+": case "-":
                return 1;
            case "*": case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    private double evaluateRPN(List<String> rpn) {
        Stack<Double> stack = new Stack<>();
        for (String token : rpn) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("Divide by zero");
                        }
                        stack.push(a / b);
                        break;
                    case "^":
                        stack.push(Math.pow(a, b));
                        break;
                }
            }
        }
        return stack.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private void setButtonsEnabled(boolean enabled) {
        for (JButton button : buttons) {
            button.setEnabled(enabled);
        }
    }
}