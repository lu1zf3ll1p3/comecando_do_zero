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
            System.out.print("Selecione a opção para continuar: \n[1]Cadastrar um novo usuário ou Atualizar um usuário existente " +
                    "\n[2]Deletar um usuário existente \n[3]Listar usuários da lista \n[4]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    //Realiza o Cadastro de Usuários.
                    System.out.println("Deseja realizar uma [C]adastro ou [A]tualização: ");
                    String cadastro = scanner.nextLine();
                    System.out.print("Informe o nome do Usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Informe o CPF do Usuário: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Informe o email do Usuário: ");
                    String email = scanner.nextLine();
                    System.out.print("Informe a data de nascimento do Usuário: ");
                    LocalDate dataNascimento;
                    try {
                        dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (Exception e) {
                        System.out.println("Data inválida. O campo será considerado vazio, assim colocando o existente.");
                        dataNascimento = null;
                    }
                    System.out.print("Informe o sexo do Usuário: ");
                    Sexo sexo = scanner.nextLine().equalsIgnoreCase("F") ? Sexo.FEMININO : scanner.nextLine().equalsIgnoreCase("M") ? Sexo.MASCULINO : null;
                    Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
                    if (cadastro.equalsIgnoreCase("C")) {
                        service.save(usuario);
                        System.out.println(usuario);
                    } else {
                        if (service.findAll() != null) {
                            System.out.println("Digite o ID que deseja atualizar: ");
                            int id = scanner.nextInt();
                            System.out.println("\nDeseja realizar essa alteração ? [S/N]");
                            String alteracao = scanner.next();
                            if (alteracao.equalsIgnoreCase("s")) {
                                service.update(id, nome, cpf, email, dataNascimento, sexo);
                            }
                        } else {
                            System.out.println("Não existem cadastros para serem atualizados.");
                        }
                    }
                }
                case 2 -> {
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
                    } catch (Exception exclusaoSemSucesso) {
                        System.err.println("não foi possivel realizar essa exclusão !");
                    }
                }
                case 3 -> {
                    //Busca os cadastros da lista.
                    try {
                        List<Usuario> usuariosList = service.findAll();
                        for (Usuario usuario : usuariosList) {
                            System.out.println(usuario.toString());
                        }
                    } catch (NullPointerException semUsuariosNaLista) {
                        System.err.println("Não existem usuarios na lista... !");
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
