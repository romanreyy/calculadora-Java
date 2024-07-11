package escuelatecnica.quintoprimera.trabajopractico.calculadora;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import calculoSistemaEcuaciones.SistemasEcuaciones;

public class MyCalculatorSistemaEcuaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField[][] txtCoef2x2;
    private JTextField[][] txtCoef3x3;
    private JLabel lblResuX;
    private JLabel lblResuY;
    private JLabel lblResuZ;

    public MyCalculatorSistemaEcuaciones(int i, int j, int width, int height) {
        setBounds(i, j, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(224, 255, 255));

        JButton btnBack = new JButton("Volver");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setBounds(10, 240, 120, 40); // Ajustado el tamaño y la posición
        getContentPane().add(btnBack);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Operaciones");
        menuBar.add(menu);

        JMenuItem menuItem2x2 = new JMenuItem("Sistema de Ecuaciones 2x2");
        menu.add(menuItem2x2);
        JMenuItem menuItem3x3 = new JMenuItem("Sistema de Ecuaciones 3x3");
        menu.add(menuItem3x3);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(10, 10, 565, 400);
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        menuItem2x2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();

                String[][] placeholders = {{"x1", "x2", "x3"}, {"y1", "y2", "y3"}};
                txtCoef2x2 = new JTextField[2][3];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        txtCoef2x2[i][j] = new JTextField(placeholders[i][j]);
                        txtCoef2x2[i][j].setBounds(150 + j * 60, 20 + i * 30, 50, 20);
                        txtCoef2x2[i][j].setForeground(Color.GRAY);
                        txtCoef2x2[i][j].setHorizontalAlignment(JTextField.RIGHT);
                        txtCoef2x2[i][j].addFocusListener(new FocusAdapter() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                JTextField source = (JTextField) e.getSource();
                                if (source.getForeground().equals(Color.GRAY)) {
                                    source.setText("");
                                    source.setForeground(Color.BLACK);
                                }
                            }
                            @Override
                            public void focusLost(FocusEvent e) {
                                JTextField source = (JTextField) e.getSource();
                                if (source.getText().isEmpty()) {
                                    source.setForeground(Color.GRAY);
                                    source.setText(source.getName());
                                }
                            }
                        });
                        txtCoef2x2[i][j].setName(placeholders[i][j]);
                        mainPanel.add(txtCoef2x2[i][j]);
                    }
                }

                lblResuX = new JLabel("x =");
                lblResuX.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuX.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuX.setBounds(200, 80, 150, 30);
                mainPanel.add(lblResuX);

                lblResuY = new JLabel("y = ");
                lblResuY.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuY.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuY.setBounds(200, 120, 150, 30);
                mainPanel.add(lblResuY);

                JButton btnSolve2x2 = new JButton("Resolver 2x2");
                btnSolve2x2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double[][] coef = new double[2][3];
                            boolean validInput = true;

                            for (int i = 0; i < 2; i++) {
                                for (int j = 0; j < 3; j++) {
                                    try {
                                        coef[i][j] = Double.parseDouble(txtCoef2x2[i][j].getText());
                                    } catch (NumberFormatException ex) {
                                        validInput = false;
                                        lblResuX.setText("Error: Entrada no válida en campo [" + (i+1) + "][" + (j+1) + "]");
                                        lblResuY.setText("");
                                    }
                                }
                            }

                            if (validInput) {
                                double[] resultados = SistemasEcuaciones.resolverSistema2x2(coef);
                                if (resultados != null) {
                                    lblResuX.setText("x = " + redondear(resultados[0]));
                                    lblResuY.setText("y = " + redondear(resultados[1]));
                                } else {
                                    lblResuX.setText("No tiene solución única");
                                    lblResuY.setText("");
                                }
                            }
                        } catch (Exception ex) {
                            lblResuX.setText("Error: " + ex.getMessage());
                            lblResuY.setText("");
                        }
                    }
                });
                btnSolve2x2.setBounds(210, 160, 150, 30);
                btnSolve2x2.setFont(new Font("Arial", Font.BOLD, 15));
                mainPanel.add(btnSolve2x2);

                revalidate();
                repaint();
            }
        });

        menuItem3x3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();

                String[][] placeholders = {{"x1", "x2", "x3", "x4"}, {"y1", "y2", "y3", "y4"}, {"z1", "z2", "z3", "z4"}};
                txtCoef3x3 = new JTextField[3][4];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        txtCoef3x3[i][j] = new JTextField(placeholders[i][j]);
                        txtCoef3x3[i][j].setBounds(120 + j * 60, 20 + i * 30, 50, 20);
                        txtCoef3x3[i][j].setForeground(Color.GRAY);
                        txtCoef3x3[i][j].setHorizontalAlignment(JTextField.RIGHT);
                        txtCoef3x3[i][j].addFocusListener(new FocusAdapter() {
                       
                            public void focusGained(FocusEvent e) {
                                JTextField source = (JTextField) e.getSource();
                                if (source.getForeground().equals(Color.GRAY)) {
                                    source.setText("");
                                    source.setForeground(Color.BLACK);
                                }
                            }
                     
                            public void focusLost(FocusEvent e) {
                                JTextField source = (JTextField) e.getSource();
                                if (source.getText().isEmpty()) {
                                    source.setForeground(Color.GRAY);
                                    source.setText(source.getName());
                                }
                            }
                        });
                        txtCoef3x3[i][j].setName(placeholders[i][j]);
                        mainPanel.add(txtCoef3x3[i][j]);
                    }
                }
                lblResuX = new JLabel("x =");
                lblResuX.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuX.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuX.setBounds(200, 100, 150, 30);
                mainPanel.add(lblResuX);

                lblResuY = new JLabel("y =");
                lblResuY.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuY.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuY.setBounds(200, 140, 150, 30);
                mainPanel.add(lblResuY);

                lblResuZ = new JLabel("z =");
                lblResuZ.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                lblResuZ.setHorizontalAlignment(SwingConstants.CENTER);
                lblResuZ.setBounds(200, 180, 150, 30);
                mainPanel.add(lblResuZ);

                JButton btnSolve3x3 = new JButton("Resolver 3x3");
                btnSolve3x3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double[][] coef = new double[3][4];
                            boolean validInput = true;

                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 4; j++) {
                                    try {
                                        coef[i][j] = Double.parseDouble(txtCoef3x3[i][j].getText());
                                    } catch (NumberFormatException ex) {
                                        validInput = false;
                                        lblResuX.setText("Error: Entrada no válida en campo [" + (i+1) + "][" + (j+1) + "]");
                                        lblResuY.setText("");
                                        lblResuZ.setText("");
                                    }
                                }
                            }

                            if (validInput) {
                                double[] resultados = SistemasEcuaciones.resolverSistema3x3(coef);
                                if (resultados != null) {
                                    lblResuX.setText("x = " + redondear(resultados[0]));
                                    lblResuY.setText("y = " + redondear(resultados[1]));
                                    lblResuZ.setText("z = " + redondear(resultados[2]));
                                } else {
                                    lblResuX.setText("No tiene solución única");
                                    lblResuY.setText("");
                                    lblResuZ.setText("");
                                }
                            }
                        } catch (Exception ex) {
                            lblResuX.setText("Error: " + ex.getMessage());
                            lblResuY.setText("");
                            lblResuZ.setText("");
                        }
                    }
                });
                btnSolve3x3.setBounds(200, 220, 180, 40);
                btnSolve3x3.setFont(new Font("Arial", Font.BOLD, 15));
                mainPanel.add(btnSolve3x3);

                revalidate();
                repaint();
            }
        });
    }

    private String redondear(double valor) {
        return String.format("%.2f", Math.round(valor * 100.0) / 100.0);
    }
}