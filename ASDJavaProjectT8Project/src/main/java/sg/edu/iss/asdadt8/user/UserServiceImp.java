package sg.edu.iss.asdadt8.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import sg.edu.iss.asdadt8.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service @Transactional
public class UserServiceImp implements UserService, UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public List<User> getUsers() {
		return userRepo.findAll();
	}

	@Override
	public void deleteUser(User user) {
		userRepo.delete(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		//as there is no username in the entity, use email as a substitute
		String email = username;
		User user = userRepo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("user not found in the database");
		}
		 // add authority for user
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles()));
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
	}

}
