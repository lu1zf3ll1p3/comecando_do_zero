package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.entity.UsuarioAtualiza;
import br.com.desafio.model.enums.Sexo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioService {
    UsuarioAtualiza atualizarUsuario = new UsuarioAtualiza();
    private List<Usuario> usuarios;

    public Usuario save(Usuario usuario) {
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }
        setarId(usuario);
        usuarios.add(usuario);
        return usuario;
    }


    public List<Usuario> findAll() {
        return this.usuarios;
    }

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

    public boolean delete(int id) {
        boolean find = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (id == usuarios.get(i).getId()) {
                find = true;
                usuarios.remove(i);
                break;
            }
        }
        return find;
    }


    public void userUpdate(String nomeAtualizado, String cpfAtualizado, String emailAtualizado, LocalDate dataNascimentoAtualizado, Sexo sexoAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (!Objects.equals(usuarios.get(i).getNome(), atualizarUsuario.getNome())) {
                usuarios.get(i).setNome(atualizarUsuario.setNome(nomeAtualizado));
            }else if(!Objects.equals(usuarios.get(i).getCpf(), atualizarUsuario.getCpf())){
                usuarios.get(i).setCpf(atualizarUsuario.setCpf(cpfAtualizado));
            }else if(!Objects.equals(usuarios.get(i).getEmail(), atualizarUsuario.getEmail())){
                usuarios.get(i).setEmail(atualizarUsuario.setEmail(emailAtualizado));
            }else if (!Objects.equals(usuarios.get(i).getDataNascimento(), atualizarUsuario.getDataNascimento())){
                usuarios.get(i).setDataNascimento(atualizarUsuario.setDataNascimento(dataNascimentoAtualizado));
            }else{
                usuarios.get(i).setSexo(atualizarUsuario.setSexo(sexoAtualizado));
            }
        }
    }
}

