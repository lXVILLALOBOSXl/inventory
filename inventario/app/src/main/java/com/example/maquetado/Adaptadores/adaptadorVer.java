package com.example.maquetado.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.maquetado.R;
import com.example.maquetado.VerActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adaptadorVer extends RecyclerView.Adapter<adaptadorVer.MiniActivity> {
    public Context context;
    int numero;

    @NonNull
    @Override
    public MiniActivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.activity_datos,null);
        MiniActivity obj = new MiniActivity(v);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull MiniActivity miniActivity, int i) {
        final int pos = i+1;
        buscarProducto("http://192.168.1.82/inventarionueva/buscalista.php?codigoProducto="+pos+"",miniActivity);
        miniActivity.linearPadre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent tarjeta  = new Intent(context, VerActivity.class);
                tarjeta.putExtra("posicion",pos);
                context.startActivity(tarjeta);
            }
        });

    }

    @Override
    public int getItemCount() {
        selectCount("http://192.168.1.82/inventarionueva/contadorobjetos.php");
        return numero;
    }

    public void buscarProducto(String url, final MiniActivity miniActivity){
        JsonArrayRequest jsonArrarRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        miniActivity.nombreProducto.setText(jsonObject.getString("nombreProducto"));
                        miniActivity.nombreMarca.setText(jsonObject.getString("nombreMarca"));
                        miniActivity.cantidadProducto.setText(jsonObject.getString("cantidad"));
                        miniActivity.unidadProducto.setText(jsonObject.getString("unidad"));
                        miniActivity.codigoProducto.setText(jsonObject.getString("codigoInterno"));
                        Picasso
                                .get()
                                .load(jsonObject.getString("imagenProducto"))
                                .into(miniActivity.imagenProducto);
                    } catch (JSONException e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrarRequest);
    }

    public void selectCount(String url){
        JsonArrayRequest jsonArrarRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        numero = jsonObject.getInt("count(*)");
                    } catch (JSONException e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrarRequest);
    }

    class MiniActivity extends RecyclerView.ViewHolder{
        public TextView nombreProducto, nombreMarca, cantidadProducto, unidadProducto, codigoProducto;
        public ImageView imagenProducto;
        public LinearLayout linearPadre;

        public MiniActivity(View itemView){
            super(itemView);
            nombreProducto = itemView.findViewById(R.id.lblNombreVer);
            nombreMarca = itemView.findViewById(R.id.lblNombreMarca);
            cantidadProducto = itemView.findViewById(R.id.lblCantidad);
            unidadProducto = itemView.findViewById(R.id.lblUnidad);
            codigoProducto = itemView.findViewById(R.id.lblCodigo);
            linearPadre = itemView.findViewById(R.id.linearPadre);
            imagenProducto = itemView.findViewById(R.id.ivProducto);
        }
    }
}
