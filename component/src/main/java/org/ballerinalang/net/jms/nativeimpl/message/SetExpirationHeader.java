/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.ballerinalang.net.jms.nativeimpl.message;

import org.ballerinalang.bre.Context;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BStruct;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.Attribute;
import org.ballerinalang.natives.annotations.BallerinaAnnotation;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.Receiver;
import org.ballerinalang.net.jms.JMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Native function to set Expiration header to jms message.
 */

@BallerinaFunction(
        packageName = "ballerina.net.jms",
        functionName = "setExpiration",
        receiver = @Receiver(type = TypeKind.STRUCT, structType = "JMSMessage",
                             structPackage = "ballerina.net.jms"),
        args = {@Argument(name = "value", type = TypeKind.INT)},
        isPublic = true
)
@BallerinaAnnotation(annotationName = "Description", attributes = {@Attribute(name = "value",
        value = "Sets Expiration value to transport header") })
@BallerinaAnnotation(annotationName = "Param", attributes = {@Attribute(name = "jmsmessage",
        value = "The current message") })
@BallerinaAnnotation(annotationName = "Param", attributes = {@Attribute(name = "value",
        value = "The header value") })
public class SetExpirationHeader extends AbstractNativeFunction {

    private static final Logger log = LoggerFactory.getLogger(SetExpirationHeader.class);

    @Override
    public BValue[] execute(Context context) {

        BStruct messageStruct  = ((BStruct) this.getRefArgument(context, 0));
        long value = this.getIntArgument(context, 0);

        Message jmsMessage = JMSUtils.getJMSMessage(messageStruct);

        try {
            jmsMessage.setJMSExpiration(value);
        } catch (JMSException e) {
            log.error("Unable to set Expiration header to the JMS Message. " + e.getLocalizedMessage());
        }

        if (log.isDebugEnabled()) {
            log.debug("Add Expiration to JMS message");
        }

        return AbstractNativeFunction.VOID_RETURN;
    }
}
