package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by juancho on 13/01/2017.
 */
 @Controller
public class IndexController {

     @RequestMapping("/")
     public String index(){
         //return the name of the template (html implied)
         return "index";
     }
}
