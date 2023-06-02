package com.example.park.models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.park.R;
import com.example.park.views.AdaptadorCarrosInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorCarros extends RecyclerView.Adapter<AdaptadorCarros. MyViewHolder> {
    private final Context context;
    private static List<ParquearCarroModelo> parquearCarroModeloList = null;

    private static AdaptadorCarrosInterface listener;

    public AdaptadorCarros(Context context, AdaptadorCarrosInterface listener) {
        this.context = context;
        this.parquearCarroModeloList = new ArrayList<>();
        this.listener = listener;
    }

    /*public AdaptadorCarros(Context context) {
        this.context = context;
        parquearCarroModeloList = new ArrayList<>();
    }*/

    @SuppressLint("NotifyDataSetChanged")
    public void add(ParquearCarroModelo parquearCarroModelo) {
        parquearCarroModeloList.add(parquearCarroModelo);
        notifyDataSetChanged();
    }
    public void remove(int pos) {
        parquearCarroModeloList.remove(pos);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.park_item,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ParquearCarroModelo model = parquearCarroModeloList.get(position);
        holder.numero_Carro.setText(model.getNumero_Carro());
        holder.nombre_Carro.setText(model.getNombre_Carro());
        holder.tipo_vehiculo_Carro.setText(model.getTipo_Vehiculo_Carro());
        holder.placa_Carro.setText(model.getPlaca_Carro());
        holder.tarifa_Carro.setText(model.getTarifa_Carro());
        holder.status_Carro.setText(model.getStatus_Carro());

        String[] dateAndtime=longIntoString(model.getTiempo_Carro());
        holder.tiempo_Carro.setText(dateAndtime[0]+"\n"+dateAndtime[1]);

    }

    @Override
    public int getItemCount() {
        return parquearCarroModeloList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  tipo_vehiculo_Carro, nombre_Carro, numero_Carro,
                placa_Carro, tarifa_Carro, tiempo_Carro, status_Carro;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tipo_vehiculo_Carro = itemView.findViewById(R.id.tipo_vehiculo);
            nombre_Carro = itemView.findViewById(R.id.nombre);
            numero_Carro = itemView.findViewById(R.id.numero);
            placa_Carro = itemView.findViewById(R.id.placa);
            tarifa_Carro = itemView.findViewById(R.id.tarifa);
            tiempo_Carro = itemView.findViewById(R.id.hora);
            status_Carro = itemView.findViewById(R.id.status);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnLongClick(getAdapterPosition(),
                            parquearCarroModeloList.get(getAdapterPosition()).getId_Carro());
                    return true;
                }
            });
        }
    }
    private String[] longIntoString(long milliseconds) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return new String[] {dateFormat.format(milliseconds), timeFormat.format(milliseconds)};
    }

    public void clear() {
        parquearCarroModeloList.clear();
        notifyDataSetChanged();
    }

}
