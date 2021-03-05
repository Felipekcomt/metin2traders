package metin2traders.com.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="categories")
@Getter
@Setter
public class Category {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotBlank
    private String name;
}
