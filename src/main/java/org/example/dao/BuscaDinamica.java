package org.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuscaDinamica {
    public static void main(String[] args) {
        // 1. Criar uma lista de nomes
        List<String> nomes = new ArrayList<>();
        nomes.add("Carlos");
        nomes.add("Caio");
        nomes.add("Camila");
        nomes.add("Diego");
        nomes.add("Daniel");
        nomes.add("Fernanda");

        Scanner scanner = new Scanner(System.in);

        // Loop para ler o que o usuário digita continuamente
        while (true) {
            System.out.print("\nDigite algo para buscar (ou 'sair' para encerrar): ");
            String filtro = scanner.nextLine();

            // Condição de parada
            if (filtro.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.println("Resultados encontrados:");
            boolean encontrou = false;

            // 2. Filtrar a lista
            for (String nome : nomes) {
                // Verifica se o nome começa com o que o usuário digitou
                if (nome.startsWith(filtro)) {
                    System.out.println("- " + nome);
                    encontrou = true;
                }
            }

            if (!encontrou) {
                System.out.println("Nenhum nome encontrado.");
            }
        }

        scanner.close();
    }
}