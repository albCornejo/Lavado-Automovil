package cl.duoc.lavadovehiculo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alberto on 25-11-2016.
 */

public class lavadoAdapter extends RecyclerView.Adapter<lavadoAdapter.ViewHolder> {

    private ArrayList<Lavado> dataSet;
    private Context context;

    public lavadoAdapter(ArrayList<Lavado> dataSet, Context context){
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item__lavado, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final Lavado lavado = dataSet.get(position);

        viewHolder.txtTipoV.setText(lavado.getTipoV());
        viewHolder.txtPatente.setText(lavado.getPatente());
        viewHolder.txtValorLavado.setText(lavado.getValorLavado());
        viewHolder.txtFechaLavado.setText(lavado.getFechaLavado());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtTipoV;
        public TextView txtPatente;
        public TextView txtValorLavado;
        public TextView txtFechaLavado;

        public ViewHolder(View v) {
            super(v);
            txtTipoV = (TextView) v.findViewById(R.id.txtTipoV);
            txtPatente = (TextView) v.findViewById(R.id.txtPatente);
            txtValorLavado = (TextView) v.findViewById(R.id.txtValorLavado);
            txtFechaLavado = (TextView) v.findViewById(R.id.txtFechaLavado);
        }
    }
}
