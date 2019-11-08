package com.mengqid.core.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
 
        public SecurityInitializer() {
                super(MySecurityConfig.class, SessionConfig.class);
        }
}
