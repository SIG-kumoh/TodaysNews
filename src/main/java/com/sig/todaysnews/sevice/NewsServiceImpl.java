package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.ArticleDto;
import com.sig.todaysnews.dto.ClusterDto;
import com.sig.todaysnews.persistence.entity.Article;
import com.sig.todaysnews.persistence.entity.Cluster;
import com.sig.todaysnews.persistence.repository.ClusterRepository;
import com.sig.todaysnews.persistence.repository.ArticleRepository;
import com.sig.todaysnews.persistence.repository.UserRepository;
import com.sig.todaysnews.persistence.repository.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final UserRepository userRepository;
    private final ViewHistoryRepository viewHistoryRepository;
    private final ClusterRepository clusterRepository;
    private final ArticleRepository articleRepository;

    public List<ClusterDto> getProposal() {
        return null;
    }

    public List<ClusterDto> getSection(Long sid, Date date) {
        List<Cluster> clusters = clusterRepository.findClustersBySidAndDate(sid, date);

        List<ClusterDto> clusterDtoList = clusters.stream()
                .map(cluster -> makeClusterDto(cluster, MakeArticleDtoList(articleRepository.findArticlesByCid(cluster.getClusterId()))))
                .collect(Collectors.toList());

        return clusterDtoList;
    }

    public List<ClusterDto> getHotClusters() {
        return null;
    }

    public ClusterDto getCluster(Long cid) {
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

