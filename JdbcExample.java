import java.util.Collection;
import java.util.Properties;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class prog1 {
   public static void main(String args[]) {
      Connection con = null;
      Statement stmt;
      ResultSet result;
      String query;
      String name;
      List<String> list = new ArrayList<>();

      try {
         FileReader reader = new FileReader("example.properties");
         Properties p = new Properties();
         p.load(reader);

         String dbdriver = p.getProperty("db.driver");
         String dbuser = p.getProperty("db.user");
         String dbpassword = p.getProperty("db.password");
         String dburl = p.getProperty("db.url");

         Class.forName(dbdriver).newInstance();
         con = DriverManager.getConnection(dburl, dbuser, dbpassword);

         if (!con.isClosed()) {
            System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
            stmt = con.createStatement();
            query = "SELECT * FROM EMPLOYEE";
            result = stmt.executeQuery(query);
            // result.first()
            while (result.next()) {
               name = result.getString("Fname") + " " + result.getString("Lname");
               list.add(name);
            }

            Collection.sort(list);
            for (int i = 0; i < list.size(); i++) {
               System.out.println(list.get(i));
            }

         }
      } catch (Exception e) {
         System.err.println("Exception: " + e.getMessage());
      } finally {
         try {
            if (con != null)
               con.close();
         } catch (SQLException e) {
         }
      }
   }
}
