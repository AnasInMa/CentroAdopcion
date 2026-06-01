package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import modelo.Animal;
import modelo.DAOPersonas;

public class DialogoTablaPersonasConAnimales extends JDialog implements ActionListener, MouseListener{

	private static final long serialVersionUID = 4102950105202282415L;

	private JTable tablaPersonas;
	private JButton bConfirmar, bElegirAnimal, bCancelar;

	private DAOPersonas daoPersonas;
	
	private Animal animalSeleccionado;
	
	private boolean haSeleccionadoAnimal;
	private int idPersonaSeleccionada;
	
	private DialogoTablaAnimales d;

	public DialogoTablaPersonasConAnimales(JDialog dialogoPadre, String[][] filas, String[] columnas, DAOPersonas daoP) {

		super(dialogoPadre, "Elejir Persona", true);

		tablaPersonas = new JTable(filas, columnas);
		tablaPersonas.setForeground(Vista.TEXTO_OSCURO);
		tablaPersonas.setBackground(Vista.FONDO_ANIMALES);
		
		//celdasNoEditables(filas);
		daoPersonas = daoP;
		
		haSeleccionadoAnimal = false;
		
		tablaPersonas.setPreferredScrollableViewportSize(new Dimension(520, 80));

		this.add(panelPrincipal());
		
		this.control();

		this.pack();
		this.setLocationRelativeTo(dialogoPadre);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void control() {
		
		this.tablaPersonas.addMouseListener(this);
		
		this.bConfirmar.addActionListener(this);
		this.bElegirAnimal.addActionListener(this);
		this.bCancelar.addActionListener(this);
	}
	
	/*private void celdasNoEditables(String[][] filas) {
		
		for (int fila = 0; fila < filas.length; fila++) {
			
			for (int columna = 0; columna < filas[fila].length; columna++) {
				
				tablaPersonas.isCellEditable(fila, columna);
			}
		}
		
	}*/

	private JPanel panelPrincipal() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		bConfirmar = new JButton("Confirmar");
		bConfirmar.setForeground(Vista.TEXTO_CLARO);
		bConfirmar.setBackground(Vista.FONDO_BOTON);
		
		bElegirAnimal = new JButton("Elegir Animal");
		bElegirAnimal.setEnabled(false);
		bElegirAnimal.setForeground(Vista.TEXTO_CLARO);
		bElegirAnimal.setBackground(Vista.FONDO_BOTON);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setForeground(Vista.TEXTO_CLARO);
		bCancelar.setBackground(Vista.FONDO_BOTON);
				
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Vista.FONDO_PRINCIPAL);
		panelBotones.add(bConfirmar);
		panelBotones.add(bElegirAnimal);
		panelBotones.add(bCancelar);

		JScrollPane sp = new JScrollPane(tablaPersonas);
		sp.setBackground(Vista.FONDO_PRINCIPAL);
		
		panelPrincipal.add(sp, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

		return panelPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.bConfirmar) {
			
			if(this.tablaPersonas.getSelectedRowCount() <= 0) {
				
				JOptionPane.showMessageDialog(this, "No se ha seleccionado a ninguna persona", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} else if(this.tablaPersonas.getSelectedRowCount() > 1) {
				
				JOptionPane.showMessageDialog(this, "Solo se puede seleccionar a una persona", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			} else {
				
				//System.out.println(this.tablaPersonas.getColumnName(0));
				//System.out.println(this.tablaPersonas.getValueAt(this.tablaPersonas.getSelectedRow(), 0).toString());
				idPersonaSeleccionada = Integer.parseInt(this.tablaPersonas.getValueAt(this.tablaPersonas.getSelectedRow(), 0).toString());
				
				if (haSeleccionadoAnimal) {

					this.animalSeleccionado = d.getAnimal();
				}
			}

			//System.out.println("confirmar");
			//System.out.println(this.tablaPersonas.getSelectedRow());
			//System.out.println(Arrays.toString(this.tablaPersonas.getSelectedRows()));
			//System.out.println();
			
			this.dispose();
			
		} else if(e.getSource() == this.bElegirAnimal) {
			
			//System.out.println("elegir animal");
			try {
				
				if(this.tablaPersonas.getSelectedRowCount() <= 0) {
					
					JOptionPane.showMessageDialog(this, "No se ha seleccionado a ninguna persona", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else if(this.tablaPersonas.getSelectedRowCount() > 1) {
					
					JOptionPane.showMessageDialog(this, "Solo se puede seleccionar a una persona", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					//System.out.println(this.tablaPersonas.getColumnName(0));
					//System.out.println(this.tablaPersonas.getValueAt(this.tablaPersonas.getSelectedRow(), 0).toString());
					idPersonaSeleccionada = Integer.parseInt(this.tablaPersonas.getValueAt(this.tablaPersonas.getSelectedRow(), 0).toString());
					
					d = new DialogoTablaAnimales(this, daoPersonas.buscaPersona(this.idPersonaSeleccionada),
													new String[] {"idAnimal", "idCentro", "idPersona", "Nombre", "Tipo", "Raza", "Edad", "Fecha Alojamiento", "Fecha Adopcion"});
				
					//Animal animal = daoAnimales.buscaAnimal(d.getIdAnimalSeleccionado());
					animalSeleccionado = d.getAnimal();
					
					this.haSeleccionadoAnimal = true;
				}
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			
			this.dispose();
		}
		
	}
	
	public Animal getAnimalSeleccionado() {
		return animalSeleccionado;
	}

	public int getIdPersonaSeleccionada() {
		
		return this.idPersonaSeleccionada;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == this.tablaPersonas) {
			
			//System.out.println("tabla");
			
			if(this.tablaPersonas.getSelectedRowCount() == 1) {
				
				//System.out.println("sisisi");
				this.bElegirAnimal.setEnabled(true);
				
			} else {
				
				//System.out.println("nonoono");
				this.bElegirAnimal.setEnabled(false);
			}
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

	public boolean isHaSeleccionadoAnimal() {
		return haSeleccionadoAnimal;
	}
}
