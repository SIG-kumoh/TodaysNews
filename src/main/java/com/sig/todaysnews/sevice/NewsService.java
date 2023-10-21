package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.persistence.entity.Cluster;
import com.sig.todaysnews.persistence.repository.ClusterRepository;
import com.sig.todaysnews.persistence.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final ClusterRepository clusterRepository;
    private final ArticleRepository articleRepository;

    public List<ClusterDto> getProposal() {
        return null;
    }

    public List<ClusterDto> getSection(Long sid, Date date) {
        List<Cluster> clusters = clusterRepository.findClustersBySidAndDate(sid, date);
        List<ClusterDto> clusterDtos = clusters.stream()
                .map(cluster -> {
                    ClusterDto clusterDto = ClusterDto.builder().build();
                    // TODO 클러스터 세팅
                    return clusterDto;
                })
                .collect(Collectors.toList());
        return clusterDtos;
    }

    public List<ClusterDto> getHotClusters() {
        return null;
    }

    public ClusterDto getCluster(Long cid) {
        Cluster cluster = clusterRepository.findById(cid).orElse(null);
        ClusterDto clusterDto = null;

        if (cluster != null) {
            clusterDto = ClusterDto.builder().build();
            // TODO clusterDto 세팅
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
