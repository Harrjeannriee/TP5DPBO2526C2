# Janji
Saya Andina Dwi Listiana dengan NIM 2501065 mengerjakan TP 5 dalam mata kuliah Desain Pemrograman Berorientasi Objek untuk keberkahaanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
Program PeopleData dibuat untuk mengelola data mahasiswa berbasis GUI. Dalam program ini, pengelolaan data dibagi ke dalam tiga class utama, yaitu Person, PeopleMenu, dan Database. Selain itu, terdapat file PeopleMenu.form yang digunakan untuk merancang tampilan antarmuka program serta file db_people.sql yang berisi perintah SQL untuk membuat database, tabel, dan memasukkan data awal mahasiswa.

## 1. Person.java
Person.java Class Person digunakan untuk merepresentasikan data mahasiswa. Di dalamnya terdapat lima atribut yaitu ID, Nama, TahunLahir, Kategori, dan Prodi. Setiap atribut memiliki getter dan setter yang digunakan untuk mengambil dan mengubah nilai data mahasiswa. Saya memisahkan data mahasiswa ke dalam class tersendiri supaya pengelolaan setiap objek mahasiswa lebih terstruktur.

## 2. PeopleMenu.java
PeopleMenu.java Class PeopleMneu menjadi bagian utama yang mengatur jalannya program, mulai dari menampilkan data sampai menjalankan operasi CRUD. Data mahasiswa disimpan menggunakan ArrayList kemudian ditampilkan dalam bentuk tabel menggunakan JTable dan DefaultTableModel. Ada beberapa fungsi yang terdapat pada class ini yaitu:
  1. insertData : untuk menambahkan data mahasiswa baru berdasarkan input pada form
  2. updateData: untuk memperbarui data mahasiswa berdasarkan baris yang dipilih
  3. deleteData : untuk menghapus data mahasiswa yang dipilih setelah memberikan konfirmasi untuk menghapus
  4. clearForm : untuk mengosongkan form dan membatalkan pemilihan data yang dilakukan (clear form)
  5. setTable : untuk menampilkan data mahasiswa yang tersimpan dalam daftarMahasiswa ke dalam tavel. Method ini akan
     dipanggil kembali setelah data ditambah, diubah (update), atau dihapus agar tampilan tabel selalu diperbaharui.
Beberapa tambahan lainnya mengenai validasi untuk memastikan bahwa ID dan Nama tidak kosong dan harus diisi jika ingin menambahkan data, Tahun Lahir diisi dengan angka dan tidak boleh huruf serta tahun yang diisi harus berada dalam rentang waktu yang ditentukan, dan ID tidak bisa digunakan lagi jika ID sudah tercantum dalam tabel untuk menghindari adanya duplikasi data. Validasi tersebut dibuat supaya data yang dimasukkan lebih terjaga dan mengurangi kesalahan saat pengoperasian program.

## 3. PeopleMenu.form
PeopleMenu.form Pada bagian ini terdapat kolom input ID, Nama, Tahun Lahir, pilihan kategori mahasiswa, serta pilihan prodi. Untuk tabel di bawah kolom inputan berfungsi untuk menampilkan data mahasiswa yang tercatat. Di bawah tabel terdapat empat tombol untuk menjalankan operasi CRUD dan membatalkan input/clearform. Untuk atribut Kategori dan Prodi, saya menggunakan komponen JComboBox agar pengguna dapat memilih opsi yang tersedia tanpa perlu mengetikkan nilainya secara manual.
Desain program dibuat dengan memisahkan bagian data mahasiswa, logika pengelolaan data, dan rancangan antarmuka yaitu karena setiap bagian memiliki fungsi masing-masing sehingga program lebih mudah dipahami.

## 5. Database.java
Class Database digunakan untuk menghubungkan program Java dengan database MySQL. Class ini berisi pengaturan koneksi database dan method untuk menjalankan perintah SQL. Method selectQuery() digunakan untuk mengambil data dari database, sedangkan insertUpdateDeleteQuery() digunakan untuk menambahkan, mengubah, dan menghapus data. Dengan adanya class ini, pengelolaan koneksi database tidak perlu ditulis berulang kali di dalam class PeopleMenu.

## 6. db_people.sql
File db_people.sql berisi perintah SQL untuk membuat database db_people, membuat tabel person, dan memasukkan data awal mahasiswa. Tabel person memiliki lima kolom, yaitu ID, Nama, Tahun Lahir, Kategori, dan Prodi. File ini digunakan untuk menyiapkan database yang dibutuhkan sebelum program dijalankan.

Dengan adanya class Database.java dan file db_people.sql, program TP5 dapat mengambil dan mengelola data mahasiswa melalui database MySQL. Berbeda dengan TP4 yang menggunakan ArrayList untuk menyimpan data selama program berjalan, TP5 menyimpan data di database sehingga perubahan yang dilakukan dapat tetap tersimpan meskipun program ditutup.

# Penjelasan Program
## Alur Program
Secara garis besar, saat program dijalankan, constructor PeopleMenu() akan mengatur pilihan Kategori dan Prodi, membuat objek Database untuk menghubungkan program dengan database MySQL, serta memanggil method setTable() untuk mengambil data mahasiswa dari database dan menampilkannya ke dalam tabel. Setelah itu, user dapat menggunakan form dan tombol yang tersedia untuk mengelola data mahasiswa.
Ketika user ingin menambahkan data, inputan dari form akan diperiksa terlebih dahulu melalui proses validasi. Validasi ini dilakukan untuk memastikan ID dan Nama tidak kosong, Tahun Lahir berada dalam rentang yang ditentukan, serta ID tidak sama dengan ID mahasiswa lain. Jika data sudah memenuhi ketentuan, program akan menyimpannya ke dalam database MySQL.
Jika user memilih salah satu baris mahasiswa pada tabel, data mahasiswa tersebut akan ditampilkan kembali pada form sehingga dapat diubah atau dihapus. Pengubahan data dilakukan melalui tombol Ubah, sedangkan penghapusan data dilakukan melalui tombol Hapus. Sebelum data dihapus, program akan menampilkan konfirmasi untuk memastikan bahwa user benar-benar ingin menghapus data yang dipilih.
Setelah operasi tambah, ubah, atau hapus berhasil dilakukan, method setTable() akan dipanggil kembali agar tabel menampilkan data terbaru dari database. Sementara itu, tombol Batal dapat digunakan untuk mengosongkan form dan membatalkan pemilihan data pada tabel.

## Urutan Eksekusi
1. Constructor PeopleMenu() dijalankan ketika objek PeopleMenu dibuat.
2. Program membuat objek Database untuk menghubungkan aplikasi Java dengan database MySQL.
3. Constructor mengatur pilihan pada kategoriComboBox dan prodiComboBox.
4. Method setTable() dipanggil untuk mengambil data dari tabel person di database dan menampilkannya pada mahasiswaTable.
5. Constructor memasang event listener agar tombol dan pemilihan baris tabel dapat menjalankan fungsi yang sesuai.
6. Ketika user menekan tombol Tambah, Ubah, Hapus, atau Batal, program menjalankan method yang sesuai dengan tindakan tersebut.
7. Sebelum data ditambahkan atau diubah, program memeriksa input melalui proses validasi agar data yang dimasukkan sesuai dengan ketentuan.
8. Setelah operasi tambah, ubah, atau hapus berhasil dilakukan, method setTable() dipanggil kembali untuk memperbarui tampilan tabel berdasarkan data terbaru dari database.

# Dokumentasi
## Dokumentasi Program

### 1. Tampilan Awal
![Data Awal](Dokumentasi/1_Data_Awal.png)

### 2. Sebelum Menambahkan Data
![Sebelum Add Data](Dokumentasi/2_Sebelum_AddData.png)

### 3. Berhasil Menambahkan Data
![Berhasil Add Data](Dokumentasi/3_Berhasil_AddData.png)

### 4. Sebelum Mengubah Data
![Sebelum Ubah Data](Dokumentasi/4_Sebelum_UbahData.png)

### 5. Berhasil Mengubah Data
![Berhasil Ubah Data](Dokumentasi/5_Berhasil_UbahData.png)

### 6. Memilih Data yang Akan Dihapus
![Pilih Data Hapus](Dokumentasi/6_Pilih_DataHapus.png)

### 7. Konfirmasi Penghapusan
![Konfirmasi Hapus](Dokumentasi/7_Konfirmasi_HapusData.png)

### 8. Berhasil Menghapus Data
![Berhasil Hapus](Dokumentasi/8_Berhasil_HapusData.png)

### 9. Sebelum Clear Form
![Sebelum Clear Form](Dokumentasi/9_Sebelum_ClearForm.png)

### 10. Setelah Clear Form
![Clear Form](Dokumentasi/10_Clear_Form.png)

### 11. Validasi ID Kosong
![ID Kosong](Dokumentasi/11_If_ID_Kosong.png)

### 12. Validasi Nama Kosong
![Nama Kosong](Dokumentasi/12_If_Nama_Kosong.png)

### 13. Validasi Tahun Lahir di Luar Rentang
![Tahun Lahir di Luar Rentang](Dokumentasi/13_If_Tahun_Lebihdari.png)

### 14. Validasi Tahun Lahir Bukan Angka
![Tahun Lahir Bukan Angka](Dokumentasi/14_If_Tahun_bukan_angka.png)

### 15. Validasi ID Duplikat
![ID Duplikat](Dokumentasi/15_If_ID_Sama.png)
