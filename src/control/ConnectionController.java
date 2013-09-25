package control;

import java.sql.*;
import model.Station;

public class ConnectionController {

    private static ConnectionController connectionController;
    private static Connection connection;
    private static Statement stmt;

    /**
     * Retrieve the connectioncontroller, and create, if neccesary, the required
     * connection and statement
     *
     * @return
     * @throws SQLException
     */
    public static ConnectionController getConnectionController() throws SQLException {
        try {
            if (connectionController.equals(null)) {
                connectionController = new ConnectionController();
                connection = DriverManager.getConnection("JDBC:MySQL://localhost:3306/2-1leertaak1", "root", "");
                stmt = connection.createStatement();
            }
        } catch (NullPointerException e) {
            connectionController = new ConnectionController();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toolbox", "root", "");
            stmt = connection.createStatement();
        }
        return connectionController;
    }
    
    public static void addToDB(Station s) throws SQLException {
        String[] vars = {
                "" + s.getDate(),
                "" + s.getDewpoint(),
                "" + s.getEvents(),
                "" + s.getId(),
                "" + s.getOvercast(),
                "" + s.getRainfall(),
                "" + s.getSLP(),
                "" + s.getSTP(),
                "" + s.getSnowfall(),
                "" + s.getTemperature(),
                "" + s.getTime(),
                "" + s.getVisibilty(),
                "" + s.getWindDirection(),
                "" + s.getWindspeed()};
        String query = "INSERT INTO weerdata(\"date\", \"dewpoint\", \"events\", " + 
                "\"station_id\", \"overcast\", \"rainfall\", \"slp\", \"stp\", " + 
                "\"snowfall\", \"tempurature\", \"time\", \"visibility\", " +
                "\"winddirection\", \"windspeed\")\n";
        query += "VALUES(";
        for(int i = 0; i < vars.length; i ++) {
            query += " \"" + vars[i] + "\","; 
        }
        StringBuilder b = new StringBuilder(query);
        b.replace(query.lastIndexOf(","), query.lastIndexOf(",") + 1, ")");
        query = b.toString();
        stmt.executeUpdate(query);
    }
}
