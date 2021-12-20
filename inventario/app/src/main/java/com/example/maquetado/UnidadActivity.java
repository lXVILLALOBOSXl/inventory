package com.example.maquetado;

import android.os.Bundle;
import android.view.Gravity;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UnidadActivity extends AppCompatActivity {
    EditText nombreUnidad;
    Button guardarCambiosUnidad,limpiarUnidad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidad);
        nombreUnidad = findViewById(R.id.etUnidadMain);
        guardarCambiosUnidad = findViewById(R.id.btnGuardarCambiosCategoria);
        limpiarUnidad = findViewById(R.id.btnLimpiarCategoria);
    }


    public void guardarCambiosUnidad(View view) {
        if(nombreUnidad.getText().toString().isEmpty()){
            Toast alertaDatoFaltante = Toast.makeText(UnidadActivity.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();
        }else{
            ejecutarServicio("http://192.168.1.82/inventarionueva/insertarunidad.php");
        }
    }

    public void limpiarUnidad(View view) {
        nombreUnidad.setText("");
        Toast camposLimpios = Toast.makeText(UnidadActivity.this, "Campos limpios.", Toast.LENGTH_SHORT);
        camposLimpios.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
        camposLimpios.show();
    }


    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Cambios realizados", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("idTipoUnidadProducto","NULL");
                parametros.put("unidad",nombreUnidad.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
