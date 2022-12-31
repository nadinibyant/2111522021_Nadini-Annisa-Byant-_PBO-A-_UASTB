import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//interface
public class Barang implements IData {

    int id;
    String nama_barang, jenis_barang;
    int harga_barang, id_barang;

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
        try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);

            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // buat objek statement
            stmt = conn.createStatement();

            // buat query ke database
            String sql = "SELECT * FROM barang";

            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);

            // tampilkan hasil query
            System.out.println("=======================");
            System.out.println(" D A T A  B A R A N G ");
            System.out.println("=======================");

            // perulangan
            while (rs.next()) {
                System.out.println("Id Barang\t : " + rs.getInt("id_barang"));
                System.out.println("Nama Barang\t : " + rs.getString("nama_barang"));
                System.out.println("Harga Barang\t : " + rs.getString("harga_barang"));
                System.out.println("Jenis Barang\t : " + rs.getString("jenis_barang") + "\n");
            }

            stmt.close();
            conn.close();

            // exception
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
            System.out.println("HAPUS DATA BARANG");
            System.out.println("============================");
            System.out.print("Masukan id barang yang akan di hapus : ");
            int id_barang = Integer.parseInt(input.readLine());

            String sql = "DELETE FROM barang WHERE id_barang=%d";
            sql = String.format(sql, id_barang);

            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Barang Berhasil di Hapus");
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
            System.out.println("EDIT DATA BARANG");
            System.out.println("============================");
            System.out.print("Masukan id barang yang akan di edit : ");
            this.id_barang = Integer.parseInt(input.readLine());

            String sql = "SELECT barang.id_barang FROM barang WHERE barang.id_barang=%d";
            sql = String.format(sql, id_barang);
            rs2 = stmt2.executeQuery(sql);
            while (rs2.next()) {
                int idBarang = rs2.getInt("id_barang");
                if (id_barang != idBarang) {
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
            System.out.print("Nama Barang : ");
            String nama_barang = input.readLine().trim();
            System.out.print("Harga Barang : ");
            String harga_barang = input.readLine().trim();

            String sql2 = "UPDATE barang SET nama_barang='%s', harga_barang='%s' WHERE id_barang=%d";
            sql2 = String.format(sql2, nama_barang, harga_barang, id_barang);

            stmt2.execute(sql2);
            System.out.println("----------------------------------");
            System.out.println("Data Barang Berhasil di Edit");
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
            System.out.println();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("======================");
            System.out.println("INPUTKAN DATA BARANG");
            System.out.println("======================");
            System.out.print("Id Barang : ");
            id = Integer.parseInt(input.readLine().trim());
            System.out.print("Nama Barang : ");
            nama_barang = input.readLine().trim();
            System.out.print("Harga Barang : ");
            harga_barang = Integer.parseInt(input.readLine().trim());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
