package test;

import java.io.*;

import modelo.CentroAdopcion;

public class CreaFicheroConCentros {

	public static void main(String[] args) {

		CentroAdopcion arcaDelTrocal = new CentroAdopcion(0, "Arca del Torcal", "Avenida José María Fernández", 2134,
				29200, (short) 20);
		CentroAdopcion spapm = new CentroAdopcion(1, "S.P.A.P.M.", "Cam. de las Erizas", 1463, 29011, (short) 30);
		CentroAdopcion refugioDelBurrito = new CentroAdopcion(2, "Refugio del Burrito", "Cortijo Rafael Sanchez", 2356,
				29520, (short) 15);
		CentroAdopcion pad = new CentroAdopcion(3, "P.A.D.", "Urb. Cerro del Águila", 9534, 29649, (short) 25);

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("./src/modelo/Centros.dat"), false))) {

			oos.writeObject(arcaDelTrocal);
			oos.writeObject(spapm);
			oos.writeObject(refugioDelBurrito);
			oos.writeObject(pad);
			
			System.out.println("Fichero creado");

		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./src/modelo/Centros.dat")))) {
			
			while (true) {

				System.out.println(ois.readObject());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fin del archivo");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
