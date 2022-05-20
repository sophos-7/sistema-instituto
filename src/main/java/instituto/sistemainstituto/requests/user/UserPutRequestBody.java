package instituto.sistemainstituto.requests.user;

import lombok.Data;

@Data
public class UserPutRequestBody {
    private Integer id;
    private String email;
    private String name;
    private String phone;
    private String rg;
    private String cpf;
    private String profession;
    private String role;
}
