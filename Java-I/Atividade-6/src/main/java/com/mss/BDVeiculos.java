package com.mss;

import java.util.ArrayList;
import java.util.List;

public class BDVeiculos {
    private List<Passeio> listaPasseio;
    private List<Carga> listaCarga;

    public BDVeiculos() {
        this.listaPasseio = new ArrayList<Passeio>();
        this.listaCarga = new ArrayList<Carga>();
    }

    public List<Passeio> getListaPasseio() {
        return listaPasseio;
    }

    public List<Carga> getListaCarga() {
        return listaCarga;
    }

    public void setListaPasseio(List<Passeio> listaPasseio) {
        this.listaPasseio = listaPasseio;
    }

    public void setListaCarga(List<Carga> listaCarga) {
        this.listaCarga = listaCarga;
    }
}
