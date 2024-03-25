package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import it.contrader.dto.RegistryDTO;
import it.contrader.model.Registry;
import it.contrader.model.User;
import it.contrader.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.UserService;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	@Autowired
	private RegistryService registryService;

	@PostMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(value = "email", required = true) String email,
						@RequestParam(value = "password", required = true) String password) {


		if (!isValidEmail(email)) {
			request.setAttribute("error", "Formato email non valido");
			return "index";
		}


		if (!isValidCredential(password, 5)) {
			request.setAttribute("error", "La password deve essere almeno di 5 caratteri");
			return "index";
		}

		try {
			UserDTO userDTO = service.findByEmailAndPassword(email, password);
			request.getSession().setAttribute("user", userDTO);
			RegistryDTO registry = registryService.readByUser(userDTO.getId());
			request.getSession().setAttribute("registryDto", registry);

			if (registry == null || isRegistryEmpty(registry)) {
				return "/registry/updatelogin";
			}

			switch (userDTO.getUsertype()) {
				case SUPER:
					return "homesuper";
				case ADMIN:
					return "homeadmin";
				case USER:
					return "homeuser";
				default:
					return "index";
			}
		} catch (Exception e) {
			request.setAttribute("error", "Email o password non corretti");
			return "index";
		}
	}

	private boolean isRegistryEmpty(RegistryDTO registry) {
		return registry.getName() == null &&
				registry.getSurname() == null &&
				registry.getBirthDate() == null &&
				registry.getGender() == null &&
				registry.getNationality() == null &&
				registry.getCity() == null &&
				registry.getAddress() == null &&
				registry.getCf() == null &&
				registry.getBg() == null;
	}

	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "/user/users";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		service.delete(id);
		setAll(request);
		if(user.getUsertype().toString().equals("SUPER")){
			return "/user/users";
		}else{
			return "index";
		}
	}

	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "/user/updateuser";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {



		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setEmail(email);
		dto.setPassword(password);
		dto.setUsertype(usertype);

		if (!isValidEmail(email)) {
			request.setAttribute("error", "Formato email non valido");
			return "/user/updateuser";
		}


		if (!isValidCredential(password, 5)) {
			request.setAttribute("error", "La password deve essere almeno di 5 caratteri");
			return "/user/updateuser";
		}

		service.update(dto);
		if(user.getUsertype().toString().equals("SUPER")){
			setAll(request);
			return "/user/users";
		}else{
			request.getSession().setAttribute("dto", service.read(id));
			return "/user/readuser";
		}

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {
		UserDTO dto = new UserDTO();
		dto.setEmail(email);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.insert(dto);
		setAll(request);
		return "/user/users";
	}
	@PostMapping("/insertSuper")
	public String insertSuper(HttpServletRequest request, @RequestParam("email") String email,
		@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {

		if (!isValidEmail(email)) {
			request.setAttribute("error", "Formato email non valido");
			return "/user/users";
		}


		if (!isValidCredential(password, 5)) {
			request.setAttribute("error", "La password deve essere almeno di 5 caratteri");
			return "/user/users";
		}

		try {
			UserDTO dto = new UserDTO();
			dto.setEmail(email);
			dto.setPassword(password);
			dto.setUsertype(usertype);
			UserDTO user = service.insert(dto);
			setAll(request);
			RegistryDTO registry = new RegistryDTO(null, null, null, null, null, null, null, null, null, null, user.getId());
			request.getSession().setAttribute("IDUSER", registry);
			registryService.insert(registry);
			return "/user/users";
		} catch (Exception e) {
			request.setAttribute("error", "Email già in uso!");
			return "/user/users";
		}
	}

	@PostMapping("/register")
	public String register(HttpServletRequest request, @RequestParam("email") String email,
						   @RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {

		if (!isValidEmail(email)) {
			request.setAttribute("error", "Formato email non valido");
			return "registration";
		}


		if (!isValidCredential(password, 5)) {
			request.setAttribute("error", "La password deve essere almeno di 5 caratteri");
			return "registration";
		}

		try {
			UserDTO dto = new UserDTO();
			dto.setEmail(email);
			dto.setPassword(password);
			dto.setUsertype(usertype);
			service.insert(dto);
			UserDTO loginDTO = service.findByEmailAndPassword(email, password);
			request.getSession().setAttribute("user", loginDTO);
			return "/registry/insertregistry";

		} catch (Exception e) {
			request.setAttribute("error", "Email già in uso!");
			return "registration";
		}
	}


	private boolean isValidEmail(String email) {
		String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}




	private boolean isValidCredential(String value, int minLength) {
		return value.length() >= minLength;
	}


	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		UserDTO dtoUser= service.read(id);
		request.getSession().setAttribute("dto",dtoUser);
		RegistryDTO dto=registryService.readByUser(dtoUser.getId());
		request.getSession().setAttribute("registryDto",dto );
		return "/user/readuser";
	}

	@GetMapping("/search")
	public String findByEmail(HttpServletRequest request, @RequestParam("email") String email) {
		UserDTO dtoUser= service.findByEmail(email);
		request.getSession().setAttribute("dto",dtoUser);
		RegistryDTO dto=registryService.readByUser(dtoUser.getId());
		request.getSession().setAttribute("registryDto",dto );
		return "/user/readuser";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}


	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
