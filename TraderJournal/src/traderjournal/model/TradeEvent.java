package traderjournal.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.content.BinarySignatureDescriber;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.Workbench;

import traderjournal.Activator;



public class TradeEvent {

	private int ID;
	private String description;
	private Date eventDate;
	private int eventtype;
	private int tradeid;
	private int eventorder;
	
	private List<TradeEventImage> imgList = new ArrayList<TradeEventImage>();

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getEventtype() {
		return eventtype;
	}

	public void setEventtype(int eventtype) {
		this.eventtype = eventtype;
	}

	public static List<TradeEvent> getAlltradeEventsForTrade(int tradeid) {

		List<TradeEvent> events = new ArrayList<TradeEvent>();

		String sql = "Select ID,Event_date,Event_type_id,description,eventorder,tradeid from tradeevent where tradeid = "
				+ tradeid;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");

			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				TradeEvent te = new TradeEvent();
				te.setID(rset.getInt("ID"));
				te.setDescription(rset.getString("Description"));
				te.setEventtype(rset.getInt("event_type_id"));
				te.setEventorder(rset.getInt("eventorder"));
				te.setTradeid(tradeid);
				te.setEventDate(rset.getDate("event_date"));

				events.add(te);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		return events;
	}

	public static void remove(int tradeEventid) {
		String sql = "delete from tradeevent where id = " + tradeEventid;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");
			stmt = conn.createStatement();
			stmt.execute(sql);
			
			sql = "delete from tradeeventimage where event_id = " + tradeEventid;
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

	public void addNew(int tradeid) {
		DBUtils.checkAndInitID("tradeevent");
		String sql = "insert into tradeevent (id,tradeid,eventorder,event_date) values ((select (max(id) +1)  from tradeevent),"
				+ tradeid + ",(select max(eventorder) + 1 from tradeevent where tradeid = "+tradeid+"),?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, new java.sql.Date(new Date().getTime()));
			pstmt.executeUpdate();

			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("select (max(id) + 1) as maxid from tradeevent");
			if (rs.next()) {
				setID(rs.getInt("maxid"));
			}
			setTradeid(tradeid);

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

	public void update() {
		String sql = "update tradeevent set event_date = ?, event_type_id = ?, description = ?, eventorder = ?,tradeid = ? ";
		sql += " where id = " + getID();
		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");

			stmt = conn.prepareStatement(sql);
			if(getEventDate() != null)
				stmt.setDate(1, new java.sql.Date(getEventDate().getTime()));
			else
				stmt.setDate(1,null);
			stmt.setInt(2, getEventtype());
			if(getDescription() != null)
			stmt.setString(3, getDescription());
			else
				stmt.setString(3, "");
				
			stmt.setInt(4, getEventorder());
			stmt.setInt(5, getTradeid());
			//System.out.println(sql);
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

	public int getTradeid() {
		return tradeid;
	}

	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}

	public int getEventorder() {
		return eventorder;
	}

	public void setEventorder(int eventorder) {
		this.eventorder = eventorder;
	}

	public List<TradeEventImage> getAllImages(
			){
		
		return TradeEventImage.getAllImages(getID());
	}

	public void addImage(String selected) {
		DBUtils.checkAndInitID("tradeeventimage");
		
		String sql = "insert into  tradeeventimage (id,event_id,img2) values ((select (max(id) +1) from tradeeventimage),?,?) ";
		
		java.io.File f = new java.io.File(selected);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
		FileInputStream fr = new FileInputStream(f);
		int i;
		
		

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, getID());

			stmt.setBinaryStream(2, fr, (int) f.length());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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

}
