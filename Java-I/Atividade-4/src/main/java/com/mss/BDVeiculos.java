package com.mss;

public class BDVeiculos {
    private Passeio[] listaPasseio;
    private Carga[] listaCarga;

    public BDVeiculos() {
        this.listaPasseio = new Passeio[5];
        this.listaCarga = new Carga[5];
    }

    public Passeio[] getListaPasseio() {
        return listaPasseio;
    }

    public Carga[] getListaCarga() {
        return listaCarga;
    }

    public void setListaPasseio(Passeio[] listaPasseio) {
        this.listaPasseio = listaPasseio;
    }

    public void setListaCarga(Carga[] listaCarga) {
        this.listaCarga = listaCarga;
    }
}
