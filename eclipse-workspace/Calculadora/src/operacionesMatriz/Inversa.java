package operacionesMatriz;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Inversa extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField[][] textFieldMatriz1;
	private JTextField[][] textFieldMatrizResultado;
	
	public Inversa() {
		setBounds(100, 100, 500, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Volver");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(22, 260, 117, 29);
		getContentPane().add(btnBack);
		
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
		
		JButton btnClean = new JButton("limpiar");
		btnClean.setBounds(370, 260, 80, 29);
		getContentPane().add(btnClean);
		
		JButton btnIgual = new JButton("=");
		btnIgual.setBounds(250, 150, 61, 29);
		getContentPane().add(btnIgual);
		
		matriz1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seleccion = (String) matriz1.getSelectedItem();
		        int filas = Character.getNumericValue(seleccion.charAt(0));
		        int columnas = Character.getNumericValue(seleccion.charAt(2));

		        if (textFieldMatriz1 != null) {
		            for (int i = 0; i < textFieldMatriz1.length; i++) {
		                for (int j = 0; j < textFieldMatriz1[i].length; j++) {
		                    getContentPane().remove(textFieldMatriz1[i][j]);
		                }
		            }
		        }

		        textFieldMatriz1 = new JTextField[filas][columnas];
		        int x = 70;
		        int y = 130;
		        int width = 30;
		        int height = 30;
		        for (int i = 0; i < filas; i++) {
		            for (int j = 0; j < columnas; j++) {
		                textFieldMatriz1[i][j] = new JTextField();
		                textFieldMatriz1[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
		                getContentPane().add(textFieldMatriz1[i][j]);
		            }
		        }

		        	revalidate();
		        	repaint();
			}
		});
		
		btnIgual.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 calculateAndDisplayInverse();
	            }
	        });
		
		btnClean.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
	                if (textFieldMatriz1 != null) {
	                    for (int i = 0; i < textFieldMatriz1.length; i++) {
	                        for (int j = 0; j < textFieldMatriz1[i].length; j++) {
	                            textFieldMatriz1[i][j].setText("");
	                        }
	                    }
	                }
	                if (textFieldMatrizResultado != null) {
	                    for (int i = 0; i < textFieldMatrizResultado.length; i++) {
	                        for (int j = 0; j < textFieldMatrizResultado[i].length; j++) {
	                            textFieldMatrizResultado[i][j].setText("");
	                        }
	                    }
	                }
	            }
	        });
	    }
		
	private void calculateAndDisplayInverse() {
        int rows = textFieldMatriz1.length;
        int cols = textFieldMatriz1[0].length;

        if (rows != cols) {
            // La matriz no es cuadrada, no se puede calcular la inversa
            return;
        }

        double[][] matriz = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matriz[i][j] = Double.parseDouble(textFieldMatriz1[i][j].getText());
            }
        }

        double[][] inversa = new double[rows][cols];
        if (rows == 1) {
            inversa[0][0] = 1 / matriz[0][0];
        } else if (rows == 2) {
            inversa = inversa2x2(matriz);
        } else if (rows == 3) {
            inversa = inversa3x3(matriz);
        }

        if (textFieldMatrizResultado != null) {
            for (int i = 0; i < textFieldMatrizResultado.length; i++) {
                for (int j = 0; j < textFieldMatrizResultado[i].length; j++) {
                    getContentPane().remove(textFieldMatrizResultado[i][j]);
                }
            }
        }

        textFieldMatrizResultado = new JTextField[rows][cols];
        int x = 360;
        int y = 130;
        int width = 30;
        int height = 30;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                textFieldMatrizResultado[i][j] = new JTextField(String.format("%.2f", inversa[i][j]));
                textFieldMatrizResultado[i][j].setBounds(x + j * (width + 5), y + i * (height + 5), width, height);
                textFieldMatrizResultado[i][j].setEditable(false);
                getContentPane().add(textFieldMatrizResultado[i][j]);
            }
        }

        revalidate();
        repaint();
    }

    private double[][] inversa2x2(double[][] matriz) {
        double det = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        double[][] inversa = {
            { matriz[1][1] / det, -matriz[0][1] / det },
            { -matriz[1][0] / det, matriz[0][0] / det }
        };
        return inversa;
    }

    private double[][] inversa3x3(double[][] matriz) {
        double det = matriz[0][0] * (matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1]) -
                     matriz[0][1] * (matriz[1][0] * matriz[2][2] - matriz[1][2] * matriz[2][0]) +
                     matriz[0][2] * (matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0]);

        double[][] adjunta = {
            { matriz[1][1] * matriz[2][2] - matriz[1][2] * matriz[2][1], matriz[0][2] * matriz[2][1] - matriz[0][1] * matriz[2][2], matriz[0][1] * matriz[1][2] - matriz[0][2] * matriz[1][1] },
            { matriz[1][2] * matriz[2][0] - matriz[1][0] * matriz[2][2], matriz[0][0] * matriz[2][2] - matriz[0][2] * matriz[2][0], matriz[0][2] * matriz[1][0] - matriz[0][0] * matriz[1][2] },
            { matriz[1][0] * matriz[2][1] - matriz[1][1] * matriz[2][0], matriz[0][1] * matriz[2][0] - matriz[0][0] * matriz[2][1], matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0] }
        };

        double[][] inversa = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inversa[i][j] = adjunta[i][j] / det;
            }
        }
        return inversa;
    }

    public static void main(String[] args) {
        Inversa frame = new Inversa();
        frame.setVisible(true);
    }
}		
