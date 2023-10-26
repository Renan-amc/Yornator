package com.mycompany.jogoperguntasgui;
/**
 *
 * @author Renan
 */
public class Yordles {
    private String nomeYordle;
    private String tipo;
    private String range;
    private String cor;
    private String lane;
    private String reino;

    public Yordles(String nomeYordle, String tipo, String range, String cor, String lane, String reino) {
        this.nomeYordle = nomeYordle;
        this.tipo = tipo;
        this.range = range;
        this.cor = cor;
        this.lane = lane;
        this.reino = reino;
    }

    public String getNomeYordle() {
        return this.nomeYordle;
    }

    public void setNomeYordle(String nomeYordle) {
        this.nomeYordle = nomeYordle;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRange() {
        return this.range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getCor() {
        return this.cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getLane() {
        return this.lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getReino() {
        return this.reino;
    }

    public void setReino(String reino) {
        this.reino = reino;
    }
    
     public String[] getYordles() {
    String[] yordles = {nomeYordle, tipo, range, cor, lane, reino};
    return yordles;
    }
}


