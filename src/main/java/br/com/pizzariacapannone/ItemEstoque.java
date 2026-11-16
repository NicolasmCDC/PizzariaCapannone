package br.com.pizzariacapannone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/** Insumo controlado pelo estoque. */
@SuppressWarnings("this-escape")
public class ItemEstoque {
    private String codigoItem;
    private String descricaoItem;
    private double valorUnitario;
    private int quantidadeAtual;
    private int quantidadeIdeal;
    private static final List<ItemEstoque> CATALOGO = new ArrayList<>();

    public ItemEstoque(String codigoItem, String descricaoItem, double valorUnitario,
                       int quantidadeAtual, int quantidadeIdeal) {
        setCodigoItem(codigoItem);
        setDescricaoItem(descricaoItem);
        setValorUnitario(valorUnitario);
        setQuantidadeAtual(quantidadeAtual);
        setQuantidadeIdeal(quantidadeIdeal);
        CATALOGO.add(this);
    }
    public ItemEstoque pesquisarItem(String termoBusca) {
        if (termoBusca == null || termoBusca.isBlank()) return null;
        String termo = termoBusca.toLowerCase(Locale.ROOT);
        if (codigoItem.toLowerCase(Locale.ROOT).contains(termo)
                || descricaoItem.toLowerCase(Locale.ROOT).contains(termo)) return this;
        for (ItemEstoque item : CATALOGO) {
            if (item.codigoItem.toLowerCase(Locale.ROOT).contains(termo)
                    || item.descricaoItem.toLowerCase(Locale.ROOT).contains(termo)) return item;
        }
        return null;
    }
    public void atualizarQuantidade(int quantidade) {
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        this.quantidadeAtual = quantidade;
    }
    public boolean checarEstoqueCritico() { return quantidadeAtual <= quantidadeIdeal; }

    public static String resumoEstoqueGlobal() {
        StringBuilder sb = new StringBuilder("Estoque cadastrado:\n");
        for (ItemEstoque item : CATALOGO) {
            sb.append("- ").append(item.descricaoItem).append(": ")
              .append(item.quantidadeAtual).append(" (ideal ").append(item.quantidadeIdeal).append(")")
              .append(item.checarEstoqueCritico() ? " [CRÍTICO]" : "").append('\n');
        }
        return sb.toString();
    }
    public void baixarEstoque(int quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade de baixa deve ser positiva.");
        if (quantidade > quantidadeAtual) throw new IllegalStateException("Estoque insuficiente para " + descricaoItem);
        quantidadeAtual -= quantidade;
    }
    public String getCodigoItem() { return codigoItem; }
    public void setCodigoItem(String codigoItem) {
        if (codigoItem == null || codigoItem.isBlank()) throw new IllegalArgumentException("Código obrigatório.");
        this.codigoItem = codigoItem.trim();
    }
    public String getDescricaoItem() { return descricaoItem; }
    public void setDescricaoItem(String descricaoItem) {
        if (descricaoItem == null || descricaoItem.isBlank()) throw new IllegalArgumentException("Descrição obrigatória.");
        this.descricaoItem = descricaoItem.trim();
    }
    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) {
        if (!Double.isFinite(valorUnitario) || valorUnitario < 0) throw new IllegalArgumentException("Valor unitário inválido.");
        this.valorUnitario = valorUnitario;
    }
    public int getQuantidadeAtual() { return quantidadeAtual; }
    public void setQuantidadeAtual(int quantidadeAtual) {
        if (quantidadeAtual < 0) throw new IllegalArgumentException("Quantidade atual inválida.");
        this.quantidadeAtual = quantidadeAtual;
    }
    public int getQuantidadeIdeal() { return quantidadeIdeal; }
    public void setQuantidadeIdeal(int quantidadeIdeal) {
        if (quantidadeIdeal < 0) throw new IllegalArgumentException("Quantidade ideal inválida.");
        this.quantidadeIdeal = quantidadeIdeal;
    }
    public static List<ItemEstoque> getCatalogo() { return Collections.unmodifiableList(CATALOGO); }
    @Override public String toString() { return codigoItem + " - " + descricaoItem + " (" + quantidadeAtual + ")"; }
}
