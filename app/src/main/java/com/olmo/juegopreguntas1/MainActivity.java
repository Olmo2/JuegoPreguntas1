package com.olmo.juegopreguntas1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    static final int NPREGUNTAS = 10;
    private Pregunta[] preguntas = new Pregunta[NPREGUNTAS];
    private int preguntaEnCurso = 0;

    TextView pregunta;
    RadioGroup rg;
    RadioButton res1, res2, res3;
    Button siguiente;
    int resBuenas, resFallos,resVacias = 0;
    ImageView imgPregunta;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        Toasty.Config.getInstance().tintIcon(false).setTextSize(25).allowQueue(false).apply();
        pregunta = findViewById(R.id.tvPregunta);
        res1 = findViewById(R.id.rb1);
        res2 = findViewById(R.id.rb2);
        res3 = findViewById(R.id.rb3);
        siguiente = findViewById(R.id.btnSiguiente);
        imgPregunta = findViewById(R.id.imageViewPregunta);
        rg= findViewById(R.id.rg);

        cargarPreguntas();

        mostrarPreguntaEnCurso();
    }

    public void cargarPreguntas() {
        preguntas[0] = new Pregunta("¿Qué pieza es esta?", "Rey", "Caballo", "Peón", 1, "white_king");
        preguntas[1] = new Pregunta("¿Qué pieza es esta?", "Alfil", "Dama", "Torre", 2,"black_queen");
        preguntas[2] = new Pregunta("¿Cómo se mueve?", "En todas las direcciones 1 casilla", "Solo en linea recta", "Saltando en forma de L", 1, "black_king");
        preguntas[3] = new Pregunta("¿En que columnas se coloca al inicio?", "F&G", "No empieza en el tablero", "A&H", 3, "white_rook");
        preguntas[4] = new Pregunta("¿Qué pieza es esta?", "Caballo", "Consejero", "Peón", 3, "black_pawn");
        preguntas[5] = new Pregunta("¿Qué pieza es esta?", "Antonio Resines", "Alfil", "Valvula de inyección", 2,"black_bishop");
        preguntas[6] = new Pregunta("¿Cuantos de ellos hay?", "2", "8", "16", 3, "white_pawn");
        preguntas[7] = new Pregunta("¿Qué pieza es esta?", "Eje transversal", "Doble Dama", "Caballo", 3, "black_knight");
        preguntas[8] = new Pregunta("¿Se puede dar Jque mate con esta pieza solo?", "No", "Si, pero hace falta la ayuda de las piezas contrarias", "Si", 2, "white_knight");
        preguntas[9] = new Pregunta("¿Qué pieza es esta?", "Torre", "Alfil", "Caballo", 1, "black_rook");
    }

    public void validarRespuesta() {

        // Obtiene la respuesta seleccionada por el usuario
        int resSelect;

        if (res1.isChecked()) {
            resSelect = 1;
        } else if (res2.isChecked()) {
            resSelect = 2;
        } else if (res3.isChecked()) {
            resSelect = 3;
        } else {
            // El usuario no seleccionó ninguna respuesta
            resSelect = 0;
        }


        // Registra las respuestas correctas
        if (preguntas[preguntaEnCurso].getCorrecta() == resSelect) {
            resBuenas++;
            Toast correcto = Toasty.success(this, "¡Correcta!", Toast.LENGTH_SHORT, true);
            correcto.setGravity(Gravity.CENTER, 0, 750);
            correcto.show();

            //Registra las no contestadas
        }else if (resSelect==0){
            resVacias++;
            
            //Registra las Incorrectas
        }else{
            resFallos++;
            Toast fallo = Toasty.error(this, "Fallo", Toast.LENGTH_SHORT, true);
            fallo.setGravity(Gravity.CENTER,0,750);
            fallo.show();
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

        /*Para cambiar la img*/
        Resources res = getResources();
        String name = preguntaActual.getImg();
        int resID = res.getIdentifier(name , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID );
        imgPregunta.setImageDrawable(drawable);
        rg.clearCheck();



  
    }

    public void Siguiente(View v) {

        // Cuando se pasa a la siguienye pregunta, se valida la respuesta de la pregunta actual

        validarRespuesta();

    }

    public void mostrarResultado() {

        // Muestra el número de respuestas correctas al finalizar el test
        Toasty.Config.getInstance().setTextSize(12).allowQueue(false).apply();
        Toast resultadoBuenas;
        String mensaje = "Correctas: "+resBuenas+"/"+NPREGUNTAS + " " + "En Blanco: "+resVacias+"/"+NPREGUNTAS+ " " +"Fallos: "+resFallos+"/"+NPREGUNTAS ;
        resultadoBuenas = Toasty.info(this, mensaje, Toast.LENGTH_LONG, true);
        resultadoBuenas.setGravity(Gravity.CENTER, 0,750);
        resultadoBuenas.show();










    }


}