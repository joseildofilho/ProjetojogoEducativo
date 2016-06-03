package dao.daoImpls.FerramentaGravacao;

import java.io.*;

/**
 * @author Joseildo Filho
 * @version 1.1
 * @since 1.0
 *
 * Ferramenta de io em arquivos serializados, seus metodos são um pouco primitivos
 * ate mesmo ineficientes, mas para aplicações simples é pode resolver o caso
 * Possui a ideia de CRUD porem sem o U (update), onde essa tarefa fica a cargo do implmentador
 * Elasempre sobreescrevara o arquivo com mesmo nome
 * @see FerramentaGravacao
 * @see Serializable
 */
public class IOToolFile<T> implements FerramentaGravacao <T>{

    private boolean exists(final String NOME) {
        return new File(NOME).exists();
    }

    @Override
    public void gravarObjeto(T o, final String NOME) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME))) {
            oos.writeObject(o);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T lerObjeto(final String NOME) throws FileNotFoundException {
        if (!exists(NOME)) throw new FileNotFoundException();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removerObjeto(final String NOME) throws FileNotFoundException {
        if(!exists(NOME)) throw new FileNotFoundException();
        new File(NOME).delete();
    }


}
