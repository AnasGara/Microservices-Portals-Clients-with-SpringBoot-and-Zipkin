package com.owliance.portal.Models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "portal")
public class Portal {
    @Id
    @GeneratedValue
    private Integer idPortail;
    private String URL;
    private String comment;
    private Integer clientId;

    public String toString() {
        return "Portal(idPortail=" + this.getIdPortail() + ", URL=" + this.getURL() + ", comment=" + this.getComment() + ", clientId=" + this.getClientId() + ")";
    }
}
