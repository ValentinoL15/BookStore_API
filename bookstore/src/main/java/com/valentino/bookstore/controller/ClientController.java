package com.valentino.bookstore.controller;

import com.valentino.bookstore.dto.ClientDTOs.ClientDTO;
import com.valentino.bookstore.model.Client;
import com.valentino.bookstore.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ClientController {

    @Autowired
    private IClientService clientServ;

    @PostMapping("/create-client")
    public ResponseEntity<String> createClient(@RequestBody Client client){

        try {

            clientServ.createClient(client);
            return ResponseEntity.ok("El cliente fue creado correctamente");

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/client/{id_client}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable UUID id_client) {

        try {

            ClientDTO clientDto = clientServ.getClient(id_client);

            return ResponseEntity.ok(clientDto);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping("/buy-book/{id_book}/{id_client}")
    public ResponseEntity<String> buyBook(@PathVariable Long id_book,
                                          @PathVariable UUID id_client){

        try {

            clientServ.buyBook(id_book,id_client);
            return ResponseEntity.ok("Libro agregado con éxito");

        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }


}
