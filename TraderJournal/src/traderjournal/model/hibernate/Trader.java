package traderjournal.model.hibernate;
// Generated 2009/01/29 04:30:34 by Hibernate Tools 3.2.4.CR1


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Trader generated by hbm2java
 */
public class Trader  extends traderjournal.model.hibernate.HibernateBaseModel implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set trades = new HashSet(0);

    public Trader() {
    }

    public Trader(String name, Set trades) {
       this.name = name;
       this.trades = trades;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getTrades() {
        return this.trades;
    }
    
    public void setTrades(Set trades) {
        this.trades = trades;
    }





public <X> X get(String property){
    if(property.equals("id")){

     return (X) getId(); 
    }
    if(property.equals("name")){

     return (X) getName(); 
    }
    if(property.equals("trades")){

     return (X) getTrades(); 
    }
	return null;
}

public Map<String, Object> getProperties() {
		HashMap< String, Object> hm = new HashMap<String, Object>();
		
	    hm.put("id",getId() );
		
	    hm.put("name",getName() );
		
	    hm.put("trades",getTrades() );
		return hm;
}

public Collection<String> getPropertyNames() {
		List<String> ret = new ArrayList<String>();
	    ret.add("id");
	    ret.add("name");
	    ret.add("trades");
		return ret;
	}


	public <X> X remove(String property) {
		// TODO Auto-generated method stub
		return null;
	}


	public <X> X set(String property, X value) {
		    if(property.equals("id")){
		     setId(( Integer)value) ; 
		    }
		    if(property.equals("name")){
		     setName(( String)value) ; 
		    }
		    if(property.equals("trades")){
		     setTrades(( Set)value) ; 
		    }
		
		return null;
	}


}


