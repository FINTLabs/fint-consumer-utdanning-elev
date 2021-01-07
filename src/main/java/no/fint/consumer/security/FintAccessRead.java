package no.fint.consumer.security;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class FintAccessRead implements FintAccessAuthority {
    private final String name;

    @Override
    public String getAuthority() {
        return "READ:" + name;
    }

    @Override
    public boolean isAccessGranted(String method, String path) {
        return StringUtils.equals(method, "GET")
                &&
                StringUtils.startsWithIgnoreCase(path, name + "/");
    }
}
