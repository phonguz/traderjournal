package traderjournal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import traderjournal.Activator;
import traderjournal.model.hibernate.Account;
import traderjournal.model.hibernate.AccountHome;
import traderjournal.preferences.DBURLListEditor;
import traderjournal.preferences.PreferenceConstants;

public class DBUtils {
	
	private static SessionFactory sessionFactory;
	
	public static void setupHSQLDB(){
		try {
			Class.forName("org.hsqldb.jdbcDriver");
	
			String urllist = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.P_DBURLLIST);
			String url = DBURLListEditor.getFirstDBURL(urllist);
		setupDriver(url);
		setupHBM();
		//TradeEventImage.copyAllImagesfromIMGtoIMG2();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void setupHBM(){

		
		
		
		
			if(sessionFactory == null)
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			//AccountHome acm = new AccountHome();
			//Account a =acm.findById(1);
			//System.out.println(a.toString());
	}
	
	
	public static void testHBM(){
		setupHBM();
		//getSessionFactory().getCurrentSession().beginTransaction();
		//AccountHome acm = new AccountHome();
		//Account a =acm.findById(1);
		//System.out.println(a.toString());
		//getSessionFactory().getCurrentSession().getTransaction().commit();
		
	}
	
	 public static void setupDriver(String connectURI) throws Exception {
	        //
	        // First, we'll need a ObjectPool that serves as the
	        // actual pool of connections.
	        //
	        // We'll use a GenericObjectPool instance, although
	        // any ObjectPool implementation will suffice.
	        //
	        ObjectPool connectionPool = new GenericObjectPool(null);

	        //
	        // Next, we'll create a ConnectionFactory that the
	        // pool will use to create Connections.
	        // We'll use the DriverManagerConnectionFactory,
	        // using the connect string passed in the command line
	        // arguments.
	        //
	        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI,null);

	        //
	        // Now we'll create the PoolableConnectionFactory, which wraps
	        // the "real" Connections created by the ConnectionFactory with
	        // the classes that implement the pooling functionality.
	        //
	        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);

	        //
	        // Finally, we create the PoolingDriver itself...
	        //
	        Class.forName("org.apache.commons.dbcp.PoolingDriver");
	        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

	        //
	        // ...and register our pool with it.
	        //
	        driver.registerPool("tradetrack",connectionPool);

	        //
	        // Now we can just use the connect string "jdbc:apache:commons:dbcp:example"
	        // to access our pool of Connections.
	        //
	    }




	public static void checkAndInitID(String table){
		String sql = " select count(id) from " + table;
		
		String insertSql = "insert into  "+table+" (id) values (0) ";
		
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
	
		int i;
		
		
		
			conn = DriverManager
					.getConnection("jdbc:apache:commons:dbcp:tradetrack");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				int cnt = rs.getInt(1);
				if(cnt != 0)
					return;
				
			}
			
			pstmt = conn.prepareStatement(insertSql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
	
		} finally {
	
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	
	

}
