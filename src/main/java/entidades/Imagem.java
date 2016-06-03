package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by joseildo on 11/05/16.
 */
@Entity
@Table(name = "Imagem")
public class Imagem implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int ID;

    @Column(name = "Imagem", nullable = true, updatable = true)
    private String imagem;

    public Imagem() {
        this.imagem = "/imagens/vazia.png";
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imagem imagem1 = (Imagem) o;

        return imagem.equals(imagem1.imagem);

    }

    @Override
    public int hashCode() {
        return imagem.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Imagem{");
        sb.append(imagem);
        sb.append('}');
        return sb.toString();
    }
}
