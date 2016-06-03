package entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by joseildo on 11/05/16.
 */
@Entity
@Table
public class Palavra implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int ID;

    @Column
    private String palavra;
    @Column(name = "imagens", table = "Imagem")
    private List<Imagem> imagemList;

    public Palavra() {
        imagemList = new ArrayList<>();
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }


    public List<Imagem> getImagemList() {
        return imagemList;
    }

    public void setImagemList(List<Imagem> imagemList) {
        this.imagemList = imagemList;
    }

    public void relacionarImagem(Imagem imagem) {
        if(!this.imagemList.contains(imagem)) this.imagemList.add(imagem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Palavra)) return false;

        Palavra palavra1 = (Palavra) o;

        if (!getPalavra().equals(palavra1.getPalavra())) return false;
        return getImagemList().equals(palavra1.getImagemList());

    }

    @Override
    public int hashCode() {
        int result = getPalavra().hashCode();
        result = 31 * result + getImagemList().hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Palavra{");
        sb.append("palavra='").append(palavra).append('\'');
        sb.append(", imagemList=").append(imagemList);
        sb.append('}');
        return sb.toString();
    }
}
