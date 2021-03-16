package Principal;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

public class MetodosMenu {

	// CASO 1
	public static void obtenLosNodosDenominacionYPrecioDeTodosLosProductos(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc/*[self::denominacion or self::precio]").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 2
	public static void obtenLosNodosDeLosProductosQueSeanPlacasBase(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc[denominacion[contains(., 'Placa Base')]]").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 3
	public static void obtenLosNodosDeLosProductosConPrecioMayorDe60€YDeLaZona20(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc[precio[text() > 60] and cod_zona[text() = 20]]").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 4
	public static void obtenElNumeroDeLosProductosQueSeanMemoriasYDeLaZona10(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("count(/productos/produc[denominacion[contains(., 'Memoria')] and cod_zona[text() = 10]])").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println("Hay " + xmlResource.getContent() + " productos de tipo Memoria de la zona 10.");
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 5
	public static void obtenLaMediaDePrecioDeLosMicros(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("sum(/productos/produc[denominacion[contains(., 'Micro')]]/precio/text()) div count(/productos/produc[denominacion[contains(., 'Micro')]])").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println("La media de los precios de los microprocesadores es: " + xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 6
	public static void obtenLosDatosDeLosProductosCuyoStockMinimoSeaMayorQueSuStockActual(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc[number(stock_minimo) > number(stock_actual)]").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				NodeList listaDeNodos = xmlResource.getContentAsDOM().getChildNodes();

				for (int i = 0; i < listaDeNodos.getLength(); ++i) {
					Node miNodo = listaDeNodos.item(i);
					if (miNodo.getLocalName() != null) {
						System.out.println(miNodo.getLocalName() + " = " + miNodo.getTextContent());
					}
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 7
	public static void obtenElNombreDelProductoYElPrecioDeAquellosCuyoStockMinimoSeaMayorQueSuStockActualYSeanDeLaZona40(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc/*[(self::denominacion or self::precio) and number(../stock_minimo/text()) > number(../stock_actual/text()) and ../cod_zona/text() = 40]/text()").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println("Nombre: " + xmlResource.getContent());

				if (miResourceIterator.hasMoreResources()) {
					XMLResource xmlResource2 = ((XMLResource) miResourceIterator.nextResource());
					System.out.println("Precio: " + xmlResource2.getContent());
				}
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 8
	public static void obtenElProductoMasCaro(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc[precio = max(/productos/produc/precio)]").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 9
	public static void obtenElProductoMasBaratoDeLaZona20(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc[precio = min(/productos/produc[cod_zona = 20]/precio)]").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}

	// CASO 10
	public static void obtenElProductoMasCaroDeLaZona10(XPathQueryService miConsultaXPath, ResourceIterator miResourceIterator) {
		try {
			miResourceIterator = miConsultaXPath.query("/productos/produc[precio = max(/productos/produc[cod_zona = 10]/precio)]").getIterator();
			while (miResourceIterator.hasMoreResources()) {
				XMLResource xmlResource = (XMLResource) miResourceIterator.nextResource();
				System.out.println(xmlResource.getContent());
			}
		} catch (XMLDBException e) {
			e.printStackTrace();
		}
	}
}