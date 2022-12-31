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
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dini_mart2";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input2 = new BufferedReader(inputStreamReader);

    public static void main(String[] args) throws IOException {

        // method date
        Date date = new Date();

        // collection framework
        Queue<Integer> antrian = new LinkedList<>();

        Scanner input = new Scanner(System.in);
        Kasir kasir = new Kasir();
        Barang barang = new Barang();
        Pelanggan pelanggan = new Pelanggan();
        Pesanan pesanan = new Pesanan();

        System.out.println("PROGRAM PEMBELIAN BARANG DI DINI MART");
        System.out.println("===================================");
        Boolean pilihan2, pilihan3, pilihan4, pilihan5, pilihan7, pilihan8, pilihan10;
        String ok;
        int pilihan6;
        int nomor = 1;

        // percabangan
        do {
            System.out.println("Silahkan pilih : ");
            System.out.println("1. Admin \n2. Transaksi \n3. Keluar");
            System.out.println("Pilihan : ");
            int pilihan = input.nextInt();

            // percabangan
            if (pilihan == 1) {
                do {
                    System.out.println("=============================");
                    System.out.println("Pengolahan Database");
                    System.out.println("=============================");
                    System.out.println("1. Data Kasir \n2. Data Barang \n3. Data Pelanggan \n4. Data Pesanan");
                    System.out.println("Silahkan pilih data yang akan dikelola : ");
                    int pilihanMenuKelola = input.nextInt();
                    if (pilihanMenuKelola == 1) {
                        do {
                            System.out.println("=============================");
                            System.out.println(
                                    "1. Tambah Data Kasir \n2. Hapus Data Kasir \n3. Edit Data Kasir \n4. Tampilkan Data Kasir");
                            System.out.println("Silahkan pilih menu yang anda inginkan : ");
                            int pilihanMenuDatabase = input.nextInt();
                            if (pilihanMenuDatabase == 1) {
                                kasir.tambahData();
                            } else if (pilihanMenuDatabase == 2) {
                                kasir.hapusData();
                            } else if (pilihanMenuDatabase == 3) {
                                kasir.EditData();
                            } else if (pilihanMenuDatabase == 4) {
                                kasir.tampilData();
                            } else {
                                System.out.println("maaf menu yang anda tuju tidak tersedia");
                            }
                            System.out.println("Apakah ingin mengolah data kasir kembali (true/false ) : ");
                            pilihan4 = input.nextBoolean();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        } while (pilihan4 == true);
                    } else if (pilihanMenuKelola == 2) {
                        do {
                            System.out.println("=============================");
                            System.out.println(
                                    "1. Tambah Data Barang \n2. Hapus Data Barang \n3. Edit Data Barang \n4. Tampilkan Data Barang");
                            System.out.println("Silahkan pilih menu yang anda inginkan : ");
                            int pilihanMenuDatabase = input.nextInt();
                            if (pilihanMenuDatabase == 1) {
                                System.out.println("1. Makanan \n2. Minuman");
                                System.out.println("=============================");
                                System.out.println("Silahkan Pilih Kategori Barang yang Ingin Ditambahkan : ");
                                pilihan6 = input.nextInt();
                                if (pilihan6 == 1) {
                                    barang.tambahData();
                                    Makanan makanan = new Makanan(barang.id, barang.nama_barang, barang.harga_barang);
                                    makanan.tambahMakanan();
                                } else {
                                    barang.tambahData();
                                    Minuman minuman = new Minuman(barang.id, barang.nama_barang, barang.harga_barang);
                                    minuman.tambahMinuman();
                                }
                            } else if (pilihanMenuDatabase == 2) {
                                barang.hapusData();
                            } else if (pilihanMenuDatabase == 3) {
                                barang.EditData();
                            } else if (pilihanMenuDatabase == 4) {
                                barang.tampilData();
                            } else {
                                System.out.println("maaf menu yang anda tuju tidak tersedia");
                            }
                            System.out.println("Apakah ingin mengelola data barang kembali (true/false) : ");
                            pilihan5 = input.nextBoolean();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        } while (pilihan5 == true);
                    } else if (pilihanMenuKelola == 3) {
                        do {
                            System.out.println("=============================");
                            System.out.println(
                                    "1. Tambah Data Pelanggan \n2. Hapus Data Pelanggan \n3. Edit Data Pelanggan \n4. Tampilkan Data Pelanggan");
                            System.out.println("Silahkan pilih menu yang anda inginkan : ");
                            int pilihanMenuDatabase = input.nextInt();
                            if (pilihanMenuDatabase == 1) {
                                pelanggan.tambahData();
                            } else if (pilihanMenuDatabase == 2) {
                                pelanggan.hapusData();
                            } else if (pilihanMenuDatabase == 3) {
                                pelanggan.EditData();
                            } else if (pilihanMenuDatabase == 4) {
                                pelanggan.tampilData();
                            } else {
                                System.out.println("maaf menu yang anda tuju tidak tersedia");
                            }
                            System.out.println("Apakah ingin mengelola data barang kembali (true/false) : ");
                            pilihan7 = input.nextBoolean();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        } while (pilihan7 == true);
                    } else if (pilihanMenuKelola == 4) {
                        do {
                            System.out.println("=============================");
                            System.out.println(
                                    "1. Hapus Data Pesanan \n2. Edit Data Pesanan \n3. Tampilkan Data Pesanan");
                            System.out.println("Silahkan pilih menu yang anda inginkan : ");
                            int pilihanMenuDatabase = input.nextInt();
                            if (pilihanMenuDatabase == 1) {
                                pesanan.hapusData();
                            } else if (pilihanMenuDatabase == 2) {
                                pesanan.EditData();
                            } else if (pilihanMenuDatabase == 3) {
                                pesanan.tampilData();
                            } else {
                                System.out.println("maaf menu yang anda tuju tidak tersedia");
                            }
                            System.out.println("Apakah ingin mengolah data pesanan kembali (true/false ) : ");
                            pilihan10 = input.nextBoolean();
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                        } while (pilihan10 == true);
                    } else {
                        System.out.println("maaf menu yang anda tuju tidak ada!");
                    }
                    System.out.println("=============================");
                    System.out.println("Apakah ingin menampilkan menu kelola database kembali (true/false) : ");
                    pilihan3 = input.nextBoolean();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                } while (pilihan3 == true);
            } else if (pilihan == 2) {
                do {
                    System.out.println("Silahkan isikan inputan pemesanan : ");
                    pelanggan.tampilData();
                    System.out.println("====================================");
                    System.out.print("Pelanggan : ");
                    String nama_pelanggan = input.next();
                    pesanan.getNamaPelanggan(nama_pelanggan);
                    pesanan.getIdPelanggan(nama_pelanggan);
                    kasir.tampilData();
                    System.out.println("====================================");
                    System.out.print("Kasir : ");
                    String nama_kasir = input.next();
                    pesanan.getNamaKasir(nama_kasir);
                    pesanan.getIdKasir(nama_kasir);
                    System.out.println("====================================");
                    antrian.add(nomor);
                    System.out.println("No Antrian : " + nomor + "\n");

                    SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd");
                    String tgl_pesanan, jam_pesanan;
                    tgl_pesanan = ft.format(date);
                    System.out.println("Tanggal Pesanan : " + tgl_pesanan);
                    SimpleDateFormat ft2 = new SimpleDateFormat("hh:mm:ss a zzz");
                    jam_pesanan = ft2.format(date);
                    pesanan.getJam(jam_pesanan);
                    System.out.println("Jam Pesanan : " + jam_pesanan + "\n");
                    System.out.println("Silahkan Pilih Barang yang Ingin diBeli");
                    barang.tampilData();
                    int jumlah_pesanan, total_harga, total_bayar, kembalian;

                    ArrayList<Integer> totalHarga = new ArrayList<Integer>();
                    ArrayList<Integer> idBarang = new ArrayList<Integer>();
                    ArrayList<Integer> hargaBarang = new ArrayList<Integer>();
                    ArrayList<Integer> jumlahPesanan = new ArrayList<Integer>();
                    int id_barang;

                    // proses matematika
                    do {
                        System.out.println("===================================");
                        System.out.println("Barang yang di beli");
                        System.out.print("Id Barang: ");
                        id_barang = Integer.parseInt(input2.readLine());
                        idBarang.add(id_barang);
                        System.out.print("Jumlah di Beli : ");
                        jumlah_pesanan = Integer.parseInt(input2.readLine());
                        jumlahPesanan.add(jumlah_pesanan);
                        int harga_barang = pesanan.getHargaBarang(id_barang);
                        hargaBarang.add(harga_barang);
                        total_harga = pesanan.harga_barang * jumlah_pesanan;
                        totalHarga.add(total_harga);
                        System.out.println("Total Harga : " + total_harga + "\n");
                        System.out.print("Apakah ingin menambah barang (true/false): ");
                        pilihan8 = input.nextBoolean();
                    } while (pilihan8 == true);
                    nomor = nomor + 1;
                    int total = 0;
                    for (int i = 0; i < totalHarga.size(); i++) {
                        total = total + totalHarga.get(i);
                    }
                    System.out.println("Total : " + total);
                    System.out.print("Bayar : ");
                    total_bayar = input.nextInt();
                    if (total_bayar < total) {
                        do {
                            System.out.println("Maaf Uang Anda Tidak Cukup, Silhkan Ulangi lagi");
                            System.out.print("Bayar : ");
                            total_bayar = input.nextInt();
                        } while (total_bayar < total);
                    }
                    kembalian = total_bayar - total;
                    pesanan.kembalian = kembalian;
                    System.out.println("Kembalian : " + kembalian);

                    System.out.println("Pembayaran Anda Berhasil");
                    pesanan.getNoPesanan(jam_pesanan);

                    System.out.println("---------------------------------------------------------");
                    System.out.println("\t D I N I  M A R T");
                    System.out.println("---------------------------------------------------------");
                    System.out.println("No Antrian : " + nomor + "\n");
                    System.out.println(tgl_pesanan);
                    System.out.println(jam_pesanan);
                    System.out.println("---------------------------------------------------------");

                    for (int i = 0; i < idBarang.size(); i++) {
                        System.out
                                .println(i + 1 + ". " + pesanan.getNamaBarang(idBarang.get(i)) + "\n\t"
                                        + jumlahPesanan.get(i)
                                        + " x " + hargaBarang.get(i) + "\t Rp " + totalHarga.get(i));
                    }
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Total\t\t\t: Rp " + total);
                    System.out.println("Bayar\t\t\t: Rp " + total_bayar);
                    System.out.println("Kembalian\t\t: Rp " + pesanan.kembalian);
                    System.out.println("---------------------------------------------------------");
                    System.out.println("-- \tT E R I M A  K A S I H \t --");

                    System.out.println("---------------------------------------------------------");

                    // exception
                    try {
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        stmt = conn.createStatement();

                        // query simpan
                        for (int i = 0; i < idBarang.size(); i++) {
                            String sql = "INSERT INTO pesanan (tgl_pesanan, jam_pesanan, id_pelanggan, id_kasir, id_barang, total_harga, total_belanja, total_bayar, kembalian) VALUE('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
                            sql = String.format(sql, tgl_pesanan, jam_pesanan, pesanan.id_pelanggan, pesanan.id_kasir,
                                    idBarang.get(i), total_harga, total,
                                    total_bayar,
                                    kembalian);
                            // simpan
                            stmt.execute(sql);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    totalHarga.clear();
                    idBarang.clear();
                    jumlahPesanan.clear();
                    System.out.println("Apakah Ingin Melakukan Transaksi Kembali (ok/tidak): ");
                    ok = input.next();

                    // method string
                } while (ok.equalsIgnoreCase("ok"));

            } else if (pilihan == 3) {
                System.out.println("program selesai");
                System.exit(0);
            } else {
                System.out.println("Maaf menu tidak tersedia!");
            }
            System.out.println("=============================");
            System.out.println("Apakah ingin kembali ke menu utama (true/false) : ");
            pilihan2 = input.nextBoolean();
            System.out.println("===================================");
            System.out.print("\033[H\033[2J");
            System.out.flush();

        } while (pilihan2 == true);
    }
}