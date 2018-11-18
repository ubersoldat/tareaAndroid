package com.example.felipe.tareaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

    private TextView textView4;
    private TextView textView5;

    private TextView textView6;
    private TextView textView7;

    private TextView textView8;
    private TextView textView9;

    private TextView textView10;
    private TextView textView11;

    private CardView Cmensaje;
    private CardView Cmensaje2;
    private CardView Cmensaje3;
    private CardView Cmensaje4;
    private CardView Cmensaje5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        //controles
        Cmensaje = ((CardView) findViewById(R.id.mensaje1));
        Cmensaje2 = ((CardView) findViewById(R.id.mensaje2));
        Cmensaje3 = ((CardView) findViewById(R.id.mensaje3));
        Cmensaje4 = ((CardView) findViewById(R.id.mensaje4));
        Cmensaje5 = ((CardView) findViewById(R.id.mensaje5));

        textView2 = ((TextView) findViewById(R.id.textView2));
        textView3 = ((TextView) findViewById(R.id.textView3));

        textView4 = ((TextView) findViewById(R.id.textView4));
        textView5 = ((TextView) findViewById(R.id.textView5));

        textView6 = ((TextView) findViewById(R.id.textView6));
        textView7 = ((TextView) findViewById(R.id.textView7));

        textView8 = ((TextView) findViewById(R.id.textView8));
        textView9 = ((TextView) findViewById(R.id.textView9));

        textView10 = ((TextView) findViewById(R.id.textView10));
        textView11 = ((TextView) findViewById(R.id.textView11));

        //request
        requestQueue= Volley.newRequestQueue(Mensaje.this);
        Cmensaje.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Cmensaje.setEnabled(false);
                        GsonRequest<Post> gsonRequest = new GsonRequest(
                                "https://jsonplaceholder.typicode.com/posts/1",//URL
                                Post.class,//Clase a la que se convertira el JSON
                                null,
                                createRequestSuccessListener(),//listener
                                createRequestErrorListener()//listener
                        );
                        requestQueue.add(gsonRequest);
                    }
                });

        Cmensaje2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Cmensaje2.setEnabled(false);
                        GsonRequest<Post> gsonRequest = new GsonRequest(
                                "https://jsonplaceholder.typicode.com/posts/2",//URL
                                Post.class,//Clase a la que se convertira el JSON
                                null,
                                createRequestSuccessListener2(),//listener
                                createRequestErrorListener()//listener
                        );
                        requestQueue.add(gsonRequest);
                    }
                });

        Cmensaje3.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Cmensaje3.setEnabled(false);
                        GsonRequest<Post> gsonRequest = new GsonRequest(
                                "https://jsonplaceholder.typicode.com/posts/3",//URL
                                Post.class,//Clase a la que se convertira el JSON
                                null,
                                createRequestSuccessListener3(),//listener
                                createRequestErrorListener()//listener
                        );
                        requestQueue.add(gsonRequest);
                    }
                });

        Cmensaje4.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Cmensaje4.setEnabled(false);
                        GsonRequest<Post> gsonRequest = new GsonRequest(
                                "https://jsonplaceholder.typicode.com/posts/4",//URL
                                Post.class,//Clase a la que se convertira el JSON
                                null,
                                createRequestSuccessListener4(),//listener
                                createRequestErrorListener()//listener
                        );
                        requestQueue.add(gsonRequest);
                    }
                });

        Cmensaje5.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Cmensaje5.setEnabled(false);
                        GsonRequest<Post> gsonRequest = new GsonRequest(
                                "https://jsonplaceholder.typicode.com/posts/5",//URL
                                Post.class,//Clase a la que se convertira el JSON
                                null,
                                createRequestSuccessListener5(),//listener
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
                    Cmensaje.setEnabled(true);
                    //el post obtenido del REST se llena en la interfasz
                    textView2.setText(response.getTitle());
                    textView3.setText(response.getBody());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.Listener<Post> createRequestSuccessListener2() {
        return new Response.Listener<Post>() {
            @Override
            public void onResponse(Post response) {
                try {
                    Cmensaje2.setEnabled(true);
                    //el post obtenido del REST se llena en la interfaz
                    textView4.setText(response.getTitle());
                    textView5.setText(response.getBody());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.Listener<Post> createRequestSuccessListener3() {
        return new Response.Listener<Post>() {
            @Override
            public void onResponse(Post response) {
                try {
                    Cmensaje2.setEnabled(true);
                    //el post obtenido del REST se llena en la interfaz
                    textView6.setText(response.getTitle());
                    textView7.setText(response.getBody());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.Listener<Post> createRequestSuccessListener4() {
        return new Response.Listener<Post>() {
            @Override
            public void onResponse(Post response) {
                try {
                    Cmensaje2.setEnabled(true);
                    //el post obtenido del REST se llena en la interfaz
                    textView8.setText(response.getTitle());
                    textView9.setText(response.getBody());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.Listener<Post> createRequestSuccessListener5() {
        return new Response.Listener<Post>() {
            @Override
            public void onResponse(Post response) {
                try {
                    Cmensaje2.setEnabled(true);
                    //el post obtenido del REST se llena en la interfaz
                    textView10.setText(response.getTitle());
                    textView11.setText(response.getBody());

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
