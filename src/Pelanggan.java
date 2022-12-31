import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//interface
public class Pelanggan implements IData {

    String nama_pelanggan;
    int id_pelanggan;
    int harga_pelanggan, stock_pelanggan;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dini_mart2";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static ResultSet rs2;
    static Statement stmt2;
    static Connection conn2;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    // pengolahan database
    @Override
    public String tampilData() {
        // exception
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);

            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // buat objek statement
            stmt = conn.createStatement();

            // buat query ke database
            String sql = "SELECT * FROM pelanggan";

            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);

            // tampilkan hasil query
            System.out.println("=======================");
            System.out.println(" D A T A  P E L A N G G A N ");
            System.out.println("=======================");
            while (rs.next()) {
                System.out.println("Id Pelanggan\t : " + rs.getInt("id_pelanggan"));
                System.out.println("Nama Pelanggan\t : " + rs.getString("nama_pelanggan"));
                System.out.println("No Hp : " + rs.getString("no_hp_pelanggan"));
                System.out.println("Alamat : " + rs.getString("alamat_pelanggan") + "\n");
            }
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Void hapusData() {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("============================");
            System.out.println("HAPUS DATA PELANGGAN");
            System.out.println("============================");
            System.out.print("Masukan id pelanggan yang akan di hapus : ");
            int id_pelanggan = Integer.parseInt(input.readLine());

            String sql = "DELETE FROM pelanggan WHERE id_pelanggan=%d";
            sql = String.format(sql, id_pelanggan);

            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Pelanggan Berhasil di Hapus");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Void EditData() {
        // exception
        try {
            conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt2 = conn2.createStatement();

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("============================");
            System.out.println("EDIT DATA PELANGGAN");
            System.out.println("============================");
            System.out.print("Masukan id pelanggan yang akan di edit : ");
            this.id_pelanggan = Integer.parseInt(input.readLine());

            String sql = "SELECT pelanggan.id_pelanggan FROM pelanggan WHERE pelanggan.id_pelanggan=%d";
            sql = String.format(sql, id_pelanggan);
            rs2 = stmt2.executeQuery(sql);
            while (rs2.next()) {
                int idPelanggan = rs2.getInt("id_pelanggan");
                if (id_pelanggan != idPelanggan) {
                    System.out.println("Data Tidak Tersedia");
                } else {
                    tambah();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void tambah() {
        try {
            conn2 = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt2 = conn2.createStatement();
            System.out.println("============================");
            System.out.println("Silahkan Input Data Editan");
            System.out.println("============================");
            System.out.print("Nama Pelanggan : ");
            String nama_pelanggan = input.readLine().trim();
            System.out.print("No Hp : ");
            String no_hp_pelanggan = input.readLine().trim();
            System.out.print("Alamat : ");
            String alamat_pelanggan = input.readLine().trim();

            String sql2 = "UPDATE pelanggan SET nama_pelanggan='%s', no_hp_pelanggan='%s', alamat_pelanggan='%s' WHERE id_pelanggan=%d";
            sql2 = String.format(sql2, nama_pelanggan, no_hp_pelanggan, alamat_pelanggan, id_pelanggan);

            stmt2.execute(sql2);
            System.out.println("----------------------------------");
            System.out.println("Data Pelanggan Berhasil di Edit");
            System.out.println("----------------------------------");
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void tambahData() {
        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // ambil input dari user
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("======================");
            System.out.println("INPUTKAN DATA PELANGGAN");
            System.out.println("======================");
            System.out.print("Nama Pelanggan : ");
            String nama_pelanggan = input.readLine().trim();
            System.out.print("No Hp : ");
            String no_hp_pelanggan = input.readLine().trim();
            System.out.print("Alamat : ");
            String alamat_pelanggan = input.readLine().trim();

            // query simpan
            String sql = "INSERT INTO pelanggan (nama_pelanggan, no_hp_pelanggan, alamat_pelanggan) VALUE('%s', '%s', '%s')";
            sql = String.format(sql, nama_pelanggan, no_hp_pelanggan, alamat_pelanggan);

            // simpan buku
            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Pelanggan Berhasil Ditambahkan");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}