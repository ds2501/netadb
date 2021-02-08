package com.example.netadb.web.controller;

import com.example.netadb.domain.dao.entity.Geinin;
import com.example.netadb.domain.dao.entity.Neta;
import com.example.netadb.domain.service.GeininService;
import com.example.netadb.domain.service.NetaService;
import com.example.netadb.web.form.NetaForm;
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
public class NetaController {

    private final NetaService netaService;
    private final GeininService geininService;

    public NetaController(final NetaService netaService, final GeininService geininService) {
        this.netaService = netaService;
        this.geininService = geininService;
    }

    @GetMapping("/neta/home")
    public String home(final Model model) {
        List<Neta> netaList = netaService.findAllNeta();
        model.addAttribute("netas", netaList);
        return "/neta/home";
    }

    @PostMapping(path = "/neta/edit", params = "edit")
    public String edit(@RequestParam String n_id, @ModelAttribute NetaForm netaForm) {
        Neta neta = netaService.selectById(n_id);
        BeanUtils.copyProperties(neta, netaForm);
        return "/neta/edit";
    }

    @PostMapping(path = "/neta/edit", params = "back")
    public String back() {
        return "redirect:/neta/home";
    }

    @PostMapping(path = "/neta/edit", params = "delete")
    public String delete(@RequestParam String n_id) {
        netaService.delete(n_id);
        return "redirect:/neta/home";
    }

    @PostMapping(path = "/neta/edit", params = "regist")
    public String editRegist(@RequestParam String n_id, @Validated @ModelAttribute NetaForm netaForm, BindingResult result) {
        if (result.hasErrors()) {
            return edit(n_id, netaForm);
        }

        Neta neta = new Neta();
        BeanUtils.copyProperties(netaForm, neta);
        neta.setN_id(n_id);

        netaService.update(neta);
        return "redirect:/neta/home";
    }

    @PostMapping(path = "/neta/regist")
    public String newRegist(@Validated @ModelAttribute NetaForm netaForm, BindingResult result, final Model model) {
        if (result.hasErrors()) {
            return insert(netaForm, model);
        }

        Neta neta = new Neta();
        BeanUtils.copyProperties(netaForm, neta);

        netaService.insert(neta);
        return "redirect:/neta/home";
    }

    @GetMapping(path = "/neta/regist")
    public String insert(@ModelAttribute NetaForm netaForm, final Model model) {
        List<Geinin> geininList = geininService.findAllGeinin();
        model.addAttribute("geinins", geininList);
        return "/neta/regist";
    }

    @PostMapping(path = "/neta/regist", params = "back")
    public String newBack() {
        return "redirect:/neta/home";
    }
}