package joaovitorffeijo.basic_api.model.user;

public record RegisterDTO(String name, String login, String password, UserRole role) {
}
