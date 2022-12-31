import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//interface
public class Pesanan implements IData {

    String nama_pelanggan, tgl_pesanan, jam_pesanan, nama_kasir, nama_barang;
    int i = 1, no_pesanan, harga_barang, id_barang, kembalian, id_pelanggan, id_kasir;
    Boolean pilihan;

    Scanner input = new Scanner(System.in);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dini_mart2";
    static final String USER = "root";
    static final String PASS = "";

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static Connection conn2;
    static Statement stmt2;
    static ResultSet rs2;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input2 = new BufferedReader(inputStreamReader);

    // pengolahan database
    @Override
    public String tampilData() {
        // exception
        try {
            // register driver yang akan dipakai
            Class.forName(JDBC_DRIVER);

            // buat koneksi ke database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // buat objek statement
            stmt = conn.createStatement();

            // buat query ke database
            String sql = "SELECT * FROM pesanan";

            // eksekusi query dan simpan hasilnya di obj ResultSet
            rs = stmt.executeQuery(sql);

            // tampilkan hasil query
            System.out.println("=======================");
            System.out.println(" D A T A  B A R A N G ");
            System.out.println("=======================");
            while (rs.next()) {
                System.out.println("No Pesanan\t : " + rs.getInt("no_pesanan"));
                System.out.println("Tanggal Pesanan\t : " + rs.getString("tgl_pesanan"));
                System.out.println("Jam Pesanan\t : " + rs.getString("jam_pesanan"));
                System.out.println("Id Pelanggan\t : " + rs.getString("id_pelanggan"));
                System.out.println("Id Kasir\t : " + rs.getString("id_kasir"));
                System.out.println("Total Bayar\t : " + rs.getString("total_bayar"));
                System.out.println("Kembalian\t : " + rs.getString("kembalian"));
                System.out.println("Id Barang\t : " + rs.getString("id_barang"));
                System.out.println("Total_harga\t : " + rs.getString("total_harga"));
                System.out.println("Total Belanja\t : " + rs.getString("total_belanja") + "\n");
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
            System.out.println("HAPUS DATA PESANAN");
            System.out.println("============================");
            System.out.print("Masukan no pesanan yang akan di hapus : ");
            int no_pesanan = Integer.parseInt(input2.readLine());

            String sql = "DELETE FROM pesanan WHERE no_pesanan=%d";
            sql = String.format(sql, no_pesanan);

            stmt.execute(sql);
            System.out.println("----------------------------------");
            System.out.println("Data Pesanan Berhasil di Hapus");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Void EditData() {
        System.out.println("Maaf Pesanan yang Sudah Selesai Tidak Dapat di Edit");
        return null;
    }

    public String getNamaPelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
        return nama_pelanggan;
    }

    public String getNamaKasir(String nama_kasir) {
        this.nama_kasir = nama_kasir;
        return nama_kasir;
    }

    public String getNamaBarang(int id_barang) {
        this.id_barang = id_barang;
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT barang.nama_barang FROM barang WHERE barang.id_barang = %d";
            sql = String.format(sql, id_barang);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.nama_barang = rs.getString("nama_barang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nama_barang;
    }

    public String getJam(String jam_pesanan) {
        this.jam_pesanan = jam_pesanan;

        return jam_pesanan;
    }

    public int getNoPesanan(String jam_pesanan) {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT pesanan.no_pesanan FROM pesanan WHERE pesanan.jam_pesanan =jam_pesanan";
            sql = String.format(sql, jam_pesanan);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.no_pesanan = rs.getInt("no_pesanan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no_pesanan;
    }

    public int getIdPelanggan(String nama_pelanggan) {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT pelanggan.id_pelanggan FROM pelanggan WHERE pelanggan.nama_pelanggan = nama_pelanggan";
            sql = String.format(sql, nama_pelanggan);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.id_pelanggan = rs.getInt("id_pelanggan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_pelanggan;
    }

    public int getIdKasir(String nama_kasir) {
        this.nama_kasir = nama_kasir;
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT kasir.id_kasir FROM kasir WHERE kasir.nama_kasir = nama_kasir";
            sql = String.format(sql, nama_kasir);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.id_kasir = rs.getInt("id_kasir");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id_kasir;
    }

    public int getHargaBarang(int id_barang) {
        // exception
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT barang.harga_barang FROM barang WHERE barang.id_barang=%d";
            sql = String.format(sql, id_barang);
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                harga_barang = rs.getInt("harga_barang");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return harga_barang;
    }
}