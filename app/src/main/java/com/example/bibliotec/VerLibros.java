package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerLibros extends AppCompatActivity {

    List<Libros> productosList;
    RecyclerView recyclerView;
    EditText lib, fecha_prestamoP, fecha_devolucionP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libros);

        lib = findViewById(R.id.lib);
        fecha_prestamoP = findViewById(R.id.fech_prestamoP);
        fecha_devolucionP = findViewById(R.id.fech_devolucionP);
        String url = "http://192.168.1.3/bibliotec/librosDisponibles.php";

        recyclerView = findViewById(R.id.reLibros);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productosList = new ArrayList<>();
        loader(url);
    }

    public void Home(View view) {

        final String libro = this.lib.getText().toString();
        final String fechap = this.fecha_prestamoP.getText().toString();
        final String fechad = this.fecha_devolucionP.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.3/bibliotec/pedir.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("exito")) {
                    Toast.makeText(VerLibros.this, "Prestamo correcto", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(VerLibros.this, "Error: datos son incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerLibros.this, "Error comunicación", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("libro", libro);
                params.put("fechap", fechap);
                params.put("fechad", fechad);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(VerLibros.this);
        requestQueue.add(stringRequest);

    }

    private void loader(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject Libros = array.getJSONObject(i);

                                productosList.add(new Libros(
                                        Libros.getString("titulo"),
                                        Libros.getString("autor"),
                                        Libros.getString("editorial"),
                                        Libros.getString("descripcion"),
                                        Libros.getString("foto")
                                ));
                            }
                            LibrosAdapter adapter = new LibrosAdapter(VerLibros.this, productosList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);

    }
}