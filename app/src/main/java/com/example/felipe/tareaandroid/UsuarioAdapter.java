package com.example.felipe.tareaandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.ViewHolder> {
    private static final String DEBUG_TAG = "SampleMaterialAdapter";

    public Context context;
    public ArrayList<Card> cardsList;

    public UsuarioAdapter(Context context, ArrayList<Card> cardsList) {
        this.context = context;
        this.cardsList = cardsList;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        String name = cardsList.get(position).getName();
        String numero = cardsList.get(position).getNumero();
        int color = cardsList.get(position).getColorResource();

        TextView initial = viewHolder.initial;
        TextView nameTextView = viewHolder.name;
        TextView numeroTextView = viewHolder.numero;

        nameTextView.setText(name);
        numeroTextView.setText(numero);

        initial.setBackgroundColor(color);
        initial.setText(Character.toString(name.charAt(0)));
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }

    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    public void animateCircularDelete(final View view, final int list_position) {
        int centerX = view.getWidth();
        int centerY = view.getHeight();
        int startRadius = view.getWidth();
        int endRadius = 0;
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);

        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit adapter position " + list_position);
                Log.d(DEBUG_TAG, "SampleMaterialAdapter onAnimationEnd for Edit cardId " + getItemId(list_position));

                view.setVisibility(View.INVISIBLE);
                cardsList.remove(list_position);
                notifyItemRemoved(list_position);
            }
        });
        animation.start();
    }

    public void addCard(String name, String numero, int color) {
        Card card = new Card();
        card.setName(name);
        card.setNumero(numero);
        card.setColorResource(color);
        card.setId(getItemCount());
        cardsList.add(card);
        ((Usuario) context).doSmoothScroll(getItemCount());
        notifyItemInserted(getItemCount());
    }

    public void updateCard(String name, String numero, int list_position) {
        cardsList.get(list_position).setName(name);
        cardsList.get(list_position).setNumero(numero);
        Log.d(DEBUG_TAG, "list_position is " + list_position);
        notifyItemChanged(list_position);
    }

    public void deleteCard(View view, int list_position) {
        animateCircularDelete(view, list_position);
    }

    @Override
    public int getItemCount() {
        if (cardsList.isEmpty()) {
            return 0;
        } else {
            return cardsList.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return cardsList.get(position).getId();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View v = li.inflate(R.layout.card_view_holder, viewGroup, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView initial;
        private TextView name;
        private TextView numero;
        private Button deleteButton;

        public ViewHolder(View v) {
            super(v);
            initial = (TextView) v.findViewById(R.id.initial);
            name = (TextView) v.findViewById(R.id.name);
            numero = (TextView) v.findViewById(R.id.numero);
            deleteButton = (Button) v.findViewById(R.id.delete_button);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCircularDelete(itemView, getAdapterPosition());
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pair<View, String> p1 = Pair.create((View) initial, Usuario.TRANSITION_INITIAL);
                    Pair<View, String> p2 = Pair.create((View) name, Usuario.TRANSITION_NAME);
                    Pair<View, String> p3 = Pair.create((View) deleteButton, Usuario.TRANSITION_DELETE_BUTTON);
                    Pair<View, String> p4 = Pair.create((View) numero, Usuario.TRANSITION_NUMERO);

                    ActivityOptionsCompat options;
                    Activity act = (AppCompatActivity) context;
                    options = ActivityOptionsCompat.makeSceneTransitionAnimation(act, p1, p2, p3, p4);

                    int requestCode = getAdapterPosition();
                    String name = cardsList.get(requestCode).getName();
                    String numero = cardsList.get(requestCode).getNumero();
                    int color = cardsList.get(requestCode).getColorResource();

                    Log.d(DEBUG_TAG, "SampleMaterialAdapter itemView listener for Edit adapter position " + requestCode);

                    Intent transitionIntent = new Intent(context, UsuarioTransitionEdit.class);
                    transitionIntent.putExtra(Usuario.EXTRA_NAME, name);
                    transitionIntent.putExtra(Usuario.EXTRA_NUMERO, numero);
                    transitionIntent.putExtra(Usuario.EXTRA_INITIAL, Character.toString(name.charAt(0)));
                    //transitionIntent.putExtra(Usuario.EXTRA_INITIAL, Character.toString(numero.charAt(0)));
                    transitionIntent.putExtra(Usuario.EXTRA_COLOR, color);
                    transitionIntent.putExtra(Usuario.EXTRA_UPDATE, false);
                    transitionIntent.putExtra(Usuario.EXTRA_DELETE, false);
                    ((AppCompatActivity) context).startActivityForResult(transitionIntent, requestCode, options.toBundle());
                }
            });
        }
    }
}
