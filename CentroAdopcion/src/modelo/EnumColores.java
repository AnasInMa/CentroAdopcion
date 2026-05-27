package modelo;

import java.awt.Color;

import vista.Vista;

public enum EnumColores {

	MARRON(Vista.ColoresMarron, "Marron"),
	VERDE(Vista.ColoresVerde, "Verde"),
	GRIS(Vista.ColoresGris, "Gris");
	
	private Color[] colores;
	private String nombreColor;
	private static EnumColores[] enumColores;

	static {
		
		enumColores = EnumColores.values();
	}
	
	EnumColores(Color[] colores, String nombreColor) {
		
		this.colores = colores;
		this.nombreColor = nombreColor;
	}
	
	public static Color[] buscaColoresPorNombre(String nombreColor) {
		
		Color[] coloresAux = new Color[Vista.ColoresVisibles.length];
		boolean encontrado = false;
		
		for (int i = 0; i < coloresAux.length && !encontrado; i++) {
			
			if(enumColores[i].getNombreColor().equalsIgnoreCase(nombreColor)) {
				
				coloresAux = enumColores[i].getColores();
				encontrado = true;
			}
		}
		
		return coloresAux;
	}
	
	public static String[] getNombreColores() {
		
		String[] nombreColores = new String[enumColores.length];
		
		for (int i = 0; i < nombreColores.length; i++) {
			
			nombreColores[i] = enumColores[i].getNombreColor();
		}
		
		return nombreColores;
	}

	public Color[] getColores() {
		return colores;
	}

	public String getNombreColor() {
		return nombreColor;
	}
	
	
}
