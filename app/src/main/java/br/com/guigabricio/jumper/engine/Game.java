package br.com.guigabricio.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.com.guigabricio.jumper.R;
import br.com.guigabricio.jumper.elementos.Canos;
import br.com.guigabricio.jumper.elementos.GameOver;
import br.com.guigabricio.jumper.elementos.Passaro;
import br.com.guigabricio.jumper.elementos.Pontuacao;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class Game extends SurfaceView implements Runnable, View.OnTouchListener {

    private final SurfaceHolder holder = getHolder();
    private boolean estaRodando = true;
    private Tela tela;

    private Bitmap background;

    private Passaro passaro;
    private Canos canos;
    private Pontuacao pontuacao;
    private Som som;
    private Tempo tempo;

    private Context context;

    private VerificadorColisao verificadorColisao;

    public Game(Context context) {
        super(context);
        this.context = context;
        tela = new Tela(context);
        setOnTouchListener(this);
        inicializaElementos();
    }

    private void inicializaElementos() {
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);
        this.som = new Som(context);
        this.tempo = new Tempo();
        this.passaro = new Passaro(this.context,this.tela, this.som, this.tempo);
        this.pontuacao = new Pontuacao(this.som);
        this.canos = new Canos(this.context, this.tela, pontuacao);
        this.verificadorColisao = new VerificadorColisao(passaro,canos);
    }

    @Override
    public void run() {
        while (this.estaRodando) {
            if (!this.holder.getSurface().isValid())
                continue;
            Canvas canvas = this.holder.lockCanvas();

            this.tempo.passa();

            canvas.drawBitmap(this.background, 0, 0, null);

            this.passaro.desenhaNo(canvas);
            this.passaro.voa();

            this.canos.desenhaNo(canvas);

            this.canos.move();
            this.pontuacao.desenhaNo(canvas);

            if (this.verificadorColisao.temColisao()) {
                this.som.toca(Som.COLISAO);
                new GameOver(this.tela).desenharNo(canvas);
                cancela();
            }

            this.holder.unlockCanvasAndPost(canvas);

        }
    }

    public void cancela() {
        this.estaRodando = false;
    }

    public void inicia() {
        this.estaRodando = true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        this.passaro.pula();
        return false;
    }
}
