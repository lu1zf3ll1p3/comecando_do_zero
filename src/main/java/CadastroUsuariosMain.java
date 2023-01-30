import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {

        String continua = "S";
        UsuarioService service = new UsuarioService();
        Scanner scanner = new Scanner(System.in);
        while (continua.equals("S")) {
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.print("Selecione a opção para continuar: \n[1]Cadastrar um novo usuário \n[2]Atualizar um usuário existente " +
                    "\n[3]Deletar um usuário existente \n[4]Listar usuários da lista \n[5]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1: {
                    //Realiza o Cadastro de Usuários.
                    System.out.println("Informe o nome do Usuário:");
                    String nome = scanner.next();
                    System.out.println("Informe o CPF do Usuário:");
                    String cpf = scanner.next();
                    System.out.println("Informe o email do Usuário:");
                    String email = scanner.next();
                    LocalDate dataNascimento = null;
                    do {
                        try {
                            System.out.println("Informe a data de nascimento do Usuário:");
                            dataNascimento = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (Exception formatoErrado) {
                            System.err.print("O Formato da data inserida está errada. \nInsira a data com o formato correto 00/00/0000. \n");
                        }
                    } while (dataNascimento == null);
                    System.out.println("Informe o sexo do Usuário: ");
                    Sexo sexo = scanner.next().equalsIgnoreCase("F") ? Sexo.FEMININO : Sexo.MASCULINO;
                    Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
                    service.save(usuario);
                    break;
                }
                case 2: {
                    //Faz a Atualização das informações desejadas do cadastro.
                    try {
                        System.out.println("Digite o ID que deseja atualizar");
                        int id = scanner.nextInt();
                        Usuario usuarioEncontrado = service.retornaUsuario(id);
                        System.out.println(usuarioEncontrado.toString());
                        scanner.nextLine();
                        System.out.println("Informe o nome do Usuário:");
                        String nome = scanner.nextLine();
                        if (nome.equals("")) {
                            nome = usuarioEncontrado.getNome(nome);
                        }
                        System.out.println("Informe o CPF do Usuário:");
                        String cpf = scanner.nextLine();
                        if (cpf.equals("")) {
                            cpf = usuarioEncontrado.getCpf(cpf);
                        }
                        System.out.println("Informe o email do Usuário:");
                        String email = scanner.nextLine();
                        if (email.equals("")) {
                            email = usuarioEncontrado.getEmail(email);
                        }
                        LocalDate dataNascimento;
                        try {
                            System.out.println("Informe a data de nascimento do Usuário:");
                            dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (Exception formatoErrado) {
                            dataNascimento = usuarioEncontrado.getDataNascimento(null);
                        }
                        System.out.println("Informe o sexo do Usuário: ");
                        String novoSexo = scanner.nextLine();
                        Sexo sexo;
                        if(novoSexo.equalsIgnoreCase("M")){
                            sexo = Sexo.MASCULINO;
                        } else if (novoSexo.equalsIgnoreCase("F")) {
                            sexo = Sexo.FEMININO;
                        }else {
                            sexo = usuarioEncontrado.getSexo();
                        }
                        service.update(id, nome, cpf, email, dataNascimento, sexo);
                        break;
                    } catch (Exception e) {
                        System.err.println("Não existem cadastros a serem atualizados !");
                        break;
                    }
                }
                case 3: {
                    //Realiza a Exclusão de algum cadastro dentro da lista.
                    try {
                        System.out.println("Qual ID você deseja deletar: ");
                        int deletarId = scanner.nextInt();
                        boolean deletado = service.delete(deletarId);
                        if (deletado) {
                            System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                        } else {
                            System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");
                        }
                        break;
                    } catch (Exception exclusaoSemSucesso) {
                        System.err.println("não foi possivel realizar essa exclusão !");
                        break;
                    }
                }
                case 4: {
                    //Busca os cadastros da lista.
                    try {
                        List<Usuario> usuariosList = service.findAll();
                        for (int i = 0; i < usuariosList.size(); i++) {
                            System.out.println(usuariosList.get(i).toString());
                        }
                        break;
                    } catch (NullPointerException semUsuariosNaLista) {
                        System.err.println("Não existem usuarios na lista... !");
                        break;
                    }
                }
                default: {
                    continua = "N";
                    System.err.println("Fim do Programa !!!");
                }
            }
        }
    }
}
