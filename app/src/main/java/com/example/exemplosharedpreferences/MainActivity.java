package com.example.exemplosharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button salvar;
    TextView exibe;
    EditText editText;
    public  static  final String nomePrederence = "nota";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salvar = findViewById(R.id.button);
        exibe = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText);
        preferences = getSharedPreferences(nomePrederence,MODE_PRIVATE);

        editor = preferences.edit();
        salvar.setOnClickListener(clikSalvar);
        //Log.i("nome",preferences.getString("nome",""));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private final Button.OnClickListener clikSalvar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editor.putString("texto",editText.getText().toString());
            editor.commit();
            exibe.setText(preferences.getString("texto","não"));

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        editor.putString("texto",editText.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        exibe.setText(preferences.getString("texto","não"));
    }
}
