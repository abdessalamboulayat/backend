package com.example.form.ws;

import com.example.form.bean.Formulaire;
import com.example.form.service.FormulaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RequestMapping("api/v1/formulaire")
public class FormulaireWs {
    @Autowired
    private FormulaireService formulaireService;

    @PostMapping("/")
    public int save(@RequestBody Formulaire entity) {
        System.out.println("im hre!");
        return formulaireService.save(entity);
    }

    @GetMapping("/getAllRequest")
    public List<Formulaire> findAll() {
        return formulaireService.findAll();
    }

    @GetMapping("ncin/{ncin}")
    public Formulaire findByNcin(String ncin) {
        return formulaireService.findByNcin(ncin);
    }

    @GetMapping("/user/ref/{ref}")
    public Formulaire findByUserRef(String ref) {
        return formulaireService.findByUserRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ncin) {
        return formulaireService.deleteByNcin(ncin);
    }

    @GetMapping("/count/")
    public long count() {
        return formulaireService.count();
    }
}