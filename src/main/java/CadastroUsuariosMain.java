import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.Formulario;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();
        Formulario formulario = new Formulario();
        Scanner scanner = new Scanner(System.in);

        String continua = "S";
        while (continua.equals("S")) {
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.print("Selecione a opção para continuar: \n[1]Cadastrar um novo usuário ou Atualizar um usuário existente " +
                    "\n[2]Deletar um usuário existente \n[3]Listar usuários da lista \n[4]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    System.out.println("Deseja realizar um novo [C]adrastro ou uma [A]tualização: ");
                    String selecao = scanner.nextLine();
                    if (selecao.equalsIgnoreCase("c")) {
                        Usuario novoUsuario = new Usuario(formulario.nome(), formulario.cpf(), formulario.email(), formulario.dataNascimento(), formulario.sexo());
                        service.save(novoUsuario);
                    } else if (selecao.equalsIgnoreCase("a")) {
                        Usuario atualizaUsuario = new Usuario(formulario.id(), formulario.nome(), formulario.cpf(), formulario.email(), formulario.dataNascimento(), formulario.sexo());
                        service.update(atualizaUsuario);
                    }else {
                        System.out.println("Não foi possivel realizar sua solicitação tente novamente !");
                    }
                }

               /* case 2 -> {
                    System.out.println("Digite o id a ser atualizado: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioEncontrado = service.retornaUsuario(id);
                    System.out.println(usuarioEncontrado.toString());
                    System.out.println("Informe o nome do Usuário: ");
                    String nome = scanner.nextLine();
                    if (nome.isEmpty()) nome = usuarioEncontrado.getNome();
                    System.out.println("Informe o CPF do Usuário: ");
                    String cpf = scanner.nextLine();
                    if (cpf.isEmpty()) cpf = usuarioEncontrado.getCpf();
                    System.out.println("Informe o email do Usuário: ");
                    String email = scanner.nextLine();
                    if (email.isEmpty()) email = usuarioEncontrado.getEmail();
                    System.out.println("Informe a data de nascimento do Usuário: ");
                    String data = scanner.nextLine();
                    LocalDate dataNascimento = null;
                    if (data != null && !data.isEmpty()) {
                        dataNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                    if (dataNascimento == null) dataNascimento = usuarioEncontrado.getDataNascimento();
                    System.out.println("Informe o sexo do Usuário: ");
                    String atualizaSexo = scanner.nextLine();
                    Sexo sexo = null;
                    if (atualizaSexo != null && !atualizaSexo.isEmpty()) {
                        sexo = atualizaSexo.equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
                    }
                    if (sexo == null) sexo = usuarioEncontrado.getSexo();
                    Usuario usuario = new Usuario(id, nome, cpf, email, dataNascimento, sexo);
                    service.update(usuario);
                    System.out.println(usuario);
                }
*/
                    case 2 -> {
                        //Realiza a Exclusão de algum cadastro dentro da lista.
                        System.out.println("Qual ID você deseja deletar: ");
                        int deletarId = scanner.nextInt();
                        boolean deletado = service.delete(deletarId);
                        if (deletado) {
                            System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                        } else {
                            System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");

                        }
                    }

                    case 3 -> {
                        //Busca os cadastros da lista.
                        List<Usuario> usuariosList = service.findAll();
                        for (Usuario usuarios : usuariosList) {
                            System.out.println(usuarios.toString());
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
