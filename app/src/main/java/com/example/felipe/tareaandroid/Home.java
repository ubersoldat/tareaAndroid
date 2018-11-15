package com.example.felipe.tareaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private CardView usuariosCard, imagenesCard, mensajesCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // definimos los card
        usuariosCard = (CardView)findViewById(R.id.usuarios_card);
        imagenesCard = (CardView)findViewById(R.id.imagenes_card);
        mensajesCard = (CardView)findViewById(R.id.mensajes_card);

        // agregamos click listeners a los cards
        usuariosCard.setOnClickListener(this);
        imagenesCard.setOnClickListener(this);
        mensajesCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){
            case R.id.usuarios_card : i = new Intent(this,Usuario.class);startActivity(i);break;
            case R.id.imagenes_card : i = new Intent(this,Imagen.class);startActivity(i);break;
            case R.id.mensajes_card : i = new Intent(this,Mensaje.class);startActivity(i);break;
            default:break;
        }
    }
}
