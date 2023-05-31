package controller;

import model.Compromisso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AgendaController {
    private List<Compromisso> compromissos;

    public AgendaController() {
        compromissos = new ArrayList<>();
    }

    public void salvarCompromisso(Compromisso compromisso) {
        compromissos.add(compromisso);
        ordenarCompromissos();
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }

    private void ordenarCompromissos() {
        Collections.sort(compromissos, new Comparator<Compromisso>() {
            @Override
            public int compare(Compromisso c1, Compromisso c2) {
                return c1.getHora().compareTo(c2.getHora());
            }
        });
    }
}
