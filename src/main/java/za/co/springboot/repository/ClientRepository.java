package za.co.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.springboot.model.ClientDTO;

public interface ClientRepository extends JpaRepository<ClientDTO, Integer>{


}
