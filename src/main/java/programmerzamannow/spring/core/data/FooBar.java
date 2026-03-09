package programmerzamannow.spring.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor
// @Data ini untuk membuat fungsi secara otomatis setter getter dari foo dan si bar
// tanpa harus membuat secara manual
@Data
public class FooBar {
    private Foo foo;
    private Bar bar;

}
