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
            // Realiza o Cadastro de Usu?rios.
            Scanner scanner = new Scanner(System.in);
            System.out.println("#####################Sistema de Cadastro de Usuários#####################");
            System.out.println("Informe o nome do Usuário:");
            String nome = scanner.next();
            System.out.println("Informe o CPF do Usuário:");
            String cpf = scanner.next();
            System.out.println("Informe o email do Usuário:");
            String email = scanner.next();
            System.out.println("Informe a data de nascimento do Usuário:");
            LocalDate dataNascimento = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println("Informe o sexo do Usuário: ");
            Sexo sexo = scanner.next().equalsIgnoreCase("F") ? Sexo.FEMININO : Sexo.MASCULINO;
            //Cadastra o novo usuário.
            Usuario usuario = new Usuario(nome, cpf, email, dataNascimento, sexo);
            service.save(usuario);



            //Faz a Atualiza??o das informa??es desejadas do cadastro.
            System.out.println("Deseja Atualizar algum usu?rio ? [S/N]");
            if (scanner.next().equalsIgnoreCase("S")) {
                String continuar = "S";
                //  while (continuar == "S") {
                System.out.println("Digite o ID que deseja atualizar");
                int id = scanner.nextInt();
                Usuario usuarioEncontrado = service.retornaUsuario(id);
                System.out.println(usuarioEncontrado.toString());
                System.out.println("Informe o nome do Usuário:");
                String novoNome = scanner.next();
                System.out.println("Informe o CPF do Usuário:");
                String novoCpf = scanner.next();
                System.out.println("Informe o email do Usuário:");
                String novoEmail = scanner.next();
                System.out.println("Informe a data de nascimento do Usuário:");
                LocalDate novaDataNascimento = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.println("Informe o sexo do Usuário: ");
                Sexo novoSexo = scanner.next().equalsIgnoreCase("F") ? Sexo.FEMININO : Sexo.MASCULINO;
                Usuario novoUsuario = new Usuario(id, novoNome, novoCpf, novoEmail, novaDataNascimento, novoSexo);
                System.out.println(novoUsuario);
                System.out.println("Deseja salvar as novas informarções ?[S/N]");
                if (scanner.next().equalsIgnoreCase("S")){
                service.update(id, novoUsuario);
                System.out.println(novoUsuario);
                }

                // System.out.println("Deseja realizar mais alguma atualiza??o ? [S/N]");
                //  if (scanner.next().equalsIgnoreCase("N")) {
                //      continuar = "N";


            }

            //Realiza a Exclusão de algum cadastro dentro da lista.
            System.out.println("Deseja excluir algum usuário ? [S/N]");
            if (scanner.next().equalsIgnoreCase("S")) {
                System.out.println("Qual ID você deseja deletar: ");
                int deletarId = scanner.nextInt();
                boolean deletado = service.delete(deletarId);
                if (deletado) {
                    System.out.println("O ID Selecionado: " + deletarId + " foi exluido com sucesso.");
                } else {
                    System.out.println("Esse ID: " + deletarId + " não pode ser excluido ou não existe !!!");
                }
            }

            //Busca os cadastros da lista.
            System.out.println("Deseja ver a lista de usuários cadastrados? [S/N]");
            if (scanner.next().equalsIgnoreCase("S")) {
                List<Usuario> usuariosList = service.findAll();
                for (int i = 0; i < usuariosList.size(); i++) {
                    System.out.println(usuariosList.get(i).toString());
                }
            }

            System.out.println("Deseja continuar? [S/N]");
            if (scanner.next().equalsIgnoreCase("N")) {
                continua = "N";
            }
        }
        if (continua.equals("N")) {
            System.err.println("Fim do Programa !!!");
        }

    }
}