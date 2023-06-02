package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private List<Usuario> usuarios;

    //Cria uma nova lista de cadastros.
    public void save(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        setarId(usuario);
        usuarios.add(usuario);
    }

    public List<Usuario> findAll() {
        return this.usuarios;
    }

    //Gera um novo id para cada cadastro.
    private void setarId(Usuario usuario) {
        int id = 0;
        if (usuarios.isEmpty()) {
            id++;
            usuario.setId(id);
        } else {
            if (usuarios.get(usuarios.size() - 1) != null) {
                id = usuarios.get(usuarios.size() - 1).getId();
                id++;
                usuario.setId(id);
            }
        }
    }

    //Deleta um cadastro.
    public boolean delete(int id) {
        for (Usuario usuario : usuarios) {
            if (id == usuario.getId()) {
                usuarios.remove(usuario);
                return true;
            }
        }
        return false;
    }

    //Retorna um id de cadastro da lista.
    public Usuario retornaUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (id == usuario.getId()) {
                return usuario;
            }
        }
        return null;
    }

    public void update(int id, Usuario usuario) {
        Usuario usuarioEncontrado = retornaUsuario(id);
        if (!usuario.getNome().isEmpty()) usuarioEncontrado.setNome(usuario.getNome());
        if (!usuario.getCpf().isEmpty()) usuarioEncontrado.setCpf(usuario.getCpf());
        if (!usuario.getEmail().isEmpty()) usuarioEncontrado.setEmail(usuario.getEmail());
        if (usuario.getDataNascimento() != null) usuarioEncontrado.setDataNascimento(usuario.getDataNascimento());
        if (usuario.getSexo() != null) usuarioEncontrado.setSexo(usuario.getSexo());
    }

}
