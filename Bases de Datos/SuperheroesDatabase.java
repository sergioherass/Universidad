package superheroes;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SuperheroesDatabase {

	private static Connection conn = null;
	private static Statement st = null;
	private static PreparedStatement pst = null;
	public static final String SEPARADOR = ";";

	public SuperheroesDatabase() {
	}

	
	//	openConnection [0.25 puntos]: este método debe implementar la apertura de la
//	conexión con la base de datos. El método devuelve true si abre correctamente la conexión
//	y false si ninguna conexión es abierta (bien porque no se pueda o porque ya estuviera
//	abierta). Se debe llamar a este método cuando se vaya a realizar la conexión en otras
//	partes del código. Nunca debe abrir varias conexiones.
	
	public boolean openConnection() {
		//Parámetros
		String serverAddress = "localhost:3306";
		String user = "superheroes_user";
		String password = "superheroes_pass";
		String database = "superheroes";
		String url = "jdbc:mysql://" + serverAddress + "/" + database;
		boolean conecto = false;
		//Cargar el driver
		try {
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(url, user, password);
				conecto = true;
			}
		} 
		//Errores
		catch (SQLException e) {
			System.out.println("No se pudo realizar la conexion");
			System.out.println("Mensaje de error: " + e.getMessage());
			System.out.println("Codigo de error: " + e.getErrorCode());
			System.out.println("Estado SQL: "+ e.getSQLState());
//			e.printStackTrace();
		}
		return conecto;
	}
	
//	closeConnection [0.25 puntos]: este método debe implementar el cierre de la
//	conexión con la base de datos. El método devuelve true si se ejecuta sin errores y false si
//	ocurre alguna excepción. No es necesario llamar a este método en el código, ya está
//	llamado donde corresponde en la clase Main.
	
	public boolean closeConnection() {
		boolean desconecto = true;
		//Desconexión
		try {
			conn.close();
		} 
		//Errores
		catch (SQLException e) {
			System.out.println("No se pudo realizar la desconexion");
			System.out.println("Mensaje de error: " + e.getMessage());
			System.out.println("Codigo de error: " + e.getErrorCode());
			System.out.println("Estado SQL: "+ e.getSQLState());
//			e.printStackTrace();
			desconecto = false;
		}
		return desconecto;
	}

//	createTableEscena [0.5 puntos]: este método debe implementar la creación de la
//	tabla escena. El método devuelve false si la tabla no se ha podido crear (por algún tipo de
//	error o porque ésta ya existía) y true si la tabla se ha creado correctamente.

	public boolean createTableEscena() {
		//abrir la conexión
		openConnection();
		boolean tablaCreada = true;
		//crear la tabla
		try {
			String query = "CREATE TABLE escena ( "
					+ "id_pelicula INT," 
					+ "n_orden INT,"
					+ "titulo VARCHAR(100),"
					+ "duracion INT,"
					+ "PRIMARY KEY(n_orden, id_pelicula),"
					+ "FOREIGN KEY(id_pelicula) REFERENCES pelicula(id_pelicula)"
					+ "ON DELETE CASCADE ON UPDATE CASCADE"
					+ ");";
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			System.out.println("Tabla rival creada correctamente");
		} 
		//errores
		catch (SQLException e) {
			System.out.println("Error");
			System.out.println("Mensaje: " + e.getMessage());
			System.out.println("Codigo: " + e.getErrorCode());
			System.out.println("Estado SQL: "+ e.getSQLState());
//			e.printStackTrace();
			tablaCreada = false;
		}
		closeConnection();
		return tablaCreada;
	}

//	createTableRival [0.5 puntos]: este método debe implementar la creación de la
//	tabla rival. El método devuelve false si la tabla no se ha podido crear (por algún tipo de
//	error o porque ésta ya existía) y true si la tabla se ha creado correctamente.
	
	public boolean createTableRival() {
		//abrir la conexión
		openConnection();
		boolean tablaCreada = true;
		//Crear la tabla
		try {
			String query = "CREATE TABLE rival ( " +
					"id_sup INT," +
					"id_villano INT," +
					"fecha_primer_encuentro DATE," +
					"PRIMARY KEY (id_sup, id_villano)," +
					"FOREIGN KEY (id_sup) REFERENCES superheroe(id_sup)" +
					"ON DELETE CASCADE ON UPDATE CASCADE, " +
					"FOREIGN KEY (id_villano) REFERENCES villano(id_villano)" +
					"ON DELETE CASCADE ON UPDATE CASCADE "+
					");" ;
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			System.out.println("Tabla rival creada correctamente");
		} 
		//errores
		catch (SQLException e) {
			System.out.println("Error");
			System.out.println("Mensaje: " + e.getMessage());
			System.out.println("Codigo: " + e.getErrorCode());
			System.out.println("Estado SQL: "+ e.getSQLState());
//			e.printStackTrace();
			tablaCreada = false;
		} 
		closeConnection();
		return tablaCreada;
	}
	
//	loadEscenas [1 punto]: este método debe insertar en la base de datos las escenas
//	contenidas en el fichero CSV que se pasa como parámetro. El método irá leyendo cada
//	línea del CSV e insertando cada una de ellas (no hay columna de cabecera, pero los
//	atributos aparecen siempre según el orden especificado en el ejercicio 3). Cada inserción
//	debe ser tratada como una transacción separada del resto y en caso de que se produzca un
//	error, el programa debe seguir intentando insertar los elementos posteriores. El método
//	debe retornar la cantidad de elementos insertados en la tabla.

	public int loadEscenas(String fileName) {
		//abrir conexión
		openConnection();
		BufferedReader br = null;
		int cont = 0;
		//Leer CSV
		try {
			br = new BufferedReader(new FileReader(fileName));
			String linea = br.readLine();
			while(linea != null) {
				String[] campos = linea.split(SEPARADOR);		//Lee linea a linea guardando en un array
				String id_pelicula = campos[0];		//id_pelicula
				String n_orden = campos[1];			//n_orden
				String titulo = campos[2];			//titulo
				String duracion = campos[3];		//duracion
				
				String query = "INSERT INTO escena (id_pelicula, n_orden, titulo, duracion) VALUES (?,?,?,?);";
				    //Insertar parámetros
					try{
				
					pst = conn.prepareStatement(query);			//PreparedStatement
					pst.setString(1, id_pelicula);
					pst.setString(2, n_orden);
					pst.setString(3, titulo);
					pst.setString(4, duracion);
					pst.executeUpdate();
					cont++;
					}
					//errores
					catch (SQLException e) {
						System.out.println("No se pudo realizar la conexion");
						System.out.println("Mensaje de error: " + e.getMessage());
						System.out.println("Codigo de error: " + e.getErrorCode());
						System.out.println("Estado SQL: "+ e.getSQLState());
//						e.printStackTrace();
					} 
					linea = br.readLine();
				}
			
		}
		//errores
		catch (IOException e) {
			e.printStackTrace();
		}
		//parar de leer CSV
		finally {
			if(br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
				
		return cont;
	}

//	loadProtagoniza [2 puntos]: este método debe insertar en la base de datos los datos
//	sobre qué superhéroes con qué villanos protagonizan qué películas, que se encuentran en
//	el fichero CSV pasado como parámetro. El método irá leyendo cada línea del CSV e
//	insertando cada una de ellas (no hay columna de cabecera, pero el orden de cada fila
//	siempre es “superhéroe, villano, película”). Cada vez que aparezca una combinación
//	diferente de superhéroe y villano se debe introducir también un registro en la tabla rival
//	con ellos. Obligatoriamente, debe ejecutarse la creación y carga de todos los datos como
//	si fuera una única transacción, de tal forma que cualquier fallo intermedio de lugar a
//	deshacer por completo los cambios anteriores. El método debe retornar la cantidad de
//	elementos insertados (la suma de protagoniza y rival).

	public int loadProtagoniza(String fileName) {
		// Comprobamos que tenemos conexión con la BDs
		openConnection();
		int cont = 0;
		ArrayList<String> heroesYVillanos = new ArrayList<String>();
		File f = new File(fileName);
		String query = "INSERT INTO superheroes.protagoniza VALUES (?,?,?);";
		String query2 = "INSERT INTO superheroes.rival (id_sup, id_villano) VALUES (?,?);";
		String hyv;
		//leer CSV e insertar en la tabla
		try {

			Scanner sc_file = new Scanner(f);

			conn.setAutoCommit(false);
			PreparedStatement pst = conn.prepareStatement(query);
			PreparedStatement pst2 = conn.prepareStatement(query2);

			while(sc_file.hasNextLine()) {
				String linea[] = sc_file.nextLine().split(";");
				if(linea.length==3) {
					pst.setInt(1,Integer.valueOf(linea[0])); // id_sup
					pst.setInt(2,Integer.valueOf(linea[1])); // id_villano
					pst.setInt(3,Integer.valueOf(linea[2])); // id_pelicula
					pst.executeUpdate();
					cont++;

					hyv = linea[0]+","+linea[1];
					if(!heroesYVillanos.contains(hyv)) {
						heroesYVillanos.add(hyv);
						pst2.setInt(1,Integer.valueOf(linea[0])); // id_sup
						pst2.setInt(2,Integer.valueOf(linea[1])); // id_villano
						pst2.executeUpdate();
						cont++;
					}

				} else {
					System.err.println("Número incorrecto de parámetros");
					conn.rollback();
					cont=0;
				}
			}

			conn.commit();
			pst.close();
			pst2.close();
			sc_file.close();
			//errores
		} catch (FileNotFoundException e) {
			System.err.println("Ruta no encontrada\n" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("ERROR " + e.getMessage());
			try {
				conn.rollback();
				cont = 0;
			} catch (SQLException e1) {
				System.err.println("Imposible deshacer los cambios en la base de datos");
			}

		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// lanzada por el autocommit
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		return cont;	
	}
	
//	catalogo [1 punto]: este método debe consultar en la base de datos la lista de todas las
//	películas y retornar dicha lista como String con la información estructurada de la siguiente
//	forma:
//	{nombre_pelicula_1, nombre_pelicula_2, ..., nombre_pelicula_n}
//	Es decir, debe contener el nombre de todas las películas almacenadas entre llaves y
//	separando cada una de ellas con una coma y un espacio. Además, las películas deben
//	aparecer ordenadas alfabéticamente.
//	Si no hay películas almacenadas, debe retornarse “{}” (sin las comillas y sin espacios en
//	el interior). Si se produjera alguna excepción, el método debe retornar null.

	
	public String catalogo() {
		// Comprobamos que tenemos conexión con la base de datos
		openConnection();
		String query = "select titulo from pelicula order by titulo";
		String catalogo = "{";

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				if(catalogo.length()>1)
					catalogo += ", ";
				catalogo += rs.getString(1);
			}
			rs.close();
			st.close();
			catalogo += "}";
		} catch (SQLException e) {
			catalogo = null;
			System.err.println("ERROR: " + e.getMessage());
		}
		return catalogo;
	}
	
//	duracionPelicula [1.5 puntos]: este método debe retornar la duración de una
//	película cuyo nombre se da como parámetro. Dicha duración debe calcularse como la
//	suma de todas las escenas que componen la película. Si no hay ninguna película con ese
//	nombre, debe retornarse -1.0. Si se produce alguna excepción, debe retornarse -2.0. Si la
//	película existe, pero no hay escenas de ella, debe retornarse 0.0.
	
	public int duracionPelicula(String nombrePelicula) {
		// Comprobamos que tenemos conexión con la base de datos
		openConnection();
		String query = "select id_pelicula from pelicula where titulo=?;";
		int duracion = 0;

		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1,nombrePelicula);
			ResultSet rs = pst.executeQuery();				//Guarda id de pelicula con ese titulo
			if(rs.next()) {
				int id_peli = rs.getInt(1);					//Guarda el valor
				query = "select duracion from escena where id_pelicula = ?";

				pst = conn.prepareStatement(query);
				pst.setInt(1, id_peli);						//Busca escenas de la pelicula guardada antes
				rs = pst.executeQuery();
				duracion = 0;
				while(rs.next())
					duracion += rs.getInt(1); 				//Suma duracion de las escenas
			} else {  // no existe la pelicula introducida en la base de datos
				System.err.println("No existe la pelicula");
				duracion = -1;
			}

		} catch (SQLException e) {
			System.err.println("ERROR: " + e.getMessage());
			duracion = -2;
		}
		return duracion;
	}

//	getEscenas [2 puntos]: este método debe retornar la lista de nombres de todas las
//	escenas de todas las películas en las que aparezca el villano cuyo nombre se pasa como
//	parámetro con el siguiente formato:
//	{nombre_escena_1, nombre_escena_2, ..., nombre_escena_n}
//	Es decir, debe contener el nombre de todas las escenas almacenadas entre llaves y
//	separando cada una de ellas con una coma y un espacio. Como criterio de ordenación
//	debe usarse, en primer lugar, el orden alfabético de la película y, en segundo lugar, el
//	número de orden de la escena dentro de la película.
	
	public String getEscenas(String nombreVillano) {
		// Comprobamos que tenemos conexión con la base de datos
		openConnection();
		String query = "select e.titulo from escena e, pelicula p where e.id_pelicula = p.id_pelicula "
				+ "AND e.id_pelicula in (select id_pelicula from protagoniza where id_villano = "
				+ "(select id_villano from villano where nombre=?)) order by p.titulo, e.n_orden;";
		String escenas = "{";

		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1,nombreVillano);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				if(escenas.length()>1)
					escenas += ", ";
				escenas += rs.getString(1);
			}
			rs.close();
			pst.close();
			escenas += "}";
		} catch (SQLException e) {
			escenas = null;
			System.err.println("ERROR: " + e.getMessage());
		}
		return escenas;
	}

//	desenmascara [1 puntos]: el método debe obtener la imagen del avatar del superhéroe
//	que enmascara a la persona real cuyo nombre y apellido se pasan en los dos primeros
//	parámetros. La imagen debe almacenarse en la ruta indicada por el parámetro filename.
//	Debe retornar true si la imagen existía en la base de datos y se ha almacenado
//	correctamente y false en caso contrario (en este caso no se debe almacenar nada).
	
	public boolean desenmascara(String nombre, String apellido, String filename) {
		// Comprobamos que tenemos conexión con la base de datos
		openConnection();
		boolean almacenada = false; 

		try {
			//Se busca la imagen en la tabla de superheroes del superheroe con nombre real
			//nombre y apellido (parametros)
			String query = "SELECT avatar FROM superheroe WHERE id_persona="
					+ "(select id_persona from persona_real where nombre=? and apellido=?);";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, nombre);
			pst.setString(2, apellido);
			ResultSet rs = pst.executeQuery();

			byte[] data = null;
			Blob myBlob = null;

			while(rs.next()) {
				myBlob = rs.getBlob("avatar");
				if(myBlob != null){
					data = myBlob.getBytes(1, (int) myBlob.length());
					System.out.println("Imagen creada con éxito!");
				} else {
					return almacenada;
				}
			}

			FileOutputStream fos = new FileOutputStream(filename);
			
			fos.write(data);
			
			fos.close();
			rs.close();
			pst.close();
			almacenada = true;


		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}

		return almacenada;
	}

}
