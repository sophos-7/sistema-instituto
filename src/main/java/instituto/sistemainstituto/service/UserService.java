package instituto.sistemainstituto.service;

import instituto.sistemainstituto.entity.UserEntity;
import instituto.sistemainstituto.requests.user.UserPostRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import instituto.sistemainstituto.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private UserRepository userRepository;

    public List<UserEntity> listAll(){
        return userRepository.findAll();
    }

    public UserEntity findbyidOrThrowBadRequestException(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

    }

    public UserEntity save(UserPostRequestBody userPostRequestBody) {
        //produto.setId(ThreadLocalRandom.current().nextInt(3,1000000)); //se eu quiser colocar um id aleatório

        return userRepository.save(UserEntity.builder()
                .id(userPostRequestBody.getId())
                .cpf(userPostRequestBody.getCpf())
                .email(userPostRequestBody.getEmail())
                .name(userPostRequestBody.getName())
                .phone(userPostRequestBody.getPhone())
                .profession(userPostRequestBody.getProfession())
                .rg(userPostRequestBody.getRg())
                .role(userPostRequestBody.getRole())
                .build());

    }

    public void delete(int id) {
        userRepository.delete(findbyidOrThrowBadRequestException(id));
    }


}
