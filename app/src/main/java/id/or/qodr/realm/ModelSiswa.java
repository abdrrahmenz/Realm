package id.or.qodr.realm;

/**
 * Created by adul on 15/01/17.
 */

public class ModelSiswa {
    int id;
    String nama, alamat;

    public ModelSiswa(int ids, String namas, String alamats) {
        this.id = ids;
        this.nama = namas;
        this.alamat = alamats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
