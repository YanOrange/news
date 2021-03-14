package com.delay.news.repository;

import com.delay.news.entity.Fav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface FavRepository extends JpaRepository<Fav,Integer> {

    List<Fav> findAllByUserId(Integer id);

    Fav findByUserIdAndNewsId(Integer userId, Integer newsId);
}
