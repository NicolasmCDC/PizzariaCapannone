package br.com.pizzariacapannone;

/** Transação de quitação vinculada a um pedido. */
@SuppressWarnings("this-escape")
public class TransacaoPagamento {
    private String modalidade;
    private double valor;
    private boolean quitada;

    public TransacaoPagamento(String modalidade, double valor) {
        setModalidade(modalidade);
        setValor(valor);
    }
    public boolean processarQuitacao() {
        if (valor <= 0) return false;
        quitada = true;
        System.out.printf("Pagamento de R$ %.2f processado via %s.%n", valor, modalidade);
        return true;
    }
    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) {
        if (modalidade == null || modalidade.isBlank()) throw new IllegalArgumentException("Modalidade obrigatória.");
        this.modalidade = modalidade.trim();
    }
    public double getValor() { return valor; }
    public void setValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) throw new IllegalArgumentException("Valor do pagamento deve ser positivo.");
        this.valor = valor;
    }
    public boolean isQuitada() { return quitada; }
}
