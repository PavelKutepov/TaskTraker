package pkutepv.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pkutepv.Dao.Dao;
import pkutepv.model.Task;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
public class ApplicationController {

    @Inject
    private Dao dao;

    @RequestMapping(value = "task/list/by/{name}/{password}/{status}",method = RequestMethod.GET)
    public @ResponseBody List<Task> getTask(@PathVariable String name, @PathVariable String password, @PathVariable String status) {

      return  dao.getTaskList(name,password,status);

    }

    @RequestMapping(value = "add/task",method = RequestMethod.POST )

    public @ResponseBody Map addTask(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("description") String description,
            @RequestParam("status") String status)
    {
        dao.save(name,password,description,status);
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        map.put("description", description);
        map.put("status", status);
        return map;
    }
    @RequestMapping(value = "delete/task/by/{name}/{password}/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTask(@PathVariable String name, @PathVariable String password, @PathVariable Long id){
        dao.delete(name,password,id);
    }

}
