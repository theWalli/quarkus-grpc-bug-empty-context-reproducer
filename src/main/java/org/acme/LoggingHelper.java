package org.acme;

import io.grpc.Context;
import io.grpc.Deadline;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingHelper {

    public static void logDeadline(String name) {
        Deadline deadline = Context.current().getDeadline();
        if (deadline != null) {
            log.info(name + " - Deadline: {} - seconds remaining: {}", deadline,
                    deadline.timeRemaining(TimeUnit.SECONDS));
        } else {
            log.info(name + " - Received no deadline");
        }
    }
}
