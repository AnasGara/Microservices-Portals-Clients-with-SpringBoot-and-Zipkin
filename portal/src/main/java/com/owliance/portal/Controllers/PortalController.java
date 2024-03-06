package com.owliance.portal.Controllers;

import com.owliance.portal.Services.PortalService;
import com.owliance.portal.Models.Portal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/portals")
public class PortalController {

    private final PortalService portalService;

    public PortalController(PortalService portalService) {
        this.portalService = portalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePortal(@RequestBody Portal portal) {
        System.out.println(portal);
        portalService.savePortal(portal);
    }

    @GetMapping
    public ResponseEntity<List<Portal>> findAllStudents() {
        return ResponseEntity.ok(portalService.findAllPortals());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Portal>> findAllPortals(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(portalService.findAllPortalsByClientId(id));
    }


}
