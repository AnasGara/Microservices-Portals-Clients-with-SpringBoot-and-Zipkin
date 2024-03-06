package com.owliance.portal.Services;

import com.owliance.portal.Models.Portal;
import com.owliance.portal.Repositories.PortalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortalService {
    private final PortalRepository portalRepository;
    public void savePortal(Portal portal){
        portalRepository.save(portal);
    }
    public List<Portal> findAllPortals(){
        return portalRepository.findAll();
    }

    public List<Portal> findAllPortalsByClientId(Integer id) {
        return portalRepository.findAllByClientId(id);
    }
}
