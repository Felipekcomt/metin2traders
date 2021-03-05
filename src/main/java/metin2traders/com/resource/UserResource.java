package metin2traders.com.resource;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserResource {
    private Long id;
    private String name;
    private String lastname;
    private String phone_number;
    private String url_facebook;
    private String email;
}
