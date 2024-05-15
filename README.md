# Proyek Hibernate Test

Selamat datang di Proyek Hibernate Test! Ini adalah aplikasi Java sederhana yang menunjukkan operasi dasar CRUD (Create, Read, Update, Delete) menggunakan Hibernate ORM.

## Prasyarat

Sebelum Anda menjalankan proyek ini, pastikan Anda sudah menginstal:

- Java Development Kit (JDK)
- Apache Maven
- Hibernate dan dependensi yang diperlukan

## Struktur Proyek

Komponen utama dari proyek ini adalah:

- **Main.java**: Titik masuk utama aplikasi, menangani input pengguna dan operasi basis data.
- **Student.java**: Kelas entitas yang mewakili seorang siswa.
- **HibernateUtil.java**: Kelas utilitas untuk menyiapkan Hibernate SessionFactory.

## Cara Menjalankan

**Clone repository:**
   ```sh
   git clone https://github.com/veryepicindeed/hibernatetest.git
 ```
## Penggunaan 

Setelah aplikasi berjalan, Anda akan diminta untuk melakukan berbagai operasi:

Lihat Daftar Siswa:
Aplikasi akan menampilkan daftar semua siswa di database.

Masukkan Siswa:
Pilih opsi 1 untuk menambahkan siswa baru. Anda akan diminta untuk memasukkan nama siswa, usia, dan jurusan.

Perbarui Siswa:
Pilih opsi 2 untuk memperbarui detail siswa yang ada. Anda perlu memasukkan ID siswa, kemudian detail baru.

Hapus Siswa:
Pilih opsi 3 untuk menghapus seorang siswa. Anda perlu memasukkan ID siswa untuk melanjutkan penghapusan.

Keluar:
Pilih opsi 4 untuk keluar dari aplikasi.
