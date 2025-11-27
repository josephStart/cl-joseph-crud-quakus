package cl.joseph.service.impl;

import cl.joseph.constants.Constants;
import cl.joseph.entity.UserInfoEntity;
import cl.joseph.exceptions.CrudException;
import cl.joseph.model.dto.UserInfoDto;
import cl.joseph.repository.UserInfoRepository;
import cl.joseph.service.UserInfoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Inject
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfoDto> getUsers() {

        List<UserInfoEntity> userList = userInfoRepository.listAll();


        return userList.stream().map(mapper ->
                UserInfoDto.builder()
                        .name(mapper.getName())
                        .lastName(mapper.getLastName())
                        .age(mapper.getAge())
                        .email(mapper.getEmail())
                        .build()
        ).toList();
    }

    @Override
    @Transactional
    public UserInfoDto createUser(UserInfoDto userInfoDto) {


        Optional<UserInfoEntity> emailOp = userInfoRepository.find(Constants.QUERY_PARAM_EMAIL, userInfoDto.getEmail()).firstResultOptional();

        if (emailOp.isPresent()) {
            throw new CrudException("User exist");
        }

        UserInfoEntity userInfoEntity = UserInfoEntity.builder()
                .name(userInfoDto.getName())
                .lastName(userInfoDto.getLastName())
                .email(userInfoDto.getEmail())
                .age(userInfoDto.getAge())
                .build();

        userInfoRepository.persist(userInfoEntity);

        return fillUserInfoDto(userInfoEntity);
    }

    private UserInfoDto fillUserInfoDto(UserInfoEntity userInfoEntity) {
        return UserInfoDto.builder()
                .name(userInfoEntity.getName())
                .lastName(userInfoEntity.getLastName())
                .email(userInfoEntity.getEmail())
                .age(userInfoEntity.getAge())
                .build();
    }

    @Override
    @Transactional
    public UserInfoDto updateUser(UserInfoDto userInfoDto) {
        logger.info("Update user");

        Optional<UserInfoEntity> userOp = userInfoRepository.find(Constants.QUERY_PARAM_EMAIL, userInfoDto.getEmail()).firstResultOptional();

        if (userOp.isEmpty()) {
            throw new CrudException("User doesn't exist");
        }

        UserInfoEntity userInfoEntityUp = userOp.get();
        userInfoEntityUp.setName(userInfoDto.getName());
        userInfoEntityUp.setLastName(userInfoDto.getLastName());
        userInfoEntityUp.setAge(userInfoDto.getAge());

        logger.info("User updated");

        return fillUserInfoDto(userInfoEntityUp);
    }

    @Override
    @Transactional
    public void deleteUser(String userEmail) {

        Optional<UserInfoEntity> userOp = userInfoRepository.find(Constants.QUERY_PARAM_EMAIL, userEmail).firstResultOptional();

        if (userOp.isEmpty()) {
            throw new CrudException("User doesn't exist");
        }

        boolean isDeleted = userInfoRepository.deleteById(userOp.get().getIdUserInfo());
        logger.info("user is deleted: " + isDeleted);

    }
}
