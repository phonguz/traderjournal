package tradetrack.model;

import org.eclipse.swt.graphics.Image;

public class TradeEventImage {

	private int id;
	private int tradeEventId;
	private char [] img;
	private Image image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTradeEventId() {
		return tradeEventId;
	}
	public void setTradeEventId(int tradeEventId) {
		this.tradeEventId = tradeEventId;
	}
	public char[] getImg() {
		return img;
	}
	public void setImg(char[] img) {
		this.img = img;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
}
