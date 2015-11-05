package com.function.util.office;

import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;
import org.docx4j.relationships.Relationship;



public class WordUtil {

	public static void copy(){
		
//		// Source package		
//		String inputfilepath = System.getProperty("user.dir") + "/sample-docs/embedded-spreadsheet.docx";
//		WordprocessingMLPackage wordMLPackage1;// = WordprocessingMLPackage.load(new java.io.File(inputfilepath));
//		if (inputfilepath.endsWith(".xml")) {
//			// You can create one of these in Word 2007, by 
//			// choosing Save As .xml
//			// These are easier to look at / edit in a text editor than a zipped up docx
//			JAXBContext jc = Context.jcXmlPackage;
//			Unmarshaller u = jc.createUnmarshaller();
//			u.setEventHandler(new org.docx4j.jaxb.JaxbValidationEventHandler());
//
//			org.docx4j.xmlPackage.Package wmlPackageEl = (org.docx4j.xmlPackage.Package)((JAXBElement)u.unmarshal(
//					new javax.xml.transform.stream.StreamSource(new FileInputStream(inputfilepath)))).getValue(); 
//
//			org.docx4j.convert.in.FlatOpcXmlImporter xmlPackage = new org.docx4j.convert.in.FlatOpcXmlImporter( wmlPackageEl); 
//
//			wordMLPackage1 = (WordprocessingMLPackage)xmlPackage.get(); 
//		
//		} else {
//			// Its just a docx
//			wordMLPackage1 = WordprocessingMLPackage.load(new java.io.File(inputfilepath));
//		}
//		
//		// Destination
//		System.out.println( "Creating package..");
//		WordprocessingMLPackage wordMLPackage2 = WordprocessingMLPackage.createPackage();
//		
//		// Find the part we want to copy
//		RelationshipsPart rp = wordMLPackage1.getMainDocumentPart().getRelationshipsPart();
//		Relationship rel = rp.getRelationshipByType(Namespaces.EMBEDDED_PKG);		
//		Part p = rp.getPart(rel);
//		
//		System.out.println(p.getPartName().getName() );
//		
//		p.setPartName( new PartName("/word/embeddings/MySpreadsheet.xlsx") );
//		
////		System.out.println(p.getContentType() );
////		System.out.println(p.getRelationshipType() );
//		
//		// Now try adding it
//		wordMLPackage2.getMainDocumentPart().addTargetPart(p);
//		System.out.println("Done" );
//		
////		p.setOwningRelationshipPart(owningRelationshipPart)
////		p.setPackage(pack)
	}
}
