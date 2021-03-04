package example

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import software.amazon.awssdk.services.lambda.LambdaAsyncClient
import software.amazon.awssdk.services.lambda.model.GetAccountSettingsRequest
import software.amazon.awssdk.services.lambda.model.GetAccountSettingsResponse
import java.util.concurrent.CompletableFuture

class HandlerKt : RequestHandler<TestEvent, String> {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = getLogger(javaClass.enclosingClass)
        private val gson = GsonBuilder().setPrettyPrinting().create()
    }

    override fun handleRequest(event: TestEvent, context: Context): String {
       // log execution details
        logger.info("ENVIRONMENT VARIABLES: {}", gson.toJson(System.getenv()))
        logger.info("CONTEXT: {}", gson.toJson(context))
        logger.info("EVENT: {}", gson.toJson(event))

        return event.message
    }
}