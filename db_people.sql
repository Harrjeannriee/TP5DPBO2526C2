-- Database: db_people

CREATE DATABASE db_people;
USE db_people;

CREATE TABLE person (
  id varchar(255) PRIMARY KEY,
  nama varchar(255) NOT NULL,
  tahun_lahir int NOT NULL,
  kategori varchar(255) NOT NULL,
  prodi varchar(255) NOT NULL
);

INSERT INTO person (id, nama, tahun_lahir, kategori, prodi) VALUES
('M001', 'Andi Pratama', 2005, 'Mahasiswa Baru', 'Ilmu Komputer'),
('M002', 'Siti Rahmawati', 2004, 'Aktif', 'Sistem Informasi'),
('M003', 'Budi Santoso', 2003, 'Aktif', 'Kecerdasan Buatan'),
('M004', 'Dewi Lestari', 2004, 'Cuti', 'Ilmu Komputer'),
('M005', 'Rizky Ramadhan', 2005, 'Mahasiswa Baru', 'Kecerdasan Buatan'),
('M006', 'Putri Amelia', 2003, 'Aktif', 'Sistem Informasi'),
('M007', 'Fajar Nugraha', 2004, 'Cuti', 'Sistem Informasi'),
('M008', 'Nadia Safitri', 2005, 'Mahasiswa Baru', 'Ilmu Komputer'),
('M009', 'Dimas Saputra', 2003, 'Aktif', 'Kecerdasan Buatan'),
('M010', 'Aulia Permata', 2004, 'Aktif', 'Ilmu Komputer');