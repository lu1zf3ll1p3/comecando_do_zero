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
            System.out.println("#####################Sistema de Cadastro de Usu�rios#####################");
            System.out.print("Selecione a op��o para continuar: \n[1]Cadastrar um novo usu�rio \n[2]Atualizar um usu�rio existente " +
                    "\n[3]Deletar um usu�rio existente \n[4]Listar usu�rios da lista \n[5]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1 -> {
                    //Realiza o Cadastro de Usu�rios.
                    System.out.println("Deseja realizar uma cadastro ou uma atualiza��o: [C/A]");
                    String cadastro = scanner.next();
                    if (cadastro.equalsIgnoreCase("a")){
                        System.out.println("Digite o ID que deseja atualizar: ");
                        int id = scanner.nextInt();
                        Usuario usuarioEncontrado = service.retornaUsuario(id);
                        System.out.println(usuarioEncontrado);
                    }
                    System.out.print("Informe o nome do Usu�rio: ");
                    String nome = scanner.nextLine();
                    System.out.print("Informe o CPF do Usu�rio: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Informe o email do Usu�rio: ");
                    String email = scanner.nextLine();
                    System.out.print("Informe a data de nascimento do Usu�rio: ");
                    LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.err.print("O Formato da data inserida est� errada. \nInsira a data com o formato correto 00/00/0000. \n");
                    System.out.print("Informe o sexo do Usu�rio: ");
                    Sexo sexo = scanner.nextLine().equalsIgnoreCase("F") ? Sexo.FEMININO : Sexo.MASCULINO;
                    Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
                    cadastro.equalsIgnoreCase("c") ? service.save(usuario) : service.update( usuario);
                }
                case 2 -> {
                    //Faz a Atualiza��o das informa��es desejadas do cadastro.
                    try {
                        System.out.println("Digite o ID que deseja atualizar: ");
                        int id = scanner.nextInt();
                        Usuario usuarioEncontrado = service.retornaUsuario(id);
                        System.out.println(usuarioEncontrado);
                        scanner.nextLine();
                        System.out.print("Informe o nome do Usu�rio: ");
                        String nome = scanner.nextLine().equals("") ? nome = usuarioEncontrado.getNome(null) : scanner.nextLine();
                        System.out.print("Informe o CPF do Usu�rio: ");
                        String cpf = scanner.nextLine().equals("") ? cpf = usuarioEncontrado.getCpf(null) : scanner.nextLine();
                        System.out.print("Informe o email do Usu�rio: ");
                        String email = scanner.nextLine().equals("") ? email = usuarioEncontrado.getEmail(null) : scanner.nextLine();
                        System.out.print("Informe a data de nascimento do Usu�rio: ");
                        LocalDate dataNascimento;
                        try {
                            dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        } catch (Exception inputVazio) {
                            dataNascimento = usuarioEncontrado.getDataNascimento(null);
                        }
                        System.out.print("Informe o sexo do Usu�rio: ");
                        String atualizaSexo = scanner.nextLine().toUpperCase();
                        Sexo sexo = switch (atualizaSexo) {
                            case "F" -> Sexo.FEMININO;
                            case "M" -> Sexo.MASCULINO;
                            default -> usuarioEncontrado.getSexo(null);
                        };
                        Usuario usuario = new Usuario(id, nome, cpf, email, dataNascimento, sexo);
                        service.update(id, usuario);
                    } catch (Exception e) {
                        System.err.println("N�o existem cadastros a serem atualizados !");
                    }
                }
                case 3 -> {
                    //Realiza a Exclus�o de algum cadastro dentro da lista.
                    try {
                        System.out.println("Qual ID voc� deseja deletar: ");
                        int deletarId = scanner.nextInt();
                        boolean deletado = service.delete(deletarId);
                        if (deletado) {
                            System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                        } else {
                            System.out.println("Esse ID: " + deletarId + " n�o pode ser excluido ou n�o existe !!!");
                        }
                    } catch (Exception exclusaoSemSucesso) {
                        System.err.println("n�o foi possivel realizar essa exclus�o !");
                    }
                }
                case 4 -> {
                    //Busca os cadastros da lista.
                    try {
                        List<Usuario> usuariosList = service.findAll();
                        for (Usuario usuario : usuariosList) {
                            System.out.println(usuario.toString());
                        }
                    } catch (NullPointerException semUsuariosNaLista) {
                        System.err.println("N�o existem usuarios na lista... !");
                    }
                }
                default -> {
                    continua = "N";
                    System.err.println("Fim do Programa !!!");
                }
            }
        }
    }
}
