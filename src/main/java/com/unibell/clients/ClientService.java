package com.unibell.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Сохранение нового клиента
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    // Добавление нового контакта к клиенту
    public ContactInfo addContactToClient(Long clientId, ContactInfo contactInfo) {
        Client client = getClientById(clientId);
        if (client != null) {
            client.getContactInfos().add(contactInfo);
            clientRepository.save(client); // Сохраняем изменения в клиенте
            return contactInfo;
        }
        return null; // Или выбросить исключение
    }

    // Получение всех клиентов
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Получение клиента по ID
    public Client getClientById(Long clientId) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        return optionalClient.orElse(null); // Или выбросить исключение
    }

    // Получение списка контактов по ID клиента
    public List<ContactInfo> getContactsByClientId(Long clientId) {
        Client client = getClientById(clientId);
        return (client != null) ? client.getContactInfos() : null; // Или выбросить исключение
    }

    // Получение списка контактов заданного типа по ID клиента
    public List<ContactInfo> getContactsByType(Long clientId, String type) {
        List<ContactInfo> contacts = getContactsByClientId(clientId);
        if (contacts != null) {
            return contacts.stream()
                    .filter(contact -> contact.getType().equalsIgnoreCase(type))
                    .toList(); // Фильтруем по типу (phone или email)
        }
        return null; // Или выбросить исключение
    }
}
