package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LocationDAO {
    private final static String SELECT = "SELECT MEM_ID, LOCATION FROM LOCATION WHERE MEM_ID=?";
    private final static String UPDATE = "UPDATE LOCATION SET LOCATION=? WHERE MEM_ID=? AND LOCATION=?";
    private final static String INSERT = "INSERT INTO LOCATION(MEM_ID, LOCATION) VALUES (?, ?)";
    private final static String DELETE = "DELETE FROM LOCATION WHERE MEM_ID=? AND LOCATION=?";
    private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final static String NAME = "CA106";
    private static DataSource dataSource;
    
//    static {
//        try {
//            Context context = new InitialContext();
//            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }
    
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        LocationDAO locationDAO = new LocationDAO();
//        LocationVO locationVO = new LocationVO("M004", "台北火車站");
        System.out.println(locationDAO.select("M004"));
    }
    
    public LocationDAO() {}
    
    public ArrayList<LocationVO> select(String memId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<LocationVO> arrayList = new ArrayList<>();
        try {
//            connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1, memId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LocationVO locationVO = new LocationVO();
                locationVO.setMemId(resultSet.getString(1));
                locationVO.setLocation(resultSet.getString(2));
                arrayList.add(locationVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        
        return arrayList;
    }
    
    public int insert(LocationVO locationVO) {
        return excuteUpdate(locationVO, INSERT, null);
    }
    
    public int update(LocationVO locationVO, String newLocation) {
        return excuteUpdate(locationVO, UPDATE, newLocation);
    }
    
    public int delete(String memId, String location) {
        return excuteUpdate(new LocationVO(memId, location), DELETE, null);
    }
    
    private int excuteUpdate(LocationVO locationVO, String sql, String newLocation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int count = 0;
        int index = 1;
        try {
//            connection = dataSource.getConnection();
            connection = DriverManager.getConnection(URL, NAME, NAME);
            preparedStatement = connection.prepareStatement(sql);    
            if (sql.equals(UPDATE))
                preparedStatement.setString(index++, newLocation);
            preparedStatement.setString(index++, locationVO.getMemId());
            preparedStatement.setString(index++, locationVO.getLocation());
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        } // finally
        
        return count;
    }
    
    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
