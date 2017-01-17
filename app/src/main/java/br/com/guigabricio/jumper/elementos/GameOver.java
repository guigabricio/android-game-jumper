package br.com.guigabricio.jumper.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import br.com.guigabricio.jumper.engine.Cores;
import br.com.guigabricio.jumper.engine.Tela;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class GameOver {

    private static Tela tela;
    private static final Paint VERMELHO = Cores.getCorDoGameOver();

    public GameOver(Tela tela) {
        this.tela = tela;
    }

    public void desenharNo(Canvas canvas) {
        String gameOver = "Game Over";
        int centroHorizontal = centralizaTexto(gameOver);
        canvas.drawText(gameOver, centroHorizontal, tela.getAltura() / 2, VERMELHO);

    }

    private int centralizaTexto(String texto) {
        Rect limiteDoTexto = new Rect();
        VERMELHO.getTextBounds(texto, 0, texto.length(), limiteDoTexto);
        int centroHorizontal = this.tela.getLargura() / 2 - (limiteDoTexto.right - limiteDoTexto.left) / 2;
        return centroHorizontal;
    }
}
