package panomete.project.shtlk.payload;

public record ShortLinkResponse(
        String shortLink,
        String originalLink,
        String linkType
) {
}
