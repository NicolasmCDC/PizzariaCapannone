package br.com.pizzariacapannone;

/** Linha de composição de um pedido de venda. */
@SuppressWarnings("this-escape")
public class ItemPedidoVenda {
    private int quantidadeSolicit;
    private double precoPraticado;
    private ItemEstoque itemEstoque;

    public ItemPedidoVenda(int quantidadeSolicit, double precoPraticado) {
        this(quantidadeSolicit, precoPraticado, null);
    }
    public ItemPedidoVenda(int quantidadeSolicit, double precoPraticado, ItemEstoque itemEstoque) {
        setQuantidadeSolicit(quantidadeSolicit);
        setPrecoPraticado(precoPraticado);
        this.itemEstoque = itemEstoque;
    }
    public double calcularSubtotal() { return quantidadeSolicit * precoPraticado; }
    public int getQuantidadeSolicit() { return quantidadeSolicit; }
    public void setQuantidadeSolicit(int quantidadeSolicit) {
        if (quantidadeSolicit <= 0) throw new IllegalArgumentException("Quantidade solicitada deve ser positiva.");
        this.quantidadeSolicit = quantidadeSolicit;
    }
    public double getPrecoPraticado() { return precoPraticado; }
    public void setPrecoPraticado(double precoPraticado) {
        if (!Double.isFinite(precoPraticado) || precoPraticado < 0) throw new IllegalArgumentException("Preço praticado inválido.");
        this.precoPraticado = precoPraticado;
    }
    public ItemEstoque getItemEstoque() { return itemEstoque; }
    public void setItemEstoque(ItemEstoque itemEstoque) { this.itemEstoque = itemEstoque; }
}
