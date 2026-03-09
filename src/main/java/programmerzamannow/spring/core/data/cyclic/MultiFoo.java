package programmerzamannow.spring.core.data.cyclic;

import lombok.Getter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.data.Foo;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MultiFoo {

    @Getter
    private List<Foo> foos;

    public MultiFoo(ObjectProvider<Foo> objectProvider)
    {
        foos = objectProvider.stream().collect(Collectors.toList());
    }
}
