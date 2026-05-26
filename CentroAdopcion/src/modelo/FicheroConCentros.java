package modelo;

import java.io.*;

public class FicheroConCentros {

	public static void creaFicheroCentros() {

		CentroAdopcion arcaDelTrocal = new CentroAdopcion(1, "Arca del Torcal", "Avenida José María Fernández", 2134,
				29200, (short) 20);
		CentroAdopcion spapm = new CentroAdopcion(2, "S.P.A.P.M.", "Cam. de las Erizas", 1463, 29011, (short) 30);
		CentroAdopcion refugioDelBurrito = new CentroAdopcion(3, "Refugio del Burrito", "Cortijo Rafael Sanchez", 2356,
				29520, (short) 15);
		CentroAdopcion pad = new CentroAdopcion(4, "P.A.D.", "Urb. Cerro del Águila", 9534, 29649, (short) 25);

		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("./files/Centros.dat"), false))) {

			oos.writeObject(arcaDelTrocal);
			oos.writeObject(spapm);
			oos.writeObject(refugioDelBurrito);
			oos.writeObject(pad);

			System.out.println("Fichero centros creado");

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public static CentroAdopcion buscaCentro(String nombreCentro) {
		
		CentroAdopcion centro = null;
		boolean encontrado = false;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./files/Centros.dat")))) {

			while (!encontrado) {

				//System.out.println(ois.readObject());
				
				centro = (CentroAdopcion) ois.readObject();
				
				if(centro.getNombre().equalsIgnoreCase(nombreCentro)) {

					encontrado = true;
				}
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
		
		return encontrado? centro : null; 	//si se encuentra el centro lo devuelve, si no devuelve un nulo, porque si no devolveria el ultimo centro del fuichero
	}

}
