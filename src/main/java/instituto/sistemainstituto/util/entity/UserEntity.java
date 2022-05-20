package instituto.sistemainstituto.util.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    private Integer id;

    private String email;
    private String name;
    private String phone;
    private String rg;
    private String cpf;
    private String profession;
    private String role;

}
