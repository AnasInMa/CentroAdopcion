package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

import javax.swing.*;

import fechas.LibFechas8;
import modelo.Animal;
import modelo.Persona;

public class DialogoTablaAnimales extends JDialog implements ActionListener{

	private static final long serialVersionUID = -861861385644810250L;
	
	private JTable tablaAnimales;
	
	private Persona persona;
	private Animal animal;
	
	private JButton bConfirmar, bCancelar;
	
	public DialogoTablaAnimales(JDialog dialogoPadre, Persona persona, String[] columnas) {
		
		super(dialogoPadre, "Animales adoptados por " + persona.getNombre(), true);

		try {
			
			this.persona = persona;

			tablaAnimales = new JTable(leeFichero(), columnas);

			tablaAnimales.setForeground(Vista.TEXTO_OSCURO);
			tablaAnimales.setBackground(Vista.FONDO_ANIMALES);

			// celdasNoEditables(filas);

			tablaAnimales.setPreferredScrollableViewportSize(new Dimension(520, 100));

			this.add(panelPrincipal());

			this.control();

			this.pack();
			this.setLocationRelativeTo(dialogoPadre);
			// this.setResizable(false);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			
		} catch (Exception e) {

			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private String[][] leeFichero() throws Exception {
		
		LinkedList<Animal> listaAnimales = new LinkedList<>();
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("./files/AnimalesAdoptados.dat")))) {
			
			//Animal animal;
			
			listaAnimales = (LinkedList<Animal>) ois.readObject();
			
			//System.out.println(listaAnimales);
			
//			while(true) {
//				
//				animal = (Animal) ois.readObject();
//				
//				//System.out.println(animal.getIDPersona());
//				
//				if(animal.getIDPersona() == this.persona.getIDPersona()) {	//filtro por el id de la persona para que solo me aparezca los animales adoptados de la persona que se ha seleccionado
//					
//					listaAnimales.add(animal);
//				}
//				
//			}
			
		} catch(FileNotFoundException e) {
			
			throw new FileNotFoundException("No hay ningun animal adoptado");
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}


		int cont = 0;
		
		//Bucle para saber el tamaño del jtable
		for (Animal animal : listaAnimales) {
			
			if(animal.getIDPersona() == this.persona.getIDPersona()) {
				
				cont++;
			}
			
		}
		
		
		String[][] animales = new String[cont][10];
		
		cont = 0;
		
		for (Animal animal : listaAnimales) {
			
			if(animal.getIDPersona() == this.persona.getIDPersona()) {
				
				animales[cont][0] = animal.getIDAnimal() + "";
				animales[cont][1] = animal.getIDCentro() + "";
				animales[cont][2] = animal.getIDPersona() + "";
				animales[cont][3] = animal.getNombre();
				animales[cont][4] = animal.getTipo();
				animales[cont][5] = animal.getRaza();
				animales[cont][6] = animal.getDescripcion();
				animales[cont][7] = animal.getEdad() + "";
				animales[cont][8] = LibFechas8.getFechaShort(animal.getFechaAlojamiento());
				animales[cont][9] = LibFechas8.getFechaShort(animal.getFechaAdopcion());
				
				cont++;				
			}
			
		}
		
		if(cont == 0) throw new Exception(this.persona.getNombre() + " no tiene ningun animal adoptado disponible");
		
		return animales;
	}
	
	private void quitarAnimalDelFichero(Animal animal) {
		
		LinkedList<Animal> listaAnimales = new LinkedList<>();
		//System.out.println(listaAnimales);
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./files/AnimalesAdoptados.dat"))) {
	        
			listaAnimales = (LinkedList<Animal>) ois.readObject();

	    } catch (ClassNotFoundException | IOException e) {
	    	
	        e.printStackTrace();
	    }

		for (Animal animalLista : listaAnimales) {
			
			if(animalLista.equals(animal)) {
				
				listaAnimales.remove(animalLista);
			}
		}

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("./files/AnimalesAdoptados.dat")))) {
	    	
	        oos.writeObject(listaAnimales);
	        
	    } catch (IOException e) {
	        
	        e.printStackTrace();
	    }
	    
	    //System.out.println(listaAnimales);
	}
	
	private void control() {
		
		this.bConfirmar.addActionListener(this);
		this.bCancelar.addActionListener(this);
	}
	
	private JPanel panelPrincipal() {
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		JScrollPane sp = new JScrollPane(tablaAnimales);
		sp.setBackground(Vista.FONDO_PRINCIPAL);
		
		panelPrincipal.add(sp, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		
		bConfirmar = new JButton("Confirmar");
		bConfirmar.setForeground(Vista.TEXTO_CLARO);
		bConfirmar.setBackground(Vista.FONDO_BOTON);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setForeground(Vista.TEXTO_CLARO);
		bCancelar.setBackground(Vista.FONDO_BOTON);
		
		panel.add(bConfirmar);
		panel.add(bCancelar);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.bConfirmar) {
			
			//System.out.println("confirmar");
			if(this.tablaAnimales.getSelectedRowCount() < 1) {
				
				JOptionPane.showMessageDialog(this, "Tiene que seleccionar a un animal", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} else if(this.tablaAnimales.getSelectedRowCount() > 1) {
				
				JOptionPane.showMessageDialog(this, "Solo se puede seleccionar a un animal", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} else {
				
				try {
					
					this.animal = new Animal(
							Integer.parseInt(this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 0).toString()),
							this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 3).toString(),
							this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 4).toString(),
							this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 5).toString(),
							this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 6).toString(),
							Byte.parseByte(this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 7).toString()),
							this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 8).toString(),
							Integer.parseInt(this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 1).toString()), 
							Integer.parseInt(this.tablaAnimales.getValueAt(this.tablaAnimales.getSelectedRow(), 2).toString()));
					
					this.quitarAnimalDelFichero(animal);
					
					//System.out.println(animal);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			
			this.dispose();
			
		} else {
			
			//System.out.println("cancelar");
			//animal = null;
			
			this.dispose();
		}
		
	}

	public Animal getAnimal() {
		return animal;
	}

}
