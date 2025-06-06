package com.example.apptruco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttontruco, button6, button9, button12, buttonResetRodada;
    private Button button1pteq1, button1pteq2;
    private TextView ptseq1, ptseq2;
    private int pontosEquipe1 = 0;
    private int pontosEquipe2 = 0;
    private int valorRodada = 1;

    private boolean jogoFinalizado = false; // Controle para evitar múltiplas finalizações

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização dos componentes
        buttontruco = findViewById(R.id.buttontruco);
        button6 = findViewById(R.id.button6);
        button9 = findViewById(R.id.button9);
        button12 = findViewById(R.id.button12);
        buttonResetRodada = findViewById(R.id.buttonResetRodada);
        ptseq1 = findViewById(R.id.ptseq1);
        ptseq2 = findViewById(R.id.ptseq2);
        button1pteq1 = findViewById(R.id.button1pteq1);
        button1pteq2 = findViewById(R.id.button1pteq2);

        // Configuração inicial dos textos e botões
        ptseq1.setText(String.valueOf(pontosEquipe1));
        ptseq2.setText(String.valueOf(pontosEquipe2));
        atualizarTextoBotao1Ponto();
        resetarBotao();

        // Listeners dos botões de truco
        buttontruco.setOnClickListener(v -> {
            if (jogoFinalizado) return;
            valorRodada = 3;
            atualizarTextoBotao1Ponto();
            buttontruco.setVisibility(View.GONE);
            button6.setVisibility(View.VISIBLE);
            button9.setVisibility(View.GONE);
            button12.setVisibility(View.GONE);
            buttonResetRodada.setVisibility(View.GONE);
        });
        button6.setOnClickListener(v -> {
            if (jogoFinalizado) return;
            valorRodada = 6;
            atualizarTextoBotao1Ponto();
            buttontruco.setVisibility(View.GONE);
            button6.setVisibility(View.GONE);
            button9.setVisibility(View.VISIBLE);
            button12.setVisibility(View.GONE);
            buttonResetRodada.setVisibility(View.GONE);
        });
        button9.setOnClickListener(v -> {
            if (jogoFinalizado) return;
            valorRodada = 9;
            atualizarTextoBotao1Ponto();
            buttontruco.setVisibility(View.GONE);
            button6.setVisibility(View.GONE);
            button9.setVisibility(View.GONE);
            button12.setVisibility(View.VISIBLE);
            buttonResetRodada.setVisibility(View.GONE);
        });
        button12.setOnClickListener(v -> {
            if (jogoFinalizado) return;
            valorRodada = 12;
            atualizarTextoBotao1Ponto();
            buttontruco.setVisibility(View.GONE);
            button6.setVisibility(View.GONE);
            button9.setVisibility(View.GONE);
            button12.setVisibility(View.GONE);
            buttonResetRodada.setVisibility(View.VISIBLE);
        });
        buttonResetRodada.setOnClickListener(v -> {
            if (jogoFinalizado) return;
            valorRodada = 1;
            atualizarTextoBotao1Ponto();
            resetarBotao();
        });

        // Listeners dos botões de pontuação
        button1pteq1.setOnClickListener(v -> {
            if (jogoFinalizado) return;

            pontosEquipe1 += valorRodada;
            ptseq1.setText(String.valueOf(pontosEquipe1));

            String vencedor = null;
            if (pontosEquipe1 >= 12) {
                if (pontosEquipe2 >= 12) {
                    vencedor = (pontosEquipe1 >= pontosEquipe2) ? "DUPLA 1" : "DUPLA 2";
                } else {
                    vencedor = "DUPLA 1";
                }
            } else if (pontosEquipe2 >= 12) {
                vencedor = "DUPLA 2";
            }

            if (vencedor != null) {
                iniciarFimDePartida(vencedor);
            } else {
                valorRodada = 1;
                atualizarTextoBotao1Ponto();
                resetarBotao();
            }
        });

        button1pteq2.setOnClickListener(v -> {
            if (jogoFinalizado) return;

            pontosEquipe2 += valorRodada;
            ptseq2.setText(String.valueOf(pontosEquipe2));

            String vencedor = null;
            if (pontosEquipe2 >= 12) {
                if (pontosEquipe1 >= 12) {
                    vencedor = (pontosEquipe2 >= pontosEquipe1) ? "DUPLA 2" : "DUPLA 1";
                } else {
                    vencedor = "DUPLA 2";
                }
            } else if (pontosEquipe1 >= 12) {
                vencedor = "DUPLA 1";
            }

            if (vencedor != null) {
                iniciarFimDePartida(vencedor);
            } else {
                valorRodada = 1;
                atualizarTextoBotao1Ponto();
                resetarBotao();
            }
        });

        // Botões de diminuir pontos
        Button buttondiminuirpteq1 = findViewById(R.id.buttondiminuirpteq1);
        buttondiminuirpteq1.setOnClickListener(v -> {
            if (pontosEquipe1 > 0 && !jogoFinalizado) {
                pontosEquipe1--;
                ptseq1.setText(String.valueOf(pontosEquipe1));
            }
        });

        Button buttondiminuirpteq2 = findViewById(R.id.buttondiminuirpteq2);
        buttondiminuirpteq2.setOnClickListener(v -> {
            if (pontosEquipe2 > 0 && !jogoFinalizado) {
                pontosEquipe2--;
                ptseq2.setText(String.valueOf(pontosEquipe2));
            }
        });
    }

    private void resetarBotao() {
        buttontruco.setVisibility(View.VISIBLE);
        button6.setVisibility(View.GONE);
        button9.setVisibility(View.GONE);
        button12.setVisibility(View.GONE);
        buttonResetRodada.setVisibility(View.GONE);
    }

    private void atualizarTextoBotao1Ponto() {
        if (button1pteq1 != null) {
            button1pteq1.setText("+" + valorRodada);
        }
        if (button1pteq2 != null) {
            button1pteq2.setText("+" + valorRodada);
        }
    }

    private void iniciarFimDePartida(String equipeVencedora) {
        if (jogoFinalizado) {
            return;
        }
        jogoFinalizado = true;

        Intent intent = new Intent(MainActivity.this, FimPartidaActivity.class);
        intent.putExtra("EQUIPE_VENCEDORA", equipeVencedora);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            pontosEquipe1 = 0;
            pontosEquipe2 = 0;
            valorRodada = 1;
            ptseq1.setText("0");
            ptseq2.setText("0");
            atualizarTextoBotao1Ponto();
            resetarBotao();
            jogoFinalizado = false;
        }
    }
}
