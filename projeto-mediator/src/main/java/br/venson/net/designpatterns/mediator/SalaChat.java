package br.venson.net.designpatterns.mediator;

import java.util.LinkedHashMap;
import java.util.Map;

// Mediador concreto: e o unico lugar que sabe quem esta na sala e
// quais sao as regras de entrega das mensagens.
public class SalaChat implements MediadorChat {
    private final String nome;
    private final Map<String, Usuario> participantes = new LinkedHashMap<>();

    public SalaChat(String nome) {
        this.nome = nome;
    }

    @Override
    public void registrar(Usuario usuario) {
        if (participantes.containsKey(usuario.getNome())) {
            throw new IllegalArgumentException("Ja existe um " + usuario.getNome() + " na sala " + nome);
        }
        participantes.put(usuario.getNome(), usuario);
        avisar(usuario.getNome() + " entrou na sala");
    }

    @Override
    public void remover(Usuario usuario) {
        if (participantes.remove(usuario.getNome()) != null) {
            avisar(usuario.getNome() + " saiu da sala");
        }
    }

    // Regra da sala aberta: todo mundo recebe, menos quem enviou.
    @Override
    public void enviar(Usuario remetente, String mensagem) {
        for (Usuario participante : participantes.values()) {
            if (participante != remetente) {
                participante.receber(remetente.getNome(), mensagem);
            }
        }
    }

    // Regra da mensagem privada: so o destinatario recebe, e ele precisa estar na sala.
    @Override
    public void enviarPrivado(Usuario remetente, String destinatario, String mensagem) {
        Usuario alvo = participantes.get(destinatario);
        if (alvo == null) {
            remetente.receber(nome, destinatario + " nao esta na sala");
            return;
        }
        alvo.receber(remetente.getNome() + " (privado)", mensagem);
    }

    private void avisar(String texto) {
        System.out.println("[" + nome + "] " + texto);
    }
}
