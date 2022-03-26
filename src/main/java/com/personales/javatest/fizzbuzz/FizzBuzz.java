package com.personales.javatest.fizzbuzz;

public class FizzBuzz {


    public String fizzBuzz(int n) {
        String result = "";

        if(n % 3 == 0){
            result = "Fizz";
        }

        if(n % 5 == 0){
            result = "Buzz";
        }

        if(n % 3 == 0 && n%5 ==0){
            result = "FizzBuzz";
        }

        return result.isEmpty() ? String.valueOf(n):result;

    }


}
