package de.atruvia.simplespring.demo;

import de.atruvia.simplespring.translator.Translator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") // Default
//@Scope("prototype")

//@Lazy(false)
@RequiredArgsConstructor
public class Demo {

    //@Autowired // Fieldinjection
    @Qualifier("upper")
    private final Translator translator;

    /*@Autowired // Setterinjection
    public void setTranslator(final Translator translator) {
        this.translator = translator;
    }

     */

    /*
    // Constructor Injection
    public Demo(@Qualifier("upper") final Translator translator) {
        this.translator = translator;

    }
    */


    @PostConstruct
    public void peter() {
        System.out.println(translator.translate("Postconstruct Demo"));
    }
}
