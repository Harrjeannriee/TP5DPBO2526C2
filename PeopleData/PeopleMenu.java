import javax.swing.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PeopleMenu {
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

    //tempat menyimpan data mahasiswa
    private ArrayList<Person> daftarMahasiswa = new ArrayList<>();

    //Constructor buat mengisi pilihan kategori dan prodi
    public PeopleMenu()
    {
        //isi pilihan kategori
        kategoriComboBox.addItem("Aktif");
        kategoriComboBox.addItem("Cuti");
        kategoriComboBox.addItem("Alumni");
        kategoriComboBox.addItem("Mahasiswa Baru");

        //isi pilihan prodi
        prodiComboBox.addItem("Ilmu Komputer");
        prodiComboBox.addItem("Sistem Informasi");
        prodiComboBox.addItem("Kecerdasan Buatan");

        //isi data awal
        daftarMahasiswa.add(new Person(
                "2501065", "Andina", 2006, "Aktif", "Ilmu Komputer"
        ));

        daftarMahasiswa.add(new Person(
                "2501066", "Dwi", 2005, "Cuti", "Sistem Informasi"
        ));

        daftarMahasiswa.add(new Person(
                "2501067", "Listiana", 2004, "Alumni", "Kecerdasan Buatan"
        ));

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

        for (Person person : daftarMahasiswa)
        {
            Object[] rowData = {
                    person.getId(),
                    person.getNama(),
                    person.getTahunLahir(),
                    person.getKategori(),
                    person.getProdi()
            };
            model.addRow(rowData);
        }
        mahasiswaTable.setModel(model);
    }

    //METHOD BUAT INSERT DATA

    public void insertData() {
        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            int tahunLahir = Integer.parseInt(tahunLahirField.getText().trim());
            String kategori = (String) kategoriComboBox.getSelectedItem();
            String prodi = (String) prodiComboBox.getSelectedItem();

            if (!validasiData(id, nama, tahunLahir, -1)) {
                return;
            }

            Person person = new Person(id, nama, tahunLahir, kategori, prodi);
            daftarMahasiswa.add(person);

            setTable();
            clearForm();

            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Data mahasiswa berhasil ditambahkan!"
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Tahun lahir harus berupa angka!",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
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
                    mainPanel,
                    "Pilih data mahasiswa yang ingin diubah!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            int tahunLahir = Integer.parseInt(tahunLahirField.getText().trim());
            String kategori = (String) kategoriComboBox.getSelectedItem();
            String prodi = (String) prodiComboBox.getSelectedItem();

            if (!validasiData(id, nama, tahunLahir, selectedIndex)) {
                return;
            }

            Person person = daftarMahasiswa.get(selectedIndex);

            person.setId(id);
            person.setNama(nama);
            person.setTahunLahir(tahunLahir);
            person.setKategori(kategori);
            person.setProdi(prodi);

            setTable();
            clearForm();

            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Data mahasiswa berhasil diubah!"
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Tahun lahir harus berupa angka!",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    //METHOD BUAT DELETE DATA
    public void deleteData()
    {
        if (selectedIndex == -1)
        {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Pilih data mahasiswa yang ingin dihapus!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
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
            daftarMahasiswa.remove(selectedIndex);

            setTable();
            clearForm();

            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Data mahasiswa berhasil dihapus!"
            );
        }
    }

    //METHOD VALIDASI
    private boolean validasiData(String id, String nama, int tahunLahir, int indexDiabaikan) {
        if (id.isEmpty() || nama.isEmpty()) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "ID dan Nama tidak boleh kosong!",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        if (tahunLahir < 1900 || tahunLahir > 2026) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Tahun lahir harus antara 1900 dan 2026!",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (i != indexDiabaikan &&
                    daftarMahasiswa.get(i).getId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(
                        mainPanel,
                        "ID mahasiswa sudah digunakan!",
                        "Input tidak valid",
                        JOptionPane.WARNING_MESSAGE
                );
                return false;
            }
        }
        return true;
    }


}
