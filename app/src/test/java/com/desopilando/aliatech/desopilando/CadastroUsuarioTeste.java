package com.desopilando.aliatech.desopilando;

import android.media.MediaCodec;
import android.util.StringBuilderPrinter;
import android.widget.EditText;

import com.desopilando.aliatech.desopilando.activity.CadastroUsuarioActivity;
import com.desopilando.aliatech.desopilando.activity.model.Usuario;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import junit.framework.*;
import junit.framework.Assert;

import org.hamcrest.*;
import org.junit.*;
import org.junit.Test;

import java.util.regex.*;

/**
 * Created by francisco on 29/09/17.
 */

public class CadastroUsuarioTeste {
    Usuario usuario;
    String repeteSenha;



    // Inicializar variaveis e atributos para testes
    @Before
    public void setUp() throws Exception{
        usuario = new Usuario();
        usuario.setSenha("pdsi233");
        repeteSenha = "pdsi233";
        usuario.setEmail("francisco@ufpi.com.br");
        usuario.setNome("Francisco Antonio");

    }



    // Testar se o cadastro esta sendo efetuado com sucesso
    @Test
    public void testCadastrarUsuario(){
        Assert.assertEquals("Francisco Antonio", usuario.getNome());
        Assert.assertEquals(true, validaremail(usuario.getEmail()));
        Assert.assertEquals(true, validarsenha(usuario.getSenha(), repeteSenha));
    }



    // Verifica se a senha está de acordo com as especificações definidas
    public boolean validarsenha(String senha, String repeteSenha){
        if (senha == repeteSenha && senha.length() >=6 ){
            return true;
        }
        else{
            return false;
        }
    }

    // Verifica se a senha está de acordo com as especificações definidas
    public static boolean validaremail(String email)
    {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            java.util.regex.Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

}
