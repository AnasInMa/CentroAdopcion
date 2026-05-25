package test;

import java.awt.Dimension;
import java.util.*;

import javax.swing.*;

import modelo.Animal;
import modelo.CentroAdopcion;
import vista.VistaAnimales;

public class TestVistaAnimales {

	public static void main(String[] args) throws Exception {

		Animal[] animales = {
				new Animal(0, "Wilson", "Gato", "Dorado Sombreado",
						"es un gato que duerme mucho, come mucho y caga mucho", (byte) 2, "12/02/2025"),
				new Animal(1, "Firulais", "Perro", "Chiuaua",
						"es un perro que lleva mucho tiempo aqui, y a pesar de ladrar mucho siempre le gusta dar y recibir cariño",
						(byte) 5, "12/02/2023"),
				new Animal(2, "", "Pajaro", "Periquito", "es un pajaro que...", (byte) 1, "12/02/2026"),
				new Animal(3, "", "Conejo", "Holandes", "es un conejo que...", (byte) 1, "12/02/2026"),
				new Animal(4, "Adolfo", "Perro", "Pastor Aleman", "es un perro que...", (byte) 1, "12/02/2026"),
				new Animal(5, "Luna", "Gato", "Siamés", "es una gata muy cariñosa y juguetona...", (byte) 2,
						"15/04/2024"),
				new Animal(6, "Rocky", "Perro", "Golden Retriever", "es un perro amigable, ideal para familias...",
						(byte) 3, "20/08/2023"),
				new Animal(7, "Coco", "Loro", "Yaco", "es un loro muy inteligente que imita sonidos...", (byte) 1,
						"05/11/2025"),
				new Animal(8, "Bella", "Conejo", "Angora", "es una coneja tranquila con un pelaje muy suave...",
						(byte) 0, "10/01/2026")};

		VistaAnimales vAnimales = new VistaAnimales(new CentroAdopcion(0, "Arca del Torcal",
				"Avenida José María Fernández", 2134, 29200, (short) 20, new TreeSet<Animal>(Arrays.asList(animales))));

		JFrame f = new JFrame("Test");
		f.setContentPane(vAnimales);

		f.pack();
		f.setMinimumSize(new Dimension(600, 400));
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		try (Scanner sc = new Scanner(System.in)) {

			boolean b;

			while (true) {

				System.out.print("true/false: ");
				b = sc.nextBoolean();

				if (b) {

					vAnimales.siguienteFila();

				} else
					vAnimales.anteriorFila();
			}
		}
	}

}
