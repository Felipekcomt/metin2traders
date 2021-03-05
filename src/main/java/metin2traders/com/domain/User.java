package metin2traders.com.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastname;
    @NotNull
    @NotBlank
    private String phone_number;
    private String url_facebook;
    @NotNull
    @NotBlank
    private String email;
}
