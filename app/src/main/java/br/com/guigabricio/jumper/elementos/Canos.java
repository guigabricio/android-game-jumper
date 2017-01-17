package br.com.guigabricio.jumper.elementos;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import br.com.guigabricio.jumper.engine.Tela;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Canos {

    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int DISTANCIA_ENTRE_CANOS = 200;
    private final List<Cano> canos = new ArrayList<Cano>();
    private Tela tela;
    private Pontuacao pontuacao;
    private Context context;

    public Canos(Context context, Tela tela, Pontuacao pontuacao) {
        int posicao = 200;
        for (int i = 0; i < QUANTIDADE_DE_CANOS; i++) {
            posicao += DISTANCIA_ENTRE_CANOS;
            this.canos.add(new Cano(context, tela, posicao));
        }
        this.tela = tela;
        this.pontuacao = pontuacao;
        this.context = context;
    }

    public void desenhaNo(Canvas canvas) {
        for (Cano cano : this.canos)
            cano.desenharNo(canvas);
    }

    public void move() {
        ListIterator<Cano> iterator = this.canos.listIterator();
        while(iterator.hasNext()) {
            Cano cano = (Cano) iterator.next();
            cano.move();
            if(cano.saiuDaTela()) {
                this.pontuacao.aumenta();
                iterator.remove();
                Cano outroCano = new Cano(this.context,this.tela, maiorPosicao() + DISTANCIA_ENTRE_CANOS);
                iterator.add(outroCano);
            }
        }
    }

    private int maiorPosicao() {
        int maximo = 0;
        for (Cano cano : canos) {
            maximo = Math.max(cano.getPosicao(), maximo);
        }
        return maximo;
    }
    public boolean temColisaoCom(Passaro passaro) {
        for (Cano cano : this.canos) {
            if (cano.cruzouHorizontalmenteComPassaro() && cano.cruzouVerticalmenteCom(passaro)) {
                return true;
            }
        }
        return false;
    }
}
