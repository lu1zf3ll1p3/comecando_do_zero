import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {
        UsuarioService service = new UsuarioService();
        Usuario usuario = new Usuario();
        Scanner scanner = new Scanner(System.in);

        String continua = "S";
        while (continua.equals("S")) {
            System.out.println("#####################Sistema de Cadastro de Usu�rios#####################");
            System.out.print("Selecione a op��o para continuar: \n[1]Cadastrar um novo usu�rio ou Atualizar um usu�rio existente " +
                    "\n[2]Deletar um usu�rio existente \n[3]Listar usu�rios da lista \n[4]Sair \nSelecione: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            switch (escolha) {
                case 1 -> {
                    System.out.print("Informe o nome do Usu�rio: ");
                    String nome = scanner.nextLine();
                    usuario.campoVazio(nome);
                    System.out.print("Informe o CPF do Usu�rio: ");
                    String cpf = scanner.nextLine();
                    usuario.campoVazio(cpf);
                    System.out.print("Informe o email do Usu�rio: ");
                    String email = scanner.nextLine();
                    usuario.campoVazio(email);
                    System.out.print("Informe a data de nascimento do Usu�rio: ");
                    String data = scanner.nextLine();
                    usuario.campoVazio(data);
                    LocalDate dataNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.out.print("Informe o sexo do Usu�rio: ");
                    Sexo sexo;
                    String atualizaSexo = scanner.nextLine();
                    usuario.campoVazio(atualizaSexo);
                    sexo = atualizaSexo.equalsIgnoreCase("f") ? Sexo.FEMININO : Sexo.MASCULINO;
                    Usuario novoUsuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
                    service.save(novoUsuario);
                }

               /* case 2 -> {
                    System.out.println("Digite o id a ser atualizado: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuarioEncontrado = service.retornaUsuario(id);
                    System.out.println(usuarioEncontrado.toString());
                    System.out.println("Informe o nome do Usu�rio: ");
                    String nome = scanner.nextLine();
                    if (nome.isEmpty()) nome = usuarioEncontrado.getNome();
                    System.out.println("Informe o CPF do Usu�rio: ");
                    String cpf = scanner.nextLine();
                    if (cpf.isEmpty()) cpf = usuarioEncontrado.getCpf();
                    System.out.println("Informe o email do Usu�rio: ");
                    String email = scanner.nextLine();
                    if (email.isEmpty()) email = usuarioEncontrado.getEmail();
                    System.out.println("Informe a data de nascimento do Usu�rio: ");
                    String data = scanner.nextLine();
                    LocalDate dataNascimento = null;
                    if (data != null && !data.isEmpty()) {
                        dataNascimento = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                    if (dataNascimento == null) dataNascimento = usuarioEncontrado.getDataNascimento();
                    System.out.println("Informe o sexo do Usu�rio: ");
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
                case 3 -> {
                    //Realiza a Exclus�o de algum cadastro dentro da lista.
                    System.out.println("Qual ID voc� deseja deletar: ");
                    int deletarId = scanner.nextInt();
                    boolean deletado = service.delete(deletarId);
                    if (deletado) {
                        System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                    } else {
                        System.out.println("Esse ID: " + deletarId + " n�o pode ser excluido ou n�o existe !!!");

                    }
                }

                case 4 -> {
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
