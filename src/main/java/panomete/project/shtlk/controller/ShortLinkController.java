package panomete.project.shtlk.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import panomete.project.shtlk.entity.LinkType;
import panomete.project.shtlk.payload.ShortLinkRequest;
import panomete.project.shtlk.payload.ShortLinkResponse;
import panomete.project.shtlk.service.ShortLinkService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/short-link")
@Tag(name = "Short Link", description = "Short link API")
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    // redirect to original URL
    @Operation(summary = "Redirect to original URL")
    @GetMapping("/{type}/{short-link}")
    public RedirectView redirectToOriginalUrl(
            @PathVariable("type") String type,
            @PathVariable("short-link") String shortLink
    ) {
        LinkType linkType = null;
        if (type.equals("r")) {
            linkType = LinkType.RANDOM;
        } else if (type.equals("c")) {
            linkType = LinkType.CUSTOM;
        } else {
            throw new IllegalArgumentException("Invalid link type");
        }
        ShortLinkResponse response = shortLinkService.getShortLink(shortLink, linkType.name());
        if (response == null) {
            throw new IllegalArgumentException("Short link not found");
        }
        // todo: implement redirect logic
        return new RedirectView(response.originalLink());
    }

    // create short link
    @Operation(summary = "Create short link")
    @PostMapping("/api/v1/short-link")
    public ResponseEntity<ShortLinkResponse> createShortLink(
            @RequestBody ShortLinkRequest request
    ) {
        ShortLinkResponse response = shortLinkService.createShortLink(request);
        return ResponseEntity.ok(response);
    }

}
