package panomete.project.shtlk.service;

import org.springframework.stereotype.Service;
import panomete.project.shtlk.payload.ShortLinkRequest;
import panomete.project.shtlk.payload.ShortLinkResponse;

import java.util.List;

@Service
public class ShortLinkServiceImpl implements ShortLinkService {
    @Override
    public ShortLinkResponse createShortLink(ShortLinkRequest request) {
        return null;
    }

    @Override
    public List<ShortLinkResponse> getAllShortLink() {
        return List.of();
    }
}
