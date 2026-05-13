package test;

import javax.swing.*;

import modelo.CentroAdopcion;
import vista.VistaCentroAdopcion;

public class TestVistaCentroAdopcion {

	public static void main(String[] args) {

		VistaCentroAdopcion v = new VistaCentroAdopcion(new CentroAdopcion(0, "Arca del Torcal", "Avenida José María Fernández", 213456, 29200, (short) 20));
		
		JFrame f = new JFrame();
		f.setContentPane(v);
		
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
