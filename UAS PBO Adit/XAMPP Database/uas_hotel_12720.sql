-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2022 at 05:18 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uas_hotel_12720`
--

-- --------------------------------------------------------

--
-- Table structure for table `cek_in`
--

CREATE TABLE `cek_in` (
  `Kode_tamu` char(12) NOT NULL,
  `Tgl_checkin` date NOT NULL,
  `Jam_checkin` time NOT NULL,
  `Kode_kamar` char(12) NOT NULL,
  `Posisi_kamar` varchar(12) NOT NULL,
  `Lama_inap` int(2) DEFAULT NULL,
  `Harga` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cek_in`
--

INSERT INTO `cek_in` (`Kode_tamu`, `Tgl_checkin`, `Jam_checkin`, `Kode_kamar`, `Posisi_kamar`, `Lama_inap`, `Harga`) VALUES
('pel02', '2022-07-02', '13:00:00', 'kmr-B-02', 'Lantai 2', 1, 800000);

-- --------------------------------------------------------

--
-- Table structure for table `cek_out`
--

CREATE TABLE `cek_out` (
  `Kode_tamu` char(12) NOT NULL,
  `Tgl_checkout` date NOT NULL,
  `Jam_checkout` time NOT NULL,
  `Kode_kamar` char(12) NOT NULL,
  `Lama_inap` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cek_out`
--

INSERT INTO `cek_out` (`Kode_tamu`, `Tgl_checkout`, `Jam_checkout`, `Kode_kamar`, `Lama_inap`) VALUES
('pel01', '2022-07-04', '09:00:00', 'kmr-A-01', 2);

-- --------------------------------------------------------

--
-- Table structure for table `kamar`
--

CREATE TABLE `kamar` (
  `Kode_kamar` char(12) NOT NULL,
  `Nama_kamar` varchar(40) DEFAULT NULL,
  `Kelas` char(1) DEFAULT NULL,
  `Harga` int(12) DEFAULT NULL,
  `Fasilitas` varchar(25) DEFAULT NULL,
  `Posisi_kamar` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kamar`
--

INSERT INTO `kamar` (`Kode_kamar`, `Nama_kamar`, `Kelas`, `Harga`, `Fasilitas`, `Posisi_kamar`) VALUES
('kmr-A-01', 'Kamar 01 Kelas A', 'A', 1000000, 'Family Bed', 'Lantai 1'),
('kmr-A-02', 'Kamar 02 Kelas A', 'A', 1000000, 'Family Bed', 'Lantai 1'),
('kmr-A-03', 'Kamar 03 Kelas A', 'A', 1000000, 'Family Bed', 'Lantai 1'),
('kmr-A-04', 'Kamar 04 Kelas A', 'A', 1000000, 'Family Bed', 'Lantai 1'),
('kmr-B-01', 'Kamar 01 Kelas B', 'B', 800000, 'Double-bed', 'Lantai 2'),
('kmr-B-03', 'Kamar 03 Kelas B', 'B', 800000, 'Double-bed', 'Lantai 2'),
('kmr-B-04', 'Kamar 04 Kelas B', 'B', 800000, 'Double-bed', 'Lantai 2'),
('kmr-C-01', 'Kamar 01 Kelas C', 'C', 500000, 'Single-bed', 'Lantai 3'),
('kmr-C-02', 'Kamar 02 Kelas C', 'C', 500000, 'Single-bed', 'Lantai 3'),
('kmr-C-03', 'Kamar 03 Kelas C', 'C', 500000, 'Single-bed', 'Lantai 3'),
('kmr-C-04', 'Kamar 04 Kelas C', 'C', 500000, 'Single-bed', 'Lantai 3');

-- --------------------------------------------------------

--
-- Table structure for table `kamar_terisi`
--

CREATE TABLE `kamar_terisi` (
  `Kode_kamar` char(12) NOT NULL,
  `Nama_kamar` varchar(40) DEFAULT NULL,
  `Kelas` char(1) DEFAULT NULL,
  `Harga` int(12) DEFAULT NULL,
  `Fasilitas` varchar(25) DEFAULT NULL,
  `Posisi_kamar` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kamar_terisi`
--

INSERT INTO `kamar_terisi` (`Kode_kamar`, `Nama_kamar`, `Kelas`, `Harga`, `Fasilitas`, `Posisi_kamar`) VALUES
('kmr-B-02', 'Kamar 02 Kelas B', 'B', 800000, 'Double-bed', 'Lantai 2');

-- --------------------------------------------------------

--
-- Table structure for table `laporan`
--

CREATE TABLE `laporan` (
  `kamarA_terisi` int(11) DEFAULT NULL,
  `kamarB_terisi` int(11) DEFAULT NULL,
  `kamarC_terisi` int(11) DEFAULT NULL,
  `kamarA_kosong` int(11) DEFAULT NULL,
  `kamarB_kosong` int(11) DEFAULT NULL,
  `kamarC_kosong` int(11) DEFAULT NULL,
  `Pendapatan_keuangan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `laporan`
--

INSERT INTO `laporan` (`kamarA_terisi`, `kamarB_terisi`, `kamarC_terisi`, `kamarA_kosong`, `kamarB_kosong`, `kamarC_kosong`, `Pendapatan_keuangan`) VALUES
(0, 1, 0, 4, 3, 4, 3800000);

-- --------------------------------------------------------

--
-- Table structure for table `tamu`
--

CREATE TABLE `tamu` (
  `Kode_tamu` char(12) NOT NULL,
  `Nama_tamu` varchar(50) NOT NULL,
  `Jns_kel` char(1) NOT NULL,
  `Usia` int(2) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tamu`
--

INSERT INTO `tamu` (`Kode_tamu`, `Nama_tamu`, `Jns_kel`, `Usia`, `alamat`) VALUES
('pel01', 'Saiful', 'L', 25, 'Jepara'),
('pel02', 'Rizqy', 'L', 22, 'Semarang'),
('pel03', 'Arya', 'P', 21, 'Cilegon'),
('pel04', 'Ramadhan', 'P', 27, 'Bali'),
('pel05', 'Anya', 'L', 25, 'Jogjakarta'),
('pel06', 'Nikita', 'L', 36, 'Demak'),
('pel07', 'Dewi', 'P', 21, 'Pati');

-- --------------------------------------------------------

--
-- Table structure for table `temporary_checkin`
--

CREATE TABLE `temporary_checkin` (
  `Kode_tamu` char(12) DEFAULT NULL,
  `Nama_tamu` varchar(45) DEFAULT NULL,
  `Jns_kel` char(1) DEFAULT NULL,
  `Usia` char(2) DEFAULT NULL,
  `Tgl_checkin` date DEFAULT NULL,
  `Jam_checkin` time DEFAULT NULL,
  `Kode_kamar` char(12) DEFAULT NULL,
  `Kelas_kamar` char(1) DEFAULT NULL,
  `Harga` int(12) DEFAULT NULL,
  `Lama_inap` int(2) DEFAULT NULL,
  `fasilitas` varchar(20) DEFAULT NULL,
  `Posisi_kamar` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `temporary_checkout`
--

CREATE TABLE `temporary_checkout` (
  `Kode_tamu` char(12) DEFAULT NULL,
  `Nama_tamu` varchar(45) DEFAULT NULL,
  `Jns_kel` char(1) DEFAULT NULL,
  `Usia` char(2) DEFAULT NULL,
  `Tgl_checkout` date DEFAULT NULL,
  `Jam_checkout` time DEFAULT NULL,
  `Kode_kamar` char(12) DEFAULT NULL,
  `Kelas_kamar` char(1) DEFAULT NULL,
  `Harga` int(12) DEFAULT NULL,
  `Lama_inap` int(2) DEFAULT NULL,
  `fasilitas` varchar(20) DEFAULT NULL,
  `Posisi_kamar` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cek_in`
--
ALTER TABLE `cek_in`
  ADD PRIMARY KEY (`Kode_tamu`);

--
-- Indexes for table `cek_out`
--
ALTER TABLE `cek_out`
  ADD PRIMARY KEY (`Kode_tamu`);

--
-- Indexes for table `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`Kode_kamar`);

--
-- Indexes for table `kamar_terisi`
--
ALTER TABLE `kamar_terisi`
  ADD PRIMARY KEY (`Kode_kamar`);

--
-- Indexes for table `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`Pendapatan_keuangan`);

--
-- Indexes for table `tamu`
--
ALTER TABLE `tamu`
  ADD PRIMARY KEY (`Kode_tamu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
