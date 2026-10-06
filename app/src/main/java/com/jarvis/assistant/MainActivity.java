package com.jarvis.assistant;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class MainActivity extends Activity {

    TextToSpeech voz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout tela = new LinearLayout(this);
        tela.setOrientation(LinearLayout.VERTICAL);
        tela.setPadding(30, 50, 30, 30);
        tela.setGravity(Gravity.CENTER_HORIZONTAL);
        tela.setBackgroundColor(Color.rgb(5, 7, 10));

        TextView titulo = new TextView(this);
        titulo.setText("JARVIS");
        titulo.setTextColor(Color.WHITE);
        titulo.setTextSize(32);
        titulo.setGravity(Gravity.CENTER);

        TextView status = new TextView(this);
        status.setText("JARVIS ONLINE");
        status.setTextColor(Color.CYAN);
        status.setTextSize(18);
        status.setGravity(Gravity.CENTER);

        EditText pergunta = new EditText(this);
        pergunta.setHint("Digite sua pergunta...");
        pergunta.setTextColor(Color.WHITE);
        pergunta.setHintTextColor(Color.GRAY);

        Button falar = new Button(this);
        falar.setText("PERGUNTAR AO JARVIS");

        TextView resposta = new TextView(this);
        resposta.setTextColor(Color.WHITE);
        resposta.setTextSize(18);
        resposta.setPadding(10, 30, 10, 10);

        tela.addView(titulo);
        tela.addView(status);
        tela.addView(pergunta);
        tela.addView(falar);
        tela.addView(resposta);

        setContentView(tela);

        voz = new TextToSpeech(this, resultado -> {
            if (resultado == TextToSpeech.SUCCESS) {
                voz.setLanguage(new Locale("pt", "BR"));
            }
        });

        falar.setOnClickListener(v -> {
            String texto = pergunta.getText().toString().trim();

            if (!texto.isEmpty()) {
                String mensagem = "Olá, senhor Daniel. Você perguntou: " + texto;
                resposta.setText(mensagem);
                voz.speak(mensagem, TextToSpeech.QUEUE_FLUSH, null, "jarvis");
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (voz != null) {
            voz.stop();
            voz.shutdown();
        }
        super.onDestroy();
    }
}
