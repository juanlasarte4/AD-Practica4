package Principal;

import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

public class MetodosMenuXQuery {
	// CASO 1
	public static void obtenPorCadaZonaElNumeroDeProductosQueTiene(XPathQueryService miConsultaXPath,
			ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"for $v in distinct-values(/productos/produc/cod_zona) return ($v, count(/productos/produc[cod_zona = $v]))")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print("La zona: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println(" contiene " + xmlResource.getContent() + " productos");
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 2
	public static void obtenLaDenominacionDeLosProductosEntreEtiquetas(XPathQueryService miConsultaXPath,
			ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"for $v in distinct-values(/productos/produc/cod_zona) return element{ 'zona' || $v }{ /productos/produc[cod_zona = $v]/denominacion }")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 3
	public static void obtenPorCadaZonaLaDenominacionDelODeLosProductosMasCaros(XPathQueryService miConsultaXPath,
			ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"for $v in distinct-values(/productos/produc/cod_zona) return ($v, /productos/produc[precio = max(/productos/produc[cod_zona = $v]/precio)]/denominacion/text())")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print("En la zona: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println(", el producto mas caro es " + xmlResource.getContent() + " productos");
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 4
	public static void obtenLaDenominacionDeLosProductosContenidaEntreEtiquetas2(XPathQueryService miConsultaXPath,
			ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath
					.query("(<placa>{/productos/produc/denominacion[contains(., 'Placa Base')]}</placa>,\n"
							+ "<micro>{/productos/produc/denominacion[contains(., 'Micro')]}</micro>,\n"
							+ "<memoria>{/productos/produc/denominacion[contains(., 'Memoria')]}</memoria>,\n"
							+ "<otros>{/productos/produc/denominacion[not(contains(., 'Memoria') or contains(., 'Micro') or contains(., 'Placa Base'))]}</otros>)")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 5
	public static void devuelveElCodigoDeSucursalYElNumeroDeCuentasQueTieneDeTipoAhorroYDeTipoPensiones(
			XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"for $suc in /sucursales/sucursal return (data($suc/@codigo), count($suc/cuenta[data(@tipo)='AHORRO']), count($suc/cuenta[data(@tipo)='PENSIONES']))")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print("La sucursal " + xmlResource.getContent());

				xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.print(" tiene " + xmlResource.getContent() + " cuentas tipo ahorro y ");

				xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println(xmlResource.getContent() + " cuentas tipo pensiones");
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 6
	public static void devuelvePorCadaSucursalElCodigoDeSucursalElDirectorLaPoblacionLaSumaDelTotalSaldodebeYLaSumaDelTotalSaldohaberDeSusCuentas(
			XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"for $suc in /sucursales/sucursal return (data($suc/@codigo), $suc/director/text(), $suc/poblacion/text(), sum($suc/cuenta/saldodebe), sum($suc/cuenta/saldohaber))")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Código: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Director: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Población: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Total saldodebe: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Total saldohaber: " + xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 7
	public static void devuelvePorCadaSucursalElCodigoDeSucursalYLaPoblacionDeLasSucursalesConMasDe3Cuentas(
			XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"for $suc in /sucursales/sucursal[count(cuenta) > 3] return (data($suc/@codigo), $suc/director/text(), $suc/poblacion/text())")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print(xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Director: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Poblacion: " + xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 8
	public static void devuelveElNombreDeLosDirectoresElCodigoDeSucursalYLosDatosDeLasCuentasConMasSaldodebe(
			XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"for $suc in /sucursales/sucursal return (data($suc/@codigo), $suc/cuenta[saldodebe = max($suc/cuenta/saldodebe)]/*/text())")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print("Cuenta de la sucursal con mas saldodebe: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Nombre: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Numero: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Saldohaber: " + xmlResource.getContent());

				xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.println("Saldodebe: " + xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 9
	public static void devuelveLaCuentaDelTipoPensionesQueHaHechoMasAportacion(XPathQueryService miConsultaXPath,
			ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query(
					"/sucursales/sucursal/cuenta[data(@tipo) = 'PENSIONES' and aportacion = max(/sucursales/sucursal/cuenta/aportacion)]")
					.getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = ((XMLResource) miResourceIterator.nextResource());
				System.out.print(xmlResource.getContent());
			}
			System.out.println();
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}
}