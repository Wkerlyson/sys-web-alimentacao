package com.secti.converters;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.secti.model.Programa;
import com.secti.model.Receita;

@FacesConverter(forClass=Receita.class, value="programaConverter")
public class ProgramaConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;

		return this.getAttributesFrom(component).get(value);
	}
	  
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {  
		if(value == null || "".equals(value))
			return "";
		
	    Programa entity = (Programa) value;  
	  
	    this.addAttribute(component, entity);  
	  
	    Long codigo = entity.getId();  
	            
	    if (codigo != null)
	        return String.valueOf(codigo);  
	  
	    return (String) value;  
	}  
	  
	protected void addAttribute(UIComponent component, Programa programa) {
		String key = programa.getId().toString(); 
		this.getAttributesFrom(component).put(key, programa);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
	
}