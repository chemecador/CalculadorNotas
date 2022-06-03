package com.example.calculadornotas;



public class Nota {

    private String acertadas;
    private String falladas;
    private String notaTotal;
    private String notaDiez;

    public Nota() {
    }

    public Nota(String acertadas, String falladas, String notaTotal, String notaDiez) {
        this.acertadas = acertadas;
        this.falladas = falladas;
        this.notaTotal = notaTotal;
        this.notaDiez = notaDiez;
    }

    public String getAcertadas() {
        return acertadas;
    }

    public void setAcertadas(String acertadas) {
        this.acertadas = acertadas;
    }

    public String getFalladas() {
        return falladas;
    }

    public void setFalladas(String falladas) {
        this.falladas = falladas;
    }

    public String getNotaTotal() {
        return notaTotal;
    }

    public void setNotaTotal(String notaTotal) {
        this.notaTotal = notaTotal;
    }

    public String getNotaDiez() {
        return notaDiez;
    }

    public void setNotaDiez(String notaDiez) {
        this.notaDiez = notaDiez;
    }
}