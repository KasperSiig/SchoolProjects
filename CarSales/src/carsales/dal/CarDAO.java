/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsales.dal;

import carsales.be.Car;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kasper Siig
 */
public class CarDAO {
    public List<Car> getAllCars() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("kns_CarDealer");
        ds.setUser("CS2017A_17");
        ds.setPassword("pht84gxp");
        ds.setServerName("EASV-DB2");
        ds.setPortNumber(1433);
        
        List<Car> cars = new ArrayList();
        
        try (Connection con = ds.getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Cars");
            while(rs.next()) {
                Car curCar = new Car();
                curCar.setId(rs.getInt("id"));
                curCar.setMake(rs.getString("make"));
                curCar.setModel(rs.getString("model"));
                curCar.setPrice(rs.getFloat("price"));
                curCar.setYear(rs.getInt("year"));
                curCar.setDescription(rs.getString("description"));
                cars.add(curCar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return cars;
    }
}
