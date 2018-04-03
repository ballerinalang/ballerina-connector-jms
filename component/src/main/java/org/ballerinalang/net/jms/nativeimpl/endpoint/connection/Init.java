package org.ballerinalang.net.jms.nativeimpl.endpoint.connection;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.CallableUnitCallback;
import org.ballerinalang.model.NativeCallableUnit;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Connection init function for JMS connection endpoint.
 *
 * @since 0.970
 */

@BallerinaFunction(
        orgName = "ballerina", packageName = "jms",
        functionName = "initEndpoint",
        receiver = @Receiver(type = TypeKind.STRUCT, structType = "Connection", structPackage = "ballerina.jms"),
        args = {@Argument(name = "config", type = TypeKind.STRUCT, structType = "ConnectionConfiguration")
        },
        isPublic = true
)
public class Init implements NativeCallableUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(Init.class);

    @Override
    public void execute(Context context, CallableUnitCallback callableUnitCallback) {
        LOGGER.info("init");
    }

    @Override
    public boolean isBlocking() {
        return true;
    }
}