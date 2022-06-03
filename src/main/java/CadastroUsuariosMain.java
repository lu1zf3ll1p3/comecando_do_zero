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
        while (continua == "S") {
            Scanner scanner = new Scanner(System.in);
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.println("Informe o nome do Usuário:");
            String nome = scanner.nextLine();
            System.out.println("Informe o CPF do Usuário:");
            String cpf = scanner.nextLine();
            System.out.println("Informe o email do Usuário:");
            String email = scanner.nextLine();
            System.out.println("Informe a data de nascimento do Usuário:");
            LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println("Informe o sexo do Usuário:");
            Sexo sexo = scanner.next().equals("F") ? Sexo.FEMININO : Sexo.MASCULINO;
            Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
            service.save(usuario);
            System.out.println("Deseja continuar? [S/N]");
            if (scanner.next().equals("N")) {
                continua = "N";
            }

            System.out.println("Deseja Atualizar algum usuário ? [S/N]");
            if (scanner.next().equals("S")) {
                String continuar = "S";
                while (continuar == "S") {
                    System.out.println("Digite o ID que deseja atualizar: ");
                    int id = scanner.nextInt();
                    Usuario updateId = service.retornaUsuario(id);
                    if (updateId != null) {
                        String info = scanner.next();
                        service.retornaInfo(info);
                    }else {
                            System.out.print("Id: " + id + " não encontrado.");
                    }

                    System.out.println("Deseja realizar mais alguma atualização ? [S/N]");
                    if (scanner.next().equals("N")) {
                        continuar = "N";
                    }
                }
            }

            System.out.println("Deseja excluir algum usuário ? [S/N]");
            if (scanner.next().equals("S")) {
                System.out.println("Qual ID você deseja deletar: ");
                int deletarId = scanner.nextInt();
                boolean deletado = service.delete(deletarId);
                if (deletado) {
                    System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                } else {
                    System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");
                }
            }

            System.out.println("Deseja ver a lista de usuários cadastrados? [S/N]");
            if (scanner.next().equals("S")) {
                List<Usuario> usuariosList = service.findAll();
                for (int i = 0; i < usuariosList.size(); i++) {
                    System.out.println(usuariosList.get(i).toString());
                }
            }
        }
    }
}

