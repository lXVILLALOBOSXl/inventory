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

public class CantidadActivity extends AppCompatActivity {
    EditText nombreCantidad;
    Button guardarCambiosCantidad,limpiarCantidad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantidad);
        nombreCantidad = findViewById(R.id.etCantidad);
        guardarCambiosCantidad = findViewById(R.id.btnGuardarCambiosCantidad);
        limpiarCantidad = findViewById(R.id.btnLimpiarCantidad);
    }

    public void guardarCambiosCantidad(View view) {
        if(nombreCantidad.getText().toString().isEmpty()){
            Toast alertaDatoFaltante = Toast.makeText(CantidadActivity.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();
        }else{
            ejecutarServicio("http://192.168.1.82/inventarionueva/insertarcantidad.php");
        }
    }

    public void limpiarCantidad(View view) {
        nombreCantidad.setText("");
        Toast camposLimpios = Toast.makeText(CantidadActivity.this, "Campos limpios.", Toast.LENGTH_SHORT);
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
                parametros.put("idTipoCantidadProducto","NULL");
                parametros.put("cantidad",nombreCantidad.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
