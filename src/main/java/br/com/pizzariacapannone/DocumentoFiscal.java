package br.com.pizzariacapannone;

import java.util.UUID;

/** Documento fiscal associado a um pedido de venda. */
@SuppressWarnings("this-escape")
public class DocumentoFiscal {
    private String chaveDocumento;
    private boolean emitido;

    public DocumentoFiscal() { this(UUID.randomUUID().toString().replace("-", "").substring(0, 32)); }
    public DocumentoFiscal(String chaveDocumento) { setChaveDocumento(chaveDocumento); }
    public boolean emitirDocumentoFiscal() {
        if (chaveDocumento == null || chaveDocumento.isBlank()) return false;
        emitido = true;
        System.out.println("Documento fiscal emitido. Chave: " + chaveDocumento);
        return true;
    }
    public String getChaveDocumento() { return chaveDocumento; }
    public void setChaveDocumento(String chaveDocumento) {
        if (chaveDocumento == null || chaveDocumento.isBlank()) throw new IllegalArgumentException("Chave do documento obrigatória.");
        this.chaveDocumento = chaveDocumento.trim();
    }
    public boolean isEmitido() { return emitido; }
}
