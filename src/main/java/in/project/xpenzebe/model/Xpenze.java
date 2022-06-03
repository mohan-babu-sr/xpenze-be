package in.project.xpenzebe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="xpenze")
public class Xpenze {
    @Id
    private String id;

    @NotNull(message="Xpenze cannot be null")
    private String xpenze;

    @NotNull(message="Description cannot be null")
    private String description;

    @NotNull(message="Completed cannot be null")
    private Boolean completed;

    private Date createdAt;

    private Date updatedAt;
}
