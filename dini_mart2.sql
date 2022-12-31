-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 31 Des 2022 pada 11.26
-- Versi server: 10.4.27-MariaDB
-- Versi PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dini_mart2`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `nama_barang` varchar(50) NOT NULL,
  `harga_barang` int(11) NOT NULL,
  `jenis_barang` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga_barang`, `jenis_barang`) VALUES
(1, 'ciki', 1000, 'Makanan'),
(2, 'soda', 2000, 'Minuman');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kasir`
--

CREATE TABLE `kasir` (
  `id_kasir` int(11) NOT NULL,
  `nama_kasir` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kasir`
--

INSERT INTO `kasir` (`id_kasir`, `nama_kasir`, `username`, `password`) VALUES
(1, 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `id_pelanggan` int(11) NOT NULL,
  `nama_pelanggan` varchar(50) NOT NULL,
  `no_hp_pelanggan` varchar(12) NOT NULL,
  `alamat_pelanggan` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`id_pelanggan`, `nama_pelanggan`, `no_hp_pelanggan`, `alamat_pelanggan`) VALUES
(11, 'dini', '082283426568', 'alai');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pesanan`
--

CREATE TABLE `pesanan` (
  `no_pesanan` int(11) NOT NULL,
  `tgl_pesanan` varchar(25) NOT NULL,
  `jam_pesanan` varchar(25) NOT NULL,
  `id_pelanggan` int(11) NOT NULL,
  `id_kasir` int(11) NOT NULL,
  `total_bayar` int(7) NOT NULL,
  `kembalian` int(7) NOT NULL,
  `total_harga` int(7) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `total_belanja` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pesanan`
--

INSERT INTO `pesanan` (`no_pesanan`, `tgl_pesanan`, `jam_pesanan`, `id_pelanggan`, `id_kasir`, `total_bayar`, `kembalian`, `total_harga`, `id_barang`, `total_belanja`) VALUES
(12, 'Sat 2022.12.31', '02:50:44 PM ICT', 11, 1, 5000, 3000, 0, 0, 0),
(13, 'Sat 2022.12.31', '02:53:25 PM ICT', 11, 1, 5000, 3000, 0, 0, 0),
(14, 'Sat 2022.12.31', '02:55:18 PM ICT', 11, 1, 10000, 6000, 0, 0, 0),
(15, 'Sat 2022.12.31', '03:22:08 PM ICT', 11, 1, 5000, 2000, 0, 0, 0),
(16, 'Sat 2022.12.31', '03:22:53 PM ICT', 11, 1, 5000, 1000, 0, 0, 0),
(17, 'Sat 2022.12.31', '03:32:42 PM ICT', 11, 1, 3000, 1000, 2000, 1, 2000),
(18, 'Sat 2022.12.31', '03:44:12 PM ICT', 11, 1, 10000, 2000, 6000, 2, 8000),
(19, 'Sat 2022.12.31', '03:46:37 PM ICT', 11, 1, 10000, 2000, 6000, 1, 8000),
(20, 'Sat 2022.12.31', '03:48:57 PM ICT', 11, 1, 12000, 4000, 6000, 1, 8000),
(21, 'Sat 2022.12.31', '03:48:57 PM ICT', 11, 1, 12000, 4000, 6000, 2, 8000),
(22, 'Sat 2022.12.31', '03:54:29 PM ICT', 11, 1, 10000, 4000, 4000, 1, 6000),
(23, 'Sat 2022.12.31', '03:54:29 PM ICT', 11, 1, 10000, 4000, 4000, 2, 6000),
(24, 'Sat 2022.12.31', '05:17:40 PM ICT', 11, 1, 10000, 2000, 6000, 1, 8000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `kasir`
--
ALTER TABLE `kasir`
  ADD PRIMARY KEY (`id_kasir`) USING BTREE;

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`no_pesanan`),
  ADD KEY `id_pelanggan` (`id_pelanggan`),
  ADD KEY `id_kasir` (`id_kasir`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `no_pesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `pelanggan` (`id_pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pesanan_ibfk_2` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
