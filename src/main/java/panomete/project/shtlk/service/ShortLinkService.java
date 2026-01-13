package panomete.project.shtlk.service;

import panomete.project.shtlk.payload.ShortLinkListResponse;
import panomete.project.shtlk.payload.ShortLinkRequest;
import panomete.project.shtlk.payload.ShortLinkResponse;

import java.util.List;

public interface ShortLinkService {
    ShortLinkResponse createShortLink(ShortLinkRequest request);
    List<ShortLinkListResponse> getAllShortLink();
    boolean isLinkExist(String shortLink, String linkType);
    ShortLinkResponse getShortLink(String shortLink, String linkType);
}
