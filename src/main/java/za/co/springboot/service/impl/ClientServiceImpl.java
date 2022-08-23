package za.co.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import za.co.springboot.exception.ResourceNotFoundException;
import za.co.springboot.model.ClientDTO;
import za.co.springboot.repository.ClientRepository;
import za.co.springboot.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	private ClientRepository clientRepository;
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@Override
	public ClientDTO saveClient(ClientDTO client) {
		return clientRepository.save(client);
	}

	@Override
	public List<ClientDTO> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public ClientDTO getClient(String searchParameter) {
		Optional<ClientDTO> client2 = null;
//		if(client.isPresent()) {
//			return client.get();
//		}else {
//			throw new ResourceNotFoundException("Client", "Id", id);
//		}
		
		List<ClientDTO> clients = clientRepository.findAll();
		
		for(ClientDTO client: clients) {
			
			if(client.getIdNumber().equalsIgnoreCase(searchParameter)){
				return client;
			}else if(client.getMobileNumber().equalsIgnoreCase(searchParameter)) {
				client2 = clientRepository.findById(client.getClientId());
				return client2.get();
			}else if(client.getFirstName().equalsIgnoreCase(searchParameter)) {
				client2 = clientRepository.findById(client.getClientId());
				return client2.get();
			}
		}
		
		throw new ResourceNotFoundException("Client", "Id", searchParameter);
		
	}

	@Override
	public ClientDTO updateClient(ClientDTO client, String id) {
		
		// we need to check whether Client with given id is exist in DB or not

		ClientDTO existingClient = getClient(id); 
		
			
			if(existingClient!= null){
		
				existingClient.setIdNumber(client.getIdNumber());
				existingClient.setFirstName(client.getFirstName());;
				existingClient.setLastName(client.getLastName());
				existingClient.setMobileNumber(client.getMobileNumber());
				existingClient.setPhysicalAddress(client.getPhysicalAddress());
				// save existing Client to DB
				clientRepository.save(existingClient);
			}else {
				throw new ResourceNotFoundException("Client", "Id", id);
		    }
		return existingClient;
	}


	@Override
	public void deleteClient(String id) {
		// we need to check whether Client with given id is exist in DB or not

		ClientDTO existingClient = getClient(id); 
		
			
			if(existingClient!= null){
				clientRepository.deleteById(existingClient.getClientId());
			}else {
				throw new ResourceNotFoundException("Client", "Id", id);
		    }
		
	}
	
}
