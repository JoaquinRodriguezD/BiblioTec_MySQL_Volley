package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AgregarLibro extends AppCompatActivity {

    private static final String url = "http://192.168.1.3/bibliotec/agregarLibro.php";
    EditText titu, aut, edit, edic, is, fech, desc, est, rese;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);

        titu = findViewById(R.id.titulo);
        aut = findViewById(R.id.autor);
        edit = findViewById(R.id.editorial);
        edic = findViewById(R.id.edicion);
        is = findViewById(R.id.isbn);
        fech = findViewById(R.id.fechapub);
        desc = findViewById(R.id.descripcion);
        est = findViewById(R.id.estado);
        rese = findViewById(R.id.reserva);
    }

    public void RegistrarL(View v) {

        final String titulo = this.titu.getText().toString();
        final String autor = this.aut.getText().toString();
        final String editorial = this.edit.getText().toString();
        final String edicion = this.edic.getText().toString().trim();
        final String isbn = this.is.getText().toString();
        final String fechapu = this.fech.getText().toString().trim();
        final String descripcion = this.desc.getText().toString();
        final String estado = this.est.getText().toString();
        final String reserva = this.rese.getText().toString();

        if (titulo.equals("") || autor.equals("") || editorial.equals("") || edicion.equals("") || isbn.equals("") || fechapu.equals("") || descripcion.equals("") || estado.equals("") || reserva.equals("")) {

            titu.setError("Error: Campo vacio!");
            aut.setError("Error: Campo vacio!");
            edit.setError("Error: Campo vacio!");
            edic.setError("Error: Campo vacio!");
            is.setError("Error: Campo vacio!");
            fech.setError("Error: Campo vacio!");
            desc.setError("Error: Campo vacio!");
            est.setError("Error: Campo vacio!");
            rese.setError("Error: Campo vacio!");

        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                public void onResponse(String response) {
                    if (response.equals("exito")) {
                        Toast.makeText(AgregarLibro.this, "Nuevo libro agregado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AgregarLibro.this, "Error: el libro no se agrego correctamente", Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {

                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AgregarLibro.this, "Error: De comunicación" + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("titulo", titulo);
                    params.put("autor", autor);
                    params.put("editorial", editorial);
                    params.put("edicion", edicion);
                    params.put("isbn", isbn);
                    params.put("fechapu", fechapu);
                    params.put("descripcion", descripcion);
                    params.put("estado", estado);
                    params.put("reserva", reserva);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(AgregarLibro.this);
            requestQueue.add(stringRequest);
        }
    }
}
