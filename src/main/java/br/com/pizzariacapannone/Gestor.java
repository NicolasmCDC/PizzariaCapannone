package br.com.pizzariacapannone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Usuário responsável pela administração do negócio e relatórios. */
public class Gestor extends UsuarioSistema {
    private final List<GeradorRelatorio> relatorios = new ArrayList<>();

    public Gestor(String login, String senha, String nomeCompleto) {
        super(login, senha, nomeCompleto);
    }

    public void administrarNegocio() {
        System.out.println("Gestor " + getNomeCompleto() + " acessou o painel administrativo.");
        System.out.println("Relatórios disponíveis: " + relatorios.size());
    }

    public void adicionarRelatorio(GeradorRelatorio relatorio) {
        if (relatorio == null) throw new IllegalArgumentException("Relatório não pode ser nulo.");
        relatorios.add(relatorio);
    }
    public List<GeradorRelatorio> getRelatorios() { return Collections.unmodifiableList(relatorios); }
}
