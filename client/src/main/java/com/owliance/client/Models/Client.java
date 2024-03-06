package com.owliance.client.Models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    Integer id;
    String clientName;
    String emailContact;
    String mobileAppLink;
    @Lob
    String logo;
    String extraInformation;

    public String toString() {
        return "Client(id=" + this.getId() + ", clientName=" + this.getClientName() + ", emailContact=" + this.getEmailContact() + ", mobileAppLink=" + this.getMobileAppLink() + ", logo=" + this.getLogo() + ", extraInformation=" + this.getExtraInformation() + ")";
    }


}
