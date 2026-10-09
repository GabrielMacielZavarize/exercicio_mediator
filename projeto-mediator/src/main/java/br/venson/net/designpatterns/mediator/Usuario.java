package br.venson.net.designpatterns.mediator;

public class Usuario {
    private final String nome;
    private MediadorChat mediador;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void entrar(MediadorChat sala) {
        sair();
        sala.registrar(this);
        this.mediador = sala;
    }

    public void sair() {
        if (mediador != null) {
            mediador.remover(this);
            mediador = null;
        }
    }

    public void enviar(String mensagem) {
        verificarConexao();
        mediador.enviar(this, mensagem);
    }

    public void enviarPrivado(String destinatario, String mensagem) {
        verificarConexao();
        mediador.enviarPrivado(this, destinatario, mensagem);
    }

    public void receber(String remetente, String mensagem) {
        System.out.println(nome + " recebeu de " + remetente + ": " + mensagem);
    }

    private void verificarConexao() {
        if (mediador == null) {
            throw new IllegalStateException(nome + " nao esta em nenhuma sala");
        }
    }
}
