package panomete.project.shtlk.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import panomete.project.shtlk.payload.ConfirmPasswordRequest;
import panomete.project.shtlk.payload.ShortLinkListResponse;
import panomete.project.shtlk.payload.ShortLinkRequest;
import panomete.project.shtlk.payload.ShortLinkResponse;
import panomete.project.shtlk.service.ShortLinkService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/short-link")
@Tag(name = "Short Link", description = "Short link API")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    // create short link
    @Operation(summary = "Create short link")
    @PostMapping
    public ResponseEntity<ShortLinkResponse> createShortLink(
            @RequestBody ShortLinkRequest request
    ) {
        ShortLinkResponse response = shortLinkService.createShortLink(request);
        return ResponseEntity.ok(response);
    }

    // get all short links
    @Operation(summary = "Get all short links in the system")
    @GetMapping
    public ResponseEntity<List<ShortLinkListResponse>> getAllShortLinks() {
        return ResponseEntity.ok(shortLinkService.getAllShortLink());
    }

    @PostMapping("/confirm")
    @Operation(summary = "Confirm action with password")
    public ResponseEntity<Boolean> confirmAction(
            @RequestBody ConfirmPasswordRequest request
    ) {
        boolean isConfirmed = shortLinkService.confirmAction(request.password());
        if(isConfirmed) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(401).body(false);
        }
    }

}
