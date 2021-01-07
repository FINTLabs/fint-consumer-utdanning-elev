package no.fint.consumer.security;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class FintAccessCollection implements FintAccessAuthority {
    private final String name;

    @Override
    public String getAuthority() {
        return "COLLECTION:" + name;
    }

    @Override
    public boolean isAccessGranted(String method, String path) {
        return StringUtils.equals(method, "GET")
                &&
                StringUtils.equalsIgnoreCase(path, name);
    }
}
