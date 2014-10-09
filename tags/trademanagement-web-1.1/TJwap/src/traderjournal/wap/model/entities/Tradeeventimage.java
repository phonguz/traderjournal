package traderjournal.wap.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Tradeeventimage implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="event_id")
	private Tradeevent tradeevent;
	private String description;
	private byte[] img;

	public Tradeeventimage() {
	}

	public Tradeeventimage(int id) {
		this.id = id;
	}

	public Tradeeventimage(int id, Tradeevent tradeevent, String description,
			byte[] img) {
		this.id = id;
		this.tradeevent = tradeevent;
		this.description = description;
		this.img = img;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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
