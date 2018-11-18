package com.example.felipe.tareaandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Usuario extends AppCompatActivity {

    private static final String DEBUG_TAG = "AppCompatActivity";

    public static final String EXTRA_UPDATE = "update";
    public static final String EXTRA_DELETE = "delete";
    public static final String EXTRA_NAME = "name";
    //ultimo en  AGREGar
    public static final String EXTRA_NUMERO = "numero";
    public static final String EXTRA_COLOR = "color";
    public static final String EXTRA_INITIAL = "initial";

    public static final String TRANSITION_FAB = "fab_transition";
    public static final String TRANSITION_INITIAL = "initial_transition";
    public static final String TRANSITION_NAME = "name_transition";
    //ultimo en  AGREGar
    public static final String TRANSITION_NUMERO = "numero_transition";
    public static final String TRANSITION_DELETE_BUTTON = "delete_button_transition";

    private RecyclerView recyclerView;
    private UsuarioAdapter adapter;
    private ArrayList<Card> cardsList = new ArrayList<>();
    private int[] colors;
    private String[] names;

    //ultimo en  AGREGar
    private String[] numeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        names = getResources().getStringArray(R.array.names_array);
        colors = getResources().getIntArray(R.array.initial_colors);
        numeros = getResources().getStringArray(R.array.numeros_array);

        initCards();

        if (adapter == null) {
            adapter = new UsuarioAdapter(this, cardsList);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pair<View, String> pair = Pair.create(v.findViewById(R.id.fab), TRANSITION_FAB);

                ActivityOptionsCompat options;
                Activity act = Usuario.this;
                options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, pair);

                Intent transitionIntent = new Intent(act, UsuarioTransitionAdd.class);
                act.startActivityForResult(transitionIntent, adapter.getItemCount(), options.toBundle());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(DEBUG_TAG, "requestCode is " + requestCode);

        if (requestCode == adapter.getItemCount()) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra(EXTRA_NAME);
                String numero = data.getStringExtra(EXTRA_NUMERO);
                int color = data.getIntExtra(EXTRA_COLOR, 0);
                adapter.addCard(name, numero, color);
            }
        } else {

            if (resultCode == RESULT_OK) {

                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(requestCode);
                if (data.getExtras().getBoolean(EXTRA_DELETE, false)) {

                    adapter.deleteCard(viewHolder.itemView, requestCode);
                } else if (data.getExtras().getBoolean(EXTRA_UPDATE)) {

                    String name = data.getStringExtra(EXTRA_NAME);
                    String numero = data.getStringExtra(EXTRA_NUMERO);
                    viewHolder.itemView.setVisibility(View.INVISIBLE);
                    adapter.updateCard(name, numero, requestCode);
                }
            }
        }
    }

    public void doSmoothScroll(int position) {
        recyclerView.smoothScrollToPosition(position);
    }

    private void initCards() {
        for (int i = 0; i < 10; i++) {
            Card card = new Card();
            card.setId((long) i);
            card.setName(names[i]);
            card.setColorResource(colors[i]);
            card.setNumero(numeros[i]);
            Log.d(DEBUG_TAG, "Card created with id " + card.getId() + ", name " + card.getName() + ", numero " + card.getNumero() + ", color " + card.getColorResource());
            cardsList.add(card);
        }
    }
}
