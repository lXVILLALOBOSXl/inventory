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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TipoActivity extends AppCompatActivity {

    EditText nombreTipo,descripcionTipo;
    Button guardarCambiosTipo,limpiarTipo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo);
        nombreTipo = findViewById(R.id.etNombreTipo);
        descripcionTipo = findViewById(R.id.etDiscripcionTipo);
        guardarCambiosTipo = findViewById(R.id.btnGuardarCambiosTipo);
        limpiarTipo = findViewById(R.id.btnLimpiarTipo);
    }


    public void guardarCambiosTipo(View view) {
        if(nombreTipo.getText().toString().isEmpty() || descripcionTipo.getText().toString().isEmpty()){
            Toast alertaDatoFaltante = Toast.makeText(TipoActivity.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();

        }else{
            ejecutarServicio("http://192.168.1.82/inventarionueva/insertartipo.php");
        }
    }

    public void limpiarTipo(View view) {
        nombreTipo.setText("");
        descripcionTipo.setText("");
        Toast camposLimpios = Toast.makeText(TipoActivity.this, "Campos limpios.", Toast.LENGTH_SHORT);
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
                parametros.put("idTipoProducto","NULL");
                parametros.put("nombreProducto",nombreTipo.getText().toString());
                parametros.put("descripcionProducto",descripcionTipo.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
