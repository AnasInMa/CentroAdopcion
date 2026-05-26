package modelo;

import java.io.*;

public class FicheroConAnimales {

	public static void creaFicheroAnimales() {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File("./files/Animales.dat"), false))) {
			
		Animal[] animales = {
				//Centro 1
				new Animal(0, "Wilson", "Gato", "Dorado Sombreado", "es un gato que duerme mucho, come mucho y caga mucho", (byte) 2, "12/02/2025", 1),
				new Animal(1, "Firulais", "Perro", "Chiuaua", "es un perro que lleva mucho tiempo aqui, y a pesar de ladrar mucho siempre le gusta dar y recibir cariño", (byte) 5, "12/02/2023", 1),
				new Animal(2, "Luna", "Gata", "Siamés", "es una gata muy curiosa y juguetona, le encanta perseguir cualquier cosa que se mueva", (byte) 1, "05/06/2025", 1),
				new Animal(3, "Rocky", "Perro", "Labrador", "es un perro muy energético y cariñoso, le encanta nadar y jugar con pelotas", (byte) 3, "18/03/2024", 1),
				new Animal(4, "Nala", "Gata", "Bengalí", "es una gata salvaje de carácter pero muy leal con quien se gana su confianza", (byte) 4, "22/07/2024", 1),
				new Animal(5, "Thor", "Perro", "Husky Siberiano", "es un perro muy activo que necesita mucho ejercicio, le encanta el frío y aullar por las noches", (byte) 2, "30/01/2025", 1),
				new Animal(6, "Mochi", "Conejo", "Mini Lop", "es un conejo muy tranquilo que pasa el día comiendo hierba y tumbado al sol", (byte) 1, "14/09/2025", 1),
				new Animal(7, "Simba", "Gato", "Maine Coon", "es un gato enorme y perezoso, se lleva bien con todos pero prefiere dormir a jugar", (byte) 6, "03/04/2022", 1),
				new Animal(8, "Bella", "Perra", "Golden Retriever", "es una perra dulce y obediente, perfecta para familias con niños pequeños", (byte) 3, "11/11/2024", 1),
				new Animal(9, "Pipa", "Loro", "Cacatúa", "es una cacatúa muy habladora que repite todo lo que escucha y le encanta el protagonismo", (byte) 7, "28/05/2021", 1),
				//Centro2
				new Animal(10, "Rex", "Perro", "Pastor Alemán", "es un perro muy inteligente y protector, ideal para personas activas que disfruten de paseos largos", (byte) 4, "07/08/2024", 2),
				new Animal(11, "Cleo", "Gata", "Persa", "es una gata de pelo largo muy tranquila, le gusta que la cepillen y estar en ambientes silenciosos", (byte) 5, "19/02/2023", 2),
				new Animal(12, "Toby", "Perro", "Beagle", "es un perro muy curioso y algo cabezota, sigue cualquier olor que le llame la atención", (byte) 2, "25/10/2025", 2),
				new Animal(13, "Mía", "Gata", "Angora", "es una gata elegante y algo distante, tarda en coger confianza pero cuando lo hace es muy cariñosa", (byte) 3, "13/06/2024", 2),
				new Animal(14, "Buddy", "Perro", "Bóxer", "es un perro muy juguetón y algo torpe, siempre está feliz y contagia su energía a todos", (byte) 1, "02/03/2026", 2),
				new Animal(15, "Nube", "Conejo", "Angora", "es un conejo esponjoso y tranquilo, le encanta que le acaricien las orejas y comer zanahoria", (byte) 2, "16/12/2024", 2),
				new Animal(16, "Kira", "Perra", "Dálmata", "es una perra muy activa y sociable, necesita espacio para correr y mucha atención", (byte) 3, "08/07/2023", 2),
				new Animal(17, "Manchas", "Gato", "Europeo", "es un gato callejero rescatado, algo desconfiado al principio pero muy agradecido con quien le cuida", (byte) 6, "21/01/2022", 2),
				new Animal(18, "Lola", "Perra", "Caniche", "es una perra muy lista y presumida, aprende trucos rápidamente y le encanta llamar la atención", (byte) 4, "09/04/2025", 2),
				new Animal(19, "Paco", "Loro", "Amazona", "es un loro muy hablador y dominante, le gusta mandar y que todo gire a su alrededor", (byte) 9, "17/03/2020", 2),
				//Centro3
				new Animal(20, "Sombra", "Gato", "Bombay", "es un gato completamente negro y muy misterioso, le gusta observar desde las alturas y salir de noche", (byte) 3, "14/08/2024", 3),
				new Animal(21, "Max", "Perro", "Rottweiler", "es un perro de aspecto intimidante pero corazón enorme, muy fiel y cariñoso con su familia", (byte) 5, "06/05/2023", 3),
				new Animal(22, "Canela", "Gata", "Abisinio", "es una gata muy ágil y curiosa, explora cada rincón de la casa y no para quieta en todo el día", (byte) 2, "29/11/2025", 3),
				new Animal(23, "Duke", "Perro", "Gran Danés", "es un perro gigante que no sabe lo grande que es, siempre intenta subirse al sofá y acurrucarse", (byte) 4, "18/06/2024", 3),
				new Animal(24, "Perla", "Gata", "Ragdoll", "es una gata muy dócil y relajada, se queda flácida cuando la coges en brazos y no protesta nunca", (byte) 1, "03/02/2026", 3),
				new Animal(25, "Chispa", "Perra", "Jack Russell", "es una perra diminuta con energía de sobra, corre sin parar y necesita mucha estimulación mental", (byte) 3, "22/09/2024", 3),
				new Animal(26, "Oreo", "Gato", "Europeo", "es un gato blanco y negro muy goloso, siempre ronda la cocina esperando que le den algo rico", (byte) 5, "11/04/2023", 3),
				new Animal(27, "Nemo", "Pez", "Pez Payaso", "es un pez muy vistoso y tranquilo, perfecto para quien quiera una mascota de bajo mantenimiento", (byte) 2, "07/01/2026", 3),
				new Animal(28, "Zeus", "Perro", "Akita Inu", "es un perro muy noble y reservado, extremadamente leal a su dueño pero desconfiado con extraños", (byte) 6, "30/08/2022", 3),
				new Animal(29, "Tarta", "Tortuga", "Tortuga Mediterránea", "es una tortuga lenta y longeva, lleva en el centro más tiempo que ningún trabajador y ya forma parte del mobiliario", (byte) 15, "01/06/2010", 3),
				//Centro4
				new Animal(30, "Amber", "Perra", "Cocker Spaniel", "es una perra muy dulce y melancólica, le encanta apoyar la cabeza en el regazo de cualquiera que se siente", (byte) 4, "15/07/2024", 4),
				new Animal(31, "Misty", "Gata", "Azul Ruso", "es una gata elegante y silenciosa, observa todo con sus ojos verdes y raramente hace ruido", (byte) 3, "28/03/2025", 4),
				new Animal(32, "Bruno", "Perro", "Bulldog Francés", "es un perro muy tranquilo y algo ronca por las noches, pero compensa con su carácter afectuoso", (byte) 2, "19/10/2025", 4),
				new Animal(33, "Estrella", "Gata", "Noruego del Bosque", "es una gata de pelo larguísimo que se adapta genial al frío, muy independiente pero cariñosa a su manera", (byte) 5, "04/12/2023", 4),
				new Animal(34, "Tofu", "Conejo", "Holandés", "es un conejo pequeño y muy nervioso, da saltos de alegría cuando le sacan de la jaula a explorar", (byte) 1, "23/08/2025", 4),
				new Animal(35, "Hera", "Perra", "Shiba Inu", "es una perra muy orgullosa e independiente, hace lo que quiere pero siempre vuelve para un abrazo", (byte) 4, "10/02/2024", 4),
				new Animal(36, "Carbón", "Gato", "Europeo", "es un gato negro rescatado de la calle, al principio araña pero con paciencia se convierte en el mejor compañero", (byte) 7, "16/05/2022", 4),
				new Animal(37, "Pip", "Hámster", "Hámster Sirio", "es un hámster muy activo por las noches, corre kilómetros en su rueda y acumula comida en los carrillos", (byte) 1, "31/01/2026", 4),
				new Animal(38, "Atena", "Perra", "Border Collie", "es una perra extremadamente inteligente, aprende cualquier orden en minutos y se aburre si no tiene retos", (byte) 2, "05/09/2025", 4),
				new Animal(39, "Goldie", "Pez", "Pez Dorado", "es un pez tranquilo y brillante, lleva años en el centro y sobrevive a todo, es prácticamente inmortal", (byte) 8, "20/04/2018", 4)};
		
			for (int i = 0; i < animales.length; i++) {
				
				oos.writeObject(animales[i]);
			}

			System.out.println("Fichero animales creado");

		} catch (IOException e) {
			
			e.printStackTrace();
			
		} catch (Exception e1) {
			
			System.err.println(e1.getMessage());
		}
	}

}
