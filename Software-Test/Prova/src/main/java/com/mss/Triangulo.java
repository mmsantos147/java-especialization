package com.mss;

public class Triangulo {
    private Double lado1;
    private Double lado2;
    private Double lado3;

    public Triangulo() {}

    public Triangulo(Double lado1, Double lado2, Double lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    public boolean isTriangulo(Double lado1, Double lado2, Double lado3) {
        if (lado1 <= 0 || lado2 <= 0 || lado3 <= 0) throw new IllegalArgumentException("O valor do lado deve ser positivo e maior que 0");
        return lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1;
    }

    public String tipoTriangulo(Double lado1, Double lado2, Double lado3) {
        if (lado1.equals(lado2) && lado2.equals(lado3)) return "equilatero";
        if (lado1.equals(lado2) || lado1.equals(lado3) || lado2.equals(lado3)) return "isosceles";
        return "escaleno";
    }

    public void setLado1(Double lado1) {
        this.lado1 = lado1;
    }

    public void setLado2(Double lado2) {
        this.lado2 = lado2;
    }
    
    public void setLado3(Double lado3) {
        this.lado3 = lado3;
    }

    public Double getLado1() {
        return lado1;
    }
    
    public Double getLado2() {
        return lado2;
    }

    public Double getLado3() {
        return lado3;
    }
}
