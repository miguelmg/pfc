package com.mym.pfc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import org.xml.sax.*;

import android.sax.*;
import android.util.Xml;

public class XmlHandler{
	    private List<Actividad> actividades;
	    private Actividad actividadActual;
	    private StringBuilder sbTexto;
	 
	    private String filename;
	    private FileInputStream xmlFile;
	    
	    /*private URL rssUrl;
	 
	    public XmlHandler(String url)
	    {
	        try
	        {
	            this.rssUrl = new URL(url);
	        }
	        catch (MalformedURLException e)
	        {
	            throw new RuntimeException(e);
	        }
	    }*/
	    
	    public XmlHandler(String filename)
	    {
	        try
	        {
	            //this.rssUrl = new URL(url);
	        	xmlFile = new FileInputStream(filename);
	        }
	        catch (FileNotFoundException e)
	        {
	            throw new RuntimeException(e);
	        }
	    }
	 
	    public List<Actividad> parse()
	    {
	        actividades = new ArrayList<Actividad>();
	 
	        RootElement root = new RootElement("xml");
	        Element channel = root.getChild("actividades");
	        Element item = channel.getChild("actividad");
	        
	        item.setStartElementListener(new StartElementListener(){
	            /*public void start(Attributes attributes) {
	            	actividadActual = new Actividad();
	            }*/
				public void start(org.xml.sax.Attributes attributes) {
					actividadActual = new Actividad();
				}
	        });
	 
	        item.setEndElementListener(new EndElementListener(){
	            public void end() {
	                actividades.add(actividadActual);
	            }
	        });
	 
	        item.getChild("titulo").setEndTextElementListener(
	            new EndTextElementListener(){
	                public void end(String body) {
	                	actividadActual.setTitulo(body);
	                }
	        });
	 
	        item.getChild("recursos").setEndTextElementListener(
	            new EndTextElementListener(){
	                public void end(String body) {
	                	actividadActual.setRecursos(body);
	                }
	        });
	 
	        item.getChild("cantidad").setEndTextElementListener(
	            new EndTextElementListener(){
	                public void end(String body) {
	                	actividadActual.setCantidad(Integer.parseInt(body));
	                }
	        });
	 
	        item.getChild("objetivo").setEndTextElementListener(
	            new EndTextElementListener(){
	                public void end(String body) {
	                	actividadActual.setObjetivo(body);
	                }
	        });
	 
	        try
	        {
	            /*Xml.parse(this.getInputStream(),
	                    Xml.Encoding.UTF_8,
	                    root.getContentHandler());*/
	        	Xml.parse(xmlFile, Xml.Encoding.UTF_8, root.getContentHandler());
	        }
	        catch (Exception ex)
	        {
	            throw new RuntimeException(ex);
	        }
	 
	        return actividades;
	    }
	 
	    /*private InputStream getInputStream()
	    {
	        try
	        {
	            return rssUrl.openConnection().getInputStream();
	        }
	        catch (IOException e)
	        {
	            throw new RuntimeException(e);
	        }
	    }*/
}
