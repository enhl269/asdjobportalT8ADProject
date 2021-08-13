package sg.edu.iss.asdadt8.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sg.edu.iss.asdadt8.domain.Applicant;
import sg.edu.iss.asdadt8.domain.Role;
import sg.edu.iss.asdadt8.domain.User;
import sg.edu.iss.asdadt8.repositories.ApplicantRepository;
import sg.edu.iss.asdadt8.repositories.UserRepository;

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
	private ApplicantRepository arepo;
	
	
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
	
	public ApplicantDTO getApplicant(String email) {
		Applicant applicant = arepo.findByEmail(email);
		ApplicantDTO applicantDTO = applicantToDTO(applicant); 
		return applicantDTO;
	}
	
	//completed
	@Override
	public void saveApplicant(ApplicantDTO dto) {
		Applicant a = DTOToApplicant(dto);
		//check the password
		if(dto.getPassword()!=null) {
			//means the user update the password
			a.setPassword(passwordEncoder.encode(dto.getPassword()));
		} else {
			a.setPassword(arepo.findByEmail(dto.getUsername()).getPassword());
		}
		arepo.save(a);
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


	
	ApplicantDTO applicantToDTO(Applicant a) {
		ApplicantDTO dto = new ApplicantDTO();
		dto.setId(a.getId());
		dto.setUsername(a.getEmail());
		dto.setFirstName(a.getFirstName());
		dto.setLastName(a.getLastName());
		dto.setContactNumber(a.getContactNumber());
		dto.setAvatarImageURl(a.getAvatarImageUrl());
		dto.setChatstatus(a.getChatstatus());
		dto.setDob(a.getDob());
		dto.setGender(a.getGender());
		dto.setResumeURl(a.getResumeURl());
		dto.setRoles(Role.APPLICANT.toString());
		dto.setSelfIntroduction(a.getSelfIntroduction());
		dto.setUserStatus(a.getUserStatus());
		return dto;
	}
	
	Applicant DTOToApplicant(ApplicantDTO d) {
		Applicant a = new Applicant();
		if(d.getId()!=null) {
			a.setId(d.getId());
		}
		a.setEmail(d.getUsername());
		a.setFirstName(d.getFirstName());
		a.setLastName(d.getLastName());
		a.setContactNumber(d.getContactNumber());
		a.setAvatarImageUrl(d.getAvatarImageURl());
		a.setChatstatus(d.getChatstatus());
		a.setDob(d.getDob());
		a.setGender(d.getGender());
		a.setResumeURl(d.getResumeURl());
		a.setRoles(Role.APPLICANT.toString());
		a.setSelfIntroduction(d.getSelfIntroduction());
		a.setUserStatus(d.getUserStatus());
		return a;
	}
	
}
