package com.mycompany.jogoPerguntasGUI;


public class Pergunta {

    private String pergunta;
    private String opcaoA;
    private String opcaoB;
    private String opcaoC;
    private String opcaoD;
    private String opcaoE;

    public Pergunta(String pergunta, String opcaoA, String opcaoB, String opcaoC, String opcaoD, String opcaoE) {
        this.pergunta = pergunta;
        this.opcaoA = opcaoA;
        this.opcaoB = opcaoB;
        this.opcaoC = opcaoC;
        this.opcaoD = opcaoD;
        this.opcaoE = opcaoE;    
    }

    public String getPergunta() {
        return pergunta;
    }

    public void exibirPergunta() {
        System.out.println(pergunta);
        System.out.println("a) " + opcaoA);
        System.out.println("b) " + opcaoB);
        System.out.println("c) " + opcaoC);
    }

    public String getOpcaoA() {
        return opcaoA;
    }

    public void setOpcaoA(String opcaoA) {
        this.opcaoA = opcaoA;
    }

    public String getOpcaoB() {
        return opcaoB;
    }

    public void setOpcaoB(String opcaoB) {
        this.opcaoB = opcaoB;
    }

    public String getOpcaoC() {
        return opcaoC;
    }

    public void setOpcaoC(String opcaoC) {
        this.opcaoC = opcaoC;
    }

    public String getOpcaoD() {
        return opcaoD;
    }

    public void setOpcaoD(String opcaoD) {
        this.opcaoD = opcaoD;
    }

    public String getOpcaoE() {
        return opcaoE;
    }

    public void setOpcaoE(String opcaoE) {
        this.opcaoE = opcaoE;
    }
    
    public String[] getOpcoes() {
    String[] opcoes = {opcaoA, opcaoB, opcaoC, opcaoD, opcaoE};
    return opcoes;
}


   
}