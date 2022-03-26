package com.personales.javatest.fizzbuzz;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class FizzBuzzTest {

    private FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void print_fizz_if_number_is_3() {
        assertThat(fizzBuzz.fizzBuzz(3), is("Fizz"));

    }

    @Test
    public void print_fizz_if_number_is_divisible_by_3() {
        assertThat(fizzBuzz.fizzBuzz(9), is("Fizz"));
    }

    @Test
    public void print_buzz_if_number_is_5(){
        assertThat(fizzBuzz.fizzBuzz(5), is("Buzz"));

    }

    @Test
    public void print_buzz_if_number_is_divisible_by_5(){
        assertThat(fizzBuzz.fizzBuzz(10), is("Buzz"));

    }

    @Test
    public void should_print_fizzbuzz_if_they_receive_a_multiple_of_3_and_5() {
        assertThat(fizzBuzz.fizzBuzz(15), is("FizzBuzz"));
    }


    @Test
    public void print_number_if_number_not_divible_by_3_and_5(){
        assertThat(fizzBuzz.fizzBuzz(7), is("7"));

    }
}