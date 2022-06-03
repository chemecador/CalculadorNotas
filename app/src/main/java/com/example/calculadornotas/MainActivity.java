package com.example.calculadornotas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int totalPreguntas;
    private int totalRespuestas;
    private int opcionResta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        EditText respuestas = findViewById(R.id.txtRespuestas);
        EditText preguntas = findViewById(R.id.txtPreguntas);

        // colores disponibles para seleccionar
        String[] opciones = {"No resta", "3 mal restan 1 bien", "4 mal restan 1 bien"};

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapterSpinner);

        spinner.setSelection(0);
        Button calcular = findViewById(R.id.bCalcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (respuestas.getText().length() < 1 || preguntas.getText().length() < 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Los campos no pueden estar vacíos");
                    builder.setPositiveButton("OK", null);
                    Dialog dialog = builder.create();
                    dialog.show();
                    return;
                }
                if (Integer.parseInt(respuestas.getText().toString()) >
                        Integer.parseInt(preguntas.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    String s = "¿Cómo has podido responder " + respuestas.getText().toString() +
                            " preguntas, si solo hay " + preguntas.getText().toString() + "?";
                    builder.setMessage(s);
                    builder.setPositiveButton("OK", null);
                    Dialog dialog = builder.create();
                    dialog.show();
                    return;
                }
                totalPreguntas = Integer.parseInt(preguntas.getText().toString());
                totalRespuestas = Integer.parseInt(respuestas.getText().toString());
                opcionResta = spinner.getSelectedItemPosition();
                mostrarNotas();
            }
        });
    }

    private void mostrarNotas() {
        setContentView(R.layout.nota_final);
        NotaAdapter adapter = new NotaAdapter(MainActivity.this, calcularNotas());
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);
        Button bVolver = findViewById(R.id.bVolver);
        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private ArrayList<Nota> calcularNotas() {
        ArrayList<Nota> notas = new ArrayList<>();
        for (int i = 0; i <= totalRespuestas; i++) {
            Double tr = (double) totalRespuestas;
            Double tp = (double) totalPreguntas;
            String pattern = "#.##";
            DecimalFormat decimalFormat =  new DecimalFormat(pattern);
            Nota n = null;
            if (opcionResta == 0) {
                double notaDiez = i * 10.0 / tp;
                String nd = decimalFormat.format(notaDiez);
                n = new Nota("" + i, "" + (totalRespuestas - i),
                        i + "/" + totalPreguntas, nd + "/10");
            } else if (opcionResta == 1) {
                double notaTotal = i - (tr - i) / 3;
                double notaDiez = notaTotal * 10.0 / tp;

                String nt = decimalFormat.format(notaTotal);
                String nd = decimalFormat.format(notaDiez);
                n = new Nota("" + i, "" + ( totalRespuestas - i),
                        nt + "/" + totalPreguntas, nd + "/10");
            } else if (opcionResta == 2) {
                double notaTotal = i - (tr - i) / 4;
                double notaDiez = notaTotal * 10.0 / tp;

                String nt = decimalFormat.format(notaTotal);
                String nd = decimalFormat.format(notaDiez);
                n = new Nota("" + i, "" + (totalRespuestas - i),
                        nt + "/" + totalPreguntas, nd + "/10");
            }
            notas.add(n);
        }
        return notas;
    }

    @Override
    protected void onPause() {
        super.onPause();
        setContentView(R.layout.activity_main);
    }
}