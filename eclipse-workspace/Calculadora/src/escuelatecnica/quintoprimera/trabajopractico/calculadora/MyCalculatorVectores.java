package escuelatecnica.quintoprimera.trabajopractico.calculadora;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.PlainDocument;

import restriccionVectores.NumberCommaDocumentFilter;

public class MyCalculatorVectores extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldVector1;
    private JTextField textFieldVector2;
    private JTextField textFieldEscalar;
    private JLabel lblResultado;
    private JTextField textFieldResultado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyCalculatorVectores frame = new MyCalculatorVectores();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MyCalculatorVectores() {
        getContentPane().setBackground(new Color(224, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 400);
        getContentPane().setLayout(null);

        JButton btnBack = new JButton("Volver");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setBounds(22, 230, 117, 29);
        getContentPane().add(btnBack);

        JLabel lblVector1 = new JLabel("Vector 1:");
        lblVector1.setBounds(10, 57, 86, 14);
        getContentPane().add(lblVector1);

        textFieldVector1 = new JTextField();
        textFieldVector1.setBounds(106, 54, 307, 20);
        getContentPane().add(textFieldVector1);
        textFieldVector1.setColumns(10);
        ((PlainDocument) textFieldVector1.getDocument()).setDocumentFilter(new NumberCommaDocumentFilter());

        JLabel lblVector2 = new JLabel("Vector 2:");
        lblVector2.setBounds(10, 89, 86, 14);
        getContentPane().add(lblVector2);

        textFieldVector2 = new JTextField();
        textFieldVector2.setBounds(106, 86, 307, 20);
        getContentPane().add(textFieldVector2);
        textFieldVector2.setColumns(10);
        ((PlainDocument) textFieldVector2.getDocument()).setDocumentFilter(new NumberCommaDocumentFilter());

        JLabel lblEscalar = new JLabel("Escalar:");
        lblEscalar.setBounds(463, 125, 86, 14);
        getContentPane().add(lblEscalar);

        textFieldEscalar = new JTextField();
        textFieldEscalar.setBounds(520, 122, 86, 20);
        getContentPane().add(textFieldEscalar);
        textFieldEscalar.setColumns(10);
        ((PlainDocument) textFieldEscalar.getDocument()).setDocumentFilter(new NumberCommaDocumentFilter());

        lblResultado = new JLabel("Resultado:");
        lblResultado.setBounds(10, 189, 86, 14);
        getContentPane().add(lblResultado);

        textFieldResultado = new JTextField();
        textFieldResultado.setBounds(106, 186, 307, 20);
        getContentPane().add(textFieldResultado);
        textFieldResultado.setColumns(10);

        JButton btnSuma = new JButton("Suma");
        btnSuma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float[] v1 = parseVector(textFieldVector1.getText());
                    float[] v2 = parseVector(textFieldVector2.getText());
                    float[] result = suma(v1, v2);
                    textFieldResultado.setText(vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnSuma.setFont(new Font("Arial", Font.BOLD, 15));
        btnSuma.setBounds(463, 53, 143, 23);
        getContentPane().add(btnSuma);

        JButton btnResta = new JButton("Resta");
        btnResta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float[] v1 = parseVector(textFieldVector1.getText());
                    float[] v2 = parseVector(textFieldVector2.getText());
                    float[] result = resta(v1, v2);
                    textFieldResultado.setText(vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnResta.setFont(new Font("Arial", Font.BOLD, 15));
        btnResta.setBounds(616, 53, 160, 23);
        getContentPane().add(btnResta);

        JButton btnMultiplicacionEscalar = new JButton("Multiplicar Escalar");
        btnMultiplicacionEscalar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float[] v1 = parseVector(textFieldVector1.getText());
                    float escalar = Float.parseFloat(textFieldEscalar.getText());
                    float[] result = multiplicacionEscalar(v1, escalar);
                    textFieldResultado.setText(vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnMultiplicacionEscalar.setFont(new Font("Arial", Font.BOLD, 15));
        btnMultiplicacionEscalar.setBounds(616, 121, 160, 23);
        getContentPane().add(btnMultiplicacionEscalar);

        JButton btnProductoEscalar = new JButton("Producto Escalar");
        btnProductoEscalar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float[] v1 = parseVector(textFieldVector1.getText());
                    float[] v2 = parseVector(textFieldVector2.getText());
                    float result = productoEscalar(v1, v2);
                    textFieldResultado.setText("" + result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnProductoEscalar.setFont(new Font("Arial", Font.BOLD, 15));
        btnProductoEscalar.setBounds(463, 85, 142, 23);
        getContentPane().add(btnProductoEscalar);

        JButton btnProductoVectorial = new JButton("Producto Vectorial");
        btnProductoVectorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    float[] v1 = parseVector(textFieldVector1.getText());
                    float[] v2 = parseVector(textFieldVector2.getText());
                    float[] result = productoVectorial(v1, v2);
                    textFieldResultado.setText(vectorToString(result));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        btnProductoVectorial.setFont(new Font("Arial", Font.BOLD, 15));
        btnProductoVectorial.setBounds(616, 85, 160, 23);
        getContentPane().add(btnProductoVectorial);

        JLabel lblAviso = new JLabel("Los valores del vector deben estar separados por \",\", ejemplo: 1, 2, 3.");
        lblAviso.setBounds(10, 11, 403, 23);
        getContentPane().add(lblAviso);

        JLabel lblOperaciones = new JLabel("Operaciones");
        lblOperaciones.setBounds(463, 15, 143, 14);
        getContentPane().add(lblOperaciones);

    }

    private float[] parseVector(String text) {
        String[] parts = text.split(",");
        float[] vector = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
        	vector[i] = Float.parseFloat(parts[i]);
        }
        return vector;
        }
    private String vectorToString(float[] vector) {
        StringBuilder sb = new StringBuilder();
        for (float v : vector) {
            sb.append(v).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    public static float[] suma(float[] v1, float[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma longitud.");
        float[] result = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    public static float[] resta(float[] v1, float[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma longitud.");
        float[] result = new float[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
        }
        return result;
    }

    public static float[] multiplicacionEscalar(float[] v, float escalar) {
        float[] result = new float[v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i] * escalar;
        }
        return result;
    }

    public static float productoEscalar(float[] v1, float[] v2) {
        if (v1.length != v2.length) throw new IllegalArgumentException("Los vectores deben tener la misma longitud.");
        float result = 0;
        for (int i = 0; i < v1.length; i++) {
            result += v1[i] * v2[i];
        }
        return result;
    }

    public static float[] productoVectorial(float[] v1, float[] v2) {
        if (v1.length != 3 || v2.length != 3) throw new IllegalArgumentException("Los vectores deben tener 3 elementos.");
        float[] result = new float[3];
        result[0] = v1[1] * v2[2] - v1[2] * v2[1];
        result[1] = v1[2] * v2[0] - v1[0] * v2[2];
        result[2] = v1[0] * v2[1] - v1[1] * v2[0];
        return result;
    }
}