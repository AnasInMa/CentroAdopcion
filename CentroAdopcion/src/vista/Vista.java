package vista;

import java.awt.*;
import java.io.*;

import javax.swing.*;

import modelo.CentroAdopcion;
import modelo.EnumColores;

public class Vista extends JPanel{

	private static final long serialVersionUID = -5752211613049689258L;
	
	private static final byte BASE_HORIZONTAL, BASE_VERTICAL;
	public static final short ANCHO_PANEL, ALTO_PANEL;
	public static final int ANCHO_COMPONENTE, ALTO_COMPONENTE;
	
	public static float multiplicadorPanel,		//esta variable se va a utilizar para las resoluciones de los paneles
						multiplicadorAnchoComponente,
						multiplicadorAltoComponente;	//y estos para el tamaño de los componetes

	public static Color FONDO_DATOS, FONDO_ANIMALES, FONDO_PRINCIPAL, FONDO_BOTON,
						TEXTO_CLARO, TEXTO_OSCURO;	//Colores que tendra la vista, y cambiara en funcion de la eleccion del usuario en el panel opciones
	public static final Color FONDO_DATOS_MARRON, FONDO_ANIMALES_MARRON, FONDO_PRINCIPAL_MARRON, FONDO_BOTON_MARRON,
								TEXTO_CLARO_MARRON, TEXTO_OSCURO_MARRON,
							FONDO_DATOS_VERDE, FONDO_ANIMALES_VERDE, FONDO_PRINCIPAL_VERDE, FONDO_BOTON_VERDE,
								TEXTO_CLARO_VERDE, TEXTO_OSCURO_VERDE,
							FONDO_DATOS_GRIS, FONDO_ANIMALES_GRIS, FONDO_PRINCIPAL_GRIS, FONDO_BOTON_GRIS,
								TEXTO_CLARO_GRIS, TEXTO_OSCURO_GRIS,
							FONDO_DATOS_AZUL, FONDO_ANIMALES_AZUL, FONDO_PRINCIPAL_AZUL, FONDO_BOTON_AZUL,
		                        TEXTO_CLARO_AZUL, TEXTO_OSCURO_AZUL,
	                        FONDO_DATOS_MORADO, FONDO_ANIMALES_MORADO, FONDO_PRINCIPAL_MORADO, FONDO_BOTON_MORADO,
		                        TEXTO_CLARO_MORADO, TEXTO_OSCURO_MORADO,
	                        FONDO_DATOS_ROJO, FONDO_ANIMALES_ROJO, FONDO_PRINCIPAL_ROJO, FONDO_BOTON_ROJO,
		                        TEXTO_CLARO_ROJO, TEXTO_OSCURO_ROJO,
	                        FONDO_DATOS_TURQUESA, FONDO_ANIMALES_TURQUESA, FONDO_PRINCIPAL_TURQUESA, FONDO_BOTON_TURQUESA,
		                        TEXTO_CLARO_TURQUESA, TEXTO_OSCURO_TURQUESA;
	
	public static Color[] ColoresVisibles, ColoresMarron, ColoresVerde, ColoresGris,
						ColoresAzul, ColoresMorado, ColoresRojo, ColoresTurquesa;
	
	public static Font FuenteTexto, FuenteTextoPC;	//PC -> Pantalla Completa
	public static final Font FUENTE_BOTONES;
	
	public static final String FicheroOpciones;
	public static boolean EsPantallaCompleta;
	
	public static final File archivoAnimalesAdoptados = new File("./files/AnimalesAdoptados.dat");
	
	static {
		
		//Dimensiones de la ventana y los componentes
		BASE_HORIZONTAL = 16;
		BASE_VERTICAL = 9;
		
		multiplicadorPanel = 60;

		multiplicadorAnchoComponente = 0.3f;
		multiplicadorAltoComponente = 0.1f;
		
		ANCHO_PANEL = (short) (BASE_HORIZONTAL * multiplicadorPanel);
		ALTO_PANEL = (short) (BASE_VERTICAL * multiplicadorPanel);
		
		ANCHO_COMPONENTE = (int) (ANCHO_PANEL * multiplicadorAnchoComponente);
		ALTO_COMPONENTE = (int) (ALTO_PANEL * multiplicadorAltoComponente);

		/*
		 * Fondo principal: new Color(245, 238, 216) 
		 * Tarjetas: new Color(232, 213, 163)
		 * Cabecera: new Color(139, 94, 60)
		 * Botón Adoptar: new Color(196, 134, 78)
		 * Texto: new Color(107, 79, 42)
		 * 
		 * Fondo principal: new Color(237, 242, 230)
		 * Tarjetas: new Color(212, 230, 191)
		 * Cabecera: new Color(74, 122, 58)
		 * Botón Adoptar: new Color(125, 184, 90)
		 * Texto: new Color(58, 92, 46)
		 * 
		 * Fondo principal: new Color(242, 239, 233)
		 * Tarjetas: new Color(224, 221, 213)
		 * Cabecera: new Color(90, 86, 80)
		 * Botón Adoptar: new Color(196, 114, 74)
		 * Texto: new Color(61, 58, 53)
		 */
		
		//Marron
		FONDO_DATOS_MARRON = new Color(139, 94, 60);
		FONDO_ANIMALES_MARRON = new Color(232, 213, 163);
		FONDO_PRINCIPAL_MARRON = new Color(245, 238, 216);
		FONDO_BOTON_MARRON = new Color(196, 134, 78);
		
		TEXTO_CLARO_MARRON = new Color(243, 238, 234);
		TEXTO_OSCURO_MARRON = new Color(107, 79, 42);
		
		ColoresMarron = new Color[] {FONDO_DATOS_MARRON, FONDO_ANIMALES_MARRON, FONDO_PRINCIPAL_MARRON, FONDO_BOTON_MARRON,
								TEXTO_CLARO_MARRON, TEXTO_OSCURO_MARRON};
		
		//Verde
		FONDO_DATOS_VERDE = new Color(74, 122, 58);
		FONDO_ANIMALES_VERDE = new Color(212, 230, 191);
		FONDO_PRINCIPAL_VERDE = new Color(237, 242, 230);
		FONDO_BOTON_VERDE = new Color(125, 184, 90);
		
		TEXTO_CLARO_VERDE = new Color(243, 238, 234);
		TEXTO_OSCURO_VERDE = new Color(58, 92, 46);
		
		ColoresVerde = new Color[] {FONDO_DATOS_VERDE, FONDO_ANIMALES_VERDE, FONDO_PRINCIPAL_VERDE, FONDO_BOTON_VERDE,
								TEXTO_CLARO_VERDE, TEXTO_OSCURO_VERDE};

		//Gris
		FONDO_PRINCIPAL_GRIS = new Color(242, 239, 233);
		FONDO_ANIMALES_GRIS = new Color(224, 221, 213);
		FONDO_DATOS_GRIS = new Color(90, 86, 80);
		FONDO_BOTON_GRIS = new Color(90, 86, 80);
		
		TEXTO_CLARO_GRIS = new Color(243, 238, 234);
		TEXTO_OSCURO_GRIS = new Color(61, 58, 53);
		
		ColoresGris = new Color[] {FONDO_DATOS_GRIS, FONDO_ANIMALES_GRIS, FONDO_PRINCIPAL_GRIS, FONDO_BOTON_GRIS,
								TEXTO_CLARO_GRIS, TEXTO_OSCURO_GRIS};
		
		//Azul
		FONDO_DATOS_AZUL = new Color(58, 100, 160);
		FONDO_ANIMALES_AZUL = new Color(191, 213, 237);
		FONDO_PRINCIPAL_AZUL = new Color(230, 238, 248);
		FONDO_BOTON_AZUL = new Color(90, 145, 210);

		TEXTO_CLARO_AZUL = new Color(240, 244, 250);
		TEXTO_OSCURO_AZUL = new Color(30, 65, 115);

		ColoresAzul = new Color[] {FONDO_DATOS_AZUL, FONDO_ANIMALES_AZUL, FONDO_PRINCIPAL_AZUL, FONDO_BOTON_AZUL,
		                        TEXTO_CLARO_AZUL, TEXTO_OSCURO_AZUL};

		//Morado
		FONDO_DATOS_MORADO = new Color(110, 60, 160);
		FONDO_ANIMALES_MORADO = new Color(220, 195, 245);
		FONDO_PRINCIPAL_MORADO = new Color(242, 235, 252);
		FONDO_BOTON_MORADO = new Color(155, 95, 210);

		TEXTO_CLARO_MORADO = new Color(248, 243, 252);
		TEXTO_OSCURO_MORADO = new Color(75, 35, 115);

		ColoresMorado = new Color[] {FONDO_DATOS_MORADO, FONDO_ANIMALES_MORADO, FONDO_PRINCIPAL_MORADO, FONDO_BOTON_MORADO,
		                        TEXTO_CLARO_MORADO, TEXTO_OSCURO_MORADO};
		
		//Rojo
		FONDO_DATOS_ROJO = new Color(160, 40, 35);
		FONDO_ANIMALES_ROJO = new Color(240, 195, 190);
		FONDO_PRINCIPAL_ROJO = new Color(252, 235, 233);
		FONDO_BOTON_ROJO = new Color(205, 70, 60);

		TEXTO_CLARO_ROJO = new Color(252, 243, 242);
		TEXTO_OSCURO_ROJO = new Color(110, 25, 20);

		ColoresRojo = new Color[] {FONDO_DATOS_ROJO, FONDO_ANIMALES_ROJO, FONDO_PRINCIPAL_ROJO, FONDO_BOTON_ROJO,
		                        TEXTO_CLARO_ROJO, TEXTO_OSCURO_ROJO};
		
		//Teal
		FONDO_DATOS_TURQUESA = new Color(32, 115, 105);
		FONDO_ANIMALES_TURQUESA = new Color(185, 225, 220);
		FONDO_PRINCIPAL_TURQUESA = new Color(230, 245, 243);
		FONDO_BOTON_TURQUESA = new Color(55, 165, 150);

		TEXTO_CLARO_TURQUESA = new Color(235, 248, 246);
		TEXTO_OSCURO_TURQUESA = new Color(18, 75, 68);

		ColoresTurquesa = new Color[] {FONDO_DATOS_TURQUESA, FONDO_ANIMALES_TURQUESA, FONDO_PRINCIPAL_TURQUESA, FONDO_BOTON_TURQUESA,
		                        TEXTO_CLARO_TURQUESA, TEXTO_OSCURO_TURQUESA};
		
		FicheroOpciones = "./files/opciones.txt";
		//Colores
		ColoresVisibles = new Color[6];
		ColoresVisibles = cargaColorFichero();
		
		FONDO_DATOS = ColoresVisibles[0];
		FONDO_ANIMALES = ColoresVisibles[1];
		FONDO_PRINCIPAL = ColoresVisibles[2];
		FONDO_BOTON = ColoresVisibles[3];
		
		TEXTO_CLARO = ColoresVisibles[4];
		TEXTO_OSCURO = ColoresVisibles[5];
		
		//Fuentes
		FuenteTexto = new Font(Font.SERIF, Font.BOLD, 25);
		FuenteTextoPC = new Font(Font.SERIF, Font.BOLD, 35);
		FUENTE_BOTONES = new Font(Font.SANS_SERIF, Font.BOLD, 25);
		
		//Crear archivo para guardar los animales adoptados si no estaba creado de antes
		/*
		if(!archivoAnimalesAdoptados.exists()) {
			
			try {
				
				Files.createFile(Paths.get(archivoAnimalesAdoptados.getPath()));
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}*/
	}
	
	private static Color[] cargaColorFichero() {
		
		Color[] colores = ColoresGris;
		
		EnumColores.buscaColoresPorNombre(FicheroOpciones);
		
		try(BufferedReader br = new BufferedReader(new FileReader((new File(FicheroOpciones))))) {
			
			EsPantallaCompleta = Boolean.parseBoolean(br.readLine());
			colores = EnumColores.buscaColoresPorNombre(br.readLine());
			
		} catch (FileNotFoundException e) {
			
			//e.printStackTrace();
			
		} catch (IOException e) {
			
			//e.printStackTrace();
			
		}
		
		return colores;
	}
	
	private CardLayout cartas;
	
	private VistaMenu vMenu;
	private VistaOpcionesCentros vOpcionesCentros;
	private VistaCentroAdopcion vCentroAdopcion;
	private VistaOpciones vOpciones;
	private VistaSalir vSalir;
	
	public Vista() {
		
		cartas = new CardLayout();
		this.setLayout(cartas);
		
		this.add(vMenu = new VistaMenu(), "menu");
		this.add(vOpcionesCentros = new VistaOpcionesCentros(), "opcionesCentros");
		this.add(vOpciones = new VistaOpciones(), "opciones");
		this.add(vSalir = new VistaSalir(), "salir");
	}
	
	public void añadeVistaCentroAdopcion(CentroAdopcion centro) {
		
		this.add(vCentroAdopcion = new VistaCentroAdopcion(centro), "centroAdopcion");
	}
	
	public void muestraPrimerPanel() {
		
		cartas.first(this);
	}
	
	public void muestraSiguientePanel() {
		
		cartas.next(this);
	}

	public void muestraPanelOpciones() {
		
		cartas.show(this, "opciones");
	}
	
	public void muestraPanelOpcionesCentros() {
		
		cartas.show(this, "opcionesCentros");
	}
	
	public void muestraPanelCentro() {
		
		cartas.show(this, "centroAdopcion");
	}
	
	public void muestraPanelSalir() {
		
		cartas.show(this, "salir");
	}
	
	public void muestraAnteriorPanel() {
		
		cartas.previous(this);
	}

	public VistaMenu getvMenu() {
		return vMenu;
	}

	public VistaOpcionesCentros getvOpcionesCentros() {
		return vOpcionesCentros;
	}
	
	public VistaCentroAdopcion getvCentroAdopcion() {
		return vCentroAdopcion;
	}

	public VistaOpciones getvOpciones() {
		return vOpciones;
	}

	public VistaSalir getvSalir() {
		return vSalir;
	}
}
