package br.com.desafio.model.entity;

import br.com.desafio.model.enums.Sexo;

import java.time.LocalDate;

public class UsuarioAtualiza {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private Sexo sexo;

    public UsuarioAtualiza(String nome, String cpf, String email, LocalDate dataNascimento, Sexo sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public UsuarioAtualiza() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String setNome(String nome) {
        this.nome = nome;
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalDate setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Sexo setSexo(Sexo sexo) {
        this.sexo = sexo;
        return sexo;
    }

    @Override
    public String toString() {
        return "UsuarioAtualiza{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo=" + sexo +
                '}';
    }
}
