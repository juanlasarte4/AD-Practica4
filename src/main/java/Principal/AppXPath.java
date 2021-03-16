package Principal;

import java.util.Objects;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import utilidades.Leer;

public class AppXPath {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		// INICIAMOS LA CONEXION A LA BBDD
		try {
			Database db = (Database) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
			Collection miColeccion = Objects.requireNonNull(db.getCollection("exist://localhost:6969/exist/xmlrpc/db/AD-Practica4", "admin", "4DM1n4DM1n"));
			XPathQueryService miConsultaXPath = Objects.requireNonNull((XPathQueryService) miColeccion.getService(XPathQueryService.SERVICE_NAME, null));
			ResourceIterator miResourceIterator = null;
		

		// VARIABLE QUE UTILIZAREMOS PARA ELEGIR LA FUNCION DEL MENU
		int opcion = 0;
		do {
			// IMPRIMIMOS MENU
			System.out.println("1.- Obten los nodos denominacion y precio de todos los productos");
			System.out.println("2.- Obten los nodos de los productos que sean placas base");
			System.out.println("3.- Obten los nodos de los productos con precio mayor de 60€ y de la zona 20");
			System.out.println("4.- Obten el numero de productos que sean memorias y de la zona 10");
			System.out.println("5.- Obten la media de precio de los micros");
			System.out.println("6.- Obten los datos de los productos cuyo stock minimo sea mayor que su stock actual");
			System.out.println("7.- Obten el nomnre del producto y el precio de aquellos cuyo stock minimo sea mayor que su stock actual y sean de la zona 40");
			System.out.println("8.- Obten el producto mas caro");
			System.out.println("9.- Obten el producto mas barato de la zona 20");
			System.out.println("10.- Obten el producto mas caro de la zona 10");
			System.out.println("0.- Salir del programa");

			// PEDIMOS QUE ES LO QUE QUIERE EL USUARIO
			opcion = Leer.pedirEnteroValidar();

			// SEGUN LO QUE HA ELEGIDO EJECUTAREMOS LA FUNCION CORRESPONDIENTE
			switch (opcion) {
			case 1:
				MetodosMenu.obtenLosNodosDenominacionYPrecioDeTodosLosProductos(miConsultaXPath, miResourceIterator);
				break;
			case 2:
				MetodosMenu.obtenLosNodosDeLosProductosQueSeanPlacasBase(miConsultaXPath, miResourceIterator);
				break;
			case 3:
				MetodosMenu.obtenLosNodosDeLosProductosConPrecioMayorDe60€YDeLaZona20(miConsultaXPath, miResourceIterator);
				break;
			case 4:
				MetodosMenu.obtenElNumeroDeLosProductosQueSeanMemoriasYDeLaZona10(miConsultaXPath, miResourceIterator);
				break;
			case 5:
				MetodosMenu.obtenLaMediaDePrecioDeLosMicros(miConsultaXPath, miResourceIterator);
				break;
			case 6:
				MetodosMenu.obtenLosDatosDeLosProductosCuyoStockMinimoSeaMayorQueSuStockActual(miConsultaXPath, miResourceIterator);
				break;
			case 7:
				MetodosMenu.obtenElNombreDelProductoYElPrecioDeAquellosCuyoStockMinimoSeaMayorQueSuStockActualYSeanDeLaZona40(miConsultaXPath, miResourceIterator);
				break;
			case 8:
				MetodosMenu.obtenElProductoMasCaro(miConsultaXPath, miResourceIterator);
				break;
			case 9:
				MetodosMenu.obtenElProductoMasBaratoDeLaZona20(miConsultaXPath, miResourceIterator);
				break;
			case 10:
				MetodosMenu.obtenElProductoMasCaroDeLaZona10(miConsultaXPath, miResourceIterator);
			}
		} while (opcion != 0);
		// CERRAMOS LA CONEXION A LA BBDD
		miColeccion.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | XMLDBException e) {
			e.printStackTrace();
		}
	}
}