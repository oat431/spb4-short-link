package panomete.project.shtlk.payload;

import io.swagger.v3.oas.annotations.media.Schema;

public record ConfirmPasswordRequest(
        String password
) { }
