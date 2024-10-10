package com.unibell.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // 1. Добавление нового клиента
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.saveClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    // 2. Добавление нового контакта клиента
    @PostMapping("/{clientId}/contacts")
    public ResponseEntity<ContactInfo> addContactToClient(@PathVariable Long clientId, @RequestBody ContactInfo contactInfo) {
        ContactInfo savedContact = clientService.addContactToClient(clientId, contactInfo);
        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
    }

    // 3. Получение списка клиентов
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // 4. Получение информации по заданному клиенту (по id)
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(@PathVariable Long clientId) {
        Client client = clientService.getClientById(clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    // 5. Получение списка контактов заданного клиента
    @GetMapping("/{clientId}/contacts")
    public ResponseEntity<List<ContactInfo>> getContactsByClientId(@PathVariable Long clientId) {
        List<ContactInfo> contacts = clientService.getContactsByClientId(clientId);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // 6. Получение списка контактов заданного типа заданного клиента
    @GetMapping("/{clientId}/contacts/{type}")
    public ResponseEntity<List<ContactInfo>> getContactsByType(@PathVariable Long clientId, @PathVariable String type) {
        List<ContactInfo> contacts = clientService.getContactsByType(clientId, type);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
