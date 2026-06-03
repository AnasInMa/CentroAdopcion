package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import modelo.Animal;
import modelo.Persona;
import utilidades.UtilidadesFicherosLista;
import utilidades.UtilidadesVariables;

public class DialogoTablaAnimales extends JDialog implements ActionListener{

	private static final long serialVersionUID = -861861385644810250L;
	
	private JTable tablaAnimales;
	
	private Animal animal;
	
	private JButton bConfirmar, bCancelar;
	
	public DialogoTablaAnimales(JDialog dialogoPadre, Persona persona, String[] columnas) {
		
		super(dialogoPadre, "Animales adoptados por " + persona.getNombre(), true);

		try {
			
			tablaAnimales = new JTable(UtilidadesFicherosLista.leeFichero(persona, UtilidadesVariables.archivoAnimalesAdoptados), columnas);

			tablaAnimales.setForeground(UtilidadesVariables.TEXTO_OSCURO);
			tablaAnimales.setBackground(UtilidadesVariables.FONDO_ANIMALES);

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
	
	private void control() {
		
		this.bConfirmar.addActionListener(this);
		this.bCancelar.addActionListener(this);
	}
	
	private JPanel panelPrincipal() {
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		JScrollPane sp = new JScrollPane(tablaAnimales);
		sp.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
		panelPrincipal.add(sp, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		
		bConfirmar = new JButton("Confirmar");
		bConfirmar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bConfirmar.setBackground(UtilidadesVariables.FONDO_BOTON);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bCancelar.setBackground(UtilidadesVariables.FONDO_BOTON);
		
		panel.add(bConfirmar);
		panel.add(bCancelar);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == this.bConfirmar) {
			
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
					
					UtilidadesFicherosLista.quitarAnimalDelFichero(animal, UtilidadesVariables.archivoAnimalesAdoptados);
					
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(this, "El animal no ha podido ser creado", "ERROR", JOptionPane.ERROR_MESSAGE);
				} 
			}
			
			this.dispose();
			
		} else { // cancelar

			this.dispose();
		}
		
	}

	public Animal getAnimal() {
		return animal;
	}

}
