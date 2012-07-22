package org.traderjournal.model.gen;

// Generated 06 Apr 2011 7:47:04 AM by Hibernate Tools 3.4.0.CR1

import net.sf.gilead.pojo.gwt.LightEntity;

/**
 * Tradeeventimage generated by hbm2java
 */
public class Tradeeventimage extends LightEntity implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Tradeevent tradeevent;
	private String description;
	private byte[] img;

	public Tradeeventimage() {
	}

	public Tradeeventimage(Tradeevent tradeevent, String description, byte[] img) {
		this.tradeevent = tradeevent;
		this.description = description;
		this.img = img;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tradeevent getTradeevent() {
		return this.tradeevent;
	}

	public void setTradeevent(Tradeevent tradeevent) {
		this.tradeevent = tradeevent;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

}