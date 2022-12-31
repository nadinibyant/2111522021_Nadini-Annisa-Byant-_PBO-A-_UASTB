import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

//inheritance
public class Minuman extends Barang {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dini_mart2";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public Minuman(int i, String nama_barang, int harga_barang) {
        this.id = i;
        this.nama_barang = nama_barang;
        this.harga_barang = harga_barang;
        this.jenis_barang = "Minuman";
    }

    // pengolahan database
    public void tambahMinuman() {
        // exception
        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // query simpan
            String sql = "INSERT INTO barang (id_barang, nama_barang, harga_barang,jenis_barang) VALUE('%s', '%s', '%s', '%s')";
            sql = String.format(sql, id, nama_barang, harga_barang, jenis_barang);

            // simpan buku
            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Barang Minuman Berhasil Ditambahkan");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
