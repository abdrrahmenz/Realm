package id.or.qodr.realm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adul on 15/01/17.
 */

public class AdapterSiswa extends RecyclerView.Adapter<AdapterSiswa.ViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<ModelSiswa> siswas;

    public interface OnItemClickListener {
        void onClick(ModelSiswa item);
    }

    public AdapterSiswa(ArrayList<ModelSiswa> siswas,OnItemClickListener listener) {
        this.siswas = siswas;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.click(siswas.get(position), listener);
        holder.id.setText(String.valueOf(siswas.get(position).getId()));
        holder.nama.setText(String.valueOf(siswas.get(position).getNama()));
        holder.alamat.setText(String.valueOf(siswas.get(position).getAlamat()));
    }

    @Override
    public int getItemCount() {
        return siswas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, nama, alamat;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.txtId);
            nama = (TextView) itemView.findViewById(R.id.txtnama);
            alamat = (TextView) itemView.findViewById(R.id.txtalamat);
        }

        public void click (final ModelSiswa modelSiswa, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(modelSiswa);
                }
            });
        }
    }
}
