package controlador;

import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.*;

import modelo.Animal;
import modelo.DAOAnimales;
import modelo.DAOPersonas;
import vista.*;

public class ControladorCentroAdopcion implements MouseListener, ActionListener {

	private JFrame ventanaPadre;
	
	private Vista vista;
	private VistaCentroAdopcion vCentroAdopcion;
	private VistaAnimales vAnimales;
	
	private DAOAnimales daoAnimales;
	private DAOPersonas daoPersonas;

	public ControladorCentroAdopcion(Vista v) {

		vista = v;

		vCentroAdopcion = vista.getvCentroAdopcion();
		vCentroAdopcion.control(this);
		
		vAnimales = vCentroAdopcion.getVistaAnimales();
		
		daoAnimales = vAnimales.getDao();
		
		try {
			daoPersonas = new DAOPersonas();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ventanaPadre = (JFrame) SwingUtilities.getWindowAncestor(vista);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == vCentroAdopcion.getbAdoptar()) {

			//System.out.println("adoptar");
			
			try {
				
				//System.out.println("adoptar");
				DialogoAdoptar d = new DialogoAdoptar(ventanaPadre, vAnimales.panelAnimalSeleccionado(), daoAnimales, daoPersonas);
				//new ControladorDialogoAdoptar(d);
				
				if(d.getAnimalAdoptado() != null) {
					
					//Animal animal = daoAnimales.adoptaAnimal(d.getAnimalAdoptado().getIDAnimal());
					guardaAnimalEnFichero(daoAnimales.adoptaAnimal(d.getAnimalAdoptado().getIDAnimal(), d.getIdPersona()));
					
					this.vCentroAdopcion.getCentroAdopcion().setAnimalesAlojados(daoAnimales.getAnimalesCentro(this.vCentroAdopcion.getCentroAdopcion()));
					
					JOptionPane.showMessageDialog(vAnimales, "Gracias " + d.getNombrePersona() + " por adoptar a " +  d.getAnimalAdoptado().getNombre() + "!", "Adoptado en " + this.vCentroAdopcion.getCentroAdopcion().getNombre(), JOptionPane.INFORMATION_MESSAGE);
					
					this.vista.muestraPanelOpcionesCentros();
				}
				
				
			} catch (NullPointerException error) {
				
				JOptionPane.showMessageDialog(vCentroAdopcion, "Debe de seleccionar un animal para poder adoptarlo" , "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (e.getSource() == vCentroAdopcion.getbDarEnAdopcion()) {
			
			new DialogoDarEnAdopcion(ventanaPadre, vCentroAdopcion.getCentroAdopcion(), daoPersonas, daoAnimales);

			this.vista.muestraPanelOpcionesCentros();
			
		} else if (e.getSource() == vCentroAdopcion.getbPrimero()) {

			//System.out.println("primero");
			
			/*try {

				dao.getCuatroPrimeros();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.primeraFila();

		} else if (e.getSource() == vCentroAdopcion.getbAnterior()) {

			/*try {

				dao.getCuatroAnteriores();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.anteriorFila();

		} else if (e.getSource() == vCentroAdopcion.getbSiguiente()) {

			/*try {

				dao.getCuatroSiguientes();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.siguienteFila();

		} else {

			// System.out.println("boton ultimo");
			/*try {

				dao.getCuatroUltimos();

			} catch (Exception e1) {

				e1.printStackTrace();
			}*/
			
			vAnimales.ultimaFila();
		}
		
	}
	
	/**
	 * Metodo que guardara el animal recien adoptado (y eliminado de la base de datos)
	 * en un fichero, para despues poder ver todos los animales que ha adoptado una persona
	 * 
	 * @param animal
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	private void guardaAnimalEnFichero(Animal animal) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		LinkedList<Animal> listaAnimales = new LinkedList<>();
		
		//Por si el fichero no esta creado
		if(!(new File("./files/AnimalesAdoptados.dat").exists())) {
			
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./files/AnimalesAdoptados.dat")))) {
				
				oos.writeObject(listaAnimales);
			}			
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./files/AnimalesAdoptados.dat")))) {
			
			listaAnimales = (LinkedList<Animal>) ois.readObject();
		}
		
		listaAnimales.add(animal);
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./files/AnimalesAdoptados.dat")))) {
			
			oos.writeObject(listaAnimales);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vCentroAdopcion.getAtras()) {

			// System.out.println("atras");

			vista.muestraPanelOpcionesCentros();
			vAnimales.primeraFila();	//si no pusiese esto el contador no se resetearia, y entonces al salir del centro y el contador es 3 por ejemplo, al volver al entrar otra vez al mismo centro se muestra el primer panel de los animales, pero el contador sigue siendo 3, entonces si quisieras navegar por los botones de abajo no iria como se espera que fuese
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
