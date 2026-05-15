package test;

import java.awt.*;

import javax.swing.*;

public class TestCarrusel {

	private static JButton bSiguiente;
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		JPanel panelVisible = new JPanel();
		JPanel panelPrincipal = new JPanel(new GridLayout(4, 4));
		JPanel[][] paneles = new JPanel[4][4];
		
		int cont = 1;
		
		for(int fila = 0; fila < paneles.length; fila++) {
			
			for(int columna = 0; columna < paneles[fila].length; columna++) {
				
				paneles[fila][columna] = new JPanel();
				paneles[fila][columna].add(new JLabel("Panel " + cont++));

				panelPrincipal.add(paneles[fila][columna]);
			}
		}
		
		for (int columna = 0; columna < paneles[0].length; columna++) {
			
			panelVisible.add(paneles[0][columna]);
		}
		
		bSiguiente = new JButton("Siguiente");
		panelVisible.add(bSiguiente);
		
		f.setContentPane(panelVisible);
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}
	
	

}
