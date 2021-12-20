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

public class MarcaActivitiy extends AppCompatActivity {
    EditText nombreMarca,paisMarca,sitioWebMarca,telefonoMarca;
    Button guardarCambiosMarca,limpiarMarca;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);
        nombreMarca = findViewById(R.id.etNombreMarca);
        paisMarca = findViewById(R.id.etPaisMarca);
        sitioWebMarca = findViewById(R.id.etSitioWebMarca);
        telefonoMarca = findViewById(R.id.etTelefonoMarca);
        guardarCambiosMarca = findViewById(R.id.btnGuardarCambiosMarca);
        limpiarMarca = findViewById(R.id.btnLimpiarMarca);
    }


    public void guardarCambiosMarca(View view) {
        if(nombreMarca.getText().toString().isEmpty()||paisMarca.getText().toString().isEmpty()||sitioWebMarca.getText().toString().isEmpty()||telefonoMarca.getText().toString().isEmpty()){
            Toast alertaDatoFaltante = Toast.makeText(MarcaActivitiy.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();
        }else{
            ejecutarServicio("http://192.168.1.82/inventarionueva/insertarmarca.php");
        }
    }

    public void limpiarMarca(View view) {
        nombreMarca.setText("");
        sitioWebMarca.setText("");
        paisMarca.setText("");
        telefonoMarca.setText("");
        Toast camposLimpios = Toast.makeText(MarcaActivitiy.this, "Campos limpios.", Toast.LENGTH_SHORT);
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
                parametros.put("idMarcaProducto","NULL");
                parametros.put("nombreMarca",nombreMarca.getText().toString());
                parametros.put("paisMarca",paisMarca.getText().toString());
                parametros.put("sitioWebMarca",sitioWebMarca.getText().toString());
                parametros.put("telefonoMarca",telefonoMarca.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
