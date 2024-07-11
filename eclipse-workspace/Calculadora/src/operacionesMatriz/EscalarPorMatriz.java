package operacionesMatriz;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class EscalarPorMatriz extends JFrame {

    private JComboBox<String> comboMatriz;
    private JTextField textFieldEscalar;
    private JButton btnIgual, btnVolver, btnClean;
    private JPanel panelMatriz, panelResultado;
    private JTextField[][] matrizCampos, matrizResultado;

    public EscalarPorMatriz() {
        setTitle("Escalar por Matriz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        setLayout(null);

        // Matriz
        JLabel lblMatriz = new JLabel("Matriz");
        lblMatriz.setBounds(20, 20, 80, 25);
        add(lblMatriz);

        comboMatriz = new JComboBox<>(new String[]{"1x1", "1x2", "1x3", "2x1", "2x2", "2x3", "3x1", "3x2", "3x3"});
        comboMatriz.setBounds(20, 45, 80, 25);
        add(comboMatriz);

        // Escalar
        JLabel lblEscalar = new JLabel("Escalar");
        lblEscalar.setBounds(150, 20, 80, 25);
        add(lblEscalar);

        textFieldEscalar = new JTextField();
        textFieldEscalar.setBounds(150, 45, 80, 25);
        textFieldEscalar.setDocument(new NumericDocument());
        add(textFieldEscalar);

        // Botón igual
        btnIgual = new JButton("=");
        btnIgual.setBounds(280, 45, 50, 25);
        add(btnIgual);

        // Paneles para matrices
        panelMatriz = new JPanel(new GridLayout(3, 3, 2, 2));
        panelMatriz.setBounds(20, 80, 150, 100);
        panelMatriz.setVisible(false);
        add(panelMatriz);

        panelResultado = new JPanel(new GridLayout(3, 3, 2, 2));
        panelResultado.setBounds(220, 80, 150, 100);
        add(panelResultado);

        // Botones inferiores
        btnVolver = new JButton("Volver");
        btnVolver.setBounds(20, 220, 100, 30);
        add(btnVolver);

        btnClean = new JButton("Clean");
        btnClean.setBounds(270, 220, 100, 30);
        add(btnClean);

        // Inicializar matrices
        matrizCampos = new JTextField[3][3];
        matrizResultado = new JTextField[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizCampos[i][j] = new JTextField(3);
                matrizCampos[i][j].setDocument(new NumericDocument());
                matrizCampos[i][j].setHorizontalAlignment(JTextField.RIGHT);
                panelMatriz.add(matrizCampos[i][j]);

                matrizResultado[i][j] = new JTextField(3);
                matrizResultado[i][j].setEditable(false);
                matrizResultado[i][j].setHorizontalAlignment(JTextField.RIGHT);
                panelResultado.add(matrizResultado[i][j]);
            }
        }

        // Añadir listeners
        comboMatriz.addActionListener(e -> actualizarMatriz());
        btnIgual.addActionListener(e -> calcularEscalarPorMatriz());
        btnClean.addActionListener(e -> limpiarMatrices());
        btnVolver.addActionListener(e -> dispose());

        setResizable(false);
    }

    private void actualizarMatriz() {
        String seleccion = (String) comboMatriz.getSelectedItem();
        int filas = Character.getNumericValue(seleccion.charAt(0));
        int columnas = Character.getNumericValue(seleccion.charAt(2));

        panelMatriz.removeAll();
        panelMatriz.setLayout(new GridLayout(filas, columnas, 2, 2));

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                panelMatriz.add(matrizCampos[i][j]);
            }
        }

        panelMatriz.setVisible(true);
        panelMatriz.revalidate();
        panelMatriz.repaint();
    }

    private void calcularEscalarPorMatriz() {
        try {
            double escalar = Double.parseDouble(textFieldEscalar.getText());
            String seleccion = (String) comboMatriz.getSelectedItem();
            int filas = Character.getNumericValue(seleccion.charAt(0));
            int columnas = Character.getNumericValue(seleccion.charAt(2));

            panelResultado.removeAll();
            panelResultado.setLayout(new GridLayout(filas, columnas, 2, 2));

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    double valor = Double.parseDouble(matrizCampos[i][j].getText());
                    double resultado = valor * escalar;
                    String resultadoRedondeado = redondearYFormatear(resultado);
                    matrizResultado[i][j].setText(resultadoRedondeado);
                    panelResultado.add(matrizResultado[i][j]);
                }
            }

            panelResultado.revalidate();
            panelResultado.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String redondearYFormatear(double valor) {
        BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
        return bd.stripTrailingZeros().toPlainString();
    }

    private void limpiarMatrices() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizCampos[i][j].setText("");
                matrizResultado[i][j].setText("");
            }
        }
        textFieldEscalar.setText("");
        panelMatriz.setVisible(false);
        panelResultado.removeAll();
        panelResultado.revalidate();
        panelResultado.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EscalarPorMatriz().setVisible(true);
        });
    }
}

class NumericDocument extends PlainDocument {
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        String newStr = str.replaceAll("[^0-9.-]", "");
        super.insertString(offs, newStr, a);
    }
}