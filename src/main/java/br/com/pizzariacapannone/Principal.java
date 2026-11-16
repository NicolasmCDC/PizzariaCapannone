package br.com.pizzariacapannone;

import java.util.Date;

/** Demonstração executável e bateria de verificações do sistema. */
public class Principal {
    private static int aprovados;
    private static int total;

    private static void verificar(String descricao, boolean resultado) {
        total++;
        if (resultado) {
            aprovados++;
            System.out.println("[OK] " + descricao);
        } else {
            System.out.println("[FALHOU] " + descricao);
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       PIZZARIA CAPANNONE - SISTEMA JAVA");
        System.out.println("==================================================");

        Gestor gestor = new Gestor("gestor", "senha123", "Mariana Costa");
        Atendente atendente = new Atendente("atendente", "abc123", "Lucas Silva", "Atendimento");
        verificar("Autenticação do gestor", gestor.autenticarSistema());
        verificar("Autenticação do atendente", atendente.autenticarSistema());
        verificar("Herança de UsuarioSistema", gestor instanceof UsuarioSistema && atendente instanceof UsuarioSistema);

        RotinaChecklist rotina = new RotinaChecklist("Conferir limpeza do salão");
        atendente.adicionarRotina(rotina);
        verificar("Atendente recebe rotina de checklist", atendente.getRotinas().size() == 1);
        rotina.finalizarTarefa();
        verificar("Finalização de tarefa atualiza status", rotina.isStatusConclusao());
        verificar("Atendente executa atividade", atendente.executarAtividade());

        ItemEstoque farinha = new ItemEstoque("INS-001", "Farinha", 5.50, 30, 10);
        ItemEstoque queijo = new ItemEstoque("INS-002", "Queijo", 28.00, 8, 12);
        verificar("Pesquisa de item por código/descrição", farinha.pesquisarItem("far") == farinha);
        verificar("Estoque crítico detectado", queijo.checarEstoqueCritico());
        farinha.atualizarQuantidade(35);
        verificar("Atualização de quantidade", farinha.getQuantidadeAtual() == 35);

        FornecedorInsumo fornecedor = new FornecedorInsumo("12.345.678/0001-90", "compras@fornecedor.com");
        fornecedor.adicionarItemFornecido(farinha);
        fornecedor.adicionarItemFornecido(queijo);
        fornecedor.emitirPedidoCompra();
        verificar("Fornecedor mantém itens fornecidos", fornecedor.getItensFornecidos().size() == 2);

        PedidoVenda pedido = new PedidoVenda(new Date());
        verificar("Pedido aceita primeiro item", pedido.adicionarItem(new ItemPedidoVenda(2, 49.90, farinha)));
        verificar("Pedido aceita segundo item", pedido.adicionarItem(new ItemPedidoVenda(1, 59.90, queijo)));
        verificar("Cálculo do montante total", Math.abs(pedido.getMontanteTotal() - 159.70) < 0.001);
        verificar("Pedido registra lançamento", pedido.registrarLancamento());

        TransacaoPagamento pagamento = new TransacaoPagamento("Cartão", pedido.getMontanteTotal());
        verificar("Pagamento processado", pagamento.processarQuitacao() && pagamento.isQuitada());
        pedido.adicionarTransacao(pagamento);
        verificar("Pedido associa transação de pagamento", pedido.getTransacoes().size() == 1);

        DocumentoFiscal documento = new DocumentoFiscal();
        verificar("Documento fiscal emitido", documento.emitirDocumentoFiscal() && documento.isEmitido());
        pedido.adicionarDocumentoFiscal(documento);
        verificar("Pedido associa documento fiscal", pedido.getDocumentosFiscais().size() == 1);
        PedidoVenda.consolidarVendasDiarias();

        GeradorRelatorio relatorioVendas = new GeradorRelatorio("vendas");
        GeradorRelatorio relatorioEstoque = new GeradorRelatorio("estoque");
        gestor.adicionarRelatorio(relatorioVendas);
        gestor.adicionarRelatorio(relatorioEstoque);
        gestor.administrarNegocio();
        relatorioVendas.extrairRelatorio("vendas");
        relatorioEstoque.extrairRelatorio("estoque");
        verificar("Gestor mantém relatórios", gestor.getRelatorios().size() == 2);

        verificar("Remoção de item recalcula total", pedido.removerItem(pedido.getItens().get(1))
                && Math.abs(pedido.getMontanteTotal() - 99.80) < 0.001);
        verificar("Pedido permanece com composição mínima", pedido.getItens().size() == 1);

        boolean bloqueouInvalido = false;
        try { farinha.atualizarQuantidade(-1); } catch (IllegalArgumentException ex) { bloqueouInvalido = true; }
        verificar("Validação impede estoque negativo", bloqueouInvalido);
        boolean bloqueouPreco = false;
        try { new ItemPedidoVenda(1, -10); } catch (IllegalArgumentException ex) { bloqueouPreco = true; }
        verificar("Validação impede preço negativo", bloqueouPreco);
        boolean bloqueouQtd = false;
        try { new ItemPedidoVenda(0, 10); } catch (IllegalArgumentException ex) { bloqueouQtd = true; }
        verificar("Validação impede quantidade zero", bloqueouQtd);
        boolean bloqueouUsuario = false;
        try { new Gestor("", "x", "Nome"); } catch (IllegalArgumentException ex) { bloqueouUsuario = true; }
        verificar("Validação impede login vazio", bloqueouUsuario);

        System.out.println("==================================================");
        System.out.printf("TESTES CONCLUÍDOS: %d/%d aprovados%n", aprovados, total);
        System.out.println("==================================================");
        if (aprovados != total) throw new IllegalStateException("Existem testes reprovados.");
    }
}
