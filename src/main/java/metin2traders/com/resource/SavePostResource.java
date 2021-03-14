package metin2traders.com.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavePostResource {
    private String post_name;
    private String price;
    @Lob
    private String description;

    private Long serverId;
}
