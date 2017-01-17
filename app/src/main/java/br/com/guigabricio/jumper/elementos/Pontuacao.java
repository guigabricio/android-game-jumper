package br.com.guigabricio.jumper.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.guigabricio.jumper.engine.Cores;
import br.com.guigabricio.jumper.engine.Som;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Pontuacao {

    private static final Paint BRANCO = Cores.getCorDaPontuacao();
    private int pontos = 0;
    private Som som;

    public Pontuacao(Som som){
        this.som = som;
    }

    public void aumenta() {
        this.som.toca(Som.PONTUACAO);
        this.pontos++;
    }

    public void desenhaNo(Canvas canvas) {
        canvas.drawText( String.valueOf(pontos),10 ,100 ,BRANCO);
    }
}
