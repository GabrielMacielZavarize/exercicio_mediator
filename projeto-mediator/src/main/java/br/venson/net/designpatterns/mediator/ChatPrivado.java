package br.venson.net.designpatterns.mediator;

import java.util.ArrayList;
import java.util.List;

// Outro mediador concreto, com regras diferentes da SalaChat:
// aceita no maximo duas pessoas e toda mensagem ja e privada.
// A classe Usuario e reaproveitada sem nenhuma alteracao.
public class ChatPrivado implements MediadorChat {
    private final List<Usuario> participantes = new ArrayList<>();

    @Override
    public void registrar(Usuario usuario) {
        if (participantes.size() == 2) {
            throw new IllegalStateException("Chat privado aceita apenas duas pessoas");
        }
        participantes.add(usuario);
    }

    @Override
    public void remover(Usuario usuario) {
        participantes.remove(usuario);
    }

    @Override
    public void enviar(Usuario remetente, String mensagem) {
        for (Usuario participante : participantes) {
            if (participante != remetente) {
                participante.receber(remetente.getNome() + " (privado)", mensagem);
            }
        }
    }

    @Override
    public void enviarPrivado(Usuario remetente, String destinatario, String mensagem) {
        // Aqui so existe uma outra pessoa, entao privado e normal dao no mesmo.
        enviar(remetente, mensagem);
    }
}
