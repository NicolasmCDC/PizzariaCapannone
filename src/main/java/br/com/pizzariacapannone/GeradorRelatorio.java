package br.com.pizzariacapannone;

/** Gera relatórios simples a partir de um critério informado. */
@SuppressWarnings("this-escape")
public class GeradorRelatorio {
    private String criterio;

    public GeradorRelatorio(String criterio) { setCriterio(criterio); }

    public void extrairRelatorio(String criterio) {
        setCriterio(criterio);
        System.out.println("Relatório solicitado. Critério: " + this.criterio);
        switch (this.criterio.toLowerCase()) {
            case "vendas", "venda" -> PedidoVenda.consolidarVendasDiarias();
            case "estoque" -> System.out.println(ItemEstoque.resumoEstoqueGlobal());
            case "pedidos", "pedido" -> System.out.println("Relatório de pedidos: consulte os lançamentos registrados.");
            default -> System.out.println("Critério registrado para consulta: " + this.criterio);
        }
    }
    public String getCriterio() { return criterio; }
    public void setCriterio(String criterio) {
        if (criterio == null || criterio.isBlank()) throw new IllegalArgumentException("Critério obrigatório.");
        this.criterio = criterio.trim();
    }
}
