package modelo;

public class Missao {

    private int id;
    private String titulo;
    private String descricao;
    private String dificuldade;
    private String recompensa;
    private boolean concluida;

    public Missao() {}

    public Missao(int id, String titulo, String descricao, String dificuldade, String recompensa, boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.recompensa = recompensa;
        this.concluida = concluida;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }

    public String getRecompensa() { return recompensa; }
    public void setRecompensa(String recompensa) { this.recompensa = recompensa; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}
