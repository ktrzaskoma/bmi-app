package io.github.ktrzaskoma.bmijava.user;

import java.time.LocalDate;

public record UserWriteModel (
       String name,
       String surname,
       LocalDate birthDate,
       String gender
) {
}
