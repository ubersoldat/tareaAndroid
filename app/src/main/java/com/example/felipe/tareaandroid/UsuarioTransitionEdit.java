package com.example.felipe.tareaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UsuarioTransitionEdit extends AppCompatActivity {

    private EditText nameEditText;
    private EditText numeroEditText;
    private TextView initialTextView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_transition_edit);

        nameEditText = (EditText) findViewById(R.id.name);
        numeroEditText = (EditText) findViewById(R.id.numero);
        initialTextView = (TextView) findViewById(R.id.initial);
        Button update_button = (Button) findViewById(R.id.update_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);

        intent = getIntent();
        String nameExtra = intent.getStringExtra(Usuario.EXTRA_NAME);
        String numeroExtra = intent.getStringExtra(Usuario.EXTRA_NUMERO);
        String initialExtra = intent.getStringExtra(Usuario.EXTRA_INITIAL);
        int colorExtra = intent.getIntExtra(Usuario.EXTRA_COLOR, 0);

        nameEditText.setText(nameExtra);
        nameEditText.setSelection(nameEditText.getText().length());

        numeroEditText.setText(numeroExtra);
        numeroEditText.setSelection(numeroEditText.getText().length());

        initialTextView.setText(initialExtra);
        initialTextView.setBackgroundColor(colorExtra);

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    initialTextView.setText("");
                } else if (s.length() >= 1) {
                    initialTextView.setText(String.valueOf(s.charAt(0)));
                    intent.putExtra(Usuario.EXTRA_UPDATE, true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        numeroEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    initialTextView.setText("");
                } else if (s.length() >= 1) {
                    initialTextView.setText(String.valueOf(s.charAt(0)));
                    intent.putExtra(Usuario.EXTRA_UPDATE, true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = initialTextView.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre/numero valido", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra(Usuario.EXTRA_UPDATE, true);
                    intent.putExtra(Usuario.EXTRA_NAME, String.valueOf(nameEditText.getText()));
                    intent.putExtra(Usuario.EXTRA_NUMERO, String.valueOf(numeroEditText.getText()));

                    intent.putExtra(Usuario.EXTRA_INITIAL, String.valueOf(nameEditText.getText().charAt(0)));

                    setResult(RESULT_OK, intent);
                    supportFinishAfterTransition();
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(Usuario.EXTRA_DELETE, true);
                setResult(RESULT_OK, intent);
                supportFinishAfterTransition();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
