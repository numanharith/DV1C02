package com.numan.dv1c02.petclinic.owner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
class PetController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FROM = "pets/createOrUpdateForm";

    private final OwnerRepository owners;

    public PetController(OwnerRepository owners) {
        this.owners = owners;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.owners.findPetTypes();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") int ownerId) {
        return this.owners.findById(ownerId);
    }

    @ModelAttribute("pet")
    public Pet findPet(@PathVariable("ownerId") int ownerId,
                       @PathVariable(name = "petId", required = false) Integer petId) {
        return petId == null ? new Pet() : this.owners.findById(ownerId).getPet(petId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new PetValidator());
    }

    @GetMapping("pets/new")
    public String initCreationForm(Owner owner, ModelMap model) {
        Pet pet = new Pet();
        owner.addPet(pet);
        model.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FROM;
    }

    @PostMapping
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.addPet(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FROM;
        }

        this.owners.save(owner);
        return "redirect:/owners/{ownerId}";
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(Owner owner, @PathVariable("petId") int petId, ModelMap model) {
        Pet pet = owner.getPet(petId);
        model.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FROM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model) {
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FROM;
        }

        owner.addPet(pet);
        this.owners.save(owner);
        return "redirect:/owners/{ownerId}";
    }
}
