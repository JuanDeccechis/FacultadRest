package edu.isistan;



import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.isistan.entidad.Carrera;
import edu.isistan.entidad.Estudiante;
import edu.isistan.entidad.Matricula;
import edu.isistan.repository.CarreraJPARepository;
import edu.isistan.repository.EstudianteJPARepository;
import edu.isistan.repository.MatriculaJPARepository;
/** Esta clase ejecuta los diferentes incisos que tienen que ver con insercion de datos
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia*/
public class Select {

	public static void main(String[] args) throws ParseException {
		Date parsedDate = new Date(System.currentTimeMillis());
		
		
		Timestamp fecha = new java.sql.Timestamp(parsedDate.getTime());

		//PUNTO 2
		//INCISO A) DAR DE ALTA UN ESTUDIANTE
		EstudianteJPARepository ejpa = new EstudianteJPARepository();
		Estudiante ultimoEstudiante = ejpa.getUltimoEstudiante();
		int luNueva = ultimoEstudiante.getLu() + 1;
		Estudiante e1 = new Estudiante(luNueva, "Juan", "Perez", 18, "M", 12325678, "Tandil");
//		ejpa.insert(e1);

		//INCISO B) MATRICULAR UN ESTUDIANTE
		CarreraJPARepository cjpa = new CarreraJPARepository();
		Carrera c1 = cjpa.getCarreraId(1);
		if (c1 == null) {
			c1 = new Carrera(1, "Tudai");
			cjpa.insertCarrera(c1);
		}
		MatriculaJPARepository mjpa = new MatriculaJPARepository();
		Matricula m1 = new Matricula(e1, c1,fecha, false);
		mjpa.insert(m1);

		//INCISO C) LISTAR TODOS LOS ESTUDIANTES -( EN ESTE CASO ORDENADOS POR APELLIDO)
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES ORDENADOS POR APELLIDO");
		for (Estudiante estudiantes : ejpa.getEstudiantesOrdenados()) {
			System.out.println(estudiantes);
		}

		//INCISO D) RECUPERAR UN ESTUDIANTE POR NUMERO DE LIBRETA -( EN ESTE CASO EL NUMERO DE LU 1)
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES POR LU");
		System.out.println(ejpa.getLU(1));

		//INCISO E) RECUPERAR TODOS LOS ESTUDIANTES POR GENERO
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES POR GENERO");
		for (Estudiante estudiantes : ejpa.getEstudiantesGenero("F")) {
			System.out.println(estudiantes);
		}

		
		//INCISO F) RECUPERAR LAS CARRERAS CON ESTUDIANTES INSCRIPTOS Y ORDENAR POR CANTIDAD DE INSCRIPTOS
		
		System.out.println("------------------------------------------");
		System.out.println("CARRERAS ORDENADAS POR CANTIDAD DE INSCRIPTOS");
		for (Carrera carreras : cjpa.getCarrerasOrdCantEstudiantes()) {
			System.out.println(carreras);
		}
		
		//INCISO G) RECUPERAR TODOS LOS ESTUDIANTES DE UNA DETERMINADA CIUDAD Y CARRERA
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES DE TUDAI Y TANDIL");
		for (Estudiante estudiantes : ejpa.getEstudiantesCarreraCiudad("Tudai", "Tandil")) {
			System.out.println(estudiantes);
		}
		
		
		//PUNTO3
		//GENERAR UN REPORTE QUE MUESTRE LAS CARRERAS CON LA CANTIDAD DE INSCRIPTOS Y EGRESADOS POR A�O ORDENADOS POR NOMBRE Y A�O
		System.out.println("------------------------------------------");
		System.out.println("REPORTE CARRERAS");
		System.out.println("CARRERA CANTINSCRIPTOS CANTEGRESADOS ANIO");
		for (Object[] reporte : mjpa.getReporte()) {
			System.out.print(((String)reporte[0])+" | "+(reporte[1])+" | "+((BigDecimal)reporte[2])+" | "+((Integer)reporte[3]));
			System.out.println();
		}
		

	}

}
