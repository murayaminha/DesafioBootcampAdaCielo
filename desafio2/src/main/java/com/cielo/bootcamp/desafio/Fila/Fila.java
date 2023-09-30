package com.cielo.bootcamp.desafio.Fila;

import com.cielo.bootcamp.desafio.exception.DesafioException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Fila <T> implements FilaInterface <T> {

private static Object[] fila=new Object[30];
private static int contador=0;



    @Override
    public T consutarFila() {
        return (T) fila;
    }

    @Override
    public void inserirDado(T o) {
        if(contador== fila.length) aumentarFila();
        fila[contador++]=o;
    }

@SuppressWarnings("unchecked")

    @Override
    public T consumirFila() {
        if(fila.length==0) {
            throw new DesafioException("404", "NOT FOUND", "não foram encontradas mensagens");
        }
    contador--;
    return (T) fila[0];
    }

    @Override
    public void aumentarFila() {
        Object[] filaAumentada = new Object[30 + contador];
            System.arraycopy(this.fila, 0, filaAumentada, 0, fila.length);
        fila=filaAumentada;
    }


}
