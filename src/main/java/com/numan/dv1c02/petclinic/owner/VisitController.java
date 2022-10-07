package com.numan.dv1c02.petclinic.owner;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class VisitController {

    private final OwnerRepository owners;

    public VisitController(OwnerRepository owners) {
        this.owners = owners;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
     * we always have fresh data - Since we do not use the session scope, make sure that
     * Pet object always has an id (Even though id is not part of the form fields)
     * @param petId
     * @return Pet
     */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("ownerId") int ownerId,
                                  @PathVariable("petId") int petId,
                                  Map<String, Object> model) {
        Owner owner = this.owners.findById(ownerId);

        Pet pet = owner.getPet(petId);
        model.put("pet", pet);
        model.put("owner", owner);

        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String initNewVisitForm() {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visit/new")
    public String processNewVisitForm(@ModelAttribute Owner owner,
                                      @PathVariable int petId, @Valid Visit visit,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        }

        owner.addVisit(petId, visit);
        this.owners.save(owner);
        return "redirect:/owners/{ownerId}";
    }
}
