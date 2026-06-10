package com.mss;

import java.util.Random;

public class MyRandomNumber {

    Random random = new Random();

    private int lastNumber = Integer.MIN_VALUE;

    /**
    *
    * @param begin inicio do intervalo
    * @param end fim do intervalo
    * @return retornar um numero aleatorio entre [begin, end]
    * o numero aleatorio retornado nao pode ser igual ao anterior
    * @throws IntervaloInvalidoException
    * essa excecao eh lancada quando begin >= end ou (begin<0 || end<0)
    *
    */
    public int nextRandomNumber(int begin, int end) throws IntervaloInvalidoException {
        if (begin < 0)
            throw new IntervaloInvalidoException("begin eh menor que zero");
        if (end < 0)
            throw new IntervaloInvalidoException("end eh menor que zero");
        if (begin >= end)
            throw new IntervaloInvalidoException("begin deve ser menor que end");

        int number;
        
        do {
            number = begin + random.nextInt(end - begin + 1);
        } while (number == lastNumber);

        lastNumber = number;
        return number;
    }
}
