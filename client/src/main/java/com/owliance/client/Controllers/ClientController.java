package com.owliance.client.Controllers;

import com.owliance.client.Models.Client;
import com.owliance.client.Models.FullClientResponse;
import com.owliance.client.Services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody Client client) {
        System.out.println("ClientController.saveClient" + client.toString());
        clientService.saveClient(client);
    }


    @GetMapping
    public ResponseEntity<List<Client>> findAllStudents(){
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @GetMapping("/with-portals/{clientId}")
    public ResponseEntity<FullClientResponse> findAllClients(

            @PathVariable("clientId") Integer clientId){

        return ResponseEntity.ok(clientService.findClientWithPortals(clientId));
    }



}
