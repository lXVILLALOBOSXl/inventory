package com.example.maquetado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuAnadirActivity extends AppCompatActivity {
    Button producto,tipo,categoria,unidad,cantidad,marca;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuanadir);
        producto = findViewById(R.id.btnProductoAnadir);
        tipo = findViewById(R.id.btnTipoAnadir);
        categoria = findViewById(R.id.btnCategoriaAnadir);
        unidad = findViewById(R.id.btnUnidadAnadir);
        cantidad = findViewById(R.id.btnCantidadAnadir);
        marca = findViewById(R.id.btnMarcaAnadir);
    }

    public void productoMenuAnadir(View view) {
        Intent cambio = new Intent(this, AnadirActivity.class);
        startActivity(cambio);
    }

    public void tipoMenuAnadir(View view) {
        Intent cambio = new Intent(this, TipoActivity.class);
        startActivity(cambio);
    }

    public void categoriaMenuAnadir(View view) {
        Intent cambio = new Intent(this, CategoriaActivity.class);
        startActivity(cambio);
    }

    public void unidadMenuAnadir(View view) {
        Intent cambio = new Intent(this, UnidadActivity.class);
        startActivity(cambio);
    }

    public void cantidadMenuAnadir(View view) {
        Intent cambio = new Intent(this, CantidadActivity.class);
        startActivity(cambio);
    }

    public void marcaMenuAnadir(View view) {
        Intent cambio = new Intent(this, MarcaActivitiy.class);
        startActivity(cambio);
    }
}
