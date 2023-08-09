package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Override
    @Transactional
    public void delete(CourseModel courseModel) {
        //Todo Retorna os modules de um determinado course
        List<ModuleModel> moduleModelList = moduleRepository
                .findAllModulesIntoCourse(courseModel.getCourseId());

        if(!moduleModelList.isEmpty()) {
            for(ModuleModel moduleModel : moduleModelList) {

                //Todo Retorna os lessons de um determinado module
                List<LessonModel> lessonModelList = lessonRepository
                        .findAllLessonsIntoModule(moduleModel.getModuleId());
                if(!lessonModelList.isEmpty()) {

                    //Todo Deleta todas as lessons de um determinado module
                    lessonRepository.deleteAll(lessonModelList);
                }
            }
            moduleRepository.deleteAll(moduleModelList);
        }
        courseRepository.delete(courseModel);
    }
}
