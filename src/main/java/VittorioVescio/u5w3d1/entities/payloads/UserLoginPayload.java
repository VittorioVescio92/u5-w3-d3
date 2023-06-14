package VittorioVescio.u5w3d1.entities.payloads;

import lombok.Getter;

@Getter
public class UserLoginPayload {
	String email;
	String password;
}