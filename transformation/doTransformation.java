package transformationTest;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class doTransformation {

	public void testTemplate(String xml, String xsl) {

		String transformXML = null ;
		
		try {
			System.out.println("XML file: " + xml);
			System.out.println("XSL file: " + xsl);
			String finalstring = transformXML = transformXML(xml, xsl);
			System.out.println("finalstring...\n"+finalstring);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String transformXML(String inXml, String xsl)
			throws Exception {

		//constroi o objeto de entrada
		StreamSource source = new StreamSource(inXml);
		
		//constroi o objeto de transformacao
		StreamSource stylesource = new StreamSource(xsl);
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(stylesource);

		//constroi o objeto de saida
		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult(outWriter);

		//executa a transformacao
		transformer.transform(source, result);
		
		StringBuffer buffer = outWriter.getBuffer(); 
		String outXml = buffer.toString();
		
		return outXml;
	}
	
	
	public boolean validateXMLSchema(String xsd, String xml){

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsd));
			Validator validator = schema.newValidator();

			validator.validate(new StreamSource(new StringReader(xml)));
		} catch (IOException | SAXException e) {
			e.printStackTrace();
			
			System.out.println("Exception: "+e.getMessage());
			return false;
		}
		return true;
	}
	
	public String getCdata(String xml) {
		int start = xml.indexOf( "CDATA[");
		int end= xml.indexOf("]]");
		String substring = xml.substring(start+6,end);
		return substring;
	}
}
