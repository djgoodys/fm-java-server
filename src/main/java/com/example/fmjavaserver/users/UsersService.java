package com.example.fmjavaserver.users;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Objects;


@Service
public class UsersService {
	private final UsersRepository usersRepository;

	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	public String login(String username, String password) {
		Optional<Users> userOptional = usersRepository.findByusername(username);
		if (userOptional.isPresent()) {
			Users user = userOptional.get();
			// Handle login logic here, e.g., check password
			if (user.getPassword().equals(password)) {
				return "passed";
			} else {
				return "failed";
			}
		} else {
			throw new IllegalStateException("User not found");
		}
	}
	

	public List<Users> manageUsers(Long Id, String action, String username, String password, String email, String admin) {
		System.out.println("action=" + action + " username="+username);
		
		if ("get-all-users".equals(action)) {
			return usersRepository.findAll();
		} else if ("update-user".equals(action)) {
			if (Id != null) {
				Users user = usersRepository.findById(Id)
						.orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + Id));
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setAdmin(admin);

				usersRepository.save(user);
			}
		} else if ("add-new-user".equals(action)) {
			if (username != null) {
				Optional<Users> userByusername = usersRepository.findByusername(username);
				if (userByusername.isPresent()) {
					throw new IllegalStateException("User name: " + username + " is already in use");
				} else {
					Users newUser = new Users(
							password,
							username,
							admin,
							(email != null && !"undefined".equals(email)) ? email : "");
					usersRepository.save(newUser);

				}
			}
		} else if ("delete-user".equals(action)) {
			System.out.println("deleteing with id=" + Id);
			if (Id != null) {
				usersRepository.deleteById(Id);
			} else {
				throw new IllegalArgumentException("Invalid user ID: " + Id);
			}
		}
		return usersRepository.findAll();
	}

	public void deleteUsers(Long Id) {

		boolean exists = usersRepository.existsById(Id);
		if (!exists) {
			throw new IllegalStateException("users with id " + Id + " does not exists");
		}
		usersRepository.deleteById(Id);
	}

	@Transactional
	public void updateUsers(Long Id, String username, String email, String password, String admin) {
		Users users = usersRepository.findById(Id)
				.orElseThrow(() -> new IllegalStateException("Users with id " + Id + " does not exist"));

		if (username != null && username.length() > 0 && !Objects.equals(users.getUsername(), username)) {
			users.setUsername(username);
		}

		if (password != null && email.length() > 0) {
			Optional<Users> usersOptional = usersRepository.findById(Id);
			users.setPassword(password);
		}

		if (email != null && email.length() > 0) {
			Optional<Users> usersOptional = usersRepository.findById(Id);
			users.setEmail(email);
		}

		if (admin != null && admin.length() > 0) {
			Optional<Users> usersOptional = usersRepository.findById(Id);
			users.setAdmin(admin);
		}
	}
}
