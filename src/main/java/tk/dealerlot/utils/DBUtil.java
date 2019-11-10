package tk.dealerlot.utils;

import tk.dealerlot.models.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    Car car = new Car(2555,253,"acura","tl","oceanBlue","image dot jpeg");

    public static List<Car> getAllCarsInDb(){
        List<Car> carList = new ArrayList<Car>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://95.217.14.25:3306/dealerlot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&"
                ,"testuser"
                ,"Password@1");
        Statement statement = conn.createStatement();

        String sampleQuery = "SELECT * FROM cars";

        ResultSet rs = statement.executeQuery(sampleQuery);

        while(rs.next()){
            Car car = new Car(rs.getInt("year")
                    ,rs.getInt("stock")
                    ,rs.getString("make")
                    ,rs.getString("model")
                    ,rs.getString("color")
                    ,rs.getString("image"));
            carList.add(car);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carList;

    }
        public static boolean doesStockExistInDb(int stockNo){
        List<Car> list = getAllCarsInDb();
        for(Car i: list){
            if(i.stock==stockNo){
                return true;
            }
        }
        return false;
        }

    public static void main(String[] args) {
        getNumberOfCarsFromDb();
    }


    public static int getNumberOfCarsFromDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://95.217.14.25:3306/dealerlot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&"
                    ,"testuser"
                    ,"Password@1");
            Statement statement = conn.createStatement();

            String sampleQuery = "SELECT * FROM cars";

            ResultSet rs = statement.executeQuery(sampleQuery);
            int count = 0;
            while(rs.next()){
                count++;
            }
        return count;


        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    public static String getModelNameByStockNumber(int stockNumber) {
        List<Car> cars = getAllCarsInDb();
        String modelNameInDb = "";
        for(Car i : cars){
            if(i.stock == stockNumber) {
                modelNameInDb = i.model;
                break;
            }
        }
        return modelNameInDb;
    }
}