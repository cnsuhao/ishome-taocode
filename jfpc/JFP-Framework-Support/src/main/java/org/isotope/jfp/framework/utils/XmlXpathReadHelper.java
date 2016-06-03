package org.isotope.jfp.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XmlXpathReadHelper {
	
	
	
//	public static void main(String[] args) throws IOException {
//		//System.out.println((new XmlXpathReadHelper()).readFromMessage("<?xml version='1.0' encoding='UTF-8'?><msg><cmd>1</cmd><ret>n</ret><status>m</status></msg>", 
//				"cmd"));
//		
//		//System.out.println((new XmlXpathReadHelper()).readFromFile("HelpMessage.xml", "WYQJ"));
//	}
	/**
	 * @param args
	 */
	public static String readFromMessage(String message,String xpathid) {
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			//(XmlXpathReadText.class.getResource("HelpMessage.xml").toString())
			InputStream is = new  ByteArrayInputStream(message.getBytes("UTF-8"));
			Document doc = builder.parse(is);//filename

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile("//"+xpathid+"/text()");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;	
			for (int i = 0; i < nodes.getLength();) {
				return (nodes.item(0).getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param args
	 */
	public static String readFromFile(String filename,String xpathid) {
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse((ClassLoader.getSystemResource(filename).toString()));

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath.compile("//"+xpathid+"/text()");
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;	
			for (int i = 0; i < nodes.getLength();) {
				return (nodes.item(0).getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
