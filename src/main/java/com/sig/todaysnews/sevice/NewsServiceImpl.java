package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.persistence.entity.Cluster;
import com.sig.todaysnews.persistence.entity.HotCluster;
import com.sig.todaysnews.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ClusterDto> getProposal() {
        return null;
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
        // TODO History 등록
        Cluster cluster = clusterRepository.findById(cid).orElse(null);
        ClusterDto clusterDto = null;

        if (cluster != null) {
            clusterDto = makeClusterDto(cluster, MakeArticleDtoList(articleRepository.findArticlesByCid(cluster.getClusterId())));
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

