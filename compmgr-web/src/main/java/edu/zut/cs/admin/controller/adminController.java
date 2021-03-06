package edu.zut.cs.admin.controller;

import com.alibaba.fastjson.JSON;
import edu.zut.cs.curriculum.model.ViewLesson;
import edu.zut.cs.curriculum.service.ViewLessonService;
import edu.zut.cs.tools.NowWeek;
import edu.zut.cs.user.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * create_by Intellij IDEA
 *
 * @author zouguo0212@
 * @package_name edu.zut.cs.admin.controller
 * @description
 * @date 2018/8/29 14:34
 */
@Controller
@RequestMapping(value = "/admin")
public class adminController {

    @Autowired
    ViewLessonService viewLessonService;

    /**
     * 得到当前周
     * @return
     */
    @GetMapping(value = "/getnowweek", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getNowWeek() {
        Integer week = new NowWeek().getNowWeek();
        String resultJson = JSON.toJSONString(week);
        return resultJson;
    }

    /**
     * 根据week得到当前用户当前week的课程信息
     * 当week为""时 表示查询当前userNum的所有课程信息
     * @param str
     * @return
     */
    @PostMapping(value = "getcoursebyweek", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String getCourseForUser(@RequestBody String str, HttpSession session) {
        Map map = JSON.parseObject(str);
        System.out.println("接受前台参数：" + map);
        Integer week = null;
        String weekStr = (String) map.get("week");
        if (!weekStr.equalsIgnoreCase(""))
            week = Integer.valueOf(weekStr);
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        List<ViewLesson> lessonList = viewLessonService.getLessonByUserNumAndWeek(teacher.getTeacherNum(),week);
//        测试用
//        List<ViewLesson> lessonList = viewLessonService.getLessonByUserNumAndWeek("3857", week);
        String result = JSON.toJSONString(lessonList);
        return result;
    }

}
