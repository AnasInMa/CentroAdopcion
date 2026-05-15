package test;

import javax.swing.*;

import modelo.Animal;
import modelo.CentroAdopcion;
import vista.VistaCentroAdopcion;

public class TestVistaCentroAdopcion {

	public static void main(String[] args) {

		CentroAdopcion centro1 = new CentroAdopcion(0, "Arca del Torcal", "Avenida José María Fernández", 213456, 29200,
				(short) 20);

		centro1.alojaAnimal(new Animal(234123, "Wilson", "Gato", "Dorado Sombreado",
				"es un gato que duerme mucho, come mucho y caga mucho", (byte) 2, "12-02-2026"));
		centro1.alojaAnimal(new Animal(1, "Firulais", "Perro", "Chiuaua",
				"es un perro que lleva mucho tiempo aqui, y a pesar de ladrar mucho siempre le gusta dar y recibir cariño",
				(byte) 5, "12-02-2023"));

		VistaCentroAdopcion v = new VistaCentroAdopcion(centro1);

		JFrame f = new JFrame();
		f.setContentPane(v);

		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
