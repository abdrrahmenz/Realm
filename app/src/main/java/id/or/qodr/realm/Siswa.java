package id.or.qodr.realm;

import io.realm.RealmObject;

/**
 * Created by adul on 15/01/17.
 */

public class Siswa extends RealmObject {
    private int id;
    private String nama, alamat;

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
