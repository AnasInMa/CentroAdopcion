package vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class DialogoAdoptar extends JDialog implements ActionListener{

	private static final long serialVersionUID = 7518864593720141813L;

	private JPanel panelAnimal;
	private JTextField tfNombre, tfDni, tfApellido1, tfApellido2, tfEdad;
	private JButton bConfirmar, bCancelar, bElegirPersona;
	
	public DialogoAdoptar(JFrame ventanaPadre, JPanel panelAnimal) {
		
		super(ventanaPadre, ventanaPadre.getTitle(), true);	//el true es para que no se pueda cambiar de ventana hasta que se cierre
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		JPanel panelAnimalPersona = new JPanel(new GridLayout(1, 2, 10, 10));
		panelAnimalPersona.setBorder(VistaCentroAdopcion.BORDEVACIO);
		panelAnimalPersona.setBackground(Vista.FONDO_PRINCIPAL);
		panelAnimalPersona.add(this.panelAnimal = panelAnimal);
		panelAnimalPersona.add(panelPersona());
		
		panelPrincipal.add(panelAnimalPersona);
		panelPrincipal.add(panelBotones());
		
		this.add(panelPrincipal);

		control();
		
		this.pack();
		this.setLocationRelativeTo(ventanaPadre);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void buscaIdAnimal(JPanel panelAnimal) {
		
		Component[] componentes = panelAnimal.getComponents();
		JLabel l;
		boolean encontrado = false;
		for (int i = 0; i < componentes.length && !encontrado; i++) {

			if (componentes[i] instanceof JLabel) {
				
				l = (JLabel) componentes[i];

				try {
					
					Integer.parseInt(l.getText());
					
					encontrado = true;
					
				} catch(NumberFormatException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	private void control () {
		
		this.bConfirmar.addActionListener(this);
		this.bCancelar.addActionListener(this);
		this.bElegirPersona.addActionListener(this);
	}
	
	private JPanel panelPersona() {
		/*
		JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelNombre.setBackground(Vista.FONDO_PRINCIPAL);
		panelNombre.add(new JLabel("Nombre: "));
		panelNombre.add(tfNombre = new JTextField(10));
		
		JPanel panelDni = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelDni.setBackground(Vista.FONDO_PRINCIPAL);
		panelDni.add(new JLabel("DNI: "));
		panelDni.add(tfDni = new JTextField(10));
		
		JPanel panelApellido1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelApellido1.setBackground(Vista.FONDO_PRINCIPAL);
		panelApellido1.add(new JLabel("Primer Apellido: "));
		panelApellido1.add(tfApellido1 = new JTextField(10));
		
		JPanel panelApellido2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelApellido2.setBackground(Vista.FONDO_PRINCIPAL);
		panelApellido2.add(new JLabel("Segundo Apellido: "));
		panelApellido2.add(tfApellido2 = new JTextField(10));
		
		JPanel panelEdad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelEdad.setBackground(Vista.FONDO_PRINCIPAL);
		panelEdad.add(new JLabel("Edad: "));
		panelEdad.add(tfEdad = new JTextField(10));
		
		modificaPaneles(new JPanel[] {panelNombre, panelDni, panelApellido1, panelApellido2, panelEdad});;
		 */

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(VistaCentroAdopcion.BordeLinea);
		panel.setBackground(Vista.FONDO_ANIMALES);
		
//		panel.add(panelNombre);
//		panel.add(panelDni);
//		panel.add(panelApellido1);
//		panel.add(panelApellido2);
//		panel.add(panelEdad);
//		panel.add(Box.createVerticalStrut(150));

		tfNombre = new JTextField(10);
		tfNombre.setBorder(new TitledBorder("Nombre"));
		tfNombre.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfNombre);
		
		tfApellido1 = new JTextField(10);
		tfApellido1.setBorder(new TitledBorder("Primer Apellido"));
		tfApellido1.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfApellido1);
		
		tfApellido2 = new JTextField(10);
		tfApellido2.setBorder(new TitledBorder("Segundo Apellido"));
		tfApellido2.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfApellido2);
		
		tfDni = new JTextField(10);
		tfDni.setBorder(new TitledBorder("DNI"));
		tfDni.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfDni);
		
		tfEdad = new JTextField(10);
		tfEdad.setBorder(new TitledBorder("Edad"));
		tfEdad.setBackground(Vista.FONDO_ANIMALES);
		panel.add(tfEdad);

		panel.add(Box.createVerticalStrut(120));
		
		bElegirPersona = new JButton("Elegir Persona");
		bElegirPersona.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		panel.add(bElegirPersona);
		
//		panel.add(new JLabel(" "));
//		panel.add(new JLabel(" "));
//		panel.add(new JLabel(" "));
//		panel.add(new JLabel(" "));
//		panel.add(new JLabel(" "));
//		panel.add(new JLabel(" "));
//		panel.add(new JLabel(" "));
//		panel.add(new JLabel(" "));

		return panel;
	}
		
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Vista.FONDO_PRINCIPAL);
		
		bConfirmar = new JButton("Confirmar");
		bCancelar = new JButton("Cancelar");
		
		modificaBotones();
		
		panel.add(bConfirmar);
		panel.add(bCancelar);
		
		return panel;
	}
	
	private void modificaBotones() {
		
		JButton[] botones = {this.bConfirmar, this.bCancelar, this.bElegirPersona};
		
		for (JButton boton : botones) {
			
			boton.setForeground(Vista.TEXTO_CLARO);
			boton.setBackground(Vista.FONDO_BOTON);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == bConfirmar) {
			
			//System.out.println("confirmar");
			
		} else if (e.getSource()	 == bCancelar) {
			
			this.dispose();
			
		} else { 	//Elegir persona
			
			
			
		}
		
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfDni() {
		return tfDni;
	}

	public JTextField getTfApellido1() {
		return tfApellido1;
	}

	public JTextField getTfApellido2() {
		return tfApellido2;
	}

	public JTextField getTfEdad() {
		return tfEdad;
	}

	public JButton getbConfirmar() {
		return bConfirmar;
	}

	public JButton getbCancelar() {
		return bCancelar;
	}
}
