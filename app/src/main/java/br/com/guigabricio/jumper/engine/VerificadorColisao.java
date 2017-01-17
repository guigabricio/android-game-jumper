package br.com.guigabricio.jumper.engine;

import br.com.guigabricio.jumper.elementos.Canos;
import br.com.guigabricio.jumper.elementos.Passaro;

/**
 * Created by Gabricio on 02/02/2016.
 */
public class VerificadorColisao {

    private final Passaro passaro;
    private final Canos canos;

    public VerificadorColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao() {
        return canos.temColisaoCom(passaro);
    }
}
