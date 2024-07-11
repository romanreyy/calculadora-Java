package escuelatecnica.quintoprimera.trabajopractico.calculadora;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class MyCalculatorMenuPrincipal {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Establecer la apariencia del sistema operativo
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    MyCalculatorMenuPrincipal window = new MyCalculatorMenuPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MyCalculatorMenuPrincipal() {
        initialize();
    }

    private void initialize() {
    	frame = new JFrame();
    	frame.setBounds(100, 100, 450, 300);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("MyCalculator - Menu Principal");
    	frame.getContentPane().setBackground(new Color(224, 255, 255));
    	frame.setLayout(new GridLayout(1, 1));


        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 255, 255));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        frame.getContentPane().add(panel);

        JButton btnFour = createNewButton("Cálculos Básicos");
        btnFour.addActionListener(e -> {
            MyCalculatorCalculosBasicos addScreen = new MyCalculatorCalculosBasicos();
            addScreen.setVisible(true);
        });
        panel.add(btnFour);

        JButton btnThree = createNewButton("Cálculos de Vectores");
        btnThree.addActionListener(e -> {
            MyCalculatorVectores addScreen = new MyCalculatorVectores();
            addScreen.setVisible(true);
        });
        panel.add(btnThree);

        JButton btnOne = createNewButton("Cálculos Básicos de Matrices");
        btnOne.addActionListener(e -> {
            MyCalculatorMatriz matrixCalculator = new MyCalculatorMatriz(100, 100, 643, 346);
            matrixCalculator.setVisible(true);
        });
        panel.add(btnOne);

        JButton btnTwo = createNewButton("Sistema de Ecuaciones");
        btnTwo.addActionListener(e -> {
            MyCalculatorSistemaEcuaciones addScreen = new MyCalculatorSistemaEcuaciones(100, 100, 643, 346);
            addScreen.setVisible(true);
        });
        panel.add(btnTwo);
    }

    private JButton createNewButton(String name) {
        JButton btn = new JButton(name);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(new Color(135, 206, 250));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return btn;
    }
}

