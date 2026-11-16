package br.com.pizzariacapannone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Fornecedor responsável pelo abastecimento de insumos. */
@SuppressWarnings("this-escape")
public class FornecedorInsumo {
    private String cnpjFornecedor;
    private String dadosContato;
    private final List<ItemEstoque> itensFornecidos = new ArrayList<>();

    public FornecedorInsumo(String cnpjFornecedor, String dadosContato) {
        setCnpjFornecedor(cnpjFornecedor);
        setDadosContato(dadosContato);
    }
    public void emitirPedidoCompra() {
        System.out.println("Pedido de compra emitido para fornecedor " + cnpjFornecedor
                + ". Itens vinculados: " + itensFornecidos.size());
        for (ItemEstoque item : itensFornecidos) {
            if (item.checarEstoqueCritico()) {
                System.out.println("  Repor: " + item.getDescricaoItem()
                        + " (atual " + item.getQuantidadeAtual() + ", ideal " + item.getQuantidadeIdeal() + ")");
            }
        }
    }
    public void adicionarItemFornecido(ItemEstoque item) {
        if (item == null) throw new IllegalArgumentException("Item de estoque obrigatório.");
        if (!itensFornecidos.contains(item)) itensFornecidos.add(item);
    }
    public String getCnpjFornecedor() { return cnpjFornecedor; }
    public void setCnpjFornecedor(String cnpjFornecedor) {
        if (cnpjFornecedor == null || cnpjFornecedor.isBlank()) throw new IllegalArgumentException("CNPJ obrigatório.");
        this.cnpjFornecedor = cnpjFornecedor.trim();
    }
    public String getDadosContato() { return dadosContato; }
    public void setDadosContato(String dadosContato) {
        if (dadosContato == null || dadosContato.isBlank()) throw new IllegalArgumentException("Dados de contato obrigatórios.");
        this.dadosContato = dadosContato.trim();
    }
    public List<ItemEstoque> getItensFornecidos() { return Collections.unmodifiableList(itensFornecidos); }
}
