package utilidades;

import javax.swing.*;
import javax.swing.border.*;

public class UtilidadesPaneles {

	public static JPanel panelPersona(JTextField tfNombre, JTextField tfApellido1, JTextField tfApellido2, JTextField tfDni, JTextField tfEdad, JButton bElegirPersona) {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(UtilidadesVariables.BordeLinea);
		panel.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		
		tfNombre.setBorder(new TitledBorder("Nombre"));
		tfNombre.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfNombre);

		tfApellido1.setBorder(new TitledBorder("Primer Apellido"));
		tfApellido1.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfApellido1);
		
		tfApellido2.setBorder(new TitledBorder("Segundo Apellido"));
		tfApellido2.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfApellido2);
		
		tfDni.setBorder(new TitledBorder("DNI"));
		tfDni.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfDni);
		
		tfEdad.setBorder(new TitledBorder("Edad"));
		tfEdad.setBackground(UtilidadesVariables.FONDO_ANIMALES);
		panel.add(tfEdad);

		panel.add(Box.createVerticalStrut(120));
		
		bElegirPersona.setAlignmentX(JButton.CENTER_ALIGNMENT);
		bElegirPersona.setForeground(UtilidadesVariables.TEXTO_CLARO );
		bElegirPersona.setBackground(UtilidadesVariables.FONDO_BOTON);
		
		panel.add(bElegirPersona);
		
		return panel;
	}
	
}
