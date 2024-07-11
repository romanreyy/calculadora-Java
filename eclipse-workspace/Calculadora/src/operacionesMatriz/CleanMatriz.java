package operacionesMatriz;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class CleanMatriz{
	
	public void cleanMatriz (JTextField[][] textFieldMatriz1, JTextField[][] textFieldMatriz2, JTextField[][] textFieldMatrizResultado, JFrame frame) {
        if (textFieldMatriz1 != null) {
            for (int i = 0; i < textFieldMatriz1.length; i++) {
                for (int j = 0; j < textFieldMatriz1[i].length; j++) {
                    frame.getContentPane().remove(textFieldMatriz1[i][j]);
                }
            }
        }

        if (textFieldMatriz2 != null) {
            for (int i = 0; i < textFieldMatriz2.length; i++) {
                for (int j = 0; j < textFieldMatriz2[i].length; j++) {
                    frame.getContentPane().remove(textFieldMatriz2[i][j]);
                }
            }
        }

        if (textFieldMatrizResultado != null) {
            for (int i = 0; i < textFieldMatrizResultado.length; i++) {
                for (int j = 0; j < textFieldMatrizResultado[i].length; j++) {
                    frame.getContentPane().remove(textFieldMatrizResultado[i][j]);
                }
            }
        }

        frame.revalidate();
        frame.repaint();
	
	}
}
