package com.app.service;

import java.util.List;

import com.app.dto.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO userDTO);

	UserDTO updateUser(UserDTO userDTO, int userId);

	void deleteUser(int userId);

	UserDTO getUser(int userId);

	List<UserDTO> getAllUsers();

	List<UserDTO> searchUser(String name);

	UserDTO searchUserByEmail(String email);

//	UserDTO updateUserWithImage(int imageId, int userId);
}