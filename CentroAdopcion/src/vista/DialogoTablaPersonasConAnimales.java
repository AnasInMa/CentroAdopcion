package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import modelo.Animal;
import modelo.DAOPersonas;
import utilidades.UtilidadesVariables;

public class DialogoTablaPersonasConAnimales extends JDialog implements ActionListener, MouseListener{

	private static final long serialVersionUID = 4102950105202282415L;

	private JTable tablaPersonas;
	private JButton bConfirmar, bElegirAnimal, bCancelar;

	private DAOPersonas daoPersonas;
	
	private Animal animalSeleccionado;
	
	private boolean haSeleccionadoAnimal, haPulsadoBotonConfirmar;
	private int idPersonaSeleccionada;
	
	private DialogoTablaAnimales d;

	public DialogoTablaPersonasConAnimales(JDialog dialogoPadre, String[][] filas, String[] columnas, DAOPersonas daoP) {

		super(dialogoPadre, "Elegir Persona", true);

		tablaPersonas = new JTable(filas, columnas);
		tablaPersonas.setForeground(UtilidadesVariables.TEXTO_OSCURO);
		tablaPersonas.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		
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

	private JPanel panelPrincipal() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		bConfirmar = new JButton("Confirmar");
		bConfirmar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bConfirmar.setBackground(UtilidadesVariables.FONDO_BOTON);
		
		bElegirAnimal = new JButton("Elegir Animal");
		bElegirAnimal.setEnabled(false);
		bElegirAnimal.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bElegirAnimal.setBackground(UtilidadesVariables.FONDO_BOTON);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bCancelar.setBackground(UtilidadesVariables.FONDO_BOTON);
				
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		panelBotones.add(bConfirmar);
		panelBotones.add(bElegirAnimal);
		panelBotones.add(bCancelar);

		JScrollPane sp = new JScrollPane(tablaPersonas);
		sp.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
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
				
				idPersonaSeleccionada = Integer.parseInt(this.tablaPersonas.getValueAt(this.tablaPersonas.getSelectedRow(), 0).toString());
				
				if (haSeleccionadoAnimal) {

					this.animalSeleccionado = d.getAnimal();
				}
				
				haPulsadoBotonConfirmar = true;
			}

			this.dispose();
			
		} else if(e.getSource() == this.bElegirAnimal) {
			
			try {
				
				//nunca va a poder darse el caso porque ya esta controlado en el mouseListener de la tabla
				/*if(this.tablaPersonas.getSelectedRowCount() <= 0) {
					
					JOptionPane.showMessageDialog(this, "No se ha seleccionado a ninguna persona", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else*/
				if(this.tablaPersonas.getSelectedRowCount() > 1) {
					
					JOptionPane.showMessageDialog(this, "Solo se puede seleccionar a una persona", "ERROR", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					idPersonaSeleccionada = Integer.parseInt(this.tablaPersonas.getValueAt(this.tablaPersonas.getSelectedRow(), 0).toString());
					
					d = new DialogoTablaAnimales(this, daoPersonas.buscaPersona(this.idPersonaSeleccionada),
													new String[] {"idAnimal", "idCentro", "idPersona", "Nombre", "Tipo", "Raza", "Descripcion", "Edad", "Fecha Alojamiento", "Fecha Adopcion"});

					animalSeleccionado = d.getAnimal();
					
					this.haSeleccionadoAnimal = (animalSeleccionado == null)? false : true;
				}
				
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		} else {
			
			this.haSeleccionadoAnimal = false;
			
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
			
			if(this.tablaPersonas.getSelectedRowCount() == 1) {
				
				this.bElegirAnimal.setEnabled(true);
				
			} else {
				
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

	public boolean isHaPulsadoBotonConfirmar() {
		return haPulsadoBotonConfirmar;
	}
}
