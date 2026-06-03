package vista;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import modelo.Animal;
import modelo.DAOAnimales;
import modelo.DAOPersonas;
import modelo.Persona;
import utilidades.UtilidadesAnimales;
import utilidades.UtilidadesVariables;

public class DialogoAdoptar extends JDialog implements ActionListener{

	private static final long serialVersionUID = 7518864593720141813L;

	private JPanel panelAnimal;
	private JTextField tfNombre, tfDni, tfApellido1, tfApellido2, tfEdad;
	private JButton bConfirmar, bCancelar, bElegirPersona;
	
	private DAOAnimales daoAnimales;
	private DAOPersonas daoPersonas;
	
	private DialogoTablaPersonas dialogoP;
	
	private Animal animalAdoptado;
	private Persona persona, personaSeleccionada;
	
	private int idPersona;
	private String nombrePersona;
	
	private boolean haElegidoPersona;
	
	public DialogoAdoptar(JFrame ventanaPadre, JPanel panelAnimal, DAOAnimales daoA, DAOPersonas daoP) throws ClassNotFoundException, SQLException {
		
		super(ventanaPadre, "Adoptar Animal", true);		//el true es para que no se pueda cambiar de ventana hasta que se cierre
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		JPanel panelAnimalPersona = new JPanel(new GridLayout(1, 2, 10, 10));
		panelAnimalPersona.setBorder(UtilidadesVariables.BORDEVACIO);
		panelAnimalPersona.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		panelAnimalPersona.add(this.panelAnimal = panelAnimal);
		panelAnimalPersona.add(panelPersona());
		
		panelPrincipal.add(panelAnimalPersona);
		panelPrincipal.add(panelBotones());
		
		this.add(panelPrincipal);
		
		haElegidoPersona = false;

		control();
		daoAnimales = daoA;
		daoPersonas = daoP;
		
		this.pack();
		this.setLocationRelativeTo(ventanaPadre);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void control () {
		
		this.bConfirmar.addActionListener(this);
		this.bCancelar.addActionListener(this);
		this.bElegirPersona.addActionListener(this);
	}
	
	private JPanel panelPersona() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(UtilidadesVariables.BordeLinea);
		panel.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		
		tfNombre = new JTextField(10);
		tfNombre.setBorder(new TitledBorder("Nombre"));
		tfNombre.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfNombre);
		
		tfApellido1 = new JTextField(10);
		tfApellido1.setBorder(new TitledBorder("Primer Apellido"));
		tfApellido1.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfApellido1);
		
		tfApellido2 = new JTextField(10);
		tfApellido2.setBorder(new TitledBorder("Segundo Apellido"));
		tfApellido2.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfApellido2);
		
		tfDni = new JTextField(10);
		tfDni.setBorder(new TitledBorder("DNI"));
		tfDni.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfDni);
		
		tfEdad = new JTextField(10);
		tfEdad.setBorder(new TitledBorder("Edad"));
		tfEdad.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfEdad);

		panel.add(Box.createVerticalStrut(120));
		
		bElegirPersona = new JButton("Elegir Persona");
		bElegirPersona.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		panel.add(bElegirPersona);
		
		return panel;
	}
		
	private JPanel panelBotones() {
		
		JPanel panel = new JPanel();
		panel.setBackground(UtilidadesVariables.FONDO_PRINCIPAL);
		
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
			
			boton.setForeground(UtilidadesVariables.TEXTO_CLARO);
			boton.setBackground(UtilidadesVariables.FONDO_BOTON);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == bConfirmar) {
			
			try {
				
				animalAdoptado = daoAnimales.buscaAnimal(UtilidadesAnimales.buscaIdAnimal(this.panelAnimal));
				
				if(!haElegidoPersona) {
										
					verificaPersona();
					
					if(daoPersonas.buscaPersonaPorDni(this.tfDni.getText()) == null) {	//no existe la persona
						
															// el id da igual porque va a coger el ultimo + 1
						daoPersonas.insertaPersonaSinId(new Persona(0, new TreeSet<Animal>(Arrays.asList(new Animal[] {animalAdoptado})), this.tfNombre.getText(), this.tfDni.getText(), this.tfApellido1.getText(), this.tfApellido2.getText(), (byte) Byte.parseByte(this.tfEdad.getText())));
						
						persona = daoPersonas.getUltimo(); //si hago esto puedo obtener el id que se le ha asignado
						persona.adoptaAnimal(animalAdoptado);
						
						this.idPersona = persona.getIDPersona();
						
					} else {
						
						throw new Exception("La persona introducida ya existe");
					}
					
				} else {
					
					 if(personaSeleccionada.getEdad() < 18) {
						 
			                throw new Exception("Lo siento. No se puede adoptar un animal siendo menor de edad");
			            }
					
					personaSeleccionada.adoptaAnimal(animalAdoptado);
					
					this.idPersona = personaSeleccionada.getIDPersona();
				}
				
				daoAnimales.modificaAnimal(animalAdoptado);
				
				nombrePersona = this.tfNombre.getText();
				
				this.dispose();	//Lo pongo dentro del try para que solo se cierre una vez esten todos los campos correctos
				
			} catch (Exception error) {
				
				animalAdoptado = null;
				
				JOptionPane.showMessageDialog(this, error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
			
		} else if (e.getSource()	 == bCancelar) {
			
			animalAdoptado = null;	//Lo tengo que poner aqui a nulo porque si no despues de darle a confirmar y que te de un error por los campos, le das al boton cancelar, se adopta al animal igualmente
			
			this.dispose();
			
		} else { 	//Elegir persona
			
			try {
				
				dialogoP = new DialogoTablaPersonas(this, daoPersonas.getAllMatriz(),
								new String[] {"IdPersona", "Nombre", "DNI", "Primer Apellido", "Segundo Apellido", "Edad"});
				
				personaSeleccionada = daoPersonas.buscaPersona(dialogoP.getIdPersonaSeleccionada());
				
				try {
					
					añadePersonaACampos();
					
					haElegidoPersona = true;
					
				} catch(NullPointerException error) {
					
					JOptionPane.showMessageDialog(this, "No se ha seleccionado a ninguna persona");
				}
				
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
		}
		
	}
	
	private void verificaPersona() throws Exception {
		
		if(this.tfNombre.getText().isBlank()) {
			
			throw new Exception("El campo Nombre no puede estar vacio");
			
		} else if(this.tfApellido1.getText().isBlank()) {
			
			throw new Exception("El campo Primer Apelllido no puede estar vacio");
			
		} else if(this.tfApellido2.getText().isBlank()) {
			
			throw new Exception("El campo Segundo Apelllido no puede estar vacio");
			
		} else if(this.tfDni.getText().isBlank()) {
			
			throw new Exception("El campo DNI no puede estar vacio");
			
		} else if(this.tfEdad.getText().isBlank()) {
			
			throw new Exception("El campo Edad no puede estar vacio");
			
		} else if(Integer.parseInt(this.tfEdad.getText()) < 18) {
			
			throw new Exception("Lo siento. No se puede adoptar a un animal siendo menor de edad");
			
		} else if(Integer.parseInt(this.tfEdad.getText()) > Byte.MAX_VALUE) {
			
			throw new Exception("Edad no válida");
		}
		
		Persona.validaDni(this.tfDni.getText());
	}
	
	private void añadePersonaACampos() {
		
		this.tfNombre.setText(personaSeleccionada.getNombre());
		this.tfApellido1.setText(personaSeleccionada.getPrimerApellido());
		this.tfApellido2.setText(personaSeleccionada.getSegundoApellido());
		this.tfDni.setText(personaSeleccionada.getNif());
		this.tfEdad.setText(personaSeleccionada.getEdad() + "");
		
		this.tfNombre.setEditable(false);
		this.tfNombre.setFocusable(false);
		
		this.tfApellido1.setEditable(false);
		this.tfApellido1.setFocusable(false);
		
		this.tfApellido2.setEditable(false);
		this.tfApellido2.setFocusable(false);
		
		this.tfDni.setEditable(false);
		this.tfDni.setFocusable(false);
		
		this.tfEdad.setEditable(false);
		this.tfEdad.setFocusable(false);
	}

	public Animal getAnimalAdoptado() {
		return animalAdoptado;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}
}
