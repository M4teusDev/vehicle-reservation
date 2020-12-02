package com.example.vehiclereservation.service;

import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.DTO.UpdateOrSaves.ClientDTO;
import com.example.vehiclereservation.model.Client;
import com.example.vehiclereservation.repository.RepositoryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceClient {

    @Autowired
	private RepositoryClient repositoryClient;
	
	@Autowired
	private ServiceReservation serviceReservation;

	public Client saveClient(ClientDTO clientDTO) {
        return repositoryClient.saveClient(DTOToClient(clientDTO)); //Antes de enviar para o repositorio transforma clienteDTO em cliente
	}

	public Client DTOToClient(ClientDTO clientDTO){
		Client client = new Client();

		client.setAdress(clientDTO.getAdress());
		client.setCpf(clientDTO.getCpf());
		client.setName(clientDTO.getName());

		return client;
	}

	public List<Client> getAllClients() {
		return repositoryClient.getAllClients();
	}

	public Client getClientByCode(int code) {
        Optional<Client> opClient = repositoryClient.getClientByCode(code);
        return opClient.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado!"));
	}

	public Client updateDTO(int code, ClientDTO clientDTO) {
		return repositoryClient.updateDTO(clientDTO, getClientByCode(code));
	}

	public void deleteClient(int code) {
		Client client = getClientByCode(code);
		serviceReservation.verifyPossibilityToDelete(client);
		repositoryClient.deleteClient(client);
	}
}
