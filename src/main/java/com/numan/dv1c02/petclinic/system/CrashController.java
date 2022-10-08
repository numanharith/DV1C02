package com.numan.dv1c02.petclinic.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author Michael Isvy
 * <p/>
 * Also see how a view that resolves to "error" has been added ("error.html").
 */
@Controller
public class CrashController {
    @GetMapping("/oups")
    public String triggerException() {
        throw new RuntimeException(
                "Expected: controller used to showcase what happens when exception is thrown"
        );
    }
}
