package escuelatecnica.quintoprimera.trabajopractico.calculadora;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


import operacionesMatriz.Determinante;
import operacionesMatriz.EscalarPorMatriz;
import operacionesMatriz.Inversa;
import operacionesMatriz.MatrixCalculator;

/**
 * @author Roman Rey
 */
public class MyCalculatorMatriz extends JFrame{
	private static final long serialVersionUID = 1L;


	public MyCalculatorMatriz(int i, int j, int width, int height) {
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
		btnBack.setBounds(22, 283, 117, 29);
		getContentPane().add(btnBack);
		
		JButton btnOne = this.createNewButton("Calculos Basicos de Matrices", 6, 20, 268, 50);
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MatrixCalculator matrixCalculator = new MatrixCalculator(100, 100, 643, 346);
				matrixCalculator.setVisible(true);
			}
		});
		btnOne.setFont(new Font("Arial", Font.BOLD, 15));
		getContentPane().add(btnOne);
		
		JButton btnTwo = this.createNewButton("Multiplicación de escalar por una matriz", 6, 75, 268, 50);
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EscalarPorMatriz addScreen = new EscalarPorMatriz();
				addScreen.setVisible(true);
			}
		});
		btnTwo.setFont(new Font("Arial", Font.BOLD, 15));
		getContentPane().add(btnTwo);
		
		JButton btnThree = this.createNewButton("Determinante de una matriz", 6, 130, 268, 50);
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Determinante addScreen = new Determinante();
				addScreen.setVisible(true);
			}
		});
		btnThree.setFont(new Font("Arial", Font.BOLD, 15));
		getContentPane().add(btnThree);
		
		JButton btnFour = this.createNewButton("Inversa de una matriz", 6, 185, 268, 50);
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inversa addScreen = new Inversa();
				addScreen.setVisible(true);
			}
		});
		btnFour.setFont(new Font("Arial", Font.BOLD, 15));
		getContentPane().add(btnFour);
		
	}

	
	private JButton createNewButton(String name, int x, int y, int width, int height) {
		JButton btn = new JButton(name);
		btn.setBounds(x, y, width, height);
		return btn;
	}

}
