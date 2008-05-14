package tradetrack.model;

import java.sql.DriverManager;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

public class DBUtils {
	
	public static void setupHSQLDB(){
		try {
			Class.forName("org.hsqldb.jdbcDriver");
	
		
		setupDriver("jdbc:hsqldb:hsql://localhost/TradeTrack");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

}
