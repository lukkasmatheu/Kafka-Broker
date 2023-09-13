package br.com.estacionamento.controller;

import br.com.estacionamento.model.NotaFiscal;
import br.com.estacionamento.producer.CobrancaProducer;
import br.com.estacionamento.model.Cobranca;
import br.com.estacionamento.producer.NotaFiscalProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Slf4j
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    private CobrancaProducer cobrancaProducer;
    @Autowired
    private NotaFiscalProducer notaFiscalProducer;


    @PostMapping("/cobranca")
    public ResponseEntity<String> cobrancaEstacionamento(@RequestBody Cobranca cobranca){
        String carPlate = cobrancaProducer.enviar(cobranca);
        log.info("Producer-> send message {}",cobranca);
        return new ResponseEntity<>(carPlate, HttpStatus.CREATED);
    }

    @PostMapping("/nota-fiscal")
    public void gerarNotaFiscal(@RequestBody NotaFiscal notaFiscal){
        notaFiscalProducer.enviar(notaFiscal);
    }
}