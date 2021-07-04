-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jul 04, 2021 at 02:05 AM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `puj`
--
CREATE DATABASE IF NOT EXISTS `puj` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `puj`;

-- --------------------------------------------------------

--
-- Table structure for table `proizvodi`
--

CREATE TABLE `proizvodi` (
  `id` int(11) NOT NULL,
  `naziv` varchar(100) NOT NULL,
  `cijena` int(11) NOT NULL DEFAULT 0,
  `idVrsta` int(11) NOT NULL,
  `isExist` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proizvodi`
--

INSERT INTO `proizvodi` (`id`, `naziv`, `cijena`, `idVrsta`, `isExist`) VALUES
(1, 'Voda', 1, 1, 1),
(2, 'Cola', 3, 1, 1),
(6, 'Sok', 2, 1, 0),
(7, 'Karlovacko', 4, 2, 0),
(8, 'Karlovacko', 4, 2, 1),
(9, 'Pepsi', 3, 1, 1),
(10, 'Fanta', 2, 1, 1),
(11, 'Kava', 1, 3, 1),
(12, 'Kraca kava', 1, 3, 1),
(13, 'Bijela kava', 2, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `proizvodi_racun`
--

CREATE TABLE `proizvodi_racun` (
  `idProizvod` int(11) NOT NULL,
  `idRacun` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proizvodi_racun`
--

INSERT INTO `proizvodi_racun` (`idProizvod`, `idRacun`, `kolicina`) VALUES
(1, 19, 3),
(1, 23, 6),
(2, 19, 1),
(2, 22, 1),
(8, 20, 6),
(8, 22, 1),
(8, 23, 7),
(9, 22, 5),
(10, 19, 5),
(12, 21, 2),
(12, 22, 1),
(13, 21, 3),
(13, 22, 1);

-- --------------------------------------------------------

--
-- Table structure for table `racuni`
--

CREATE TABLE `racuni` (
  `id` int(11) NOT NULL,
  `datum` varchar(30) NOT NULL,
  `idZaposlenik` int(11) NOT NULL,
  `ukupanIznos` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `racuni`
--

INSERT INTO `racuni` (`id`, `datum`, `idZaposlenik`, `ukupanIznos`) VALUES
(19, '2021.07.03_22:52:49', 7, 16),
(20, '2021.07.03_22:53:08', 7, 24),
(21, '2021.07.03_22:53:39', 7, 8),
(22, '2021.07.03_22:54:17', 1, 25),
(23, '2021.07.03_22:54:31', 1, 34);

-- --------------------------------------------------------

--
-- Table structure for table `vrstaproizvoda`
--

CREATE TABLE `vrstaproizvoda` (
  `id` int(11) NOT NULL,
  `vrsta` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vrstaproizvoda`
--

INSERT INTO `vrstaproizvoda` (`id`, `vrsta`) VALUES
(1, 'Bezalkoholno piće'),
(2, 'Alkoholno piće'),
(3, 'Topli napitak');

-- --------------------------------------------------------

--
-- Table structure for table `zaposlenik`
--

CREATE TABLE `zaposlenik` (
  `id` int(11) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `lozinka` varchar(50) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0,
  `isEmployed` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `zaposlenik`
--

INSERT INTO `zaposlenik` (`id`, `ime`, `prezime`, `lozinka`, `isAdmin`, `isEmployed`) VALUES
(1, 'Jure', 'Juric', 'jure', 0, 1),
(2, 'a', 'a', 'a', 1, 1),
(3, 'Ivoooo', 'Ivic', 'ivo', 0, 0),
(4, 'Mile', 'mileic', 'mile', 0, 0),
(5, 'Pero', 'Peric', 'pero', 0, 0),
(6, 'admin', 'adminic', 'admin', 1, 1),
(7, 'Ivo', 'Ivic', 'Ivo', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `proizvodi`
--
ALTER TABLE `proizvodi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `proizvodVrsta` (`idVrsta`);

--
-- Indexes for table `proizvodi_racun`
--
ALTER TABLE `proizvodi_racun`
  ADD PRIMARY KEY (`idProizvod`,`idRacun`),
  ADD KEY `PR-racun` (`idRacun`);

--
-- Indexes for table `racuni`
--
ALTER TABLE `racuni`
  ADD PRIMARY KEY (`id`),
  ADD KEY `R-zaposlenik` (`idZaposlenik`);

--
-- Indexes for table `vrstaproizvoda`
--
ALTER TABLE `vrstaproizvoda`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `zaposlenik`
--
ALTER TABLE `zaposlenik`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `proizvodi`
--
ALTER TABLE `proizvodi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `racuni`
--
ALTER TABLE `racuni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `vrstaproizvoda`
--
ALTER TABLE `vrstaproizvoda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `zaposlenik`
--
ALTER TABLE `zaposlenik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `proizvodi`
--
ALTER TABLE `proizvodi`
  ADD CONSTRAINT `proizvodVrsta` FOREIGN KEY (`idVrsta`) REFERENCES `vrstaproizvoda` (`id`);

--
-- Constraints for table `proizvodi_racun`
--
ALTER TABLE `proizvodi_racun`
  ADD CONSTRAINT `PR-proizvod` FOREIGN KEY (`idProizvod`) REFERENCES `proizvodi` (`id`),
  ADD CONSTRAINT `PR-racun` FOREIGN KEY (`idRacun`) REFERENCES `racuni` (`id`);

--
-- Constraints for table `racuni`
--
ALTER TABLE `racuni`
  ADD CONSTRAINT `R-zaposlenik` FOREIGN KEY (`idZaposlenik`) REFERENCES `zaposlenik` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
