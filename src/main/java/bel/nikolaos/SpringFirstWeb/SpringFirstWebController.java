/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bel.nikolaos.SpringFirstWeb;

import controllers.RecordController;
import java.util.List;
import javax.validation.Valid;
import models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author nikolaos
 */
@Controller
public class SpringFirstWebController {

    private final RecordController recCon;
    @Value("${spring.application.name}")
    String appName;

    @Autowired
    public SpringFirstWebController() {
        recCon = new RecordController();
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("paragraph1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ut lorem porttitor, sagittis dolor non, faucibus justo. Nunc sed commodo magna. Vestibulum commodo nunc sit amet dictum ultricies. In bibendum purus magna, non mollis diam fringilla quis. Cras eu mi neque. Maecenas vulputate erat quis dolor rhoncus, vel semper elit commodo. Aliquam lacinia condimentum arcu, ultricies lacinia urna pellentesque a. Proin mollis nunc vel augue gravida dignissim in non eros. Aliquam ultrices magna in eros vehicula, vel blandit justo hendrerit. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse lorem nisl, semper et magna aliquam, sollicitudin porta nisl. Pellentesque fermentum tristique urna, ac lobortis tortor dignissim nec. Sed eget mauris id dui commodo bibendum. Mauris sagittis elit et purus tincidunt pretium id id magna. Nunc sed est est.");
        model.addAttribute("paragraph2", "In quis tempus urna. Etiam aliquam consequat metus in condimentum. Suspendisse interdum dapibus rhoncus. Suspendisse potenti. Quisque lorem dolor, consequat nec turpis vel, rutrum auctor diam. Duis pellentesque diam mattis nulla efficitur hendrerit. Praesent sit amet purus interdum, tempus tortor nec, viverra massa. Sed finibus, velit in luctus rhoncus, libero magna blandit orci, vel aliquet mi mauris et libero. Suspendisse tempus dolor eget augue placerat, vitae sodales nisl faucibus. Integer odio ligula, varius rutrum eros quis, commodo ultrices ligula. Integer pellentesque, lectus at malesuada dictum, velit ipsum sodales felis, eget scelerisque enim leo ut nunc. Duis id bibendum eros. Pellentesque eu felis nec dolor euismod lobortis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Proin condimentum erat mauris, vitae facilisis nulla rhoncus non.");
        model.addAttribute("paragraph3", "In luctus scelerisque justo, vel scelerisque ex cursus eu. Interdum et malesuada fames ac ante ipsum primis in faucibus. Duis congue mi in mauris semper, sit amet porta velit porta. Aliquam erat volutpat. Proin placerat, dolor viverra luctus vestibulum, odio justo congue nibh, nec euismod turpis tortor sed sapien. Vestibulum iaculis, ex pretium ultrices suscipit, felis risus egestas lorem, feugiat rutrum felis massa vel odio. Morbi tempor urna sit amet faucibus auctor.");
        model.addAttribute("paragraph4", "Sed scelerisque erat leo, nec maximus enim pharetra ut. Nunc molestie turpis neque, eu sollicitudin odio porttitor at. Praesent auctor ligula condimentum malesuada lobortis. Nulla facilisi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed scelerisque volutpat ex ac rutrum. Quisque non urna lacus. Maecenas convallis aliquet nibh ac molestie. Fusce suscipit, augue luctus blandit vulputate, mauris dui dapibus augue, in bibendum ante justo vel dolor. Nam iaculis est massa, id scelerisque turpis consequat ut. Donec id porta leo. Donec a felis interdum mi faucibus porttitor nec ac neque. Aliquam sed gravida diam. Quisque ac ligula ut quam finibus bibendum et id nulla. Suspendisse metus arcu, luctus nec lectus eu, vulputate facilisis metus.");
        model.addAttribute("paragraph5", "Donec non risus dignissim, tincidunt leo sed, pellentesque urna. Integer volutpat malesuada arcu ac bibendum. Integer ultrices feugiat urna, et commodo ex luctus a. Donec ac consectetur elit. Vestibulum bibendum tellus non pulvinar bibendum. Nunc pulvinar consectetur pellentesque. Aenean consequat condimentum arcu eu facilisis. Vestibulum et diam ultricies, malesuada diam vel, mattis diam.");

        return "about";
    }

    @GetMapping("/records")
    public String testPage(Model model) {
        List<Record> recList = recCon.findAll();
        model.addAttribute("records", recList);
        return "records";
    }

    @GetMapping("/createrec")
    public String showCreateRecordForm(Record record) {
        return "createrec";
    }

    @PostMapping("/addrecord")
    public String addRecord(@Valid Record record, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "createrec";
        }

        record.setSelected(true);
        recCon.addRecord(record);
        model.addAttribute("records", recCon.findAll());
        return "records";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Record record = recCon.findRecordById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid record Id:" + id));

        model.addAttribute("record", record);
        return "updaterec";
    }

    @PostMapping("/update/{id}")
    public String updateRecord(@PathVariable("id") int id, @Valid Record record, BindingResult result, Model model) {
        if (result.hasErrors()) {
            record.setId(id);
            return "updaterec";
        }
        record.setSelected(false);
        recCon.updateRecord(record);
        model.addAttribute("records", recCon.findAll());
        return "redirect:/records";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        Record record = recCon.findRecordById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid record Id:" + id));

        recCon.deleteRecord(record);
        model.addAttribute("records", recCon.findAll());
        return "redirect:/records";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
