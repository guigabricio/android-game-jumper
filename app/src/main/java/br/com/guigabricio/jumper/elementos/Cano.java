package br.com.guigabricio.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.guigabricio.jumper.R;
import br.com.guigabricio.jumper.engine.Cores;
import br.com.guigabricio.jumper.engine.Tela;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Cano {

    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;

    private int alturaCanoInferior;
    private int alturaCanoSuperior;

    private Tela tela;
    private int posicao;
    private Bitmap canoInferior;
    private Bitmap canoSuperior;

    private static final Paint VERDE = Cores.getCorDoCano();

    public Cano(Context context, Tela tela, int posicao) {
        this.tela = tela;
        this.posicao = posicao;
        this.alturaCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        this.canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaCanoInferior, false);
        this.canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaCanoSuperior, false);
    }

    public void desenharNo(Canvas canvas) {
        desenhaCanoSuperior(canvas);
        desenhaCanoInferior(canvas);
    }

    private void desenhaCanoInferior(Canvas canvas) {
        canvas.drawBitmap(this.canoInferior,
                this.posicao,
                this.alturaCanoInferior,
                null);
    }

    private void desenhaCanoSuperior(Canvas canvas) {
        canvas.drawBitmap(this.canoSuperior,
                this.posicao,
                0,
                null);
    }

    public void move() {
        this.posicao -= 5;
    }

    public int valorAleatorio() {
        return (int) (Math.random() * 200);
    }

    public boolean cruzouVerticalmenteCom(Passaro passaro) {
        return passaro.getAltura() - Passaro.RAIO <
                this.alturaCanoSuperior || passaro.getAltura() + Passaro.RAIO >
                this.alturaCanoInferior;
    }

    public boolean cruzouHorizontalmenteComPassaro() {
        return this.posicao - Passaro.X < Passaro.RAIO;
    }


    public boolean saiuDaTela() {
        return this.posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao() {
        return this.posicao;
    }
}
