package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

    List<Prestamos> productosList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Intent intent = getIntent();
        String url = "http://192.168.1.3/bibliotec/inicio.php?correo=" + intent.getStringExtra(MainActivity.EXTRA_TEXT).trim();

        recyclerView = findViewById(R.id.re);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productosList = new ArrayList<>();
        loader(url);
    }

    public void Ver(View view) {
        Intent i = new Intent(Inicio.this, VerLibros.class);
        startActivity(i);
    }

    private void loader(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject Prestamos = array.getJSONObject(i);

                                productosList.add(new Prestamos(
                                        Prestamos.getString("titulo"),
                                        Prestamos.getString("fecha_prestamo"),
                                        Prestamos.getString("fecha_devolucion"),
                                        Prestamos.getString("foto")
                                ));
                            }
                            Adapter adapter = new Adapter(Inicio.this, productosList);
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