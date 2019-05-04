package br.com.neto.reserva.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

// import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 3004007038396892580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_filial", nullable=false, foreignKey=@ForeignKey(name="fk_filial"))
    private Filial filial;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_sala", nullable=false, foreignKey=@ForeignKey(name="fk_sala"))
    private Sala sala;

    @NotNull
    @FutureOrPresent
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime inicio;

    @NotNull
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fim;

    @Column(name="responsavel", columnDefinition="VARCHAR(300)")
    private String responsavel;

    private Boolean cafe;

    @Column(name="quantidade_pessoa")
    private int quantidadePessoa;

    private String descricao;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filial getFilial() {
        return this.filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Sala getSala() {
        return this.sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public LocalDateTime getInicio() {
        return this.inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return this.fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Boolean getCafe() {
        return this.cafe;
    }

    public void setCafe(Boolean cafe) {
        this.cafe = cafe;
    }

    public int getQuantidadePessoa() {
        return this.quantidadePessoa;
    }

    public void setQuantidadePessoa(int quantidadePessoa) {
        this.quantidadePessoa = quantidadePessoa;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Reserva other = (Reserva) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Reserva [id=").append(this.id).append(", filial=").append(this.filial).append(", sala=").append(this.sala).append(", inicio=").append(this.inicio).append(", fim=").append(this.fim)
                .append(", responsavel=").append(this.responsavel).append(", cafe=").append(this.cafe).append(", quantidadePessoa=").append(this.quantidadePessoa).append(", descricao=")
                .append(this.descricao).append("]");
        return builder.toString();
    }

}
