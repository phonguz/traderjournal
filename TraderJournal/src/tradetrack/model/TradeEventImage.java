package tradetrack.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.graphics.Image;

public class TradeEventImage {

	private int id;
	private int tradeEventId;
	private char [] img;
	private Image image;
	private String description;
	
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
	public  void remove() {
		String sql = "delete from tradeeventimage where id = " + getId();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");
			stmt = conn.createStatement();
			stmt.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}

	}
	public void save() {
	
			String sql = "update tradeeventimage set description = ?";
			sql += " where id = " + getId();
			Connection conn = null;
			PreparedStatement stmt = null;

			try {

				conn = DriverManager
						.getConnection("jdbc:apache:commons:dbcp:tradetrack");

				stmt = conn.prepareStatement(sql);
				if(getDescription() != null)
					stmt.setString(1,getDescription());
				else
					stmt.setString(1,null);
				
				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					stmt.close();
				} catch (Exception e) {
				}
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
