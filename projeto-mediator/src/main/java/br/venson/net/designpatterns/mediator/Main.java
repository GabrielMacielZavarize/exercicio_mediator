package br.venson.net.designpatterns.mediator;

public class Main {

    public static void main(String[] args) {
        SalaChat turma = new SalaChat("Turma");

        Usuario ana = new Usuario("Ana");
        Usuario bruno = new Usuario("Bruno");
        Usuario carla = new Usuario("Carla");

        // Cada usuario so entra na sala. Ninguem precisa conhecer ninguem.
        ana.entrar(turma);
        bruno.entrar(turma);
        carla.entrar(turma);

        ana.enviar("Bom dia, pessoal!");

        // Incluir o Diego agora e uma linha so.
        Usuario diego = new Usuario("Diego");
        diego.entrar(turma);
        diego.enviar("Cheguei atrasado, perdi alguma coisa?");

        // Remover a Carla tambem: a sala tira ela do registro e nao sobra referencia.
        carla.sair();
        bruno.enviar("A Carla saiu, depois alguem passa o recado pra ela.");

        // Mensagem privada: a regra mora na sala, nao no usuario.
        ana.enviarPrivado("Diego", "Te mando o resumo da aula depois.");
        ana.enviarPrivado("Carla", "Ainda esta por ai?");

        // A mesma classe Usuario funcionando com outro mediador.
        System.out.println();
        ChatPrivado privado = new ChatPrivado();
        ana.entrar(privado);
        bruno.entrar(privado);
        ana.enviar("Bruno, vamos fechar o trabalho hoje?");
        bruno.enviar("Fechado, 19h.");
    }
}
