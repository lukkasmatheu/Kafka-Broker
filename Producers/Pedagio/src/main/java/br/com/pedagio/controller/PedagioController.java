package br.com.pedagio.controller;

import br.com.pedagio.model.NotaFiscal;
import br.com.pedagio.producer.CobrancaProducer;
import br.com.pedagio.model.Cobranca;
import br.com.pedagio.producer.NotaFiscalProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/pedagio")
public class PedagioController {

    @Autowired
    private CobrancaProducer cobrancaProducer;
    @Autowired
    private NotaFiscalProducer notaFiscalProducer;


    @PostMapping("/cobranca")
    public ResponseEntity<String> cobrancaPedagio(@RequestBody Cobranca cobranca){
        String carPlate = cobrancaProducer.enviar(cobranca);
        return new ResponseEntity<>(carPlate, HttpStatus.CREATED);
    }

    @PostMapping("/nota-fiscal")
    public void gerarNotaFiscal(@RequestBody NotaFiscal notaFiscal){
        notaFiscalProducer.enviar(notaFiscal);
    }
}