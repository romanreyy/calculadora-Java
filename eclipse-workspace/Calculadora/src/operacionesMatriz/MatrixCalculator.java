package operacionesMatriz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixCalculator extends JFrame {

    private static final long serialVersionUID = -7580717973032844223L;

    private JTextField[][] textFieldMatriz1;
    private JTextField[][] textFieldMatriz2;
    private JTextField[][] textFieldMatrizResultado;
    private JComboBox<String> matriz1;
    private JComboBox<String> matriz2;
    private JComboBox<String> optionsOperations;

    public MatrixCalculator(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(22, 260, 117, 29);
		this.getContentPane().add(btnBack);

		JComboBox<String> matriz1 = new JComboBox<String>();
		matriz1.setBounds(71, 49, 79, 27);
		matriz1.addItem("1x1");
		matriz1.addItem("1x2");
		matriz1.addItem("1x3");
		matriz1.addItem("2x1");
		matriz1.addItem("2x2");
		matriz1.addItem("2x3");
		matriz1.addItem("3x1");
		matriz1.addItem("3x2");
		matriz1.addItem("3x3");
		getContentPane().add(matriz1);

		JComboBox<String> matriz2 = new JComboBox<String>();
		matriz2.setBounds(316, 49, 79, 27);
		matriz2.addItem("1x1");
		matriz2.addItem("1x2");
		matriz2.addItem("1x3");
		matriz2.addItem("2x1");
		matriz2.addItem("2x2");
		matriz2.addItem("2x3");
		matriz2.addItem("3x1");
		matriz2.addItem("3x2");
		matriz2.addItem("3x3");
		this.getContentPane().add(matriz2);

		JLabel lblMatriz1 = new JLabel("Matriz 1");
		lblMatriz1.setBounds(71, 21, 79, 16);
		getContentPane().add(lblMatriz1);

		JLabel lblMatriz2 = new JLabel("Matriz 2");
		lblMatriz2.setBounds(304, 21, 79, 16);
		getContentPane().add(lblMatriz2);

		JButton btnIgual = new JButton("=");
        btnIgual.setBounds(420, 141, 61, 29);
        getContentPane().add(btnIgual);

        JButton btnClean = new JButton("clean");
        btnClean.setBounds(500, 260, 80, 29);
        getContentPane().add(btnClean);

        optionsOperations = new JComboBox<String>();
        optionsOperations.setBounds(167, 147, 115, 16);
        getContentPane().add(optionsOperations);
        optionsOperations.addItem("Suma (+)");
        optionsOperations.addItem("Resta (-)");
        optionsOperations.addItem("Multiplicacion (x)");
        optionsOperations.addItem("Division (/)");

        matriz1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldMatriz1 = createMatrix(matriz1, textFieldMatriz1, 70, 100);
            }
        });

        matriz2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldMatriz2 = createMatrix(matriz2, textFieldMatriz2, 300, 100);
            }
        });

        btnIgual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CalculateCleanMatrizButton calculateMatrizButton = new CalculateCleanMatrizButton();
                textFieldMatrizResultado = calculateMatrizButton.calculateButton(textFieldMatriz1, textFieldMatriz2, textFieldMatrizResultado, MatrixCalculator.this, optionsOperations);
            }
        });

        btnClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }

    private JTextField[][] createMatrix(JComboBox<String> comboBox, JTextField[][] textFieldMatrix, int startX, int startY) {
        String seleccion = (String) comboBox.getSelectedItem();
        int filas = Character.getNumericValue(seleccion.charAt(0));
        int columnas = Character.getNumericValue(seleccion.charAt(2));

        if (textFieldMatrix != null) {
            for (JTextField[] row : textFieldMatrix) {
                for (JTextField field : row) {
                    getContentPane().remove(field);
                }
            }
        }

        textFieldMatrix = new JTextField[filas][columnas];
        int width = 30;
        int height = 30;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                textFieldMatrix[i][j] = new JTextField();
                textFieldMatrix[i][j].setBounds(startX + j * (width + 5), startY + i * (height + 5), width, height);
                textFieldMatrix[i][j].setHorizontalAlignment(JTextField.RIGHT);
                getContentPane().add(textFieldMatrix[i][j]);
            }
        }

        revalidate();
        repaint();
        return textFieldMatrix;
    }
}