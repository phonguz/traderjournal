package traderjournal.model.hibernate;
// Generated 2009/01/29 04:30:34 by Hibernate Tools 3.2.4.CR1


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Trade generated by hbm2java
 */
public class Trade  extends traderjournal.model.hibernate.HibernateBaseModel implements java.io.Serializable {


     private Integer id;
     private Trader trader;
     private Instrument instrument;
     private Account account;
     private Date openTradeDate;
     private Double openprice;
     private Date closeTradeDate;
     private Double closeprice;
     private Double stoploss;
     private Double tp;
     private Double entrycoms;
     private Double exitcoms;
     private Double entryfee;
     private Double exitfee;
     private Double pl;
     private String reference;
     private Double carrycost;
     private Double qty;
     private Set tradeevents = new HashSet(0);

    public Trade() {
    }

	
    public Trade(Trader trader, Account account) {
        this.trader = trader;
        this.account = account;
    }
    public Trade(Trader trader, Instrument instrument, Account account, Date openTradeDate, Double openprice, Date closeTradeDate, Double closeprice, Double stoploss, Double tp, Double entrycoms, Double exitcoms, Double entryfee, Double exitfee, Double pl, String reference, Double carrycost, Double qty, Set tradeevents) {
       this.trader = trader;
       this.instrument = instrument;
       this.account = account;
       this.openTradeDate = openTradeDate;
       this.openprice = openprice;
       this.closeTradeDate = closeTradeDate;
       this.closeprice = closeprice;
       this.stoploss = stoploss;
       this.tp = tp;
       this.entrycoms = entrycoms;
       this.exitcoms = exitcoms;
       this.entryfee = entryfee;
       this.exitfee = exitfee;
       this.pl = pl;
       this.reference = reference;
       this.carrycost = carrycost;
       this.qty = qty;
       this.tradeevents = tradeevents;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Trader getTrader() {
        return this.trader;
    }
    
    public void setTrader(Trader trader) {
        this.trader = trader;
    }
    public Instrument getInstrument() {
        return this.instrument;
    }
    
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    public Date getOpenTradeDate() {
        return this.openTradeDate;
    }
    
    public void setOpenTradeDate(Date openTradeDate) {
        this.openTradeDate = openTradeDate;
    }
    public Double getOpenprice() {
        return this.openprice;
    }
    
    public void setOpenprice(Double openprice) {
        this.openprice = openprice;
    }
    public Date getCloseTradeDate() {
        return this.closeTradeDate;
    }
    
    public void setCloseTradeDate(Date closeTradeDate) {
        this.closeTradeDate = closeTradeDate;
    }
    public Double getCloseprice() {
        return this.closeprice;
    }
    
    public void setCloseprice(Double closeprice) {
        this.closeprice = closeprice;
    }
    public Double getStoploss() {
        return this.stoploss;
    }
    
    public void setStoploss(Double stoploss) {
        this.stoploss = stoploss;
    }
    public Double getTp() {
        return this.tp;
    }
    
    public void setTp(Double tp) {
        this.tp = tp;
    }
    public Double getEntrycoms() {
        return this.entrycoms;
    }
    
    public void setEntrycoms(Double entrycoms) {
        this.entrycoms = entrycoms;
    }
    public Double getExitcoms() {
        return this.exitcoms;
    }
    
    public void setExitcoms(Double exitcoms) {
        this.exitcoms = exitcoms;
    }
    public Double getEntryfee() {
        return this.entryfee;
    }
    
    public void setEntryfee(Double entryfee) {
        this.entryfee = entryfee;
    }
    public Double getExitfee() {
        return this.exitfee;
    }
    
    public void setExitfee(Double exitfee) {
        this.exitfee = exitfee;
    }
    public Double getPl() {
        return this.pl;
    }
    
    public void setPl(Double pl) {
        this.pl = pl;
    }
    public String getReference() {
        return this.reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    public Double getCarrycost() {
        return this.carrycost;
    }
    
    public void setCarrycost(Double carrycost) {
        this.carrycost = carrycost;
    }
    public Double getQty() {
        return this.qty;
    }
    
    public void setQty(Double qty) {
        this.qty = qty;
    }
    public Set getTradeevents() {
        return this.tradeevents;
    }
    
    public void setTradeevents(Set tradeevents) {
        this.tradeevents = tradeevents;
    }





public <X> X get(String property){
    if(property.equals("id")){

     return (X) getId(); 
    }
    if(property.equals("trader")){

     return (X) getTrader(); 
    }
    if(property.equals("instrument")){

     return (X) getInstrument(); 
    }
    if(property.equals("account")){

     return (X) getAccount(); 
    }
    if(property.equals("openTradeDate")){

     return (X) getOpenTradeDate(); 
    }
    if(property.equals("openprice")){

     return (X) getOpenprice(); 
    }
    if(property.equals("closeTradeDate")){

     return (X) getCloseTradeDate(); 
    }
    if(property.equals("closeprice")){

     return (X) getCloseprice(); 
    }
    if(property.equals("stoploss")){

     return (X) getStoploss(); 
    }
    if(property.equals("tp")){

     return (X) getTp(); 
    }
    if(property.equals("entrycoms")){

     return (X) getEntrycoms(); 
    }
    if(property.equals("exitcoms")){

     return (X) getExitcoms(); 
    }
    if(property.equals("entryfee")){

     return (X) getEntryfee(); 
    }
    if(property.equals("exitfee")){

     return (X) getExitfee(); 
    }
    if(property.equals("pl")){

     return (X) getPl(); 
    }
    if(property.equals("reference")){

     return (X) getReference(); 
    }
    if(property.equals("carrycost")){

     return (X) getCarrycost(); 
    }
    if(property.equals("qty")){

     return (X) getQty(); 
    }
    if(property.equals("tradeevents")){

     return (X) getTradeevents(); 
    }
	return null;
}

public Map<String, Object> getProperties() {
		HashMap< String, Object> hm = new HashMap<String, Object>();
		
	    hm.put("id",getId() );
		
	    hm.put("trader",getTrader() );
		
	    hm.put("instrument",getInstrument() );
		
	    hm.put("account",getAccount() );
		
	    hm.put("openTradeDate",getOpenTradeDate() );
		
	    hm.put("openprice",getOpenprice() );
		
	    hm.put("closeTradeDate",getCloseTradeDate() );
		
	    hm.put("closeprice",getCloseprice() );
		
	    hm.put("stoploss",getStoploss() );
		
	    hm.put("tp",getTp() );
		
	    hm.put("entrycoms",getEntrycoms() );
		
	    hm.put("exitcoms",getExitcoms() );
		
	    hm.put("entryfee",getEntryfee() );
		
	    hm.put("exitfee",getExitfee() );
		
	    hm.put("pl",getPl() );
		
	    hm.put("reference",getReference() );
		
	    hm.put("carrycost",getCarrycost() );
		
	    hm.put("qty",getQty() );
		
	    hm.put("tradeevents",getTradeevents() );
		return hm;
}

public Collection<String> getPropertyNames() {
		List<String> ret = new ArrayList<String>();
	    ret.add("id");
	    ret.add("trader");
	    ret.add("instrument");
	    ret.add("account");
	    ret.add("openTradeDate");
	    ret.add("openprice");
	    ret.add("closeTradeDate");
	    ret.add("closeprice");
	    ret.add("stoploss");
	    ret.add("tp");
	    ret.add("entrycoms");
	    ret.add("exitcoms");
	    ret.add("entryfee");
	    ret.add("exitfee");
	    ret.add("pl");
	    ret.add("reference");
	    ret.add("carrycost");
	    ret.add("qty");
	    ret.add("tradeevents");
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
		    if(property.equals("trader")){
		     setTrader(( Trader)value) ; 
		    }
		    if(property.equals("instrument")){
		     setInstrument(( Instrument)value) ; 
		    }
		    if(property.equals("account")){
		     setAccount(( Account)value) ; 
		    }
		    if(property.equals("openTradeDate")){
		     setOpenTradeDate(( Date)value) ; 
		    }
		    if(property.equals("openprice")){
		     setOpenprice(( Double)value) ; 
		    }
		    if(property.equals("closeTradeDate")){
		     setCloseTradeDate(( Date)value) ; 
		    }
		    if(property.equals("closeprice")){
		     setCloseprice(( Double)value) ; 
		    }
		    if(property.equals("stoploss")){
		     setStoploss(( Double)value) ; 
		    }
		    if(property.equals("tp")){
		     setTp(( Double)value) ; 
		    }
		    if(property.equals("entrycoms")){
		     setEntrycoms(( Double)value) ; 
		    }
		    if(property.equals("exitcoms")){
		     setExitcoms(( Double)value) ; 
		    }
		    if(property.equals("entryfee")){
		     setEntryfee(( Double)value) ; 
		    }
		    if(property.equals("exitfee")){
		     setExitfee(( Double)value) ; 
		    }
		    if(property.equals("pl")){
		     setPl(( Double)value) ; 
		    }
		    if(property.equals("reference")){
		     setReference(( String)value) ; 
		    }
		    if(property.equals("carrycost")){
		     setCarrycost(( Double)value) ; 
		    }
		    if(property.equals("qty")){
		     setQty(( Double)value) ; 
		    }
		    if(property.equals("tradeevents")){
		     setTradeevents(( Set)value) ; 
		    }
		
		return null;
	}


}


