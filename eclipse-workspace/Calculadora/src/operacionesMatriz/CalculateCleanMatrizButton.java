package operacionesMatriz;

import javax.swing.*;

public class CalculateCleanMatrizButton {

    public JTextField[][] calculateButton(JTextField[][] textFieldMatriz1, JTextField[][] textFieldMatriz2, JTextField[][] textFieldMatrizResultado, JFrame frame, JComboBox<String> optionsOperations) {
        if (textFieldMatriz1 == null || textFieldMatriz2 == null) {
            return null;
        }  
        int filas1 = textFieldMatriz1.length;
        int columnas1 = textFieldMatriz1[0].length;
        int filas2 = textFieldMatriz2.length;
        int columnas2 = textFieldMatriz2[0].length;

        if (textFieldMatrizResultado != null) {
            for (int i = 0; i < textFieldMatrizResultado.length; i++) {
                for (int j = 0; j < textFieldMatrizResultado[i].length; j++) {
                    frame.getContentPane().remove(textFieldMatrizResultado[i][j]);
                }
            }
        }

        String selectedOption = (String) optionsOperations.getSelectedItem();

        if ("Suma (+)".equals(selectedOption)) {
            if (filas1 != filas2 || columnas1 != columnas2) {
            	showError("Las matrices deben ser iguales para ser sumadas.");
            }
            textFieldMatrizResultado = new JTextField[filas1][columnas1];
            int x = 515;
            int y = 100;
            int width = 30;
            int height = 30;
            for (int i = 0; i < filas1; i++) {
                for (int j = 0; j < columnas1; j++) {
                    textFieldMatrizResultado[i][j] = new JTextField();
                    textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
                    textFieldMatrizResultado[i][j].setEditable(false);
                    textFieldMatrizResultado[i][j].setHorizontalAlignment(JTextField.CENTER);
                    frame.getContentPane().add(textFieldMatrizResultado[i][j]);
                    float valor1 = Float.parseFloat(textFieldMatriz1[i][j].getText());
                    float valor2 = Float.parseFloat(textFieldMatriz2[i][j].getText());
                    float suma = valor1 + valor2;
                    textFieldMatrizResultado[i][j].setText(String.valueOf(suma));
                }
            }
        }

        if ("Resta (-)".equals(selectedOption)) {
            if (filas1 != filas2 || columnas1 != columnas2) {
            	showError("Las matrices deben ser de igual tamaño para restar.");
            }
            textFieldMatrizResultado = new JTextField[filas1][columnas1];
            int x = 515;
            int y = 100;
            int width = 30;
            int height = 30;
            for (int i = 0; i < filas1; i++) {
                for (int j = 0; j < columnas1; j++) {
                    textFieldMatrizResultado[i][j] = new JTextField();
                    textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
                    textFieldMatrizResultado[i][j].setEditable(false);
                    textFieldMatrizResultado[i][j].setHorizontalAlignment(JTextField.CENTER);
                    frame.getContentPane().add(textFieldMatrizResultado[i][j]);
                    float valor1 = Float.parseFloat(textFieldMatriz1[i][j].getText());
                    float valor2 = Float.parseFloat(textFieldMatriz2[i][j].getText());
                    float resta = valor1 - valor2;
                    textFieldMatrizResultado[i][j].setText(String.valueOf(resta));
                }
            }
        }

        if ("Multiplicacion (x)".equals(selectedOption)) {
            if (columnas1 != filas2) {
            	showError("Para multiplicar matrices, la cantidad de columnas en la matriz 1 debe ser igual a la cantidad de filas en la matriz 2.");
            }
            textFieldMatrizResultado = new JTextField[filas1][columnas2]; // Dimensión correcta
            int x = 515;
            int y = 100;
            int width = 30;
            int height = 30;
            for (int i = 0; i < filas1; i++) {
                for (int j = 0; j < columnas2; j++) { // Corrección aquí: columnas2 en lugar de filas2
                    textFieldMatrizResultado[i][j] = new JTextField();
                    textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
                    textFieldMatrizResultado[i][j].setEditable(false);
                    textFieldMatrizResultado[i][j].setHorizontalAlignment(JTextField.CENTER);
                    frame.getContentPane().add(textFieldMatrizResultado[i][j]);
                    int valorResultado = 0;
                    for (int k = 0; k < columnas1; k++) {
                    	float valor1 = Float.parseFloat(textFieldMatriz1[i][j].getText());
                        float valor2 = Float.parseFloat(textFieldMatriz2[i][j].getText());
                        valorResultado += valor1 * valor2;
                    }
                    textFieldMatrizResultado[i][j].setText(String.valueOf(valorResultado));
                }
            }
        }
        
        if ("Division (/)".equals(selectedOption)) {
            if (filas2 != columnas2) {
            	showError("Para dividir matrices, la matriz 2 debe ser cuadrada.");
            }

            double[][] matriz2 = new double[filas2][columnas2];
            for (int i = 0; i < filas2; i++) {
                for (int j = 0; j < columnas2; j++) {
                    matriz2[i][j] = Double.parseDouble(textFieldMatriz2[i][j].getText());
                }
            }

            double sumaElementos = 0;
            for (int i = 0; i < filas2; i++) {
                for (int j = 0; j < columnas2; j++) {
                    sumaElementos += matriz2[i][j];
                }
            }

            if (sumaElementos == 0) {
                System.out.print("La matriz 2 tiene una suma de elementos igual a cero, no se puede dividir.");
            }

            textFieldMatrizResultado = new JTextField[filas1][columnas2];
            int x = 515;
            int y = 100;
            int width = 30;
            int height = 30;

            for (int i = 0; i < filas1; i++) {
                for (int j = 0; j < columnas2; j++) {
                    textFieldMatrizResultado[i][j] = new JTextField();
                    textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
                    textFieldMatrizResultado[i][j].setEditable(false);
                    textFieldMatrizResultado[i][j].setHorizontalAlignment(JTextField.CENTER);
                    frame.getContentPane().add(textFieldMatrizResultado[i][j]);
                    double valorResultado = 0;
                    for (int k = 0; k < columnas1; k++) {
                        float valor1 = Float.parseFloat(textFieldMatriz1[i][k].getText());
                        float valor2 = (float) matriz2[j][k];
                        valorResultado += valor1 * valor2 / sumaElementos;
                    }
                    textFieldMatrizResultado[i][j].setText(String.valueOf(valorResultado));
                }
            }
        }
        if (textFieldMatrizResultado != null) {
            for (int i = 0; i < textFieldMatrizResultado.length; i++) {
                for (int j = 0; j < textFieldMatrizResultado[i].length; j++) {
                    frame.getContentPane().add(textFieldMatrizResultado[i][j]);
                }
            }
        }

        frame.revalidate();
        frame.repaint();
       
        return textFieldMatrizResultado;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

