package traderjournal.model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import traderjournal.Activator;

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
	
	public static List<TradeEventImage> getAllImages(int tradeeventid) {
		String sql = "select id,img2,event_id,description from tradeeventimage ";
		sql += " where event_id = " + tradeeventid;
		List<TradeEventImage> imgList = new ArrayList<TradeEventImage>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				TradeEventImage ti = new TradeEventImage();
				ti.setId(rs.getInt(1));
				Display dsp =  Activator.getDefault().getWorkbench().getDisplay();
				if(rs.getBinaryStream(2) != null){
					Image img = new Image(dsp,rs.getBinaryStream(2));
					
					ti.setImage(img);}
				
					else
						ti.setImage(null);
						
				ti.setTradeEventId(tradeeventid);
				
				ti.setDescription(rs.getString(4));
				
				imgList.add(ti);
			}
			
			return imgList;
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void copyAllImagesfromIMGtoIMG2() {
		String sql = "select id,img,event_id,description from tradeeventimage ";
		
		List<TradeEventImage> imgList = new ArrayList<TradeEventImage>();
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String usql = "update tradeeventimage set img2 = ? where id = ?";
			
			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");
			pstmt =conn.prepareStatement(usql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			byte [] bar = null;
			while(rs.next()){

		
				if(rs.getBinaryStream(2) != null){
					Blob b = rs.getBlob(2); 
					pstmt.setBlob(1,b);
					pstmt.setInt(2, rs.getInt(1));
					pstmt.executeUpdate();
					
				}
					
			

				
				
				
				
			}
			
			return ;
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ;
	}
	
}
