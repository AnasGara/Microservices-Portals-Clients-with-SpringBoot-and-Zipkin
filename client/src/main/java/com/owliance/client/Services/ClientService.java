package com.owliance.client.Services;

import com.owliance.client.Client.PortalClient;
import com.owliance.client.Models.Client;
import com.owliance.client.Models.FullClientResponse;
import com.owliance.client.Models.Portal;
import com.owliance.client.Repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    private final PortalClient portalClient;

    public void saveClient(Client client){
        System.out.println("ClientService.saveClient"+client.toString());
        clientRepository.save(client);
    }
    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    public FullClientResponse findClientWithPortals(Integer clientId) {

        var client = clientRepository
                .findById(clientId)
                .orElse(
                        Client.builder()
                                .clientName("Not_Found")
                                .build()
                );

        List<Portal> portals = portalClient.findAllPortalsByClient(clientId);

    return FullClientResponse.builder()
            .id(client.getId())
            .clientName(client.getClientName())
            .emailContact(client.getEmailContact())
            .mobileAppLink(client.getMobileAppLink())
            .portals(portals)
            .build();
    }
}
