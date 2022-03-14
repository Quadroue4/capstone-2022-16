package fleamarket.core.domain;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
}
