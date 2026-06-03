package utilidades;

import java.io.*;
import java.nio.file.*;

import javax.swing.*;

import modelo.Animal;

public class UtilidadesImagenes {

	public static File elegirImagen(JDialog dialogo) {

		JFileChooser fc = new JFileChooser();

		int opcion = fc.showOpenDialog(dialogo);

		File imagenElegida;
		
		if (opcion == JFileChooser.APPROVE_OPTION) {

			imagenElegida = fc.getSelectedFile();

			return imagenElegida;
		}

		return null;
	}
	
	public static void guardarImagen(Animal animal, File imagenElegida) {
	    
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("./imgs/Animal" + animal.getIDAnimal() + ".png")))) {
			
			byte[] bytesImagen = Files.readAllBytes(Paths.get(imagenElegida.getAbsolutePath()));
			
			for (int i = 0; i < bytesImagen.length; i++) {
				
				bos.write(bytesImagen[i]);
			}
			
		} catch (Exception e) {
			
			//JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna imagen o la imagen no es compatible (.png)", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
