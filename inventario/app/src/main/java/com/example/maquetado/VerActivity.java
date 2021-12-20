package com.example.maquetado;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.maquetado.Adaptadores.adaptadorVer;
import com.example.maquetado.Global.arrayListEmpresa;
import com.example.maquetado.Pojos.pojosContactos;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

public class VerActivity extends AppCompatActivity{

    TextView codigo,nombre,marca,cantidad,presentacion,descripcion,unidades,precio,categoria;
    String telefono;
    ImageView imagenProducto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        codigo = findViewById(R.id.lblCodigo);
        nombre = findViewById(R.id.lblNombre);
        marca= findViewById(R.id.lblMarca);
        cantidad = findViewById(R.id.lblCantidad);
        presentacion = findViewById(R.id.lblPresentacion);
        descripcion = findViewById(R.id.lblDescripcion);
        unidades = findViewById(R.id.lblUnidades);
        precio = findViewById(R.id.lblPrecio);
        categoria = findViewById(R.id.lblCategoria);
        imagenProducto = findViewById(R.id.ivProductotarjeta);
        pojosContactos empresa = new pojosContactos();

        int posicion;
        posicion = getIntent().getIntExtra("posicion",-1);
        getTel("http://192.168.1.82/inventarionueva/gettel.php?codigoProducto="+posicion+"");
        empresa.setNumero(telefono);
        arrayListEmpresa.info.add(empresa);
        buscarProducto("http://192.168.1.82/inventarionueva/setproducto.php?codigoProducto="+posicion+"");

    }

    public void buscarProducto(String url){
        JsonArrayRequest jsonArrarRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        codigo.setText(jsonObject.getString("codigoInterno"));
                        nombre.setText(jsonObject.getString("nombreProducto"));
                        marca.setText(jsonObject.getString("nombreMarca"));
                        cantidad.setText(jsonObject.getString("cantidad"));
                        presentacion.setText(jsonObject.getString("unidad"));
                        unidades.setText(jsonObject.getString("piezasProducto"));
                        precio.setText(jsonObject.getString("precioProducto"));
                        categoria.setText(jsonObject.getString("categoria"));
                        descripcion.setText(jsonObject.getString("descripcionProducto"));
                        Picasso
                                .get()
                                .load(jsonObject.getString("imagenProducto"))
                                .into(imagenProducto);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrarRequest);
    }

    public void getTel(String url){
        JsonArrayRequest jsonArrarRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        telefono = jsonObject.getString("telefonoMarca");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrarRequest);
    }

    public void llamar(View view) {
        Intent llamada =new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+telefono));

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 10);
            return;
        }
        startActivity(llamada);
    }
}
