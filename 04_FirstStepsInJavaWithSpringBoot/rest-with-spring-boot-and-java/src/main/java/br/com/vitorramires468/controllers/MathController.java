package br.com.vitorramires468.controllers;

import br.com.vitorramires468.services.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {


    @Autowired
    private MathService service;

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable String numberOne,@PathVariable String numberTwo) throws Exception {
        return service.sum(numberOne,numberTwo);
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable String numberOne,@PathVariable String numberTwo) throws Exception {
        return service.subtraction(numberOne,numberTwo);
    }

    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable String numberOne,@PathVariable String numberTwo) throws Exception {
        return service.multiplication(numberOne,numberTwo);
    }

    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable String numberOne,@PathVariable String numberTwo) throws Exception {
        return service.division(numberOne,numberTwo);
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable String numberOne,@PathVariable String numberTwo) throws Exception {
        return service.mean(numberOne,numberTwo);
    }

    @RequestMapping("/root/{number}")
    public Double squareRoot(@PathVariable String number) throws Exception {
        return service.squareRoot(number);
    }

}
