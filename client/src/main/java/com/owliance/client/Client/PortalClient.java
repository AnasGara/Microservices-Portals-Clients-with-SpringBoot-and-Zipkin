package com.owliance.client.Client;

import com.owliance.client.Models.Portal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "client-service", url = "${application.config.client-url}")
public interface PortalClient {

    @GetMapping("/client/{id}")
    List<Portal> findAllPortalsByClient(@PathVariable("id") Integer id);

}
