package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

/**
 * lombok 이 제공하는 @Getter , @Setter 어노테이션 사용
 */
@Getter @Setter
public class HelloData {

    private String username;
    private int age;
}
