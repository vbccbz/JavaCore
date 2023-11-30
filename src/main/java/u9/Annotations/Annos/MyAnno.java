package u9.Annotations.Annos;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno {
    int strangeValue();
}
