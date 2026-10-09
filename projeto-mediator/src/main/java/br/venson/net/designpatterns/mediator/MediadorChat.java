package br.venson.net.designpatterns.mediator;

// Contrato que os usuarios enxergam. Nenhum usuario conhece outro usuario,
// so conhece quem implementa esta interface.
public interface MediadorChat {

    void registrar(Usuario usuario);

    void remover(Usuario usuario);

    void enviar(Usuario remetente, String mensagem);

    void enviarPrivado(Usuario remetente, String destinatario, String mensagem);
}
