

public <X> X get(String property){
<#foreach property in pojo.getAllPropertiesIterator()>
    if(property.equals("${property.name}")){
     return (X) ${pojo.getGetterSignature(property)}(); 
    }
</#foreach>
	return null;
}

public ${pojo.importType("java.util.Map")}<String, Object> getProperties() {
		${pojo.importType("java.util.HashMap")}< String, Object> hm = new HashMap<String, Object>();
<#foreach property in pojo.getAllPropertiesIterator()>
	    hm.put("${property.name}",${pojo.getGetterSignature(property)}() );
</#foreach>
		return hm;
}

public ${pojo.importType("java.util.Collection")}<String> getPropertyNames() {
		${pojo.importType("java.util.List")}<String> ret = new ${pojo.importType("java.util.ArrayList")}<String>();
<#foreach property in pojo.getAllPropertiesIterator()>
	    ret.add("${property.name}");
</#foreach>
		return ret;
	}


	public <X> X remove(String property) {
		// TODO Auto-generated method stub
		return null;
	}


	public <X> X set(String property, X value) {
		<#foreach property in pojo.getAllPropertiesIterator()>
		    if(property.equals("${property.name}")){
		     set${pojo.getPropertyName(property)}(( ${pojo.getJavaTypeName(property, jdk5)})value) ; 
		    }
		</#foreach>
		
		return null;
	}