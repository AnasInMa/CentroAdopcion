package test;

import java.awt.event.*;

import javax.swing.*;

/**
 * Iba a utilizar esta clase que hice como ejemplo
 * para mostrar los paneles de los animales, pero
 * al final lo hize un pelin distinto
 */
public class TestCarrusel implements ActionListener {

	private JFrame f;
	private JPanel panelVisible;
	private JPanel[][] arrayPaneles;
	private JButton bSiguiente;
	private int contadorFilaPaneles;

	public static void main(String[] args) {

		TestCarrusel tc = new TestCarrusel();
		
		tc.f = new JFrame();
		tc.panelVisible = new JPanel();
		tc.arrayPaneles = new JPanel[4][4];
		tc.iniciaArrayPaneles(tc.arrayPaneles);

		tc.contadorFilaPaneles = 0;

		for (int i = 0; i < 4; i++) {

			tc.panelVisible.add(tc.arrayPaneles[tc.contadorFilaPaneles][i]);
		}

		tc.bSiguiente = new JButton("Siguiente");
		tc.bSiguiente.addActionListener(tc);
		tc.panelVisible.add(tc.bSiguiente);

		tc.f.setContentPane(tc.panelVisible);

		tc.f.pack();
		tc.f.setLocationRelativeTo(null);
		tc.f.setVisible(true);
	}

	private void iniciaArrayPaneles(JPanel[][] matriz) {

		int cont = 1;

		for (int fila = 0; fila < matriz.length; fila++) {

			for (int columna = 0; columna < matriz[fila].length; columna++) {

				matriz[fila][columna] = new JPanel();
				matriz[fila][columna].add(new JLabel("Panel " + cont++));
			}
		}
	}

	private void cambiaPaneles() {
		
		if(contadorFilaPaneles >= arrayPaneles.length) contadorFilaPaneles = 0;
		
		panelVisible.removeAll();
		panelVisible.revalidate();
		panelVisible.repaint();

		for (int i = 0; i < arrayPaneles[contadorFilaPaneles].length; i++) {

			panelVisible.add(arrayPaneles[contadorFilaPaneles][i]);
		}
		
		panelVisible.add(bSiguiente);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == bSiguiente) {

			//System.out.println("siguiente");
			
			contadorFilaPaneles++;
			
			cambiaPaneles();
			f.pack();
		}
	}
	
}