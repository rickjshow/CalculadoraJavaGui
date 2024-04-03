package com.unialfa.excecoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.showMessageDialog;

public class Calculadora extends JFrame {
    private JTextField campoValor;
    private JTextField campoValor1;
    private JButton botaoEnviar;

    public Calculadora() {
        setTitle("Janela de Entrada");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // GridBagLayout organiza os componentes em uma grade
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel labelCampoValor1 = new JLabel("Valor 1:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painel.add(labelCampoValor1, constraints);

        campoValor = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painel.add(campoValor, constraints);

        JLabel labelCampoValor2 = new JLabel("Valor 2:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painel.add(labelCampoValor2, constraints);

        campoValor1 = new JTextField(10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painel.add(campoValor1, constraints);

        botaoEnviar = new JButton("Somar");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Ocupa 2 colunas
        painel.add(botaoEnviar, constraints);

        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                somar();
            }
        });

        add(painel);
        setLocationRelativeTo(null);
    }

    private Integer converterValor(String label, String valorStr) {
        if (valorStr.isEmpty()) throw new RuntimeException("O campo '" + label + "' não pode ser vazio");
        if (valorStr.isBlank()) throw new RuntimeException("O campo '" + label + "' não pode ter espaços em branco");

        try {
            return Integer.parseInt(valorStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("O campo '" + label + "' deve conter apenas números");
        }
    }

    private void somar() {
        try {
            var valor1 = converterValor("Valor1", campoValor.getText());
            var valor2 = converterValor("Valor 2", campoValor1.getText());
            var total = valor1 + valor2;
            JOptionPane.showMessageDialog(this, "Total " + total);
        } catch (RuntimeException e) {
            showMessageDialog(this, e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}
