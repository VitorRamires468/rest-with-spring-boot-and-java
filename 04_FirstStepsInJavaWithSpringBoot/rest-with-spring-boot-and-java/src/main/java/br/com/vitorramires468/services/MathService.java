package br.com.vitorramires468.services;

import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double sum(String numberOne, String numberTwo){
        verifyException(numberOne,numberTwo);
        return convertToDouble(numberOne)+convertToDouble(numberTwo);
    }

    public Double subtraction(String numberOne, String numberTwo){
        verifyException(numberOne,numberTwo);verifyException(numberOne,numberTwo);
        return convertToDouble(numberOne)-convertToDouble(numberTwo);
    }

    public Double multiplication(String numberOne, String numberTwo){
        verifyException(numberOne,numberTwo);
        return convertToDouble(numberOne)*convertToDouble(numberTwo);
    }

    public Double division(String numberOne, String numberTwo){
        verifyException(numberOne,numberTwo);
        if(convertToDouble(numberTwo)==0){
            throw new ArithmeticException("Can not divide by zero!");
        }
        return convertToDouble(numberOne)/convertToDouble(numberTwo);
    }

    public Double mean(String numberOne, String numberTwo){
        verifyException(numberOne,numberTwo);
        return (convertToDouble(numberOne)+convertToDouble(numberTwo))/2D;
    }

    public Double squareRoot(String number){
        verifyException(number,number);
        if(convertToDouble(number)<0){
            throw new ArithmeticException("The number can not be negative");
        }
        return Math.sqrt(convertToDouble(number)) ;
    }

    private void verifyException(String numberOne, String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo) ){
            throw new UnsupportedOperationException("Please set a numeric value");
        }
    }
    private Double convertToDouble(String strNumber) {
        if(strNumber==null || strNumber.isEmpty()){
            throw new UnsupportedOperationException("Please set a numeric value");
        }
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber ==null || strNumber.isEmpty()){
            return false;
        }
        String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
