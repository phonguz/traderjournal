package tradetrack.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Trade {

	private static List<ITradeListChanged> listChangeListeners = new ArrayList();

	private int id;
	private Date tradeOpenDate;
	private double tradeOpen;
	private double tradeClose;
	private Date tradeCloseDate;
	private double stoploss;
	private double tp;
	private int qty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTradeOpenDate() {
		return tradeOpenDate;
	}

	public void setTradeOpenDate(Date tradeOpenDate) {
		this.tradeOpenDate = tradeOpenDate;
	}

	public double getTradeOpen() {
		return tradeOpen;
	}

	public void setTradeOpen(double tradeOpen) {
		this.tradeOpen = tradeOpen;
	}

	public double getTradeClose() {
		return tradeClose;
	}

	public void setTradeClose(double tradeClose) {
		this.tradeClose = tradeClose;
	}

	public Date getTradeCloseDate() {
		return tradeCloseDate;
	}

	public void setTradeCloseDate(Date tradeCloseDate) {
		this.tradeCloseDate = tradeCloseDate;
	}

	public double getStoploss() {
		return stoploss;
	}

	public void setStoploss(double stoploss) {
		this.stoploss = stoploss;
	}

	public double getTp() {
		return tp;
	}

	public void setTp(double tp) {
		this.tp = tp;
	}

	public void addTradeEvent() {
		TradeEvent te = new TradeEvent();
		te.addNew(getId());

	}

	public void removeTradeEvent(int tradeEventid) {
		TradeEvent.remove(tradeEventid);

	}

	public List<TradeEvent> getAllEvents() {
		return TradeEvent.getAlltradeEventsForTrade(getId());
	}

	public static List<Trade> getAllTrades() {
		List<Trade> trades = new ArrayList();

		String sql = "Select * from trade";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");

			stmt = conn.createStatement();

			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				Trade t = new Trade();
				t.setId(rset.getInt("ID"));
				t.setStoploss(rset.getDouble("StopLoss"));
				t.setTp(rset.getDouble("TP"));
				t.setTradeClose(rset.getDouble("ClosePrice"));
				t.setTradeOpen(rset.getDouble("OpenPrice"));
				t.setTradeOpenDate(rset.getDate("Open_trade_Date"));
				t.setTradeCloseDate(rset.getDate("Close_trade_Date"));
				t.setQty(rset.getInt("Qty"));
				trades.add(t);
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
		return trades;
	}

	public String getName() {
		return id + " - " + tradeOpenDate.toString();
	}

	public void update(){
		
		String sql = "update trade set openprice = ?, closeprice = ?, qty = ?, " +
				"stoploss =?, tp = ?,open_trade_date =?,close_trade_date=? ";
		
		sql += " where id = " +getId();
        Connection conn = null;
        PreparedStatement stmt = null;
        

        try {
           
            conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:tradetrack");
           
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, getTradeOpen());
            stmt.setDouble(2, getTradeClose());
            stmt.setInt(3, getQty());
            stmt.setDouble(4, getStoploss());
            stmt.setDouble(5, getTp());
            if(getTradeOpenDate() != null)
            stmt.setDate(6, new java.sql.Date(getTradeOpenDate().getTime()));
            else
            	stmt.setDate(6, null);
            if( getTradeCloseDate() != null)
            	stmt.setDate(7, new java.sql.Date(getTradeCloseDate().getTime()));
            else
            	stmt.setDate(7, null);
            
            System.out.println(sql);
            stmt.executeUpdate();
           
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            
            try { stmt.close(); } catch(Exception e) { }
            try { conn.close(); } catch(Exception e) { }
        }
		
	}

	public void addNewTrade() {
		String sql = "insert into trade (id,open_trade_date) values ((select (max(id)+1) from trade),?) ";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");

			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(new Date().getTime()));
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

	public static void addTradeListChangeListener(ITradeListChanged it) {
		if (!listChangeListeners.contains(it))
			listChangeListeners.add(it);
	}

	public static void removeTradeListChangeListener(ITradeListChanged it) {
		if (listChangeListeners.contains(it))
			listChangeListeners.remove(it);
	}

	public static void notifyTradeListChangeListeners() {
		for (ITradeListChanged it : listChangeListeners) {
			it.tradeListChanged();
		}
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void delete() {
		String sql = "delete from trade where id = ? ";

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, getId());
			stmt.executeUpdate();
			
			sql = "delete from tradeevent where tradeid = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, getId());
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

}
