package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.persistence.entity.Cluster;
import com.sig.todaysnews.persistence.entity.HotCluster;
import com.sig.todaysnews.persistence.entity.User;
import com.sig.todaysnews.persistence.entity.ViewHistory;
import com.sig.todaysnews.persistence.repository.*;
import com.sig.todaysnews.security.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final UserRepository userRepository;
    private final ViewHistoryRepository viewHistoryRepository;
    private final ClusterRepository clusterRepository;
    private final HotClusterRepository hotClusterRepository;
    private final ArticleRepository articleRepository;

    public List<ClusterDto> getProposal(LocalDate date) {
        User user = userRepository.findOneWithAuthoritiesByUsername(AuthenticationUtil.getCurrentUsername().get()).get();
        List<ViewHistory> viewHistories = viewHistoryRepository.findAllWithUser(user, date, PageRequest.of(0, 30));

        List<Cluster> relatedClusters = new ArrayList<>();
        for (ViewHistory vh : viewHistories) {
            Cluster cluster = vh.getCentroid().getCluster();
            if (cluster != null) relatedClusters.add(vh.getCentroid().getCluster());
        }

        List<Cluster> clusters = clusterRepository.findAllWithRelatedClusters(relatedClusters, date, PageRequest.of(0, 10));
        List<ClusterDto> clusterDtoList = clusters.stream()
                .map(cluster -> makeClusterDto(cluster, MakeArticleDtoList(articleRepository.findArticlesByCid(cluster.getClusterId()))))
                .collect(Collectors.toList());
        return clusterDtoList;
    }

    public List<ClusterDto> getSection(Long sid, LocalDate date) {
        List<Cluster> clusters = clusterRepository.findClustersBySidAndDate(sid, date);

        List<ClusterDto> clusterDtoList = clusters.stream()
                .map(cluster -> makeClusterDto(cluster, MakeArticleDtoList(articleRepository.findArticlesByCid(cluster.getClusterId()))))
                .collect(Collectors.toList());

        return clusterDtoList;
    }

    public List<ClusterDto> getHotClusters(LocalDate date) {
        List<HotCluster> hotClusters = hotClusterRepository.findHotClustersByDate(date);
        List<ClusterDto> clusterDtoList = hotClusters.stream()
                .map(hotCluster -> makeHotClusterDto(hotCluster, MakeArticleDtoList(articleRepository.findArticlesByCid(hotCluster.getCluster().getClusterId()))))
                .collect(Collectors.toList());
        return clusterDtoList;
    }

    public ClusterDto getCluster(Long cid) {
        Cluster cluster = clusterRepository.findById(cid).orElse(null);

        ClusterDto clusterDto = null;
        if (cluster != null) {
            clusterDto = makeClusterDto(cluster, MakeArticleDtoList(articleRepository.findArticlesByCid(cluster.getClusterId())));
            String username = AuthenticationUtil.getCurrentUsername().orElse(null);
            if (username != null) {
                viewHistoryRepository.save(
                        ViewHistory.builder()
                                .regdate(LocalDateTime.now())
                                .centroid(cluster.getCentroid())
                                .user(userRepository.findOneWithAuthoritiesByUsername(username).get()).build()
                );
            }
        }

        return clusterDto;
    }

    public void deleteCluster(Long cid) {
        clusterRepository.deleteById(cid);
    }

    public void deleteArticle(Long aid) {
        articleRepository.deleteById(aid);
    }
}

