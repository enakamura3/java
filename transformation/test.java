package transformationTest;

public class test {
	public static void main(String[] args) {
		
		doTransformation dt = new doTransformation();
		String xmlIn = "./src/resources/input.xml";
		String xsl = "./src/resources/transformation.xsl";
		String xmlOut = "";
		String xsd = "./src/resources/schema.xsd";
		String cdata = "";
		
		//realizando a transformacao
		try {
			xmlOut = dt.transformXML(xmlIn, xsl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("xmlOut\n"+xmlOut);
		
		//extraindo uma parte do xml
		cdata = dt.getCdata(xmlOut);
		System.out.println("cdata\n"+cdata);
		
		//realizando a validacao do xml com o xsd
		boolean result = dt.validateXMLSchema(xsd, cdata);
		System.out.println("\nSchema validation..."+result);
	}
}
