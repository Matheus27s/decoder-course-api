package com.ead.course.services;

import com.ead.course.models.LessonModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {
    Object save(LessonModel lessonModel);

    Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId);

    void delete(LessonModel lessonModel);

    List<LessonModel> findAllByModule(UUID moduleId);
}
