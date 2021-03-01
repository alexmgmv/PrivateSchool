package toolsPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private String serverIP;       
    private String srvPort;        
    private String databaseName;   
    private String username;       
    private String password;      
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Database() {
    }

    public Database(String serverIP, String srvPort, String databaseName, String username, String password) {
        this.serverIP = serverIP;
        this.srvPort = srvPort;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    public Connection createConnection() {
        try {
            this.connection = DriverManager.getConnection(getConnectionString(), this.username, this.password);
            return (this.connection);
        } catch (SQLException ex) {
            return (null);
        }
    }

    public Statement createStatement() {
        try {
            this.statement = this.connection.createStatement();
            return (this.statement);
        } catch (SQLException ex) {
            return (null);
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            this.resultSet = this.statement.executeQuery(sql);
            return (this.resultSet);
        } catch (SQLException ex) {
            return (null);
        }
    }

    public int executeUpdate(String sql) {
        try {
            int count = this.statement.executeUpdate(sql);
            return (count);
        } catch (SQLException ex) {
            return (-1);
        }
    }

    public ResultSet connectAndExecuteQuery(String serverIP, String srvPort, String databaseName, String username, String password, String sql) {
        this.setServerIP(serverIP);
        this.setSrvPort(srvPort);
        this.setDatabaseName(databaseName);
        this.setUsername(username);
        this.setPassword(password);
        this.createConnection();
        this.createStatement();
        this.resultSet = this.executeQuery(sql);
        return (this.resultSet);
    }

    public ResultSet connectAndExecuteQuery(String sql) {
        this.createConnection();
        this.createStatement();
        this.resultSet = this.executeQuery(sql);
        return (this.resultSet);
    }

    public int connectAndExecuteUpdate(String serverIP, String srvPort, String databaseName, String username, String password, String sql) {
        this.setServerIP(serverIP);
        this.setSrvPort(srvPort);
        this.setDatabaseName(databaseName);
        this.setUsername(username);
        this.setPassword(password);
        this.createConnection();
        this.createStatement();
        int count = this.executeUpdate(sql);
        return (count);
    }

    public int connectAndExecuteUpdate(String sql) {
        this.createConnection();
        this.createStatement();
        int count = this.executeUpdate(sql);
        return (count);
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getSrvPort() {
        return srvPort;
    }

    public void setSrvPort(String srvPort) {
        this.srvPort = srvPort;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionString() {
        String value = "jdbc:mysql://" + this.serverIP + ":" + this.srvPort + "/" + this.databaseName + "?zeroDateTimeBehavior=convertToNull";
        return (value);
    }

    @Override
    public String toString() {
        return "Database{" + "serverIP=" + serverIP + ", srvPort=" + srvPort + ", databaseName=" + databaseName + ", username=" + username + ", password=" + password + '}';
    }

}
