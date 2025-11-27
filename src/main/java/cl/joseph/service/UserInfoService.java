package cl.joseph.service;

import cl.joseph.model.dto.UserInfoDto;

import java.util.List;

public interface UserInfoService {
    public List<UserInfoDto> getUsers();

    public UserInfoDto createUser(UserInfoDto userInfoDto);

    public UserInfoDto updateUser(UserInfoDto userInfoDto);

    public void deleteUser(String userEmail);
}
