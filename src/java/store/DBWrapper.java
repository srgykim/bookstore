package store;
import java.sql.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Properties;
import java.util.Random;

/**
 * Class DBWrapper contains 
 * wrapper routines for using JDBC
 * to access the database.
 *
 * @author iCarnegie and Sergey Kim
 * @version 1.0
 */
public class DBWrapper {
	
    private static int CONNECTION_RETRIES = 10;
    private static int QUERY_RETRIES = 10;
    private static int DEF_ISOLATION = Connection.TRANSACTION_READ_COMMITTED;
    
    private String dbUrl;
    private String password;
    private String username;
    private String jdbcClassName;
    private Connection dbCon;	

    private boolean hasError = false;
    private String errorString = null;
    private static DBWrapper myInstance = null;
    
    /**
     * DBWrapper constructor
     */
    private DBWrapper() 
    		throws Exception {
    	
    	connectAsDefaultLibrary();
    }

    /**
     * DBWrapper conscrutor
     * @param inUrl String url of database
     * @param inJdbcClassName String containing name of jdbc driver
     * @param inUserName String containing database username
     * @param inPassWord String containing database password
     */
    private DBWrapper(String inUrl, String inJdbcClassName, String inUserName, String inPassWord) 
    		throws Exception {
    	
		dbUrl = inUrl;
		jdbcClassName = inJdbcClassName;
		username = inUserName;
		password = inPassWord;
		closeConnections();
		connect();
    }
    
    /**
     * connectAsDefaultLibrary()
     * Create a connection to the library using default connection parameters.
     * @return void
     */
    public void connectAsDefaultLibrary() 
    		throws Exception {
    	
    	dbUrl = "jdbc:postgresql:bookstore";
    	
        jdbcClassName = "org.postgresql.Driver";
        username = "srgykim";
        password = "srgykim";
		closeConnections();
		connect();
    }

    /**
     * boolean connect()
     * Connect to a database using the parameters supplied in the constructor.
     * @return void
     */
    private void connect() 
    		throws Exception {
	
		// Set the JDBC class name.
		Driver driver = (Driver) Class.forName(jdbcClassName).newInstance();
		DriverManager.registerDriver(driver);
		
		dbCon = DriverManager.getConnection(dbUrl, username, password);
    }

    
    /**
     * closeConnections closes any currently open connections
     * @return void
     */
    private void closeConnections() 
    		throws Exception {
		
    	if (dbCon!=null) {
		    dbCon.close();
		}
    }

    /**
     * DBWrapper Instance()
     * Get a singleton instance of the DBWrapper object.
     * @return DBWrapper
     */
    public static DBWrapper Instance() 
    		throws Exception {
    	
		if (myInstance == null) {
			myInstance = new DBWrapper();
		    myInstance.connectAsDefaultLibrary();
		}
		
		return myInstance;
    }

    /**
     * Statement getStmt()
     * Sets up auto/manual commit & isolation level
     *
     * @param boolean isAutoCommit true if commit at every SQL statement
     * @param int isolationLevel Level at which to run the transaction
     */
    private Statement getStmt(boolean isAutoCommit, int isolationLevel)
    		throws SQLException {
    	
		// Begin a transaction at given isolation level
		dbCon.setAutoCommit(isAutoCommit);
		dbCon.setTransactionIsolation(isolationLevel);
		
		return dbCon.createStatement();
    }

    /**
     * ResultSet runQuery()
     * Executes a query and returns a resultset.  
     *
     * @param String containing a SQL statement
     * @return ResultSet
     */
    public ResultSet runQuery(String sqlQuery) 
    		throws Exception {
    	
		Statement dbStatement = this.getStmt(true, DEF_ISOLATION);
		
		return dbStatement.executeQuery(sqlQuery);
		// Note: We cannot close dbStatement since caller needs resultset!
    }
    
    /**
     * int runUpdate()
     * Executes an update and returns true of successfully executed.  
     *
     * @param String containing a SQL statement
     * @return int number of rows updated
     */    
    public int runUpdate(String sqlQuery) 
    		throws Exception {
	
        Statement dbStatement = getStmt(true, DEF_ISOLATION);
		int rowCount = dbStatement.executeUpdate(sqlQuery);
		dbStatement.close();
		
		return rowCount;
    }
    
    /**
     * ResultSet runChainedQuery()
     * Executes a chained mode transaction query.  
     *
     * @param <b>String</b> containing a SQL statement
     * @param <b>String</b> containing the isolation level to run the transaction.
     * @return ResultSet
     */  
    public ResultSet runChainedQuery(String sqlQuery, int isolationLevel) 
    		throws Exception {
	
        int retry = 0;
        boolean txnSuccess = false;
        Statement dbStatement = null;
        ResultSet resultSet = null;

		// Connect to the database.
		// Retry the query until complete or timeout.
        while (!txnSuccess && retry++ < QUERY_RETRIES) {
		    try { 
				dbStatement = getStmt(false, isolationLevel);
				// Execute the query.
				resultSet = dbStatement.executeQuery( sqlQuery );
				// Commit the transaction.
				dbCon.commit();
				txnSuccess = true;
		    } catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				// If deadlocked, try again after 10 msec
				if (se.getSQLState().equals("40P01")) {
				    Thread.sleep(10);
				} else {
				    dbCon.setTransactionIsolation(DEF_ISOLATION);
				    throw se;
				}
		    } catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
		    }
        }
        
        return resultSet;
    }
    
    /**
     * boolean runChainedUpdate()
     * Executes a chained mode update transaction.  
     *
     * @param String[] containing a series of SQL statments
     * @param String containing the isolation level to run the transaction.
     * @return boolean true if transaction was successful
     */  
    public boolean runChainedUpdate(String [] sqlQuery, int isolationLevel) 
    		throws Exception {
		
    	int retry = 0;
		boolean txnSuccess = false;
        Statement dbStatement = null;
	
        while (!txnSuccess && retry++ < QUERY_RETRIES) {
		    // Begin a new transaction.
		    try	{
					dbStatement = getStmt(false, isolationLevel);
					
					// For each sql statement, perform the update.
					for(int i = 0; i < sqlQuery.length; i++) {
						dbStatement.executeUpdate(sqlQuery[i]);
					}
					
					// Commit the transaction and close.
					dbCon.commit();
					dbStatement.close();
					txnSuccess = true;
		    } catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				
				// If deadlocked, try again after 10 msec
				if (se.getSQLState().equals("40P01")) {
				    Thread.sleep(10);
				} else {
				    dbCon.setTransactionIsolation(DEF_ISOLATION);
				    throw se;
				}
		    } catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
		    }
        }
        
        return txnSuccess;
    }
}
