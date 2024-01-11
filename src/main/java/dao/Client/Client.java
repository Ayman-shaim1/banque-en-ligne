package dao.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    private String cin;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;




}
