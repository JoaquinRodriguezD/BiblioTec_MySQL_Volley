package com.example.bibliotec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.PlayerViewholder> {


    private final Context mCtx;
    private final List<Libros> productosList;

    public LibrosAdapter(Context mCtx, List<Libros> productosList) {
        this.mCtx = mCtx;
        this.productosList = productosList;
    }

    @NonNull
    @Override
    public PlayerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.libros_disponibles, null);
        return new PlayerViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewholder holder, int position) {
        Libros productos = productosList.get(position);

        Glide.with(mCtx)
                .load(productos.getImagen())
                .into(holder.im);
        holder.c1.setText(productos.getTitulo());
        holder.c2.setText(productos.getAutor());
        holder.c3.setText(productos.getEditorial());
        holder.c4.setText(productos.getDescripcion());

    }

    @Override
    public int getItemCount() {

        return productosList.size();
    }


    class PlayerViewholder extends RecyclerView.ViewHolder {

        TextView c1, c2, c3, c4;
        ImageView im;

        public PlayerViewholder(@NonNull View itemView) {


            super(itemView);

            c1 = itemView.findViewById(R.id.c1);
            c2 = itemView.findViewById(R.id.c2);
            c3 = itemView.findViewById(R.id.c3);
            c4 = itemView.findViewById(R.id.c4);
            im = itemView.findViewById(R.id.im);
        }
    }
}