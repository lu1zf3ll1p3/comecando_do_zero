package br.com.desafio.service;

import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class UsuarioServiceTest {

    private final Usuario usuario = new Usuario(1, "Luiz", "054", "l.com", LocalDate.parse("19/02/1994", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Sexo.MASCULINO);
    private final UsuarioService usuarioService = new UsuarioService();


    @Test
    public void deveSalvarUsuario() {
        usuarioService.save(usuario);
        Usuario usuarioEncontrado = usuarioService.retornaUsuario(1);
        Assertions.assertEquals(usuario, usuarioEncontrado);
    }

    @Test
    public void deveProcurarTodosOsUsuarios() {
        usuarioService.save(usuario);
        usuarioService.findAll();
        Assertions.assertEquals(1, usuarioService.findAll().toArray().length);
    }

    @Test
    public void deveDeletarUsuarioSelecionado() {
        usuarioService.save(usuario);
        Assertions.assertTrue(usuarioService.delete(1));
    }

    @Test
    public void retornaUsuario() {
        usuarioService.save(usuario);
        Assertions.assertEquals(usuario, usuarioService.retornaUsuario(1));
    }

    @Test
    public void deveAtualizarUmUsuario() {
        usuarioService.save(usuario);
        Usuario usuario1 = new Usuario(1, "LuizF", "054", "l.com", LocalDate.parse("19/02/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Sexo.MASCULINO);
        usuarioService.update(1, usuario1);
        Usuario usuarioAtualizado = usuarioService.retornaUsuario(1);
        Assertions.assertEquals(usuario, usuarioAtualizado);
    }
}