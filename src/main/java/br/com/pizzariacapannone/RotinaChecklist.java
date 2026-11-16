package br.com.pizzariacapannone;

/** Tarefa operacional vinculada a um atendente. */
@SuppressWarnings("this-escape")
public class RotinaChecklist {
    private String descricaoTarefa;
    private boolean statusConclusao;

    public RotinaChecklist(String descricaoTarefa) {
        setDescricaoTarefa(descricaoTarefa);
        this.statusConclusao = false;
    }
    public RotinaChecklist(String descricaoTarefa, boolean statusConclusao) {
        setDescricaoTarefa(descricaoTarefa);
        this.statusConclusao = statusConclusao;
    }
    public void finalizarTarefa() {
        statusConclusao = true;
        System.out.println("Tarefa finalizada: " + descricaoTarefa);
    }
    public String getDescricaoTarefa() { return descricaoTarefa; }
    public void setDescricaoTarefa(String descricaoTarefa) {
        if (descricaoTarefa == null || descricaoTarefa.isBlank()) throw new IllegalArgumentException("Descrição da tarefa obrigatória.");
        this.descricaoTarefa = descricaoTarefa.trim();
    }
    public boolean isStatusConclusao() { return statusConclusao; }
    public boolean getStatusConclusao() { return statusConclusao; }
    public void setStatusConclusao(boolean statusConclusao) { this.statusConclusao = statusConclusao; }
}
