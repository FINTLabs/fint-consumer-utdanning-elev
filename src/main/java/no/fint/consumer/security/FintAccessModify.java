package no.fint.consumer.security;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class FintAccessModify implements FintAccessAuthority {
    private final String name;

    @Override
    public String getAuthority() {
        return "MODIFY:" + name;
    }

    @Override
    public boolean isAccessGranted(String method, String path) {
        return StringUtils.equalsAny(method, "POST", "PUT", "DELETE")
                &&
                StringUtils.startsWithIgnoreCase(path, name);
    }
}
