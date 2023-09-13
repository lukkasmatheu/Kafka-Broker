package br.com.drive.thru.controller;

import br.com.drive.thru.producer.CobrancaProducer;
import br.com.drive.thru.model.NotaFiscal;
import br.com.drive.thru.model.Cobranca;
import br.com.drive.thru.producer.NotaFiscalProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/drivethru")
public class DriveThruController {

    @Autowired
    private CobrancaProducer cobrancaProducer;
    @Autowired
    private NotaFiscalProducer notaFiscalProducer;


    @PostMapping("/cobranca")
    public ResponseEntity<String> cobrancaDriveThru(@RequestBody Cobranca cobranca){
        String carPlate = cobrancaProducer.enviar(cobranca);
        return new ResponseEntity<>(carPlate, HttpStatus.CREATED);
    }

    @PostMapping("/nota-fiscal")
    public void gerarNotaFiscal(@RequestBody NotaFiscal notaFiscal){
        notaFiscalProducer.enviar(notaFiscal);
    }
}