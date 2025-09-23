package panomete.project.shtlk.payload;

import panomete.project.shtlk.entity.LinkType;

public record ShortLinkRequest(
        LinkType type,
        String originalLink
) {
}
