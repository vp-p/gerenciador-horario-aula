package org.example.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LinhaGrade {
    // Propriedade observável para horário
    private final StringProperty horario = new SimpleStringProperty();

    // Propriedades para os dias da semana
    private String segunda;
    private String terca;
    private String quarta;
    private String quinta;
    private String sexta;

    // Getter e Setter para horário
    public String getHorario() {
        return horario.get();
    }

    public void setHorario(String horario) {
        this.horario.set(horario);
    }

    // Método para obter a propriedade observável de horário
    public StringProperty horarioProperty() {
        return horario;
    }

    // Getters e Setters para os dias da semana
    public String getSegunda() {
        return segunda;
    }

    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

    public String getTerca() {
        return terca;
    }

    public void setTerca(String terca) {
        this.terca = terca;
    }

    public String getQuarta() {
        return quarta;
    }

    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

    public String getQuinta() {
        return quinta;
    }

    public void setQuinta(String quinta) {
        this.quinta = quinta;
    }

    public String getSexta() {
        return sexta;
    }

    public void setSexta(String sexta) {
        this.sexta = sexta;
    }
}