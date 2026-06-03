package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import utilidades.UtilidadesVariables;

public class DialogoTablaPersonas extends JDialog implements ActionListener{

	private static final long serialVersionUID = 4102950105202282415L;

	private JTable tablaPersonas;
	private JButton bConfirmar, bCancelar;
	private int idPersonaSeleccionada;

	public DialogoTablaPersonas(JDialog dialogoPadre, String[][] filas, String[] columnas) {

		super(dialogoPadre, "Elegir Persona", true);

		tablaPersonas = new JTable(filas, columnas);
		tablaPersonas.setForeground(UtilidadesVariables.TEXTO_OSCURO);
		tablaPersonas.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		
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
		
		this.bConfirmar.addActionListener(this);
		this.bCancelar.addActionListener(this);
	}

	private JPanel panelPrincipal() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		bConfirmar = new JButton("Confirmar");
		bConfirmar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bConfirmar.setBackground(UtilidadesVariables.FONDO_BOTON);
		
		bCancelar = new JButton("Cancelar");
		bCancelar.setForeground(UtilidadesVariables.TEXTO_CLARO);
		bCancelar.setBackground(UtilidadesVariables.FONDO_BOTON);
				
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		panelBotones.add(bConfirmar);
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
			}
			
			this.dispose();
			
		} else {
			
			this.dispose();
		}
		
	}
	
	public int getIdPersonaSeleccionada() {
		
		return this.idPersonaSeleccionada;
	}
}
