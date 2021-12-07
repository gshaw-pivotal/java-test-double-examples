package gs.javatestdoubleexamples.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class TheThing {

    String name;

    String desc;

    UUID id;
}
