package id.or.qodr.realm;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by adul on 15/01/17.
 */

public class RealmHelper {
    private static final String TAG = "RealmHelper";

    private Realm realm;
    private RealmResults<Siswa> realmResults;
    public Context context;

    public RealmHelper(Context context) {
        realm = Realm.getInstance(context);
        this.context = context;
    }

    //create
    public void addSiswa(String nama, String alamat) {
        Siswa siswa = new Siswa();
        siswa.setId((int) (System.currentTimeMillis() / 1000));
        siswa.setNama(nama);
        siswa.setAlamat(alamat);

        realm.beginTransaction();
        realm.copyToRealm(siswa);
        realm.commitTransaction();
        ShowLog("data " + nama + " Berhasil disimpan");
    }

    public void deleteSiswa(int id) {
        RealmResults<Siswa> dataResult = realm.where(Siswa.class)
                .equalTo("id", id).findAll();
        realm.beginTransaction();

        dataResult.remove(0);
        dataResult.removeLast();
        dataResult.clear();

        realm.commitTransaction();
        ShowLog("Data Berhasil Dihapus");
    }

    public void updateSiswa(int id, String nama, String alamat) {
        realm.beginTransaction();
        Siswa siswa = realm.where(Siswa.class)
                .equalTo("id", id).findFirst();
        siswa.setNama(nama);
        siswa.setAlamat(alamat);

        realm.commitTransaction();
        ShowLog("Data Berhasil diupdate");
    }

    public ArrayList<ModelSiswa> findAllSiswa() {
        ArrayList<ModelSiswa> data = new ArrayList<>();

        realmResults = realm.where(Siswa.class).findAll();
        realmResults.sort("id", Sort.DESCENDING);

        if (realmResults.size() > 0) {
            ShowLog("Size : " + realmResults.size());
            for (int i = 0; i < realmResults.size(); i++) {
                String nama, alamat;
                int id = realmResults.get(i).getId();
                nama = realmResults.get(i).getNama();
                alamat = realmResults.get(i).getAlamat();
                data.add(new ModelSiswa(id, nama, alamat));
            }

        }else {
            ShowLog("size = 0");
        }

        return data;
    }

    //import android.content.Context;
    private void ShowLog(String s) {
        Log.d(TAG, s);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}
