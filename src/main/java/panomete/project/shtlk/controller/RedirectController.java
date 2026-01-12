package panomete.project.shtlk.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import panomete.project.shtlk.service.ShortLinkService;

@Controller
@RequiredArgsConstructor
@Tag(name = "Redirect API", description = "Redirect Controller")
public class RedirectController {
    private final ShortLinkService shortLinkService;

    @GetMapping("api/v1/redirect/{type}/{short-link}") // development
//    @GetMapping("/{type}/{short-link}") // production
    @Operation(summary = "Redirect to original URL")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable("type") String type,
            @PathVariable("short-link") String shortLink
    ) {
        if(type.equals("r")) {
            type = "RANDOM";
        } else if(type.equals("c")) {
            type = "CUSTOM";
        } else {
            throw new IllegalArgumentException("Invalid link type");
        }

        if(!shortLinkService.isLinkExist(shortLink, type)) {
            throw new IllegalArgumentException("Short link not found");
        }

        String originalUrl = shortLinkService.getShortLink(shortLink, type).originalLink();
        return ResponseEntity.status(302).header("Location", originalUrl).build();
    }
}
