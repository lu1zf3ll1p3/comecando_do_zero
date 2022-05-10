import br.com.desafio.model.entity.Usuario;
import br.com.desafio.model.enums.Sexo;
import br.com.desafio.service.UsuarioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuariosMain {

    public static void main(String[] args) {
        String teste = "S";
        UsuarioService service = new UsuarioService();
        while (teste == "S") {
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
                teste = "N";
            }

            System.out.print("Deseja Atualizar algum usuário ? [S/N]");
            if (scanner.next().equals("S")) {
                System.out.println("Qual item da lista acima você deseja Atualizar: ");
                String atualizaItem = scanner.next();
                    switch (atualizaItem) {
                        case "nome":
                            System.out.println("Digite o novo nome: ");
                            nome = scanner.next();
                            usuario.setNome(nome);
                            System.out.print("Este é o nome escolhido: " + nome);
                            break;

                        case "cpf":
                            System.out.println("Digite o novo cpf: ");
                            cpf = scanner.next();
                            usuario.setCpf(cpf);
                            System.out.print("Este é o cpf escolhido: " + cpf);
                            break;

                        case "email":
                            System.out.println("Digite o novo email: ");
                            email = scanner.next();
                            usuario.setEmail(email);
                            System.out.print("Este é o email escolhido: " + email);
                            break;

                        case "dataNascimento":
                            System.out.println("Digite o nova data do nascimento: ");
                            dataNascimento = LocalDate.parse(scanner.next());
                            usuario.setDataNascimento(LocalDate.parse(String.valueOf(dataNascimento)));
                            System.out.print("Esta é a nova data do nascimento escolhido: " + dataNascimento);
                            break;

                        case "sexo":
                            System.out.println("Digite o novo sexo: ");
                            sexo = Sexo.valueOf(scanner.next());
                            usuario.setSexo(sexo);
                            System.out.print("Este é o sexo escolhido: " + sexo);
                            break;

                        default:
                            System.out.println("Não foi possivel realizar a sua auteração, verifique se esta correta o iten selecionado.");
                    }

                }


            System.out.print("Deseja excluir algum usuário ? [S/N]");
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