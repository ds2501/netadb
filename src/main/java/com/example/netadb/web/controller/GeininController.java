package com.example.netadb.web.controller;

import com.example.netadb.domain.dao.entity.Geinin;
import com.example.netadb.domain.service.GeininService;
import com.example.netadb.web.form.GeininForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GeininController {

    private final GeininService geininService;

    public GeininController(final GeininService geininService) {
        this.geininService = geininService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/combi/home";
    }

    @GetMapping("/combi/home")
    public String home(final Model model) {
        List<Geinin> geininList = geininService.findAllGeinin();
        model.addAttribute("geinins", geininList);
        return "/combi/home";
    }

    @PostMapping(path = "/combi/edit", params = "edit")
    public String edit(@RequestParam String g_id, @ModelAttribute GeininForm geininForm) {
        Geinin geinin = geininService.selectById(g_id);
        BeanUtils.copyProperties(geinin, geininForm);
        return "/combi/edit";
    }

    @PostMapping(path = "/combi/edit", params = "back")
    public String back() {
        return "redirect:/";
    }

    @PostMapping(path = "/combi/edit", params = "delete")
    public String delete(@RequestParam String g_id) {
        geininService.delete(g_id);
        return "redirect:/";
    }

    @PostMapping(path = "/combi/edit", params = "regist")
    public String editRegist(@RequestParam String g_id, @Validated @ModelAttribute GeininForm geininForm, BindingResult result) {
        if (result.hasErrors()) {
            return edit(g_id, geininForm);
        }

        Geinin geinin = new Geinin();
        BeanUtils.copyProperties(geininForm, geinin);
        geinin.setG_id(g_id);

        geininService.update(geinin);
        return "redirect:/";
    }

    @PostMapping(path = "/combi/regist")
    public String newRegist(@Validated @ModelAttribute GeininForm geininForm, BindingResult result) {
        if (result.hasErrors()) {
            return insert(geininForm);
        }

        Geinin geinin = new Geinin();
        BeanUtils.copyProperties(geininForm, geinin);

        geininService.insert(geinin);
        return "redirect:/";
    }

    @GetMapping(path = "/combi/regist")
    public String insert(@ModelAttribute GeininForm geininForm) {
        return "/combi/regist";
    }

    @PostMapping(path = "/combi/regist", params = "back")
    public String newBack() {
        return "redirect:/";
    }
}
