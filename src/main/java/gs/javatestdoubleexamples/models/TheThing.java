package gs.javatestdoubleexamples.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Builder
@Data
@Jacksonized
public class TheThing {

    String name;

    String desc;

    UUID id;
}
