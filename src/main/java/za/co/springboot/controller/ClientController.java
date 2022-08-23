package za.co.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.springboot.model.ClientDTO;
import za.co.springboot.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	private ClientService clientService;

	public ClientController(ClientService clientService1) {
		super();
		this.clientService = clientService1;
	}
	
	// build create client REST API
	@PostMapping()
	public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO client){
		return new ResponseEntity<ClientDTO>(clientService.saveClient(client), HttpStatus.CREATED);
	}
	
	// build get all clients REST API
	@GetMapping
	public List<ClientDTO> getAllClients(){
		return clientService.getAllClients();
	}
	
	// build get client by id REST API
	// http://localhost:8080/api/clients/1
	@GetMapping("{id_number}")
	public ResponseEntity<ClientDTO> getClient(@PathVariable("id_number") String clientId){
		return new ResponseEntity<ClientDTO>(clientService.getClient(clientId), HttpStatus.OK);
	}
	
	// build update client REST API
	// http://localhost:8080/api/clients/1
	@PutMapping("{id_number}")
	public ResponseEntity<ClientDTO> updateClient(@PathVariable("id_number") String id
												  ,@RequestBody ClientDTO client){
		return new ResponseEntity<ClientDTO>(clientService.updateClient(client, id), HttpStatus.OK);
	}
	
	// build delete client REST API
	// http://localhost:8080/api/clients/1
	@DeleteMapping("{id_number}")
	public ResponseEntity<String> deleteClient(@PathVariable("id_number") String id){
		
		// delete client from DB
		clientService.deleteClient(id);
		
		return new ResponseEntity<String>("Client deleted successfully!.", HttpStatus.OK);
	}
	
}
