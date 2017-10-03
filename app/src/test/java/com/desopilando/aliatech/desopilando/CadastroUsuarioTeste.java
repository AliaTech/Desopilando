package com.desopilando.aliatech.desopilando;

import com.desopilando.aliatech.desopilando.activity.model.Usuario;

import junit.framework.*;
import junit.framework.Assert;

import org.junit.*;
import org.junit.Test;

/**
 * Created by francisco on 29/09/17.
 */

public class CadastroUsuarioTeste {
    Usuario usuario;

    @Before
    public void setUp() throws Exception{
        usuario = new Usuario();
        usuario.setEmail("francisco@ufpi.com.br");
        usuario.setId("123");
        usuario.setNome("Francisco Antonio");
        usuario.setSenha("pdsi2");
    }

    @Test
    public void testCadastrarUsuario(){
        Assert.assertEquals("Francisco Antonio", usuario.getNome());
        Assert.assertEquals("123", usuario.getId());
        Assert.assertEquals("francisco@ufpi.com.br", usuario.getEmail());
        Assert.assertEquals("pdsi2", usuario.getSenha());
    }


}
