package edu.csu2017fa314.T30.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class QueryBuilder {
    private String user = "";
    private String pass = "";

    public QueryBuilder(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public ArrayList<Location> query(String query) { // Command line args contain username and password
        ArrayList<Location> locations = new ArrayList<>();
        String myDriver = "com.mysql.jdbc.Driver"; // Add dependencies in pom.xml
        String myUrl = "jdbc:mysql://faure.cs.colostate.edu/cs314"; // Use this line if connecting inside CSU's network
        //String myUrl = "jdbc:mysql://localhost/cs314"; // Use this line if tunneling 3306 traffic through shell
        try { // Connect to the database
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, user, pass);
            try { // Create a statement
                Statement st = conn.createStatement();
                try { // Submit a query
                    ResultSet rs = st.executeQuery(query);
                    try { // Iterate through the query results and print selected columns
                        while(rs.next()) {
                            String id = rs.getString("id");
                            String name = rs.getString("name");
                            String type = rs.getString("type");
                            String latitude = rs.getString("latitude");
                            String longitude = rs.getString("longitude");
                            String elevation = rs.getString("elevation");
                            String municipality = rs.getString("municipality");
                            String home_link = rs.getString("home_link");
                            String wikipedia_link = rs.getString("wikipedia_link");

                            System.out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s\n", id,name,type,latitude, longitude,elevation,municipality,home_link,wikipedia_link);
                            locations.add(new Location(id,name,type,latitude,longitude,elevation,municipality,home_link,wikipedia_link));
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    st.close();
                }
            } finally {
                conn.close();
            }
        } catch(Exception e) { // Catches all exceptions in the nested try's
            System.err.printf("Exception: ");
            System.err.println(e.getMessage());
        }
        return locations;
    }

    public static void main(String[] args) {
        QueryBuilder q = new QueryBuilder("myou", "830652516"); // Create new QueryBuilder instance and pass in credentials
        ArrayList<Location> queryResults = q.query("SELECT * FROM airports LIMIT 10");
        System.out.println("testingquery" + queryResults);
    }
}
