package uz.pdp.sotx.servuce;

import org.springframework.stereotype.Service;
import uz.pdp.sotx.model.AuthUser;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private final Map<String, AuthUser> AUTH_USERS = new ConcurrentHashMap<>();

    public AuthUser getAuthUser(String id) {
        return AUTH_USERS.get(id);
    }

    public void putAuthUser(String id, AuthUser authUser) {
        AUTH_USERS.put(id, authUser);
    }

    public Map<String, AuthUser> getAuthUsers() {
        return AUTH_USERS;
    }

    public void remove(Long id) {
        AUTH_USERS.remove(id.toString());
    }

}
