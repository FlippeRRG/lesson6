import java.sql.*;
import java.util.Arrays;


public class lesson6 {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    public static int[] task1(int[] arr){
        int last4;
        for (int i = arr.length - 1; i > 0; i--) {
            if(arr[i] == 4){
                last4 = i;
                int[] result = Arrays.copyOfRange( arr, last4, arr.length );
                return result;
            }
        }
        throw new RuntimeException();
    }

    public static boolean task2(int[] arr){
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] != 1 && arr[i] != 4) return false;
        }
        return true;
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:lesson6.db");
        stmt = connection.createStatement();
        stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS students (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    surname TEXT ,\n" +
                "    score   INTEGER\n" +
                ");" );
    }
    public static void insertDefaultValues(){
        try {
            pstmt = connection.prepareStatement( "INSERT INTO students (surname, score)\n" +
                    "VALUES  (?,?)" );

            pstmt.setString( 1, "Filippov" );
            pstmt.setInt( 2, 5 );
            pstmt.addBatch();
            pstmt.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }

    public static void createDB(){
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insert(String surname, int score){
        try {
            pstmt = connection.prepareStatement( "INSERT INTO students (surname, score)\n" +
                    "VALUES  (?,?)" );
            pstmt.setString( 1, surname );
            pstmt.setInt( 2, score );
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateSurname (String surname, int id){
        try {
            PreparedStatement ps = connection.prepareStatement( "UPDATE students SET surname = ? WHERE id = ?" );
            ps.setString( 1, surname );
            ps.setInt( 2, id );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateScore (int score, int id){
        try {
            PreparedStatement ps = connection.prepareStatement( "UPDATE students SET score = ? WHERE id = ?" );
            ps.setInt( 1, score );
            ps.setInt( 2, id );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String read (int id){
        String result = null;
        try {
            PreparedStatement ps = connection.prepareStatement( "SELECT surname, score FROM students WHERE id = ?" );
            ps.setInt( 1, id );
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getString( 1 );
                String score = rs.getString( 2 );
                result = result + " " + score;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int readID(){
        int res = 0;
        try {
            PreparedStatement ps = connection.prepareStatement( "SELECT id FROM students" );
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                res = rs.getInt( 1 );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void delete (int id){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement( "DELETE from students WHERE id = ?" );
            ps.setInt( 1, id );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createDB();

        try {
            disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
