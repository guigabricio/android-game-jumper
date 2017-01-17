package br.com.guigabricio.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.guigabricio.jumper.R;
import br.com.guigabricio.jumper.engine.Cores;
import br.com.guigabricio.jumper.engine.Som;
import br.com.guigabricio.jumper.engine.Tela;
import br.com.guigabricio.jumper.engine.Tempo;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Passaro {

    public static final int X = 50;
    public static final int RAIO = 100;
    private static Paint VERMELHO = Cores.getCorDoPassaro();
    private final Bitmap passaro;

    private int altura;

    private Tela tela;

    private Som som;

    private Tempo tempo;

    public Passaro(Context context, Tela tela, Som som, Tempo tempo) {
        this.altura = 600;
        this.tela = tela;
        this.tempo = tempo;
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO * 2, RAIO * 2, false);
        this.som = som;
    }

    public void desenhaNo(Canvas canvas) {
        canvas.drawBitmap(this.passaro, X - RAIO,
                this.altura - RAIO, null);
    }

    public static final int DESLOCAMENTO_DO_PULO = 5;

    public void voa() {
        double tempo = this.tempo.atual();
        double novaAltura =- DESLOCAMENTO_DO_PULO + ((2 * (tempo * tempo)) / 1.0);
        boolean chegouNoChao = this.altura + RAIO > tela.getAltura();
        if (!chegouNoChao) {
            this.altura += novaAltura;
        }
    }


    public void pula() {
        if (this.altura > RAIO) {
            this.som.toca(Som.PULO);
            this.tempo.renicia();
        }
    }

    public int getAltura() {
        return this.altura;
    }
}
