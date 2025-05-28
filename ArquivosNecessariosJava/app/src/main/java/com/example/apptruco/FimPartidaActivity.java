package com.example.apptruco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView; // IMPORTAR TextView

import androidx.appcompat.app.AppCompatActivity;

public class FimPartidaActivity extends AppCompatActivity {

    private TextView textViewMensagemVencedor;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_partida);

        textViewMensagemVencedor = findViewById(R.id.textViewMensagemVencedor);

        String nomeEquipeVencedora = getIntent().getStringExtra("EQUIPE_VENCEDORA");

        if (nomeEquipeVencedora != null && !nomeEquipeVencedora.isEmpty()) {
            textViewMensagemVencedor.setText("A equipe " + nomeEquipeVencedora + " venceu!");
        } else {
            textViewMensagemVencedor.setText("Resultado da partida indisponível.");
        }

        Button buttonVoltar = findViewById(R.id.buttonVoltar);
        buttonVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
