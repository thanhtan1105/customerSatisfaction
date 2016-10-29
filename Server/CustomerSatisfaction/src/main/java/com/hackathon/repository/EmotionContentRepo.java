package com.hackathon.repository;

import com.hackathon.constant.EEmotion;
import com.hackathon.entity.EmotionContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lethanhtan on 10/29/16.
 */
@Repository
public interface EmotionContentRepo extends JpaRepository<EmotionContentEntity, Long> {

    @Query("SELECT e FROM EmotionContentEntity  e WHERE e.emotionFirst = :first AND (:second = null  or e.emotionSecond =:second  ) AND (:third = null or e.emotionThird =:third )order by e.vote")
    public Page<EmotionContentEntity> getEmotionContent(@Param("first") EEmotion first,
                                                        @Param("second") EEmotion second,
                                                        @Param("third") EEmotion third,
                                                        Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "update emotion_content set vote = vote +1 where id  = ?1", nativeQuery = true)
    public void updateVote(Long contentId);
}
