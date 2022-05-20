package instituto.sistemainstituto.controller;

import instituto.sistemainstituto.repository.UserRepository;
import instituto.sistemainstituto.requests.user.UserPostRequestBody;
import instituto.sistemainstituto.requests.user.UserPutRequestBody;
import instituto.sistemainstituto.service.UserService;
import instituto.sistemainstituto.util.DateUtil;
import instituto.sistemainstituto.util.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController // faz com que o retorno dos dados sejam apenas strings
@RequestMapping("users") // rota no nível da classe
@Log4j2
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final DateUtil dateUtil;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> list() {
        log.info(dateUtil.formatLocalTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserEntity> findById(@PathVariable int id) {
        return new ResponseEntity<>(userService.findbyidOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> save(@RequestBody UserPostRequestBody userPostRequestBody) {
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") int id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserEntity> Put(@PathVariable(value = "id") int id, @RequestBody UserPutRequestBody userPutRequestBody) {
        Optional<UserEntity> antigoProduto = userRepository.findById(id);
        if (antigoProduto.isPresent()) {
            UserEntity user = antigoProduto.get();
            user.setCpf(userPutRequestBody.getCpf());
            user.setEmail(userPutRequestBody.getEmail());
            user.setName(userPutRequestBody.getName());
            user.setPhone(userPutRequestBody.getPhone());
            user.setProfession(userPutRequestBody.getProfession());
            user.setRg(userPutRequestBody.getRg());
            user.setRole(userPutRequestBody.getRole());
            return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
