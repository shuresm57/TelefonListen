import java.sql.*;
import java.util.*;

public class SQLDriver {

    private static final String URL = "jdbc:mysql://localhost:3306/telefonbog";
    private static final String USERNAME = "root";
    private static final String PASS = "xvn88bbu";
    private static final Connection con;

    static {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SQLDriver(){}

    public static void getTable(Map<Integer,String> liste) throws SQLException {
        String table = "SELECT * FROM telefonliste";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(table);
        while(rs.next()){
            int nr = rs.getInt("telefon_nr");
            String navn = rs.getString("navn");
            liste.putIfAbsent(nr,navn);
        }
        st.close();
        rs.close();
    }

    public static void deleteFromTable(int nr, String navn) throws SQLException {
        String delete = "DELETE FROM telefonliste WHERE telefon_nr = ? AND navn = ?";
        PreparedStatement ps = con.prepareStatement(delete);

        ps.setInt(1, nr);
        ps.setString(2, navn);

        int rowsAffected = ps.executeUpdate();
        System.out.println(rowsAffected + " række(r) slettet.");

        ps.close();
    }

    public static void saveToTable(Map<Integer, String> liste) throws SQLException {
        String save = "INSERT INTO telefonliste (telefon_nr, navn) VALUES (?, ?)" + "ON DUPLICATE KEY UPDATE navn = VALUES(navn)";;
        PreparedStatement ps = con.prepareStatement(save);

        for (Map.Entry<Integer, String> entry : liste.entrySet()) {
            int nr = entry.getKey();
            String navn = entry.getValue();

            ps.setInt(1, nr);
            ps.setString(2, navn);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
}
