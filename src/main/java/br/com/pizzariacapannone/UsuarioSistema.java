package br.com.pizzariacapannone;

import java.util.Objects;

/** Classe-base de usuários do sistema, conforme o UML. */
@SuppressWarnings("this-escape")
public class UsuarioSistema {
    private String login;
    private String senha;
    private String nomeCompleto;

    public UsuarioSistema(String login, String senha, String nomeCompleto) {
        setLogin(login);
        setSenha(senha);
        setNomeCompleto(nomeCompleto);
    }

    public boolean autenticarSistema() {
        return login != null && !login.isBlank() && senha != null && !senha.isBlank();
    }

    public String getLogin() { return login; }
    public void setLogin(String login) {
        if (login == null || login.isBlank()) throw new IllegalArgumentException("Login obrigatório.");
        this.login = login.trim();
    }
    public String getSenha() { return senha; }
    public void setSenha(String senha) {
        if (senha == null || senha.isBlank()) throw new IllegalArgumentException("Senha obrigatória.");
        this.senha = senha;
    }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) {
        if (nomeCompleto == null || nomeCompleto.isBlank()) throw new IllegalArgumentException("Nome obrigatório.");
        this.nomeCompleto = nomeCompleto.trim();
    }

    @Override public String toString() { return nomeCompleto + " (" + login + ")"; }
}
