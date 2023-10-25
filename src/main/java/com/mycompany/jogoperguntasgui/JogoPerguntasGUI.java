package com.mycompany.jogoperguntasgui;
import com.mycompany.jogoPerguntasGUI.Pergunta;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JogoPerguntasGUI extends JFrame {

    private List<Pergunta> perguntas;
    private int perguntaAtual = 0;
    private JPanel cardPanel;
    private JLabel perguntaLabel;
    private JRadioButton[] opcoes;
    private ButtonGroup group;
    private JButton responderButton;
    private List<String> respostas;
    private List<Yordles> yordlezinhos;
    private JLabel respostasLabel;

    public JogoPerguntasGUI(List<Pergunta> perguntas, List<Yordles> yordlezinhos) {
        this.perguntas = perguntas;
        this.respostas = new ArrayList<>();
        this.yordlezinhos = yordlezinhos;

        setTitle("Jogo de Perguntas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel(new CardLayout());
        add(cardPanel);

        for (int i = 0; i < perguntas.size(); i++) {
            Pergunta pergunta = perguntas.get(i);
            JPanel perguntaPanel = new JPanel(new GridLayout(7, 1));

            perguntaLabel = new JLabel(pergunta.getPergunta());
            perguntaPanel.add(perguntaLabel);

            opcoes = new JRadioButton[5];
            group = new ButtonGroup();

            String[] opcoesPergunta = pergunta.getOpcoes();

            for (int j = 0; j < 5; j++) {
                opcoes[j] = new JRadioButton(String.valueOf((char)('a' + j)) + ") " + opcoesPergunta[j]);
                group.add(opcoes[j]);
                perguntaPanel.add(opcoes[j]);
            }

            responderButton = new JButton("Responder");
            responderButton.addActionListener(new ResponderListener());
            perguntaPanel.add(responderButton);

            cardPanel.add(perguntaPanel, "Pergunta " + i);
        }
        respostasLabel = new JLabel("Respostas: ");
        add(respostasLabel);

        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "Pergunta 0");
    }

    private class ResponderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String respostaSelecionada = null;
            for (int i = 0; i < opcoes.length; i++) {
                if (opcoes[i].isSelected()) {
                    respostaSelecionada = String.valueOf((char)('a' + i));
                    break;
                }
            }

            if (respostaSelecionada != null) {
                respostas.add(respostaSelecionada);
            }

            if (perguntaAtual < perguntas.size()) {
                perguntaAtual++;
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Pergunta " + perguntaAtual);
            } else {
                // Fim do jogo
                atribuirRespostas();
            }
        }
    }

    
    private void atribuirRespostas() {
    List<String> respostasPerguntas = new ArrayList<>();

        for (int i = 0; i < perguntas.size(); i++) {
            String respostaSelecionada = respostas.get(i);
            Pergunta pergunta = perguntas.get(i);

            if (respostaSelecionada.equals("a")) {
                respostasPerguntas.add(pergunta.getOpcaoA());
            } else if (respostaSelecionada.equals("b")) {
                respostasPerguntas.add(pergunta.getOpcaoB());
            } else if (respostaSelecionada.equals("c")) {
                respostasPerguntas.add(pergunta.getOpcaoC());
            } else if (respostaSelecionada.equals("d")) {
                respostasPerguntas.add(pergunta.getOpcaoD());
            } else if (respostaSelecionada.equals("e")) {
                respostasPerguntas.add(pergunta.getOpcaoE());
            }
        }
    }

    
   private void mostrarRespostas(List<String> respostasPerguntas) {
    StringBuilder respostaString = new StringBuilder("Campeão escolhido:\n");
    int maxCorrespondencias = 0;
    String campeaoEscolhido = "";

    for (Yordles yordle : yordlezinhos) {
        int correspondencias = 0;

        if (respostasPerguntas.get(0).equalsIgnoreCase(yordle.getTipo())) {
            correspondencias++;
        }
        if (respostasPerguntas.get(1).equalsIgnoreCase(yordle.getRange())) {
            correspondencias++;
        }
        if (respostasPerguntas.get(2).equalsIgnoreCase(yordle.getCor())) {
            correspondencias++;
        }
        if (respostasPerguntas.get(3).equalsIgnoreCase(yordle.getLane())) {
            correspondencias++;
        }
        if (respostasPerguntas.get(4).equalsIgnoreCase(yordle.getReino())) {
            correspondencias++;
        }

        if (correspondencias > maxCorrespondencias) {
            maxCorrespondencias = correspondencias;
            campeaoEscolhido = yordle.getNomeYordle();
        }
    }

    if (maxCorrespondencias > 0) {
        respostaString.append(campeaoEscolhido);
    } else {
        respostaString = new StringBuilder("Campeão não encontrado!!");
    }

    JOptionPane.showMessageDialog(this, respostaString.toString());
    System.exit(0);
}






    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Pergunta> perguntas1 = new ArrayList<>();
            perguntas1.add(new Pergunta("Qual o tipo de campeão?", "Tanque", "Atirador", "Mago", "Assassino", "Suporte"));
            perguntas1.add(new Pergunta("Qual o range do campeão?", "Corpo-a-Corpo", "Alto", "Muito Alto", "Médio", ""));
            perguntas1.add(new Pergunta("Qual a cor predominante do campeão?", "Azul", "Branca", "Amarela", "Roxa", "Marrom"));
            perguntas1.add(new Pergunta("Qual a lane preferida do campeão?", "Selva", "Meio", "Topo", "Bot-Lane", ""));
            perguntas1.add(new Pergunta("A qual reino pertence o campeão?", "Shurima", "Runeterra", "Piltover", "Ionia", "Demacia"));
            List<Yordles> yordlezinhos = new ArrayList<>();
            yordlezinhos.add(new Yordles("Amumu", "Tanque", "Corpo-a-corpo", "Azul", "Selva", "Shurima"));
            yordlezinhos.add(new Yordles("Corki", "Atirador", "Alto", "Branca", "Meio", "Runeterra"));
            yordlezinhos.add(new Yordles("Heimerdinger", "Mago", "Alto", "Amarelo", "Meio", "Piltover"));
            yordlezinhos.add(new Yordles("Kennen", "Mago", "Alto", "Azul", "Topo", "Ionia"));
            yordlezinhos.add(new Yordles("Lulu", "Suporte", "Alto", "Roxo", "Bot-Lane", "Runeterra"));
            yordlezinhos.add(new Yordles("Poppy", "Tanque", "Corpo-a-corpo", "Azul", "Topo", "Demacia"));
            yordlezinhos.add(new Yordles("Rumble", "Mago", "Corpo-a-corpo", "Branca", "Topo", "Runeterra"));
            yordlezinhos.add(new Yordles("Teemo", "Atirador", "Alto", "Marrom", "Topo", "Runeterra"));
            yordlezinhos.add(new Yordles("Tristana", "Atirador", "Muito Alto", "Branca", "Bot-Lane", "Runeterra"));
            yordlezinhos.add(new Yordles("Veigar", "Mago", "Médio", "Roxo", "Meio", "Runeterra"));
            yordlezinhos.add(new Yordles("Fizz", "Assassino", "Corpo-a-corpo", "Azul", "Meio", "Runeterra"));
            JogoPerguntasGUI jogo = new JogoPerguntasGUI(perguntas1, yordlezinhos);
            jogo.setVisible(true);
            
        });}
}