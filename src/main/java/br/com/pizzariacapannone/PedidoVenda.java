package br.com.pizzariacapannone;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/** Pedido de venda; sua composição contém um ou mais itens de pedido. */
@SuppressWarnings("this-escape")
public class PedidoVenda {
    private Date dataHoraPedido;
    private double montanteTotal;
    private final List<ItemPedidoVenda> itens = new ArrayList<>();
    private final List<TransacaoPagamento> transacoes = new ArrayList<>();
    private final List<DocumentoFiscal> documentosFiscais = new ArrayList<>();
    private static final List<PedidoVenda> VENDAS = new ArrayList<>();

    public PedidoVenda() { this(new Date()); }
    public PedidoVenda(Date dataHoraPedido) {
        setDataHoraPedido(dataHoraPedido);
        VENDAS.add(this);
    }
    public boolean adicionarItem(ItemPedidoVenda item) {
        if (item == null) return false;
        itens.add(item);
        recalcularTotal();
        return true;
    }
    public boolean removerItem(ItemPedidoVenda item) {
        boolean removido = itens.remove(item);
        if (removido) recalcularTotal();
        return removido;
    }
    public boolean registrarLancamento() {
        if (itens.isEmpty() || montanteTotal <= 0) return false;
        System.out.printf("Lançamento de venda registrado: R$ %.2f%n", montanteTotal);
        return true;
    }
    public static void consolidarVendasDiarias() {
        String hoje = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        double totalDia = 0;
        int quantidade = 0;
        for (PedidoVenda pedido : VENDAS) {
            if (hoje.equals(new SimpleDateFormat("yyyy-MM-dd").format(pedido.dataHoraPedido))) {
                totalDia += pedido.montanteTotal;
                quantidade++;
            }
        }
        System.out.printf("Consolidação diária (%s): %d pedido(s), total R$ %.2f%n", hoje, quantidade, totalDia);
    }
    public void recalcularTotal() {
        montanteTotal = itens.stream().mapToDouble(ItemPedidoVenda::calcularSubtotal).sum();
    }
    public void adicionarTransacao(TransacaoPagamento transacao) {
        if (transacao == null) throw new IllegalArgumentException("Transação obrigatória.");
        transacoes.add(transacao);
    }
    public void adicionarDocumentoFiscal(DocumentoFiscal documento) {
        if (documento == null) throw new IllegalArgumentException("Documento fiscal obrigatório.");
        documentosFiscais.add(documento);
    }
    public Date getDataHoraPedido() { return new Date(dataHoraPedido.getTime()); }
    public void setDataHoraPedido(Date dataHoraPedido) {
        if (dataHoraPedido == null) throw new IllegalArgumentException("Data/hora do pedido obrigatória.");
        this.dataHoraPedido = new Date(dataHoraPedido.getTime());
    }
    public double getMontanteTotal() { return montanteTotal; }
    public void setMontanteTotal(double montanteTotal) {
        if (!Double.isFinite(montanteTotal) || montanteTotal < 0) throw new IllegalArgumentException("Montante inválido.");
        this.montanteTotal = montanteTotal;
    }
    public List<ItemPedidoVenda> getItens() { return Collections.unmodifiableList(itens); }
    public List<TransacaoPagamento> getTransacoes() { return Collections.unmodifiableList(transacoes); }
    public List<DocumentoFiscal> getDocumentosFiscais() { return Collections.unmodifiableList(documentosFiscais); }
    public static List<PedidoVenda> getVendas() { return Collections.unmodifiableList(VENDAS); }
}
