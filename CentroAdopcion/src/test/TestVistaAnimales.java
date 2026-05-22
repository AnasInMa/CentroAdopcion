package test;

import javax.swing.*;

import modelo.CentroAdopcion;
import vista.VistaAnimales;

public class TestVistaAnimales {

	public static void main(String[] args) {

		VistaAnimales vAnimales = new VistaAnimales(
				new CentroAdopcion(0, "Arca del Torcal", "Avenida José María Fernández", 2134, 29200, (short) 20));

		JFrame f = new JFrame();
		f.setContentPane(vAnimales);
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);		
	}

}
