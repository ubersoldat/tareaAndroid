package com.example.felipe.tareaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.felipe.tareaandroid.GsonRequest;
import com.example.felipe.tareaandroid.Post;

public class Mensaje extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Button button;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        //controles
        button = ((Button) findViewById(R.id.button));
        textView2 = ((TextView) findViewById(R.id.textView2));
        textView3 = ((TextView) findViewById(R.id.textView3));
        //request
        requestQueue= Volley.newRequestQueue(Mensaje.this);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        button.setEnabled(false);
                        GsonRequest<Post> gsonRequest = new GsonRequest(
                                "https://jsonplaceholder.typicode.com/posts/1",//URL
                                Post.class,//Clase a la que se convertira el JSON
                                null,//encabezado no necesitamos
                                createRequestSuccessListener(),//listener
                                createRequestErrorListener()//listener
                        );
                        requestQueue.add(gsonRequest);
                    }
                });
    }

    private Response.Listener<Post> createRequestSuccessListener() {
        return new Response.Listener<Post>() {
            @Override
            public void onResponse(Post response) {
                try {
                    button.setEnabled(true);
                    //el post obtenido del REST se llena en la interfaz
                    textView2.setText(response.getTitle());
                    textView3.setText(response.getBody());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

}
