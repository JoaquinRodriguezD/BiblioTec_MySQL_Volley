package com.example.bibliotec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.PlayerViewholder> {


    private final Context mCtx;
    private final List<Prestamos> productosList;

    public Adapter(Context mCtx, List<Prestamos> productosList) {
        this.mCtx = mCtx;
        this.productosList = productosList;
    }

    @NonNull
    @Override
    public PlayerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.lista, null);
        return new PlayerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewholder holder, int position) {
        Prestamos productos = productosList.get(position);

        Glide.with(mCtx)
                .load(productos.getImagen())
                .into(holder.img);
        holder.tv1.setText(productos.getTitulo());
        holder.tv2.setText(productos.getFecha_prestamo());
        holder.tv3.setText(productos.getFecha_devolucion());

    }

    @Override
    public int getItemCount() {

        return productosList.size();
    }


    class PlayerViewholder extends RecyclerView.ViewHolder {

        TextView tv1, tv2, tv3;
        ImageView img;

        public PlayerViewholder(@NonNull View itemView) {


            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            img = itemView.findViewById(R.id.img);
        }
    }


}
