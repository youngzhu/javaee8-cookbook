package com.youngzy.book.javaee8cookbook.ch01.security;

import javax.annotation.security.RunAs;
import javax.ejb.Stateless;

/**
 * @author youngzy
 * @since 2024-01-03
 */
public class RoleExecutor {
    public interface Executable {
        void execute() throws Exception;
    }

    @Stateless
    @RunAs(Role.ADMIN)
    public static class AdminExecutor  {
        public void run(Executable executable) throws Exception {
            executable.execute();
        }
    }

    @Stateless
    @RunAs(Role.OPERATOR)
    public static class OperatorExecutor {
        public void run(Executable executable) throws Exception {
            executable.execute();
        }
    }
}
