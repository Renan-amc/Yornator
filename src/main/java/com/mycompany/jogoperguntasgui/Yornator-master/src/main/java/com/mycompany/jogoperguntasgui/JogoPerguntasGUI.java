package com.mycompany.jogoperguntasgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JogoPerguntasGUI extends JFrame {

    private List<Pergunta> perguntas;
    private List<Yordles> yordles;
    private int perguntaAtual = 0;
    private JPanel cardPanel;
    private JLabel perguntaLabel;
    private List<JRadioButton[]> opcoesList; // Lista de matrizes de botões de opção
    private List<ButtonGroup> grupos; // Lista de grupos de botões de opção
    private List<String> respostas;
    private JButton responderButton;

    public JogoPerguntasGUI(List<Pergunta> perguntas, List<Yordles> yordles) {
        this.perguntas = perguntas;
        this.yordles = yordles; // Atribua a lista de Yordles da instância
        this.respostas = new ArrayList<>();
        this.opcoesList = new ArrayList<>();
        this.grupos = new ArrayList<>();

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

            JRadioButton[] opcoes = new JRadioButton[5]; // Crie uma nova matriz de botões de opção
            ButtonGroup group = new ButtonGroup(); // Crie um novo grupo de botões de opção

            String[] opcoesPergunta = pergunta.getOpcoes();

            for (int j = 0; j < 5; j++) {
                opcoes[j] = new JRadioButton(String.valueOf((char)('a' + j)) + ") " + opcoesPergunta[j]);
                group.add(opcoes[j]);
                perguntaPanel.add(opcoes[j]);
            }

            responderButton = new JButton("Responder");
            responderButton.addActionListener(new ResponderListener(opcoes, i)); // Passe a matriz de botões de opção e o índice da pergunta
            perguntaPanel.add(responderButton);

            opcoesList.add(opcoes); // Adicione a matriz de botões de opção à lista
            grupos.add(group); // Adicione o grupo de botões de opção à lista

            cardPanel.add(perguntaPanel, "Pergunta " + i);
        }

        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "Pergunta 0");
    }

    private class ResponderListener implements ActionListener {
        private final JRadioButton[] opcoes; // Matriz de botões de opção para a pergunta
        private final int perguntaIndex; // Índice da pergunta

        public ResponderListener(JRadioButton[] opcoes, int perguntaIndex) {
            this.opcoes = opcoes;
            this.perguntaIndex = perguntaIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String respostaSelecionada = null;
            for (int i = 0; i < opcoes.length; i++) {
                if (opcoes[i].isSelected()) {
                    respostaSelecionada = opcoes[i].getText().replaceFirst("^[a-e]\\)\\s*", ""); // Obtém o conteúdo da alternativa
                    break;
                }
            }

            if (respostaSelecionada != null) {
                respostas.add(respostaSelecionada);
            }

            if (perguntaAtual < perguntas.size() - 1) {
                perguntaAtual++;
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Pergunta " + perguntaAtual);
            } else {
                // Todas as perguntas foram respondidas
                mostrarResultado();
            }
        }
    }

    private void mostrarResultado() {
        Yordles yordleEncontrado = null;
        for (Yordles yordle : yordles) {
            if (respostas.contains(yordle.getTipo())) {
                if (respostas.contains(yordle.getRange())) {
                    if (respostas.contains(yordle.getCor())) {
                        if (respostas.contains(yordle.getLane())) {
                            if (respostas.contains(yordle.getReino())) {
                                // Todas as características correspondem a este Yordle
                                yordleEncontrado = yordle;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (yordleEncontrado != null) {
            JOptionPane.showMessageDialog(this, "Yordle encontrado: " + yordleEncontrado.getNomeYordle());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum Yordle encontrado com as características selecionadas.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Pergunta> perguntas = new ArrayList<>();
            List<Yordles> yordles = new ArrayList<>();
            perguntas.add(new Pergunta("Qual o tipo de campeão?", "Tanque", "Atirador", "Mago", "Assassino", "Suporte"));
            perguntas.add(new Pergunta("Qual o range do campeão?", "Corpo-a-Corpo", "Alto", "Muito Alto", "Médio", ""));
            perguntas.add(new Pergunta("Qual a cor predominante do campeão?", "Azul", "Branca", "Amarela", "Roxa", "Marrom"));
            perguntas.add(new Pergunta("Qual a lane preferida do campeão?", "Selva", "Meio", "Topo", "Bot-Lane", ""));
            perguntas.add(new Pergunta("A qual reino pertence o campeão?", "Shurima", "Runeterra", "Piltover", "Ionia", "Demacia"));
            yordles.add(new Yordles("Amumu", "Tanque", "Corpo-a-Corpo", "Azul", "Selva", "Shurima"));
            yordles.add(new Yordles("Corki", "Atirador", "Alto", "Branca", "Meio", "Runeterra"));
            yordles.add(new Yordles("Heimerdinger", "Mago", "Alto", "Amarelo", "Meio", "Piltover"));
            yordles.add(new Yordles("Kennen", "Mago", "Alto", "Azul", "Topo", "Ionia"));
            yordles.add(new Yordles("Lulu", "Suporte", "Alto", "Roxo", "Bot-Lane", "Demacia"));
            yordles.add(new Yordles("Poppy", "Tanque", "Corpo-a-Corpo", "Azul", "Topo", "Runeterra"));
            yordles.add(new Yordles("Rumble", "Mago", "Corpo-a-Corpo", "Marrom", "Topo", "Piltover"));
            yordles.add(new Yordles("Teemo", "Atirador", "Alto", "Marrom", "Topo", "Runeterra"));
            yordles.add(new Yordles("Tristana", "Atirador", "Muito Alto", "Branca", "Bot-Lane", "Demacia"));
            yordles.add(new Yordles("Veigar", "Mago", "Médio", "Roxo", "Meio", "Runeterra"));
            yordles.add(new Yordles("Fizz", "Assassino", "Corpo-a-Corpo", "Azul", "Meio", "Runeterra"));

            JogoPerguntasGUI jogo = new JogoPerguntasGUI(perguntas, yordles);
            jogo.setVisible(true);
        });
    }
}
