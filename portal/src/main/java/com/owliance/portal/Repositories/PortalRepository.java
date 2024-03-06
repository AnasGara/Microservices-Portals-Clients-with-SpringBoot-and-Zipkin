package com.owliance.portal.Repositories;

import com.owliance.portal.Models.Portal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortalRepository extends JpaRepository<Portal, Integer> {
    List<Portal> findAllByClientId(Integer id);
}