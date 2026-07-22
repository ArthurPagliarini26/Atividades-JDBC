package org.example.model;

public class Contato {

    private int id;
    private String nome;
    private String numero;
    protected static int contadorId = 1;

    public Contato(int id, String nome, String numero) {
    this.id = id;
    this.nome = nome;
    this.numero = numero;
    }

    public Contato() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {

        return  "\n======================\n" + 
                "Contato:\n" + 
                "id =" + id + 
                "\nnome = " + nome + 
                "\nnumero = " + numero;  
                
    }  

    
}
