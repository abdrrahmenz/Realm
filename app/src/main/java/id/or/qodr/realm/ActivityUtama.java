package id.or.qodr.realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ActivityUtama extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RealmHelper realmHelper;
    private ArrayList<ModelSiswa> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });

        data = new ArrayList<>();
        realmHelper = new RealmHelper(ActivityUtama.this);

        recyclerView = (RecyclerView) findViewById(R.id.rvSiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setRecylerView();

    }

    private void setRecylerView() {
        try {
            data = realmHelper.findAllSiswa();
        }catch (Exception e) {
            e.printStackTrace();
        }

        AdapterSiswa adapterSiswa = new AdapterSiswa(data, new AdapterSiswa.OnItemClickListener() {
            @Override
            public void onClick(ModelSiswa item) {
                //edit sama hapus ketika item dklik
                Intent intent = new Intent(getApplicationContext(), EditSiswa.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("nama", item.getNama());
                intent.putExtra("alamat", item.getAlamat());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapterSiswa);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            data = realmHelper.findAllSiswa();
        }catch (Exception e) {
            e.printStackTrace();
        }

        setRecylerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
