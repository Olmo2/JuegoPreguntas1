package com.olmo.juegopreguntas1;

import android.media.Image;
import android.provider.MediaStore;

public class Pregunta {

    private String enunciado;
    private String [] respuesta = new String[3];
    private int correcta;
    private String img;


    // Constructor para crear las preguntas

    public Pregunta(String enunciado, String res1, String res2, String res3, int correcta) {
        this.enunciado = enunciado;
        this.respuesta[0] = res1;
        this.respuesta[1] = res2;
        this.respuesta[2] = res3;
        this.correcta = correcta;
    }

    public Pregunta(String enunciado, String res1, String res2, String res3, int correcta , String img) {
        this.enunciado = enunciado;
        this.respuesta[0] = res1;
        this.respuesta[1] = res2;
        this.respuesta[2] = res3;
        this.correcta = correcta;
        this.img=img;
    }
    // Getter y Setter

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String[] getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String[] respuesta) {
        this.respuesta = respuesta;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int resCorrecta) {
        this.correcta = resCorrecta;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
