package za.co.springboot.service;

import java.util.List;

import za.co.springboot.model.ClientDTO;

public interface ClientService {
	ClientDTO saveClient(ClientDTO client);
	List<ClientDTO> getAllClients();
	ClientDTO getClient(String searchParameter);
	ClientDTO updateClient(ClientDTO client, String idNumber);
	void deleteClient(String id);
}
