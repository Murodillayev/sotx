package uz.pdp.sotx.servuce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MVRefreshService {

    public void refreshUserStatMV() {

        log.info("Refresh materialized view -> mv_user_stat... ");

    }
}
