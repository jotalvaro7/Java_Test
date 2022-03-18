package com.personales.javatest.player;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class PlayerTest {

    @Test
    public void lose_when_dice_number_is_too_low() {

        /**
         * Invocando la instancia del dado al generar las pruebas no es muy eficiente ya que por ser aleatorio puede
         * fallar y otras veces ok
         */
        //Dice dice = new Dice(6);

        /**
         * Simularemos el la clase del dado con mockito para controlar el efecto anterior y retornar el valor que queramos
         */
        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(2);

        Player player = new Player(dice, 3);
        assertFalse(player.play());

    }

    @Test
    public void lose_when_dice_number_is_big() {

        Dice dice = Mockito.mock(Dice.class);
        Mockito.when(dice.roll()).thenReturn(4);

        Player player = new Player(dice, 3);
        assertTrue(player.play());

    }
}