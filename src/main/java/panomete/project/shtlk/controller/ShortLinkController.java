package panomete.project.shtlk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import panomete.project.shtlk.entity.LinkType;
import panomete.project.shtlk.payload.ShortLinkRequest;
import panomete.project.shtlk.payload.ShortLinkResponse;
import panomete.project.shtlk.service.ShortLinkService;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    // redirect to original URL
    @GetMapping("/{type}/{short-link}")
    public void redirectToOriginalUrl(
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
        // todo: implement redirect logic
    }

    // create short link
    @PostMapping("/api/v1/short-link")
    public ResponseEntity<ShortLinkResponse> createShortLink(
            @RequestBody ShortLinkRequest request
    ) {
        ShortLinkResponse response = shortLinkService.createShortLink(request);
        return ResponseEntity.ok(response);
    }

}
