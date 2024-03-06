package com.owliance.client.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullClientResponse {

    @Id
    @GeneratedValue
    Integer id;
    String clientName;
    String emailContact;
    String mobileAppLink;
    List<Portal> portals;
}
