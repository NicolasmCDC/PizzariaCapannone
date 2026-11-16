package br.com.pizzariacapannone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Usuário que executa atividades operacionais e checklists. */
public class Atendente extends UsuarioSistema {
    private String funcaoCargo;
    private final List<RotinaChecklist> rotinas = new ArrayList<>();

    @SuppressWarnings("this-escape")
    public Atendente(String login, String senha, String nomeCompleto, String funcaoCargo) {
        super(login, senha, nomeCompleto);
        setFuncaoCargo(funcaoCargo);
    }

    public boolean executarAtividade() {
        if (!autenticarSistema()) return false;
        System.out.println("Atendente " + getNomeCompleto() + " executando atividade: " + funcaoCargo);
        return true;
    }
    public String getFuncaoCargo() { return funcaoCargo; }
    public void setFuncaoCargo(String funcaoCargo) {
        if (funcaoCargo == null || funcaoCargo.isBlank()) throw new IllegalArgumentException("Função/cargo obrigatório.");
        this.funcaoCargo = funcaoCargo.trim();
    }
    public void adicionarRotina(RotinaChecklist rotina) {
        if (rotina == null) throw new IllegalArgumentException("Rotina não pode ser nula.");
        rotinas.add(rotina);
    }
    public List<RotinaChecklist> getRotinas() { return Collections.unmodifiableList(rotinas); }
}
