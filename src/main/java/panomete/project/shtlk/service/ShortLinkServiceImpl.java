package panomete.project.shtlk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import panomete.project.shtlk.entity.LinkType;
import panomete.project.shtlk.entity.ShortLink;
import panomete.project.shtlk.payload.ShortLinkListResponse;
import panomete.project.shtlk.payload.ShortLinkRequest;
import panomete.project.shtlk.payload.ShortLinkResponse;
import panomete.project.shtlk.repository.ShortLinkRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl implements ShortLinkService {
    private final ShortLinkRepository shortLinkRepository;

    @Value("${global.address}")
    private String address;

    @Override
    public ShortLinkResponse createShortLink(ShortLinkRequest request) {
        if(request.type() == LinkType.CUSTOM){
            if(isLinkExist(request.customAlias(), LinkType.CUSTOM.name())){
                throw new IllegalArgumentException("Custom alias already exists");
            }
            ShortLink shortLink = ShortLink.builder()
                    .originalUrl(request.originalLink())
                    .shortUrl(request.customAlias())
                    .type(LinkType.CUSTOM)
                    .build();
            shortLinkRepository.save(shortLink);
            return new ShortLinkResponse(
                    shortLink.getShortUrl(),
                    shortLink.getOriginalUrl(),
                    shortLink.getType().name()
            );
        }
        // generate random short link
        String randomShortLink = generateRandomString();
        while(isLinkExist(randomShortLink, LinkType.RANDOM.name())){
            randomShortLink = generateRandomString();
        }
        ShortLink shortLink = ShortLink.builder()
                .originalUrl(request.originalLink())
                .shortUrl(randomShortLink)
                .type(LinkType.RANDOM)
                .build();
        shortLinkRepository.save(shortLink);
        return new ShortLinkResponse(
                shortLink.getShortUrl(),
                shortLink.getOriginalUrl(),
                shortLink.getType().name()
        );
    }

    private  String generateRandomString(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public List<ShortLinkListResponse> getAllShortLink() {
        List<ShortLink> shortLinks = shortLinkRepository.findAll();
        return shortLinks.stream().map(shortLink -> new ShortLinkListResponse(
                address + "/" + (shortLink.getType().name().equals("RANDOM") ? "r" : "c") + "/" + shortLink.getShortUrl(),
                shortLink.getOriginalUrl()
        )).toList();
    }

    @Override
    public boolean isLinkExist(String shortLink, String linkType) {
        return shortLinkRepository.findByShortUrlAndType(shortLink, LinkType.valueOf(linkType)) != null;
    }

    @Override
    public ShortLinkResponse getShortLink(String shortLink, String linkType) {
        ShortLink shortLinkEntity = shortLinkRepository.findByShortUrlAndType(shortLink, LinkType.valueOf(linkType));
        if(shortLinkEntity == null){
            throw new IllegalArgumentException("Link not found");
        }
        return new ShortLinkResponse(
                shortLinkEntity.getShortUrl(),
                shortLinkEntity.getOriginalUrl(),
                shortLinkEntity.getType().name()
        );
    }

    // temporary password confirmation
    @Override
    public boolean confirmAction(String password) {
        return password.equals("sh0r7L1nk@dm1n");
    }
}
