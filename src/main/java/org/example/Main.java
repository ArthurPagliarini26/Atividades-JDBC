package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.example.dao.ContatoDao;
import org.example.db.ConnectionFactory;
import org.example.model.Contato;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        inicio();

    }

    public static void inicio(){

        int opcao;
        
        do {

            try {
        System.out.print("""
                \n---Lista de Contatos---
                1 - Cadastrar contato
                2 - Editar
                3 - Listar
                4 - Buscar por nome
                5 - Função que recebe array de ids e retorna uma lista como os contatos dos ids em contatos
                6 - Deletar
                0 - Sair
                Escolha uma opção: """);
                opcao = SC.nextInt();
                SC.nextLine();

                } catch(Exception e) {
                    System.out.println("Erro: digite apenas números");
                    SC.nextLine();
                    opcao = -1;
                }

        switch(opcao){
            case 1: {
                cadastrarContato();
                break;
            }

            case 2: {
                editarContato();
                break;
            }

            case 3: {
                listarContato();
                break;
            }

            case 4: {
                buscarPorNome();
                break;
            }

            case 5: {
                percorrerLista();
                break;
            }

            case 6: {
                deletarContato();
                break;
            }

            case 0: {
                System.out.println("Saindo...");
                break;
            }

            default: {
                System.out.println("Opção inválida.");
            }
        }
        } while(opcao != 0);
    }

    public static void cadastrarContato(){
        System.out.println("Digite o nome do contato:");
        String nome = SC.nextLine();

        System.out.println("Digite o número do contato:");
        String numero = SC.nextLine();

        var contato = new Contato(0, nome, numero);

        var contatoDao = new ContatoDao();

        try{
            contatoDao.salvar(contato);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editarContato(){

        boolean existe = false;

        System.out.println("Digite o id do contato para editar:");
        int id = SC.nextInt();
        SC.nextLine();
        
        try {
        for(Contato contato : ContatoDao.listar()) {
            if(contato.getId() == id) existe = true;
        }
        } catch(SQLException e) {
            e.printStackTrace();
        } 

        if(existe) {

            System.out.println("Digite o novo nome do contato:");
            String novoNome = SC.nextLine();

            System.out.println("Digite o novo número do contato:");
            String novoNumero = SC.nextLine();

            try {
                ContatoDao.editar(id, novoNome, novoNumero);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Contato inexistente!");
        }
    }

    
    public static void listarContato(){
        var ContatoDao = new ContatoDao();
        List<Contato> contatos = new ArrayList<>();

        try {
            contatos = ContatoDao.listar();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        for(Contato contato : contatos) {
            System.out.println("====================");
            System.out.println("Contato:");
            System.out.println("ID: " + contato.getId());
            System.out.println("NOME: " + contato.getNome());
            System.out.println("NÚMERO: " + contato.getNumero());
        }
    }

    public static void buscarPorNome(){

        boolean existe = false;

        System.out.print("Digite um nome para buscar: ");
        String nome = SC.nextLine().toLowerCase();

        try {
            
        for(Contato contato : ContatoDao.listar()) {
            if(contato.getNome().toLowerCase().contains(nome)) existe = true;
        }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(existe) {
            try {
            System.out.println(ContatoDao.buscarPorNome(nome));    
            } catch(SQLException e) {
                e.printStackTrace();
            }        

        } else {
            System.out.println("Contato inexistente.");
        }
    }

    public static void percorrerLista() {

        ArrayList<Integer> ids = new ArrayList<>();

        while(true) {
            System.out.println("Digite um id para adicionar no Array: (0 para parar)");
            int id = SC.nextInt();
            ids.add(id);

            if(id == 0) { break; }
        }

        System.out.println(ContatoDao.percorrerArray(ids));
        if(ContatoDao.percorrerArray(ids).isEmpty()) {
            System.out.println("Ids não encontrados!");
        }
    }

    public static void deletarContato() {
        System.out.print("Digite o id do contato para deletar: ");
        int id = SC.nextInt();

        try {
        ContatoDao.deletar(id);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}