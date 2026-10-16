public class Person
{
    private String ID;
    private String Nama;
    private int TahunLahir;
    private String Kategori;
    private String Prodi;

    //constructor
    public Person(String ID, String Nama, int TahunLahir, String Kategori, String Prodi)
    {
        this.ID = ID;
        this.Nama = Nama;
        this.TahunLahir = TahunLahir;
        this.Kategori = Kategori;
        this.Prodi = Prodi;
    }

    //Getter
    public String getId()
    {
        return ID;
    }
    public String getNama()
    {
        return Nama;
    }
    public int getTahunLahir()
    {
        return TahunLahir;
    }
    public String getKategori()
    {
        return Kategori;
    }
    public String getProdi()
    {
        return Prodi;
    }

    //Setter
    public void setId(String ID)
    {
        this.ID = ID;
    }
    public void setNama(String Nama)
    {
        this.Nama = Nama;
    }
    public void setTahunLahir(int TahunLahir)
    {
        this.TahunLahir = TahunLahir;
    }
    public void setKategori(String Kategori)
    {
        this.Kategori = Kategori;
    }
    public void setProdi(String Prodi)
    {
        this.Prodi = Prodi;
    }
}
