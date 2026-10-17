import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PeopleMenu extends JFrame {
    private JTextField idField;
    private JPanel mainPanel;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> kategoriComboBox;
    private JComboBox<String> prodiComboBox;
    private JTable mahasiswaTable;
    private JButton tambahButton;
    private JButton ubahButton;
    private JButton hapusButton;
    private JButton batalButton;

    //nambah atribut database
    private Database database;

    //Constructor buat mengisi pilihan kategori dan prodi
    public PeopleMenu()
    {
        database = new Database();

        //isi pilihan kategori
        kategoriComboBox.addItem("Aktif");
        kategoriComboBox.addItem("Cuti");
        kategoriComboBox.addItem("Alumni");
        kategoriComboBox.addItem("Mahasiswa Baru");

        //isi pilihan prodi
        prodiComboBox.addItem("Ilmu Komputer");
        prodiComboBox.addItem("Sistem Informasi");
        prodiComboBox.addItem("Kecerdasan Buatan");

        //tampiljan tabel
        setTable();

        //listener tombol
        tambahButton.addActionListener(e -> insertData());
        ubahButton.addActionListener(e -> updateData());
        hapusButton.addActionListener(e -> deleteData());
        batalButton.addActionListener(e -> clearForm());

        //listener tabel
        mahasiswaTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = mahasiswaTable.getSelectedRow();

                if (row != -1) {
                    selectedIndex = row;

                    idField.setText(mahasiswaTable.getValueAt(row, 0).toString());
                    namaField.setText(mahasiswaTable.getValueAt(row, 1).toString());
                    tahunLahirField.setText(mahasiswaTable.getValueAt(row, 2).toString());
                    kategoriComboBox.setSelectedItem(mahasiswaTable.getValueAt(row, 3).toString());
                    prodiComboBox.setSelectedItem(mahasiswaTable.getValueAt(row, 4).toString());
                }
            }
        });


    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Data Mahasiswa");
        PeopleMenu peopleMenu = new PeopleMenu();

        frame.setContentPane(peopleMenu.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(700, 550);
        frame.setMinimumSize(new java.awt.Dimension(600, 450));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //method menampilkan data mahasiswa
    public void setTable()
    {
        String[] columnNames = {
                "ID", "Nama", "Tahun Lahir", "Kategori", "Prodi"
        };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try {
            String sql = "SELECT * FROM person";
            java.sql.ResultSet rs = database.selectQuery(sql);

            while (rs.next())
            {
                Object[] rowData = {
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getInt("tahun_lahir"),
                        rs.getString("kategori"),
                        rs.getString("prodi")
                };
                model.addRow(rowData);
            }
            rs.close();
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Gagal mengambil data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        mahasiswaTable.setModel(model);
    }


    //METHOD BUAT INSERT DATA
    public void insertData() {
        try {
            //ambil value textField dan comboBox
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            int tahunLahir = Integer.parseInt(tahunLahirField.getText().trim());
            String kategori = (String) kategoriComboBox.getSelectedItem();
            String prodi = (String) prodiComboBox.getSelectedItem();

            //validasi data
            if (!validasiData(id, nama, tahunLahir, null)) {
                return;
            }

            //tambah data ke database
            String sqlQuery = "INSERT INTO person " + "(id, nama, tahun_lahir, kategori, prodi) VALUES ('"
                    + id + "', '"
                    + nama + "', "
                    + tahunLahir + ", '"
                    + kategori + "', '"
                    + prodi + "')";
            database.insertUpdateDeleteQuery(sqlQuery);

            //update table
            setTable();

            //clear form
            clearForm();

            //feedback
            JOptionPane.showMessageDialog(
                    mainPanel, "Data berhasil ditambahkan!"
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    mainPanel, "Tahun lahir harus berupa angka!", "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
            );
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                    mainPanel, "Gagal menambahkan data!", "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }


    //clearForm ATAU METHOD BATALKAN
    public void clearForm()
    {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");

        kategoriComboBox.setSelectedIndex(0);
        prodiComboBox.setSelectedIndex(0);

        mahasiswaTable.clearSelection();
        selectedIndex = -1;
    }

    //variabel untuk nyimpen baris yg terpilih. -1 artinya belum ada yang dipilih
    private int selectedIndex = -1;


    //METHOD BUAT UPDATE DATA
    public void updateData() {
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(
                    mainPanel, "Pilih data mahasiswa yang ingin diubah!", "Peringatan", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            int tahunLahir = Integer.parseInt(tahunLahirField.getText().trim());
            String kategori = (String) kategoriComboBox.getSelectedItem();
            String prodi = (String) prodiComboBox.getSelectedItem();

            //ambil id lama dari baris yang dipilih
            int row = mahasiswaTable.getSelectedRow();

            if(row == -1){
                JOptionPane.showMessageDialog(
                        mainPanel, "Pilih data mahasiswa yang ingin diubah!", "Peringatan", JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            String idLama = mahasiswaTable.getValueAt(row, 0).toString();

            //validasi data
            if (!validasiData(id, nama, tahunLahir, idLama)){
                return;
            }
            //update data di database
            String sqlQuery = "UPDATE person SET "
                    + "id = '" + id + "', "
                    + "nama = '" + nama + "', "
                    + "tahun_lahir = " + tahunLahir + ", "
                    + "kategori = '" + kategori + "', "
                    + "prodi = '" + prodi + "' "
                    + "WHERE id = '" + idLama + "'";

            database.insertUpdateDeleteQuery(sqlQuery);

            setTable();
            clearForm();

            JOptionPane.showMessageDialog(
                    mainPanel, "Data mahasiswa berhasil diubah!"
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    mainPanel, "Tahun lahir harus berupa angka!",  "Input tidak valid",  JOptionPane.WARNING_MESSAGE
            );
        } catch (RuntimeException e){
            JOptionPane.showMessageDialog(
                    mainPanel, "Gagal mengubah data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }


    //METHOD BUAT DELETE DATA
    public void deleteData()
    {
        if (selectedIndex == -1)
        {
            JOptionPane.showMessageDialog(
                    mainPanel, "Pilih data mahasiswa yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(
                mainPanel,
                "Yakin ingin menghapus data mahasiswa ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (konfirmasi == JOptionPane.YES_OPTION)
        {
            try
            {
                int row = mahasiswaTable.getSelectedRow();
                String id = mahasiswaTable.getValueAt(row, 0).toString();

                String sqlQuery = "DELETE FROM person WHERE id = '" + id + "'";

                database.insertUpdateDeleteQuery(sqlQuery);

                setTable();
                clearForm();

                JOptionPane.showMessageDialog(
                        mainPanel, "Data mahasiswa berhasil dihapus!"
                );
            } catch (RuntimeException e){
                JOptionPane.showMessageDialog(
                        mainPanel, "Gagal menghapus data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    //VALIDASI DATA YEY
    private boolean validasiData(String id, String nama, int tahunLahir, String idLama) {
        //jika kolom id dan nama kosong maka tidak valid
        if (id.isEmpty() || nama.isEmpty()) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "ID dan Nama tidak boleh kosong!",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        //jika tahun lahir melebihi batas ini maka gak valid
        if (tahunLahir < 1900 || tahunLahir > 2026) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Tahun lahir harus antara 1900 dan 2026!",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        try {
            String sqlQuery = "SELECT id FROM person WHERE id = '" + id + "'";
            java.sql.ResultSet rs = database.selectQuery(sqlQuery);

            boolean idDuplikat = rs.next();
            rs.close();

            //id tidak boleh ada yang sama atau duplikat
            if (idDuplikat && (idLama == null || !id.equals(idLama))) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "ID mahasiswa sudah digunakan!",
                        "Input tidak valid",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Gagal memvalidasi data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        return true;
    }


}
