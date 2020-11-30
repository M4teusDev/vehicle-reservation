package com.example.vehiclereservation.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.DTO.UpdateOrSaves.ClientDTO;
import com.example.vehiclereservation.model.Client;

import org.springframework.stereotype.Component;

@Component
public class RepositoryClient {

    private List<Client> clients = new ArrayList<Client>();
    private static int nextCode = 1;

	public Client saveClient(Client client) {

        client.setCode(nextCode++);
        clients.add(client);

        return client;
    }

	public List<Client> getAllClients() {
		return clients;
	}

	public Optional<Client> getClientByCode(int code) {
        
        for(Client aux : clients)
        {
            if(aux.getCode() == code)
            {
                return Optional.of(aux);
            }
        }

        return Optional.empty();
	}

    public Client updateDTO(ClientDTO clientDTO, Client auxClient) 
    {
        auxClient.setName(clientDTO.getName());
        auxClient.setAdress(clientDTO.getAdress());
        auxClient.setCpf(clientDTO.getCpf());

        return auxClient;
	}   
}
