package com.olmo.juegopreguntas1;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int NPREGUNTAS = 10;
    private Pregunta[] preguntas = new Pregunta[NPREGUNTAS];
    private int preguntaEnCurso = 0;

    TextView pregunta;
    RadioButton res1, res2, res3;
    Button siguiente;
    int resBuenas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregunta = findViewById(R.id.tvPregunta);
        res1 = findViewById(R.id.rb1);
        res2 = findViewById(R.id.rb2);
        res3 = findViewById(R.id.rb3);
        siguiente = findViewById(R.id.btnSiguiente);

        cargarPreguntas();

        mostrarPreguntaEnCurso();
    }

    public void cargarPreguntas() {
        preguntas[0] = new Pregunta("Enunciado Pregunta 1", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[1] = new Pregunta("Enunciado Pregunta 2", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[2] = new Pregunta("Enunciado Pregunta 3", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[3] = new Pregunta("Enunciado Pregunta 4", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[4] = new Pregunta("Enunciado Pregunta 5", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[5] = new Pregunta("Enunciado Pregunta 6", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[6] = new Pregunta("Enunciado Pregunta 7", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[7] = new Pregunta("Enunciado Pregunta 8", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[8] = new Pregunta("Enunciado Pregunta 9", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
        preguntas[9] = new Pregunta("Enunciado Pregunta 10", "Respuesta 1", "Respuesta 2", "Respuesta 3", 1);
    }

    public void validarRespuesta() {

        // Obtiene la respuesta seleccionada por el usuario
        int resSelect;

        if (res1.isChecked()) {
            resSelect = 1;
        } else if (res2.isChecked()) {
            resSelect = 2;
        } else if (res2.isChecked()) {
            resSelect = 3;
        } else {
            // El usuario no seleccionó ninguna respuesta
            resSelect = 0;
        }

        // Registra las respuestas correctas
        if (preguntas[preguntaEnCurso].getCorrecta() == resSelect) {
            resBuenas++;
            Toast.makeText(this,"Respuesta Correcta",Toast.LENGTH_SHORT).show();
        }

        // Pasa a la siguiente pregunta
        preguntaEnCurso++;
        if (preguntaEnCurso < NPREGUNTAS) {
            mostrarPreguntaEnCurso();
        } else {
            // Si se llega al final del test, se desactiva el botón siguiente
            siguiente.setEnabled(false);
            mostrarResultado();
        }
    }

    public void mostrarPreguntaEnCurso() {

        // Muestra la pregunta actual

        Pregunta preguntaActual = preguntas[preguntaEnCurso];

        pregunta.setText(preguntaActual.getEnunciado());
        res1.setText(preguntaActual.getRespuesta()[0]);
        res2.setText(preguntaActual.getRespuesta()[1]);
        res3.setText(preguntaActual.getRespuesta()[2]);
  
    }

    public void Siguiente(View v) {

        // Cuando se pasa a la siguienye pregunta, se valida la respuesta de la pregunta actual

        validarRespuesta();

    }

    public void mostrarResultado() {

        // Muestra el número de respuestas correctas al finalizar el test

        Toast resultado;
        String mensaje = "Respuestas correctas: "+resBuenas+"/"+NPREGUNTAS;
        resultado = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        resultado.show();
    }


}