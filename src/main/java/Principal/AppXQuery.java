package Principal;

import java.util.Objects;

import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import utilidades.Leer;

public class AppXQuery {

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
				System.out.println("1. Obten por cada zona el numero de productos que tiene.");
				System.out.println("2. Obten la denominacion de los productos");
				System.out.println("3. Obten por cada zona la denominacion del o de los productos mas caros");
				System.out.println("4. Obten la denominacion de los productos");
				System.out.println("5. Devuelve el codigo de sucursal y el numero de cuentas que tiene de tipo ahorro y de tipo pensiones");
				System.out.println("6. Devuelve por cada sucursal el codigo de sucursal, el director, la poblacion, la suma del total saldodebe y la suma del total saldo haber de sus cuentas");
				System.out.println("7. Devuelve el nombre de los directores, el codigo de sucursal y la poblacion de las sucursales con mas de 3 cuentas");
				System.out.println("8. Devuelve por cada sucursal, el codigo de sucursal y los datos de las cuentas con mas saldo debe");
				System.out.println("9. Devuelve la cuenta del tipo pensiones que ha hecho mas aportacion");
				System.out.println("0.- Salir del programa");

				// PEDIMOS QUE ES LO QUE QUIERE EL USUARIO
				opcion = Leer.pedirEnteroValidar();

				// SEGUN LO QUE HA ELEGIDO EJECUTAREMOS LA FUNCION CORRESPONDIENTE
				switch (opcion) {
				case 1:
					MetodosMenuXQuery.obtenPorCadaZonaElNumeroDeProductosQueTiene(miConsultaXPath,miResourceIterator);
					break;
				case 2:
					MetodosMenuXQuery.obtenLaDenominacionDeLosProductosEntreEtiquetas(miConsultaXPath, miResourceIterator);
					break;
				case 3:
					MetodosMenuXQuery.obtenPorCadaZonaLaDenominacionDelODeLosProductosMasCaros(miConsultaXPath, miResourceIterator);
					break;
				case 4:
					MetodosMenuXQuery.obtenLaDenominacionDeLosProductosContenidaEntreEtiquetas2(miConsultaXPath, miResourceIterator);
					break;
				case 5:
					MetodosMenuXQuery.devuelveElCodigoDeSucursalYElNumeroDeCuentasQueTieneDeTipoAhorroYDeTipoPensiones(miConsultaXPath, miResourceIterator);
					break;
				case 6:
					MetodosMenuXQuery.devuelvePorCadaSucursalElCodigoDeSucursalElDirectorLaPoblacionLaSumaDelTotalSaldodebeYLaSumaDelTotalSaldohaberDeSusCuentas(miConsultaXPath, miResourceIterator);
					break;
				case 7:
					MetodosMenuXQuery.devuelveElNombreDeLosDirectoresElCodigoDeSucursalYLosDatosDeLasCuentasConMasSaldodebe(miConsultaXPath, miResourceIterator);
					break;
				case 8:
					MetodosMenuXQuery.devuelvePorCadaSucursalElCodigoDeSucursalYLaPoblacionDeLasSucursalesConMasDe3Cuentas(miConsultaXPath, miResourceIterator);
					break;
				case 9:
					MetodosMenuXQuery.devuelveLaCuentaDelTipoPensionesQueHaHechoMasAportacion(miConsultaXPath, miResourceIterator);
					break;
				}
			} while (opcion != 0);
			// CERRAMOS LA CONEXION A LA BBDD
			miColeccion.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | XMLDBException e) {
			e.printStackTrace();
		}
	}
}