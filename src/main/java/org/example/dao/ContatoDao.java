package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.db.ConnectionFactory;
import org.example.model.Contato;

public class ContatoDao {
    public void salvar(Contato contato) throws SQLException{
        String command = """
                INSERT INTO contatos
                (nome, numero)
                VALUES
                (?,?)
                """;

        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){

                stmt.setString(1, contato.getNome());
                stmt.setString(2, contato.getNumero());
                stmt.executeUpdate();

                System.out.println("Contato salvo com sucesso!");
        }

    }

    public void editar(int id, String novoNome, String novoTelefone) throws SQLException{
        String command = """
                    UPDATE contatos 
                    SET nome = ?, 
                    numero = ?
                    WHERE id = ? 
                    """;

        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){

                stmt.setString(1, novoNome);
                stmt.setString(2, novoTelefone);
                stmt.setInt(3, id);
                stmt.executeUpdate();

                System.out.println("Contato atualizado com sucesso!");

            } catch(SQLException e) {
                e.printStackTrace();
            }
    }

    public static List<Contato> listar() {
    String command = "SELECT id,nome,numero FROM contatos";
    List<Contato> contatos = new ArrayList<>();
    try (Connection conn = ConnectionFactory.conectar();
         PreparedStatement stmt = conn.prepareStatement(command)) {

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String numero = rs.getString("numero");

            Contato contato = new Contato(id, nome, numero);
            contatos.add(contato);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return contatos;
}

    public static List<Contato> buscarPorNome(String nome) {
        String command = """
                SELECT id, nome, numero FROM contatos 
                WHERE nome LIKE ?
                """;

        List<Contato> lista = new ArrayList<>();

        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){

                stmt.setString(1, "%" + nome + "%");

                ResultSet rs = stmt.executeQuery();

                while(rs.next()) {
                    Contato contato = new Contato();
                    contato.setId(rs.getInt("id"));
                    contato.setNome(rs.getString("nome"));
                    contato.setNumero(rs.getString("numero"));
                    lista.add(contato);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }

            return lista;
     
    }
 
}
